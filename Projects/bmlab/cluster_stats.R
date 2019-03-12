library(psych)

## Strength table
STRENGTH_TABLE <- data.frame(
	MFCAREST = numeric(60),
	MFCAREST1 = numeric(60),
	MFCAREST2 = numeric(60),
	MFCAEPSO = numeric(60),
	MFCASELF = numeric(60),
	MFCAVERB = numeric(60)
) #=> STRENGTH_TABLE

for (i in 1:60) {
	sbj <- sprintf("TADZ0%02d", i)
	load(paste0("./Datasets/Subjects/", sbj, "_Subject.RData"))
	if (i <= 46) {
		fname <- paste0("./Datasets/Cluster_Analysis/", sbj, "_MFCAREST.csv")
	} else {
		fname <- paste0("./Datasets/Cluster_Analysis/", sbj, "_MFCAREST2.csv")
	} #=> if
	max_set <- as.integer(readLines(fname)) == 1
	print(sum(max_set))
	if (i <= 46) {
		tasks <- c("MFCAREST", "MFCAEPSO", "MFCASELF", "MFCAVERB")
	} else {
		tasks <- c("MFCAREST1", "MFCAREST2", "MFCAEPSO", "MFCASELF", "MFCAVERB")
	} #=> if
	for (task in tasks) {
		cor_mat <- cor(t((SUBJECTRAW[[task]])[max_set, ]))
		z_mat <- fisherz(cor_mat[lower.tri(cor_mat)])
		STRENGTH_TABLE[i, task] <- mean(z_mat[z_mat != Inf], na.rm = TRUE)
		print(STRENGTH_TABLE[i, task])
	} #=> for
} #=> for

STRENGTH_TABLE$MFCAREST[47:60] <- STRENGTH_TABLE$MFCAREST2[47:60]
write.csv(STRENGTH_TABLE, "./Datasets/Cluster_Stats/strength_table.csv", quote = FALSE)
STRENGTH_TABLE <- read.csv("./Datasets/Cluster_Stats/strength_table.csv", header = TRUE, row.names = 1)
