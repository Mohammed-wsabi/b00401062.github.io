true <- function(sz, ...) {
	if (missing(sz)) {
		return(TRUE)
	}
	stopifnot(typeof(sz) == "integer")
	stopifnot(all(sapply(list(...), typeof) == "integer"))
	if (length(sz) == 1) {
		if (...length() == 0) {
			return(array(TRUE, c(sz, sz)))
		} else {
			stopifnot(all(sapply(list(...), length) == 1))
			return(array(TRUE, c(sz, ...)))
		}
	} else {
		stopifnot(...length() == 0)
		return(array(TRUE, sz))
	}
}
