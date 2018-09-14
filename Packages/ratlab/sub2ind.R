sub2ind <- function(siz, sub) {
	for (i in 1:length(siz)) {
		stopifnot(all(siz[i] >= sub[[i]]))
	}
	ind <- sub[[1]]
	for (i in 2:length(siz)) {
		ind <- ind + (sub[[i]] - 1) * prod(siz[1:(i-1)])
	}
	return(ind)
}
