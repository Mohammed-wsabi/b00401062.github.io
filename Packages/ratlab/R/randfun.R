randfun <- list(
	rand = function(sz, ...) {
		if (missing(sz)) {
			return(runif(1))
		}
		if (length(sz) == 1) {
			if (...length() == 0) {
				return(array(runif(sz * sz), c(sz, sz)))
			} else {
				stopifnot(all(sapply(list(...), length) == 1))
				return(array(runif(prod(c(sz, ...))), c(sz, ...)))
			}
		} else {
			stopifnot(...length() == 0)
			return(array(runif(prod(sz)), sz))
		}
	}
)