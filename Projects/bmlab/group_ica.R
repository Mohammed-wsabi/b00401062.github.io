library(oro.nifti)

NROI <- 20L

## Output z maps of components
comp_imgs <- readNIfTI("./Datasets/Group_ICA/dmn_mean_component_ica_s_all_.nii", reorient = FALSE)
comp_imgs <- matrix(comp_imgs, ncol = NROI)
TEMPLATE <- readNIfTI("./Datasets/MNI_Collect/MNI_TEMPLATE.nii", reorient = FALSE)
dim_(TEMPLATE)[5] <- NROI
TEMPLATE@.Data <- array(NaN, dim_(TEMPLATE)[2:5])
for (i in 1:NROI) {
	TEMPLATE@.Data[, , , i] <- comp_imgs[, i] <- scale(comp_imgs[, i])
} #=> for
writeNIfTI(nim = TEMPLATE, filename = "./Datasets/DMN_Zmaps/TADZALL", gzipped = FALSE)

## Load and preprocess image data for analysis
comp_imgs <- readNIfTI("./Datasets/DMN_Zmaps/TADZALL.nii", reorient = FALSE)
comp_imgs <- matrix(comp_imgs, ncol = NROI)
comp_imgs <- comp_imgs > 2
comp_imgs[comp_imgs == 0] <- -1
stopifnot(sum(comp_imgs == 1) == 279291)
dmn_img <- readNIfTI("./Datasets/MNI_Collect/mMNI_TEMPLATE.nii", reorient = FALSE)
dmn_map <- as.numeric(dmn_img)
dmn_map[is.na(dmn_map)] <- -.25
stopifnot(sum(dmn_map == 1) == 24435)

## Choose components
sets <- 1:NROI
scores <- sapply(sets, function(set) comp_imgs[, set] %*% dmn_map)
max_set <- sets[which.max(scores)]
max_score <- max(scores)
while (TRUE) {
	tmp_sets <- unique(do.call(rbind, lapply(as.list(as.data.frame(t(sets))),
		function(set) t(sapply(setdiff(1:NROI, set),
		function(i) sort(c(set, i)))))))
	scores <- apply(tmp_sets, 1, function(set) c(-1, 1)[1 + (rowSums(comp_imgs[, set] == 1) > 0)] %*% dmn_map)
	if (max(scores) < max_score) {
		break
	} #=> if
	sets <- tmp_sets[rank(-scores, ties.method = "min") <= NROI, ]
	max_set <- tmp_sets[which.max(scores), ]
	max_score <- max(scores)
	print(max_set)
	print(max_score)
} #=> while
