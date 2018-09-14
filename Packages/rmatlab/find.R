find <- function(X, n, direction = "first") {
	if (!missing(n)) {
		stopifnot(length(n) == 1)
		stopifnot(direction %in% c("first", "last"))
	}
	k <- which(X != 0)
	if (missing(n)) {
		return(k)
	}
	return(ifelse(direction == "first", head(k, n), tail(k, n)))
}
