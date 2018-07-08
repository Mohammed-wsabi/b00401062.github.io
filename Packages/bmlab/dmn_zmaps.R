library(oro.nifti)
library(parallel)

SUBJECTS <- (1:60)[-c(8, 15, 21, 48)]
MAX_SET <- c(4, 10, 16)

dmn_zmaps <- function(i) {
	cat(sprintf("== Session TADZ0%02d ==\n", SUBJECTS[i]))
	img <- readNIfTI(sprintf("./Datasets/Group_ICA/dmn_sub0%02d_component_ica_s1_.nii", i), reorient = FALSE)
	TEMPLATE <- readNIfTI("./Datasets/MNI_Collect/MNI_TEMPLATE.nii", reorient = FALSE)
	dim_(TEMPLATE)[5] <- length(MAX_SET)
	TEMPLATE@.Data <- array(NaN, dim_(TEMPLATE)[2:5])
	for (i in 1:3) {
		TEMPLATE@.Data[, , , i] = scale(img@.Data[, , , MAX_SET[i]])
	} #=> for
	writeNIfTI(nim = TEMPLATE, filename = sprintf("./Datasets/DMN_Zmaps/TADZ0%02d", SUBJECTS[i]), gzipped = FALSE)
} #=> dmn_zmaps

mclapply(1:56, dmn_zmaps, mc.cores = getOption("mc.cores", 16L))
