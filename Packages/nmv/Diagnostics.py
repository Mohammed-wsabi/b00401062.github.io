#!/usr/bin/env python3

from numpy import *
from sklearn.model_selection import ShuffleSplit
from matplotlib.pyplot import *

class Diagnostics:
	def __init__(self, tract, model):
		self.tract = tract
		self.model = model
	def metrics(self, x, y):
		y_hat = self.model.predict(x)
		cod = r2_score(y, y_hat)
		mse = mean_squared_error(y, y_hat)
		return (cod, mse)
	def scatter(self, x, y):
		scatter(x, y, s = 4)
		plot(range(18, 89), self.model.y_mean)
		fill_between(range(18, 89), self.model.y_mean - self.model.y_std, self.model.y_mean + self.model.y_std, alpha = 0.5)
		xlabel("Age")
		ylabel("Mean GFA")
		savefig("./Downloads/Projects/NMV/Figures/Scatter/{}/{}".format(self.tract, self.model.__class__.__name__))
		show()
	def residual(self, x, y):
		y_hat = self.model.predict(x)
		hist(y - y_hat, bins = linspace(-.2, .2, 100))
		xlabel("Residual")
		ylabel("Frequency")
		savefig("./Downloads/Projects/NMV/Figures/Residual/{}/{}".format(self.tract, self.model.__class__.__name__))
		show()
	def quantile(self, x, y):
		zs = []
		for train, test in ShuffleSplit(n_splits = 100, test_size = .2).split(x):
			self.model.fit(x[train], y[train])
			y_hat = self.model.predict(x[test])
			zs.extend((y[test] - y_hat) / self.model.y_std[x[test] - 18])
		hist(array(zs)[isfinite(zs)], bins = linspace(-10, 10, 100))
		xlabel("Z-score")
		ylabel("Frequency")
		savefig("./Downloads/Projects/NMV/Figures/Quantile/{}/{}".format(self.tract, self.model.__class__.__name__))
		show()
		return [sum(absolute(zs) < i) / len(zs) for i in range(1, 4)]
