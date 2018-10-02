repelem <- function(v, n) {
	if (length(n) == 1) {
		return(rep(v, each = n))
	} else {
		stopifnot(length(v) == length(n))
		return(rep(v, times = n))
	}
}
