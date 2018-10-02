library(magrittr)

isstrprop <- (function(str, category) {
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
}) %>% Vectorize(vectorize.args = "str")
