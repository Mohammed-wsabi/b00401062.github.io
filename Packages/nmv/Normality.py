#!/usr/bin/env python3

from numpy import *
from scipy.stats import normaltest
from matplotlib.pyplot import *

class Normality:
	def fit(self, DF, GFA):
		self.p = ndarray((76, 71))
		for i in range(76):
			for age in range(18, 89):
				self.p[i, age - 18] = normaltest(GFA[i].mean(axis = 0)[bitwise_and(age - 2 <= DF.Age, DF.Age <= age + 2)]).pvalue
		return self
	def hist(self):
		hist(-log10(self.p.flatten()), bins = arange(0, 10, .2))
		xlabel("-log(p)")
		ylabel("Frequency")
		savefig("./Downloads/Projects/NMV/Figures/Normality/Histogram")
		show()
	def pcolor(self):
		pcolor(-log10(self.p), cmap = "Reds")
		xticks(arange(0, 70, 5) + 2, arange(20, 90, 5))
		xlabel("Age")
		ylabel("Tract")
		colorbar(aspect = 10).ax.set_title("-log(p)")
		clim(0, 2)
		savefig("./Downloads/Projects/NMV/Figures/Normality/Heatmap")
		show()
