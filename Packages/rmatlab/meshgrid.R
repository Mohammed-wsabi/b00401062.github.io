meshgrid <- function(x, y = x) {
	stopifnot(ndims(x) == 2 && any(size(x) == 1))
	stopifnot(ndims(y) == 2 && any(size(y) == 1))
	return(list(
		X = matrix(rep(x, each = length(y)), length(y), length(x)),
		Y = matrix(rep(y, times = length(x)), length(y), length(x))
	))
}
