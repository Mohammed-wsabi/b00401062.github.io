meshgrid <- function(x, y = x) {
	stopifnot(any(size(x) == 1))
	stopifnot(any(size(y) == 1))
	return(list(
		X = matrix(rep(x, each = length(y)), length(y), length(x)),
		Y = matrix(rep(y, times = length(x)), length(y), length(x))
	))
}
