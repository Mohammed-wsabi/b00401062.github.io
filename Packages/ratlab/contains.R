library(magrittr)

contains <- function(str, pattern, IgnoreCase = TRUE) {
	return(str %>% grepl(pattern = pattern %>% paste(collapse = "|"), ignore.case = IgnoreCase))
}
