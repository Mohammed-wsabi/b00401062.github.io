library(oro.nifti)
library(psych)

SUBJECTS <- (1:60)[-c(8, 15, 21, 48)]
MAX_SET <- c(4, 10, 16)

## Strength table
STRENGTH_TABLE <- data.frame(
	MFCAREST = numeric(length(SUBJECTS)),
	MFCAREST1 = numeric(length(SUBJECTS)),
	MFCAREST2 = numeric(length(SUBJECTS)),
	MFCAEPSO = numeric(length(SUBJECTS)),
	MFCASELF = numeric(length(SUBJECTS)),
	MFCAVERB = numeric(length(SUBJECTS))
) #=> STRENGTH_TABLE

for (i in 1:length(SUBJECTS)) {
	mask <- readNIfTI(sprintf("./Datasets/DMN_Zmaps/mTADZ0%02d.nii", SUBJECTS[i]), reorient = FALSE)
	mask <- matrix(mask, ncol = length(MAX_SET)) > 2
	mask[is.na(mask)] <- FALSE
	mask <- rowSums(mask) > 0
	if (SUBJECTS[i] <= 46) {
		tasks <- c("Rest", "Epso", "Self", "Verb")
	} else {
		tasks <- c("Rest1", "Rest2", "Epso", "Self", "Verb")
	} #=> if
	for (task in tasks) {
		cat(sprintf("== Session TADZ0%02d > %s ==\n", SUBJECTS[i], task))
		fname <- sprintf("./Datasets/Residuals/TADZ0%02d/%s/Residual.nii", SUBJECTS[i], task)
		img <- matrix(readNIfTI(fname, reorient = FALSE), ncol = 211L)[mask, ]
		cor_mat <- cor(t(img))
		z_mat <- fisherz(cor_mat[lower.tri(cor_mat)])
		STRENGTH_TABLE[i, task] <- mean(z_mat[z_mat != Inf], na.rm = TRUE)
	} #=> for
} #=> for

STRENGTH_TABLE$MFCAREST[44:56] <- STRENGTH_TABLE$MFCAREST2[44:56]
write.csv(STRENGTH_TABLE, "./Datasets/Component_Stats/strength_table.csv", quote = FALSE)
STRENGTH_TABLE <- read.csv("./Datasets/Component_Stats/strength_table.csv", header = TRUE, row.names = 1)
