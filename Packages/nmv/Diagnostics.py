#!/usr/bin/env python3

from numpy import *
from scipy.stats import pearsonr
from sklearn.metrics import mean_squared_error
from sklearn.metrics import r2_score
from sklearn.model_selection import KFold
from matplotlib.pyplot import *

class Diagnostics:
	def __init__(self, tract, model):
		self.tract = tract
		self.model = model
	def fit(self, x, y):
		self.x = x
		self.y = y
		self.xs = []
		self.rs = []
		self.zs = []
		for train, test in KFold(5, True, 0).split(x):
			self.model.fit(x[train], y[train])
			y_hat = self.model.predict(x[test])
			self.xs.extend(x[test])
			self.rs.extend(y[test] - y_hat)
			self.zs.extend((y[test] - y_hat) / self.model.s[x[test] - 18])
		return self
	def metrics(self):
		y_hat = self.model.predict(self.x)
		self.mse = mean_squared_error(self.y, y_hat)
		self.cod = r2_score(self.y, y_hat)
		self.cor = pearsonr(self.xs, self.rs)[0]
		return (self.mse, self.cod, self.cor)
	def residuals(self):
		scatter(self.xs, self.rs, s = 4, alpha = .5)
		axhline(color = "red", linestyle = "--")
		xlabel("Age")
		ylabel("Residual")
		savefig("./Downloads/Projects/NMV/Figures/Residuals/{}/{}".format(self.tract, self.model.__class__.__name__))
		clf()
		return self.rs
	def standards(self):
		hist(array(self.zs)[isfinite(self.zs)], bins = linspace(-10, 10, 100))
		xlabel("Z-score")
		ylabel("Frequency")
		savefig("./Downloads/Projects/NMV/Figures/Standards/{}/{}".format(self.tract, self.model.__class__.__name__))
		clf()
		return self.zs
	def quantiles(self):
		return [sum(absolute(self.zs) < i) / len(self.zs) for i in arange(3) + 1]
	def scatter(self):
		scatter(self.x, self.y, s = 4, alpha = .5)
		plot(range(18, 89), self.model.m)
		fill_between(range(18, 89), self.model.m - self.model.s, self.model.m + self.model.s, alpha = .5)
		xlabel("Age")
		ylabel("Mean GFA")
		savefig("./Downloads/Projects/NMV/Figures/Scatter/{}/{}".format(self.tract, self.model.__class__.__name__))
		clf()
