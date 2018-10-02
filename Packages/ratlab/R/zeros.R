zeros <- function(sz, ..., typename = "double") {
	stopifnot(typename %in% c("integer", "double"))
	zero <- ifelse(typename == "integer", 0L, 0)
	if (missing(sz)) {
		return(zero)
	}
	if (length(sz) == 1) {
		if (...length() == 0) {
			return(array(zero, c(sz, sz)))
		} else {
			stopifnot(all(sapply(list(...), length) == 1))
			return(array(zero, c(sz, ...)))
		}
	} else {
		stopifnot(...length() == 0)
		return(array(zero, sz))
	}
}
