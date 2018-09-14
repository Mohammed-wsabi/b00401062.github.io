ndims <- function(A) {
	ndims <- length(dim(A))
	return(ifelse(ndims <= 2, 2, ndims))
}
