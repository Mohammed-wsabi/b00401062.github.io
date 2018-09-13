isinf <- function(A) {
	stopifnot(isnumeric(A))
	return(is.infinite(A))
}
