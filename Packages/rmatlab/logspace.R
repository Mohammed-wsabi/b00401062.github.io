logspace <- function(a, b, n = 100) {
	stopifnot(length(n) == 1 && n >= 0)
	if (n == 0) {
		return(numeric())
	} else if (n == 1) {
		return(10 ^ b)
	} else {
		return(seq(10 ^ a, 10 ^ b, length.out = n))
	}
}
