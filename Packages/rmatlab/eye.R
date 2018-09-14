eye <- function(n, m, typename = "double") {
	stopifnot(typename %in% c("integer", "double"))
	one <- ifelse(typename == "integer", 1L, 1)
	if (missing(n)) {
		return(one)
	}
	stopifnot(length(n) <= 2)
	if (length(n) == 1) {
		if (missing(m)) {
			return(diag(one, n))
		} else {
			stopifnot(length(m) == 1)
			return(diag(one, n, m))
		}
	} else if (length(n) == 2) {
		stopifnot(missing(m))
		return(diag(one, n[1], n[2]))
	}
}
