replaceBetween <- function(str, start, end, newText) {
	if (is.character(start) && is.character(end)) {
		regmatches(str, gregexpr(paste0(start, ".*", end), str)) <- paste0(start, newText, end)
	} else if (is.numeric(start) && is.numeric(end)) {
		str <- paste0(substr(str, 1, start-1), newText, substr(str, end+1, nchar(str)))
	}
	return(str)
}
