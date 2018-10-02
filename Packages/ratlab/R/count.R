library(magrittr)

count <- function(str, pattern, IgnoreCase = TRUE) {
	return(str %>% gregexpr(pattern = pattern %>% paste(collapse = "|"), ignore.case = IgnoreCase) %>% Map(f = function(x) sum(x > 0)) %>% unlist)
}