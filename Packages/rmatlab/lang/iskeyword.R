iskeyword <- function(txt) {
	keywords <- c(
		"if",
		"else",
		"repeat",
		"while",
		"function",
		"for",
		"in",
		"next",
		"break",
		"TRUE",
		"FALSE",
		"NULL",
		"Inf",
		"NaN",
		"NA",
		"NA_integer_",
		"NA_real_",
		"NA_complex_",
		"NA_character_"
	)
	if (missing(txt)) {
		return(keywords)
	} else {
		return(txt %in% keywords)
	}
}
