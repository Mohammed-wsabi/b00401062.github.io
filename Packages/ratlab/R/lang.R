lang <- list(
	ans = function() .Last.value,
	assert = function(cond, msg = "", ...) {
		if (!cond) {
			stop(sprintf(msg, ...))
		}
	},
	error = stop,
	iskeyword = function(txt) {
		keywords <- c("if", "else", "repeat", "while", "function", "for", "in", "next", "break", "TRUE", "FALSE", "NULL", "Inf", "NaN", "NA", "NA_integer_", "NA_real_", "NA_complex_", "NA_character_", "...", "..1", "..2")
		if (missing(txt)) {
			return(keywords)
		} else {
			return(txt %in% keywords)
		}
	},
	isvarname = function(s) {
		return(nchar(s) < 64 & grepl("^[A-z]", s) & !grepl("[^[:alnum:]_]", s) & !iskeyword(s))
	},
	lastwarn = function() names(last.warning)
)