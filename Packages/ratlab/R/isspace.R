library(magrittr)

isspace <- (function(A) {
	return(A %>% strsplit(split = "") %>% unlist %>% grepl(pattern = "[[:space:]]"))
}) %>% Vectorize
