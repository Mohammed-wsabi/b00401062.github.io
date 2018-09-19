assert <- function(cond, msg = "", ...) {
	stopifnot(class(msg) == "character")
	if (!cond) {
		stop(sprintf(msg, ...))
	}
}
