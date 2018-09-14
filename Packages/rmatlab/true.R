true <- function(sz, ...) {
	if (missing(sz)) {
		return(TRUE)
	}
	if (length(sz) == 1L) {
		if (...length() == 0L) {
			return(array(TRUE, c(sz, sz)))
		} else {
			stopifnot(all(sapply(list(...), length) == 1L))
			return(array(TRUE, c(sz, ...)))
		}
	} else {
		stopifnot(...length() == 0L)
		return(array(TRUE, sz))
	}
}
