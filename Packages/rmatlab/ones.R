ones <- function(sz, ..., typename = "double") {
	stopifnot(class(typename) == "character")
	stopifnot(typename %in% c("integer", "double"))
	one <- ifelse(typename == "integer", 1L, 1)
	if (missing(sz)) {
		return(one)
	}
	stopifnot(typeof(sz) == "integer")
	stopifnot(all(sapply(list(...), typeof) == "integer"))
	if (length(sz) == 1) {
		if (...length() == 0) {
			return(array(one, c(sz, sz)))
		} else {
			stopifnot(all(sapply(list(...), length) == 1))
			return(array(one, c(sz, ...)))
		}
	} else {
		stopifnot(...length() == 0)
		return(array(one, sz))
	}
}
