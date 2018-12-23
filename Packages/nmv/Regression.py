#!/usr/bin/env python3

from numpy import *
from scipy.optimize import curve_fit
from scipy.stats import t
from matplotlib.pyplot import *

class Regression:
	def __init__(self, sex, model, tract):
		self.sex = sex
		self.model = model
		self.peak = argmax(model.m) + 18
		self.tract = tract
	def fit(self, x, y):
		self.x = x
		self.y = y
		(self.b, _) = curve_fit(self.piecewise, x.astype("float").tolist(), y)
		return self
	def piecewise(self, x, b0, b1, b2):
		return piecewise(x, [x < self.peak], [lambda x: b1 * (x - self.peak) + b0, lambda x: b2 * (x - self.peak) + b0])
	@staticmethod
	def peak(model):
		return argmax(model.m) + 18
	def p(self):
		n = len(self.x)
		x1 = self.x[self.x < self.peak]
		y1 = self.y[self.x < self.peak]
		se1 = sqrt(sum(square(y1 - self.b[0] + self.b[1] * (x1 - self.peak))) / (n-2)) / sqrt(sum(square(x1 - mean(x1))))
		x2 = self.x[self.x >= self.peak]
		y2 = self.y[self.x >= self.peak]
		se2 = sqrt(sum(square(y2 - self.b[0] + self.b[2] * (x2 - self.peak))) / (n-2)) / sqrt(sum(square(x2 - mean(x2))))
		return (2 * (1 - t(n-2).cdf(abs(self.b[1] / se1))), 2 * (1 - t(n-2).cdf(abs(self.b[2] / se2))))
	def scatter(self):
		scatter(self.x, self.y, s = 4, alpha = .5)
		plot(range(18, 89), self.model.m)
		axvline(x = self.peak, color = "k")
		plot(range(18, 89), self.piecewise(arange(18., 89.), *self.b))
		xlabel("Age")
		ylabel("Integrity")
		savefig("./Downloads/Projects/NMV/Figures/Regression/{}/{}".format(self.sex.capitalize(), self.tract))
		clf()
	@staticmethod
	def pcolor(slopes, pvalues, peaks):
		color = sign(slopes).values * -log10(pvalues).values
		pcolor(list(map(lambda x: repeat(x[0], x[1]), zip(color, map(lambda peak: (peak-18, 89-peak), peaks)))), cmap = "coolwarm")
		axhline(y = 26, color = "k")
		axhline(y = 58, color = "k")
		xticks(arange(0, 70, 5) + 2, arange(20, 90, 5))
		xlabel("Age")
		ylabel("Tract")
		colorbar(aspect = 10).ax.set_title("-log(p)")
		clim(log10(0.05), -log10(0.05))
		savefig("./Downloads/Projects/NMV/Figures/Regression/Heatmap ({})".format(sex.capitalize()))
		clf()
	@staticmethod
	def dump(SLOPES, PVALUES):
		with open("./Downloads/Projects/NMV/Datasets/Slopes.pkl", "wb") as fout:
			pickle.dump(SLOPES, fout, pickle.HIGHEST_PROTOCOL)
		with open("./Downloads/Projects/NMV/Datasets/Pvalues.pkl", "wb") as fout:
			pickle.dump(PVALUES, fout, pickle.HIGHEST_PROTOCOL)
	@staticmethod
	def load():
		with open("./Downloads/Projects/NMV/Datasets/Slopes.pkl", "rb") as fin:
			SLOPES = pickle.load(fin)
		with open("./Downloads/Projects/NMV/Datasets/Pvalues.pkl", "rb") as fin:
			PVALUES = pickle.load(fin)
		return (SLOPES, PVALUES)
