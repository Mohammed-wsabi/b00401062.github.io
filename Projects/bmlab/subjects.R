library(oro.nifti)
library(parallel)
library(tidyr)

## Define constants
NIFTINROW <- 64L
NIFTINCOL <- 64L
NIFTINSLI <- 38L
NIFTINTIM <- 211L

## Read subjects without ROIs
subject1 <- function(sbj) {
	SUBJECTRAW <- list(
		MFCAREST = paste0("./Databank/", sbj, "/RESULT/FCA_Rest_detrend_filtered_masked/mFiltered_4DVolume.nii"),
		MFCAEPSO = paste0("./Databank/", sbj, "/RESULT/FCA_Epso_detrend_filtered_masked/mFiltered_4DVolume.nii"),
		MFCASELF = paste0("./Databank/", sbj, "/RESULT/FCA_Self_detrend_filtered_masked/mFiltered_4DVolume.nii"),
		MFCAVERB = paste0("./Databank/", sbj, "/RESULT/FCA_Verb_detrend_filtered_masked/mFiltered_4DVolume.nii")) %>%
		lapply(function(x) matrix(readNIfTI(x, reorient = FALSE), ncol = NIFTINTIM))
	SUBJECTRAWMASK <- complete.cases(SUBJECTRAW)
	SUBJECTRAW <- SUBJECTRAW %>% lapply(function(x) x[SUBJECTRAWMASK, ])
	SUBJECTRAW <- SUBJECTRAW %>% lapply(function(x) t(apply(x, 1, scale)))
	SUBJECTRAWMASK[SUBJECTRAWMASK] <- TMPMASK <- complete.cases(SUBJECTRAW)
	SUBJECTRAW <- SUBJECTRAW %>% lapply(function(x) x[TMPMASK, ])
	save(SUBJECTRAW, SUBJECTRAWMASK, file = paste0("./Datasets/Subjects/", sbj, "_Subject.RData"))
} #=> for
subject2 <- function(sbj) {
	SUBJECTRAW <- list(
		MFCAREST1 = paste0("./Databank/", sbj, "/RESULT/FCA_Rest1_detrend_filtered_masked/mFiltered_4DVolume.nii"),
		MFCAREST2 = paste0("./Databank/", sbj, "/RESULT/FCA_Rest2_detrend_filtered_masked/mFiltered_4DVolume.nii"),
		MFCAEPSO = paste0("./Databank/", sbj, "/RESULT/FCA_Epso_detrend_filtered_masked/mFiltered_4DVolume.nii"),
		MFCASELF = paste0("./Databank/", sbj, "/RESULT/FCA_Self_detrend_filtered_masked/mFiltered_4DVolume.nii"),
		MFCAVERB = paste0("./Databank/", sbj, "/RESULT/FCA_Verb_detrend_filtered_masked/mFiltered_4DVolume.nii")) %>%
		lapply(function(x) matrix(readNIfTI(x, reorient = FALSE), ncol = NIFTINTIM))
	SUBJECTRAWMASK <- complete.cases(SUBJECTRAW)
	SUBJECTRAW <- SUBJECTRAW %>% lapply(function(x) x[SUBJECTRAWMASK, ])
	SUBJECTRAW <- SUBJECTRAW %>% lapply(function(x) t(apply(x, 1, scale)))
	SUBJECTRAWMASK[SUBJECTRAWMASK] <- TMPMASK <- complete.cases(SUBJECTRAW)
	SUBJECTRAW <- SUBJECTRAW %>% lapply(function(x) x[TMPMASK, ])
	save(SUBJECTRAW, SUBJECTRAWMASK, file = paste0("./Datasets/Subjects/", sbj, "_Subject.RData"))
} #=> for

mclapply(sprintf("TADZ0%02d", 1:46), subject1, mc.cores = getOption("mc.cores", 16L))
mclapply(sprintf("TADZ0%02d", 47:60), subject2, mc.cores = getOption("mc.cores", 16L))
