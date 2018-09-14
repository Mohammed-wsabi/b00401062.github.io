ind2sub <- function(siz, ind) {
	stopifnot(prod(siz) >= ind)
	sub <- rep(list(integer()), length(siz))
	sub[[1]] <- (ind-1) %% siz[1] + 1
	for (i in 2:length(siz)) {
		sub[[i]] = ((ind-1) %/% prod(siz[1:(i-1)])) %% siz[i] + 1
	}
	return(sub)
}
