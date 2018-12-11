#!/usr/bin/env python3

from numpy import *
from sklearn.metrics import mean_squared_error
from sklearn.metrics import r2_score
from sklearn.model_selection import ShuffleSplit
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
		for train, test in ShuffleSplit(n_splits = 100, test_size = .2).split(x):
			self.model.fit(x[train], y[train])
			y_hat = self.model.predict(x[test])
			self.xs.extend(x[test])
			self.rs.extend(y[test] - y_hat)
			self.zs.extend((y[test] - y_hat) / self.model.y_std[x[test] - 18])
		return self
	def metrics(self):
		y_hat = self.model.predict(self.x)
		mse = mean_squared_error(self.y, y_hat)
		cod = r2_score(self.y, y_hat)
		return (mse, cod)
	def scatter(self):
		scatter(self.x, self.y, s = 4, alpha = .5)
		plot(range(18, 89), self.model.y_mean)
		fill_between(range(18, 89), self.model.y_mean - self.model.y_std, self.model.y_mean + self.model.y_std, alpha = .5)
		xlabel("Age")
		ylabel("Mean GFA")
		savefig("./Downloads/Projects/NMV/Figures/Scatter/{}/{}".format(self.tract, self.model.__class__.__name__))
		show()
	def residual(self):
		scatter(self.xs, self.rs, s = 4, alpha = .5)
		axhline(color = "red", linestyle = "--")
		xlabel("Age")
		ylabel("Residual")
		savefig("./Downloads/Projects/NMV/Figures/Residual/{}/{}".format(self.tract, self.model.__class__.__name__))
		show()
		return mean(self.rs)
	def quantile(self):
		hist(array(self.zs)[isfinite(self.zs)], bins = linspace(-10, 10, 100))
		xlabel("Z-score")
		ylabel("Frequency")
		savefig("./Downloads/Projects/NMV/Figures/Quantile/{}/{}".format(self.tract, self.model.__class__.__name__))
		show()
		return [sum(absolute(self.zs) < i) / len(self.zs) for i in range(1, 4)]
