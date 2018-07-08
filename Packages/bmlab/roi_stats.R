library(oro.nifti)
library(psych)

ROIS <- c(
	"wAngular_L", "wAngular_R",
	"wCingulum_Post_L", "wCingulum_Post_R",
	"wFrontal_Med_Orb_L", "wFrontal_Med_Orb_R",
	"wFrontal_Sup_Medial_L", "wFrontal_Sup_Medial_R",
	"wHippocampus_L", "wHippocampus_R",
	"wParaHippocampal_L", "wParaHippocampal_R",
	"wPrecuneus_L", "wPrecuneus_R",
	"wSupraMarginal_L", "wSupraMarginal_R"
) #=> ROIS

## TASK_SUBJECT_ROIS
TASK_SUBJECT_ROIS <- vector("list", 6)
names(TASK_SUBJECT_ROIS) <- c("Rest", "Rest1", "Rest2", "Epso", "Self", "Verb")
for (task in names(TASK_SUBJECT_ROIS)) {
	TASK_SUBJECT_ROIS[[task]] <- vector("list", 60)
	names(TASK_SUBJECT_ROIS[[task]]) <- sprintf("TADZ0%02d", 1:60)
	for (i in 1:60) {
		TASK_SUBJECT_ROIS[[task]][[sprintf("TADZ0%02d", i)]] <- matrix(0, 16, 211)
		rownames(TASK_SUBJECT_ROIS[[task]][[sprintf("TADZ0%02d", i)]]) <- ROIS
	} #=> for
} #=> for

for (i in 1:60) {
	sbj <- sprintf("TADZ0%02d", i)
	tasks <- c(if (i <= 46) "Rest" else c("Rest1", "Rest2"), "Epso", "Self", "Verb")
	for (task in tasks) {
		fname <- sprintf("./Databank/TADZ0%02d/RESULT/FCA_%s_detrend_filtered/Filtered_4DVolume.nii", i, task)
		img <- matrix(readNIfTI(fname, reorient = FALSE), ncol = 211L)
		for (roi in ROIS) {
			cat(sprintf("== Session %s > %s > %s ==\n", sbj, task, roi))
			roifname <- sprintf("./Datasets/DMN_Masks/TADZ0%02d/m%s.nii", i, roi)
			roiimg <- readNIfTI(roifname, reorient = FALSE)
			roimask <- which(roiimg@.Data == 1)
			TASK_SUBJECT_ROIS[[task]][[sbj]][roi, ] <- colMeans(img[roimask, ])
		} #=> for
		write.csv(TASK_SUBJECT_ROIS[[task]][[sbj]], sprintf("./Datasets/ROI_Stats/%s_%s.csv", sbj, task), quote = FALSE)
	} #=> for
} #=> for

for (i in 1:60) {
	sbj <- sprintf("TADZ0%02d", i)
	tasks <- c(if (i <= 46) "Rest" else c("Rest1", "Rest2"), "Epso", "Self", "Verb")
	for (task in tasks) {
		TASK_SUBJECT_ROIS[[task]][[sbj]] <-
		read.csv(sprintf("./Datasets/ROI_Stats/%s_%s.csv", sbj, task), row.names = 1)
	} #=> for
} #=> for
for (i in 47:60) {
	sbj <- sprintf("TADZ0%02d", i)
	TASK_SUBJECT_ROIS[["Rest"]][[sbj]] <- TASK_SUBJECT_ROIS[["Rest2"]][[sbj]]
} #=> for

