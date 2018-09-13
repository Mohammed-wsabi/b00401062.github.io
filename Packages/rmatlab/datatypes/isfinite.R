isfinite <- function(A) {
	stopifnot(isnumeric(A))
	return(is.finite(A))
}
