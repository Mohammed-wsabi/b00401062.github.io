library(R6)
library(reticulate)

Denoising <- R6Class(
	classname = "Denoising",
	public = list(
		stats = NULL,
		initialize = function(filepath) {
			self$stats = read.table(filepath, header = TRUE, sep = "\t", row.names = 1)
			use_python(system("which python3"))
		},
		plot = function() {
			pyplot <- import("matplotlib.pyplot")
			for (i in 1:dim(self$stats)[1]) {
				pyplot$plot(unlist(self$stats[i,]))
			}
			pyplot$yscale("log")
			pyplot$ylabel("log(#Sequences)")
			pyplot$savefig("./Downloads/Researches/CGM/Figures/denoising.png")
			pyplot$show()
		}
	)
)
