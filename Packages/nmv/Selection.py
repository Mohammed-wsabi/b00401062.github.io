#!/usr/bin/env python3

import pickle
from numpy import *
from pandas import DataFrame
from scipy.stats import shapiro
from scipy.stats import norm
from matplotlib.pyplot import *

class Selection:
	def fit(self, SCORES):
		self.mixed = SCORES.test_neg_mean_squared_error.unstack().idxmax(axis = 1)
		self.models = SCORES.index.levels[1].tolist()
		return self
	def mse(self, SCORES):
		mses = -SCORES.test_neg_mean_squared_error.unstack()[self.models]
		m = mses.mean(axis = 0)
		s = mses.std(axis = 0) / sqrt(76)
		e = s * norm.ppf(.975)
		errorbar(range(len(self.model)), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("MSE")
		xticks(range(len(self.model)), mses.columns)
		yticks(rotation = 60)
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/MSEs")
		clf()
		return DataFrame(array([m - e, m, m + e]).T, index = mses.columns, columns = ["lower", "mean", "upper"])
	def cods(self, SCORES):
		cods = SCORES.test_r2.unstack()[self.models]
		m = cods.mean(axis = 0)
		s = cods.std(axis = 0) / sqrt(76)
		e = s * norm.ppf(.975)
		errorbar(range(len(self.model)), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("COD")
		xticks(range(len(self.model)), cods.columns)
		axhline(0, color = "r")
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/CODs")
		clf()
		p = 1 - norm.cdf(m / s)
		return DataFrame(array([m - e, m, m + e, p]).T, index = cods.columns, columns = ["lower", "mean", "upper", "p"])
	def pearsonr(self, PEARSONRS):
		rs = PEARSONRS.r.unstack()[self.models]
		m = rs.mean(axis = 0)
		s = rs.std(axis = 0) / sqrt(76)
		e = s * norm.ppf(.975)
		errorbar(range(len(self.model)), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("Correlation")
		xticks(range(len(self.model)), rs.columns)
		axhline(0, color = "r")
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/PearsonRs")
		clf()
		p = 1 - norm.cdf(abs(m) / s)
		return DataFrame(array([m - e, m, m + e, p]).T, index = rs.columns, columns = ["lower", "mean", "upper", "p"])
	def standard(self, STANDARDS):
		ps = STANDARDS.apply(lambda x: shapiro(x)[1], axis = 1).unstack()[self.models]
		m = ps.mean(axis = 0)
		s = ps.std(axis = 0) / sqrt(76)
		e = s * norm.ppf(.975)
		errorbar(range(len(self.model)), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("p-value")
		xticks(range(len(self.model)), ps.columns)
		axhline(0.01, color = "r")
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/Standards")
		clf()
		p = 1 - norm.cdf((m - 0.01) / s)
		return DataFrame(array([m - e, m, m + e, p]).T, index = ps.columns, columns = ["lower", "mean", "upper", "p"])
	def quantile(self, QUANTILES, i):
		qs = QUANTILES.loc[:, i].unstack()[self.models]
		m = qs.mean(axis = 0)
		s = qs.std(axis = 0) / sqrt(76)
		e = s * norm.ppf(.975)
		errorbar(range(len(self.model)), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("Percentage")
		xticks(range(len(self.model)), qs.columns)
		axhline(2 * norm.cdf(i) - 1, color = "r")
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/Quantiles{}".format(i))
		clf()
		p = (1 - norm.cdf(abs(m - (2 * norm.cdf(i) - 1)) / s)) * 2
		return DataFrame(array([m - e, m, m + e, p]).T, index = qs.columns, columns = ["lower", "mean", "upper", "p"])
	@staticmethod
	def dump(SCORES, PEARSONRS, RESIDUALS, STANDARDS, QUANTILES):
		with open("./Downloads/Projects/NMV/Datasets/Scores.pkl", "wb") as fout:
			pickle.dump(SCORES, fout, pickle.HIGHEST_PROTOCOL)
		with open("./Downloads/Projects/NMV/Datasets/PearsonRs.pkl", "wb") as fout:
			pickle.dump(PEARSONRS, fout, pickle.HIGHEST_PROTOCOL)
		with open("./Downloads/Projects/NMV/Datasets/Residuals.pkl", "wb") as fout:
			pickle.dump(RESIDUALS, fout, pickle.HIGHEST_PROTOCOL)
		with open("./Downloads/Projects/NMV/Datasets/Standards.pkl", "wb") as fout:
			pickle.dump(STANDARDS, fout, pickle.HIGHEST_PROTOCOL)
		with open("./Downloads/Projects/NMV/Datasets/Quantiles.pkl", "wb") as fout:
			pickle.dump(QUANTILES, fout, pickle.HIGHEST_PROTOCOL)
	@staticmethod
	def load():
		with open("./Downloads/Projects/NMV/Datasets/Scores.pkl", "rb") as fin:
			SCORES = pickle.load(fin)
		with open("./Downloads/Projects/NMV/Datasets/PearsonRs.pkl", "rb") as fin:
			PEARSONRS = pickle.load(fin)
		with open("./Downloads/Projects/NMV/Datasets/Residuals.pkl", "rb") as fin:
			RESIDUALS = pickle.load(fin)
		with open("./Downloads/Projects/NMV/Datasets/Standards.pkl", "rb") as fin:
			STANDARDS = pickle.load(fin)
		with open("./Downloads/Projects/NMV/Datasets/Quantiles.pkl", "rb") as fin:
			QUANTILES = pickle.load(fin)
		return (SCORES, PEARSONRS, RESIDUALS, STANDARDS, QUANTILES)
