false <- function(sz, ...) {
	if (missing(sz)) {
		return(FALSE)
	}
	if (length(sz) == 1) {
		if (...length() == 0) {
			return(array(FALSE, c(sz, sz)))
		} else {
			stopifnot(all(sapply(list(...), length) == 1))
			return(array(FALSE, c(sz, ...)))
		}
	} else {
		stopifnot(...length() == 0)
		return(array(FALSE, sz))
	}
}
