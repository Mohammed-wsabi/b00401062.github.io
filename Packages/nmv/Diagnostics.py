#!/usr/bin/env python3

from numpy import *
from scipy.stats import kstest
from scipy.stats import norm
from sklearn.metrics import mean_squared_error
from sklearn.metrics import r2_score
from sklearn.model_selection import KFold
from matplotlib.pyplot import *
from nmv.Constants import *

class Diagnostics:
	def __init__(self, sex, model, tract):
		self.sex = sex
		self.model = model
		self.tract = tract
	def fit(self, x, y):
		self.xs = []
		self.ys = []
		self.rs = []
		self.zs = []
		for train, test in KFold(5, True, 0).split(x):
			self.model.fit(x[train], y[train])
			y_hat = self.model.predict(x[test])
			self.xs.extend(x[test])
			self.ys.extend(y[test])
			self.rs.extend(y[test] - y_hat)
			self.zs.extend((y[test] - y_hat) / self.model.s[x[test] - 18])
		return self
	def residual(self):
		hist(array(self.zs)[isfinite(self.zs)], bins = linspace(-4, 4, 80))
		xlabel("Z-score")
		ylabel("Frequency")
		savefig("./Downloads/Projects/NMV/Figures/Residual/{}/{}/{}".format(self.sex.capitalize(), self.model.__class__.__name__, self.tract.nickname))
		clf()
	def standard(self):
		return kstest(self.zs, "norm").pvalue
	def percentage(self):
		return sum(absolute(self.zs) < norm.ppf(1-ALPHA/2)) / len(self.zs)
