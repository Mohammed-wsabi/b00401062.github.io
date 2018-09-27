library(R6)
library(reticulate)

Denoising <- R6Class(
	classname = "Denoising",
	private = list(
		stats = NULL
	),
	public = list(
		initialize = function(filepath) {
			private$stats = read.table(filepath, header = TRUE, sep = "\t", row.names = 1)
		},
		plot = function() {
			use_python(system("which python3", intern = TRUE))
			pyplot <- import("matplotlib.pyplot")
			for (i in 1:dim(private$stats)[1]) {
				pyplot$plot(unlist(private$stats[i, ]))
			}
			pyplot$yscale("log")
			pyplot$ylabel("log(#Sequences)")
			pyplot$savefig("./Downloads/Researches/CGM/Figures/denoising.png")
			pyplot$show()
		}
	)
)
