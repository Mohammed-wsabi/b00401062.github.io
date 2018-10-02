library(magrittr)

strfun <- list(
	contains = function(str, pattern, IgnoreCase = TRUE) {
		return(str %>% grepl(pattern = pattern %>% paste(collapse = "|"), ignore.case = IgnoreCase))
	},
	count = function(str, pattern, IgnoreCase = TRUE) {
		return(str %>% gregexpr(pattern = pattern %>% paste(collapse = "|"), ignore.case = IgnoreCase) %>% Map(f = function(x) sum(x > 0)) %>% unlist)
	},
	erase = function(str, match) {
		return(gsub(match %>% paste(collapse = "|"), "", str))
	},
	isletter = (function(A) {
		return(A %>% strsplit(split = "") %>% unlist %>% grepl(pattern = "[[:alpha:]]"))
	}) %>% Vectorize,
	isspace = (function(A) {
		return(A %>% strsplit(split = "") %>% unlist %>% grepl(pattern = "[[:space:]]"))
	}) %>% Vectorize,
	isstrprop = (function(str, category) {
		return(str %>% strsplit(split = "") %>% unlist %>% grepl(pattern = list(
			alpha = "[[:alpha:]]",
			alphanum = "[[:alnum:]]",
			cntrl = "[[:cntrl:]]",
			digit = "[[:digit:]]",
			graphic = "[[:graph:]]",
			lower = "[[:lower:]]",
			print = "[[:print:]]",
			punct = "[[:punct:]]",
			wspace = "[[:space:]]",
			upper = "[[:upper:]]",
			xdigit = "[[:xdigit:]]"
		)[[category]]))
	}) %>% Vectorize(vectorize.args = "str"),
	replace = function(str, old, new) gsub(old, new, str),
	replaceBetween = function(str, start, end, newText) {
		if (is.character(start) && is.character(end)) {
			regmatches(str, gregexpr(paste0(start, ".*", end), str)) <- paste0(start, newText, end)
		} else if (is.numeric(start) && is.numeric(end)) {
			str <- paste0(substr(str, 1, start-1), newText, substr(str, end+1, nchar(str)))
		}
		return(str)
	},
	strcat = paste0,
	strlength = nchar
)
