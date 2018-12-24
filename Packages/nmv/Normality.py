#!/usr/bin/env python3

from numpy import *
from pandas import DataFrame
from scipy.stats import shapiro
from matplotlib.pyplot import *

class Normality:
	def fit(self, DF, GFA):
		self.p = DataFrame(ndarray((152, 71)), index = [repeat(Sex._fields, 76), tile(range(76), len(Sex._fields))])
		for sex in Sex._fields:
			x = DF.Age.loc[DF.Sex == sex.upper()]
			for i in range(76):
				y = GFA[i].mean(axis = 0)[DF.Sex == sex.upper()]
				for age in range(18, 89):
					mask = bitwise_and(age - 1 <= x, x <= age + 1)
					self.p.loc[(sex, i), age - 18] = shapiro(y[mask])[1] if sum(mask) >= 3 else 1
		return self
	def hist(self, sex):
		hist(-log10(array(self.p.loc[sex]).flatten()), bins = arange(0, 10, .2))
		xlim(0, 4)
		xlabel("-log(p)")
		ylabel("Frequency")
		savefig("./Downloads/Projects/NMV/Figures/Normality/Histogram ({})".format(sex.capitalize()))
		clf()
	def pcolor(self, sex):
		pcolor(-log10(self.p.loc[sex]), cmap = "Reds")
		xticks(arange(0, 70, 5) + 2, arange(20, 90, 5))
		xlabel("Age")
		ylabel("Tract")
		colorbar(aspect = 10).ax.set_title("-log(p)")
		clim(0, -log10(0.05))
		savefig("./Downloads/Projects/NMV/Figures/Normality/Heatmap ({})".format(sex.capitalize()))
		clf()
