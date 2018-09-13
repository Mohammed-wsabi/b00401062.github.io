cast <- function(A, newclass) {
	stopifnot(class(A) %in% c("integer", "numeric"))
	stopifnot(newclass %in% c("uint8", "int8", "uint16", "int16", "uint32", "int32", "uint64", "int64", "single", "double"))
	return(get(newclass)(A))
}
