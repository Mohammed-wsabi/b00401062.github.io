library(magrittr)

isletter <- (function(A) {
	return(A %>% strsplit(split = "") %>% unlist %>% grepl(pattern = "[[:alpha:]]"))
}) %>% Vectorize
