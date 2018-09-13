isvarname <- function(s) {
	stopifnot(class(s) == "character")
	return(
		nchar(s) < 64 &
		grepl("^[A-z]", s) &
		!grepl("[^[:alnum:]_]", s) &
		!iskeyword(s)
	)
}
