linspace <- function(x1, x2, n = 100) {
	stopifnot(length(n) == 1 && n >= 0)
	if (n == 0) {
		return(numeric())
	} else if (n == 1) {
		return(x2)
	} else {
		return(seq(x1, x2, length.out = n))
	}
}
