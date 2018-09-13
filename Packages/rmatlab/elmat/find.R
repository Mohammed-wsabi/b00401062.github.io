find <- function(X, n, direction = "first") {
	stopifnot(typeof(X) %in% c("logical", "integer", "double"))
	if (!missing(n)) {
		stopifnot(class(n) == "numeric" && length(n) == 1)
		stopifnot(direction %in% c("first", "last"))
	}
	k <- which(X != 0)
	if (missing(n)) {
		return(k)
	}
	return(ifelse(direction == "first", head(k, n), tail(k, n)))
}
