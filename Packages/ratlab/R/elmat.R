elmat <- list(
	eye = function(n, m = n, typename = "double") {
		stopifnot(typename %in% c("integer", "double"))
		one <- ifelse(typename == "integer", 1L, 1)
		if (missing(n)) {
			return(one)
		}
		stopifnot(length(n) <= 2)
		if (length(n) == 1) {
			return(diag(one, n, m))
		} else if (length(n) == 2) {
			stopifnot(missing(m))
			return(diag(one, n[1], n[2]))
		}
	},
	false = function(sz, ...) {
		if (missing(sz)) {
			return(FALSE)
		}
		if (length(sz) == 1) {
			if (...length() == 0) {
				return(array(FALSE, c(sz, sz)))
			} else {
				stopifnot(all(sapply(list(...), length) == 1))
				return(array(FALSE, c(sz, ...)))
			}
		} else {
			stopifnot(...length() == 0)
			return(array(FALSE, sz))
		}
	},
	find = function(X, n, direction = "first") {
		if (!missing(n)) {
			stopifnot(length(n) == 1)
			stopifnot(direction %in% c("first", "last"))
		}
		k <- which(X != 0)
		if (missing(n)) {
			return(k)
		}
		return(ifelse(direction == "first", head(k, n), tail(k, n)))
	},
	ind2sub = function(siz, ind) {
		stopifnot(prod(siz) >= ind)
		sub <- rep(list(integer()), length(siz))
		sub[[1]] <- (ind-1) %% siz[1] + 1
		for (i in 2:length(siz)) {
			sub[[i]] = ((ind-1) %/% prod(siz[1:(i-1)])) %% siz[i] + 1
		}
		return(sub)
	},
	linspace = function(x1, x2, n = 100) {
		stopifnot(length(n) == 1 && n >= 0)
		if (n == 0) {
			return(numeric())
		} else if (n == 1) {
			return(x2)
		} else {
			return(seq(x1, x2, length.out = n))
		}
	},
	logspace = function(a, b, n = 50) {
		stopifnot(length(n) == 1 && n >= 0)
		if (n == 0) {
			return(numeric())
		} else if (n == 1) {
			return(10 ^ b)
		} else {
			return(seq(10 ^ a, 10 ^ b, length.out = n))
		}
	},
	meshgrid = function(x, y = x) {
		stopifnot(ndims(x) == 2 && any(size(x) == 1))
		stopifnot(ndims(y) == 2 && any(size(y) == 1))
		return(list(
			X = matrix(rep(x, each = length(y)), length(y), length(x)),
			Y = matrix(rep(y, times = length(x)), length(y), length(x))
		))
	},
	ndims = function(A) {
		ndims <- length(dim(A))
		return(ifelse(ndims <= 2, 2, ndims))
	},
	numel = length,
	ones = function(sz, ..., typename = "double") {
		stopifnot(typename %in% c("integer", "double"))
		one <- ifelse(typename == "integer", 1L, 1)
		if (missing(sz)) {
			return(one)
		}
		if (length(sz) == 1) {
			if (...length() == 0) {
				return(array(one, c(sz, sz)))
			} else {
				stopifnot(all(sapply(list(...), length) == 1))
				return(array(one, c(sz, ...)))
			}
		} else {
			stopifnot(...length() == 0)
			return(array(one, sz))
		}
	},
	repelem = function(v, n) {
		if (length(n) == 1) {
			return(rep(v, each = n))
		} else {
			stopifnot(length(v) == length(n))
			return(rep(v, times = n))
		}
	},
	size = function(A, dim) {
		if (is.null(base::dim(A))) {
			if (length(A) == 0) {
				return(c(0, 0))
			} else {
				return(c(length(A), 1))
			}
		}
		if (missing(dim)) {
			return(base::dim(A))
		} else {
			return(base::dim(A)[dim])
		}
	},
	sub2ind = function(siz, sub) {
		for (i in 1:length(siz)) {
			stopifnot(all(siz[i] >= sub[[i]]))
		}
		ind <- sub[[1]]
		for (i in 2:length(siz)) {
			ind <- ind + (sub[[i]] - 1) * prod(siz[1:(i-1)])
		}
		return(ind)
	},
	true = function(sz, ...) {
		if (missing(sz)) {
			return(TRUE)
		}
		if (length(sz) == 1) {
			if (...length() == 0) {
				return(array(TRUE, c(sz, sz)))
			} else {
				stopifnot(all(sapply(list(...), length) == 1))
				return(array(TRUE, c(sz, ...)))
			}
		} else {
			stopifnot(...length() == 0)
			return(array(TRUE, sz))
		}
	},
	zeros = function(sz, ..., typename = "double") {
		stopifnot(typename %in% c("integer", "double"))
		zero <- ifelse(typename == "integer", 0L, 0)
		if (missing(sz)) {
			return(zero)
		}
		if (length(sz) == 1) {
			if (...length() == 0) {
				return(array(zero, c(sz, sz)))
			} else {
				stopifnot(all(sapply(list(...), length) == 1))
				return(array(zero, c(sz, ...)))
			}
		} else {
			stopifnot(...length() == 0)
			return(array(zero, sz))
		}
	}
)