assert <- function(cond, msg = "", ...) {
	if (!cond) {
		stop(sprintf(msg, ...))
	}
}
