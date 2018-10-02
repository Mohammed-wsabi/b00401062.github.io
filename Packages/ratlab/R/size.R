size <- function(A, dim) {
	if (is.null(base::dim(A))) {
		if (length(A) == 0) {
			return(c(0, 0))
		} else {
			return(c(length(A), 1))
		}
	}
	if (missing(dim)) {
	 	return(base::dim(A))
	} else {
		return(base::dim(A)[dim])
	}
}