## STRENGTH_TABLE
STRENGTH_TABLE <- vector("list", 6)
names(STRENGTH_TABLE) <- c("Rest", "Rest1", "Rest2", "Epso", "Self", "Verb")
for (task in c("Rest", "Epso", "Self", "Verb")) {
	STRENGTH_TABLE[[task]] <- vector("list", 60)
	names(STRENGTH_TABLE[[task]]) <- sprintf("TADZ0%02d", 1:60)
	for (i in 1:60) {
		sbj <- sprintf("TADZ0%02d", i)
		STRENGTH_TABLE[[task]][[sbj]] <- fisherz(cor(t(TASK_SUBJECT_ROIS[[task]][[sbj]])))
	} #=> for
} #=> for
for (task in c("Rest1", "Rest2")) {
	STRENGTH_TABLE[[task]] <- vector("list", 14)
	names(STRENGTH_TABLE[[task]]) <- sprintf("TADZ0%02d", 47:60)
	for (i in 47:60) {
		sbj <- sprintf("TADZ0%02d", i)
		STRENGTH_TABLE[[task]][[sbj]] <- fisherz(cor(t(TASK_SUBJECT_ROIS[[task]][[sbj]])))
	} #=> for
} #=> for

## T_TABLE and SIG_TABLE
T_TABLE <- vector("list", 16)
names(T_TABLE) <- ROIS
SIG_TABLE <- vector("list", 4)
names(SIG_TABLE) <- c("Rest", "Epso", "Self", "Verb")
for (i in 1:length(SIG_TABLE)) {
	SIG_TABLE[[i]] <- matrix(0, 16, 16)
	colnames(SIG_TABLE[[i]]) <- rownames(SIG_TABLE[[i]]) <- ROIS
} #=> for
for (roi1 in 1:16) {
	T_TABLE[[roi1]] <- vector("list", 16)
	names(T_TABLE[[roi1]]) <- ROIS
	for (roi2 in 1:16) {
		if (roi1 == roi2)
			next
		T_TABLE[[roi1]][[roi2]] <- matrix(0, 4, 4)
		rownames(T_TABLE[[roi1]][[roi2]]) <- colnames(T_TABLE[[roi1]][[roi2]]) <- c("Rest", "Epso", "Self", "Verb")
		table <- matrix(0, 60, 4)
		colnames(table) <- c("Rest", "Epso", "Self", "Verb")
		for (task in colnames(table)) {
			for (i in 1:60) {
				table[i, task] <- STRENGTH_TABLE[[task]][[sprintf("TADZ0%02d", i)]][roi1, roi2]
			} #=> for
		} #=> for
		for (i in 1:4) {
			for (j in 1:4) {
				T_TABLE[[roi1]][[roi2]][i, j] <- t.test(table[, i], table[, j], paired = TRUE)$p.value
			} #=> for
		} #=> for
		for (i in 2:4) {
			if (T_TABLE[[roi1]][[roi2]][i] < 0.05 / 120 / 4) {
				SIG_TABLE[[i]][roi1, roi2] <- ifelse(sum(table[, i] - table[, 1]) > 0, 1, -1)
			} #=> if
		} #=> for
	} #=> for
} #=> for
for (roi1 in 1:16) {
	for (roi2 in 1:16) {
		if (roi1 == roi2)
			next
		table <- matrix(0, 14, 2)
		colnames(table) <- c("Rest1", "Rest2")
		for (task in colnames(table)) {
			for (i in 47:60) {
				table[i - 46, task] <- STRENGTH_TABLE[[task]][[sprintf("TADZ0%02d", i)]][roi1, roi2]
			} #=> for
		} #=> for
		SIG_TABLE[[1]][roi1, roi2] <- t.test(table[, 1], table[, 2], paired = TRUE)$p.value < 0.05 / 120 / 4
	} #=> for
} #=> for

## Display tables
library(ggplot2)
require(reshape2)

for (task in names(SIG_TABLE)) {
	plt <- ggplot(data = melt(SIG_TABLE[[task]]), aes(Var1, Var2)) +
	 	geom_tile(aes(fill = factor(value, levels = c("-1", "0", "1"))), color = "black") +
		scale_fill_manual(values = c("red", "white", "green"), drop = FALSE) +
		theme(axis.text.x = element_text(angle = 90, hjust = 1)) +
		xlab("") + ylab("")
	plt$labels$fill <- "Significant"
	plt
} #=> for
