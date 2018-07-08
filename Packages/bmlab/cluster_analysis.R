library(oro.nifti)
library(parallel)
library(reshape2)

NROI <- 20L

cluster_analysis <- function(sbj, task) {
	cat(paste0("== Session ", sbj, " > ", task, " ==\n"))
	## Read subject data and DMN masks
	load(paste0("./Datasets/Subjects/", sbj, "_Subject.RData"))
	dmn_img <- readNIfTI(paste0("./Datasets/Templates/m", sbj, "_Template.nii"), reorient = FALSE)
	dmn_map <- dmn_img@.Data[SUBJECTRAWMASK]
	dmn_map[is.na(dmn_map)] <- -.2
	## Generate cluster matrices with K-means clustering
	if (!is.null(SUBJECTRAW[[task]])) {
		kmeans_ret <- kmeans(SUBJECTRAW[[task]], NROI, iter.max = 100L)
	} else {
		kmeans_ret <- kmeans(cbind(SUBJECTRAW$MFCAREST1, SUBJECTRAW$MFCAREST2), NROI, iter.max = 100L)
	} #=> if
	cluster_imgs <- matrix(-1, nrow = sum(SUBJECTRAWMASK), ncol = NROI)
	for (i in 1:sum(SUBJECTRAWMASK)) {
		cluster_imgs[i, kmeans_ret$cluster[i]] <- 1
	} #=> for
	## Determine the set of clusters to be DMN
	sets <- 1:NROI
	scores <- sapply(sets, function(set) cluster_imgs[, set] %*% dmn_map)
	max_set <- sets[which.max(scores)]
	max_score <- max(scores)
	while (TRUE) {
		tmp_sets <- unique(do.call(rbind, lapply(as.list(as.data.frame(t(sets))),
			function(set) t(sapply(setdiff(1:NROI, set),
			function(i) sort(c(set, i)))))))
		scores <- apply(tmp_sets, 1, function(set) c(-1, 1)[1 + rowSums(cluster_imgs[, set] == 1)] %*% dmn_map)
		if (max(scores) < max_score) {
			break
		} #=> if
		sets <- tmp_sets[rank(-scores, ties.method = "min") <= NROI, ]
		max_set <- tmp_sets[which.max(scores), ]
		max_score <- max(scores)
		print(max_set)
		print(max_score)
	} #=> while
	## Write DMN indices to file
	write(
		x = rowSums(cluster_imgs[, max_set, drop = FALSE] == 1), #=> which(SUBJECTRAWMASK)[x]
		file = paste0("./Datasets/Cluster_Analysis/", sbj, "_", task, ".csv"),
		sep = "\n")
	## Display spatial clusters
	load(paste0("./Datasets/Templates/", sbj, "_Template.RData"))
	component_img <- TEMPLATE
	dim_(component_img)[5] <- 1
	component_img@.Data <- array(0, dim_(component_img)[2:5])
	component_img@.Data[SUBJECTRAWMASK] <- rowSums(cluster_imgs[, max_set, drop = FALSE] == 1)
	writeNIfTI(
		nim = component_img,
		filename = paste0("./Datasets/Cluster_Analysis/", sbj, "_", task),
		gzipped = FALSE)
} #=> cluster_analysis

mcmapply(
	FUN = cluster_analysis,
	rep(sprintf("TADZ0%02d", 1:46), each = 4),
	rep(c("MFCAREST", "MFCAEPSO", "MFCASELF", "MFCAVERB"), times = 46),
	mc.cores = getOption("mc.cores", 16L))
mcmapply(
	FUN = cluster_analysis,
	rep(sprintf("TADZ0%02d", 47:60), each = 6),
	rep(c("MFCAREST", "MFCAREST1", "MFCAREST2", "MFCAEPSO", "MFCASELF", "MFCAVERB"), times = 14),
	mc.cores = getOption("mc.cores", 16L))
