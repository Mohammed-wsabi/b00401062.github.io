#!/usr/bin/env python3

import pickle
from numpy import *
from pandas import DataFrame
from scipy.stats import shapiro
from scipy.stats import norm
from matplotlib.pyplot import *

class Selection:
	columns = ["GPR (Female)", "GPR (Male)", "RWR (Female)", "RWR (Male)"]
	@staticmethod
	def mse(SCORES):
		mses = -SCORES.test_neg_mean_squared_error.unstack()
		m = mses.mean(axis = 1)
		s = mses.std(axis = 1) / sqrt(76)
		e = s * norm.ppf(.975)
		errorbar(range(4), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("MSE")
		xticks(range(4), Selection.columns)
		yticks(rotation = 60)
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/MSE")
		clf()
		return DataFrame(array([m - e, m, m + e]).T, index = Selection.columns, columns = ["lower", "mean", "upper"])
	@staticmethod
	def cod(SCORES):
		cods = SCORES.test_r2.unstack()
		m = cods.mean(axis = 1)
		s = cods.std(axis = 1) / sqrt(76)
		e = s * norm.ppf(.975)
		errorbar(range(4), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("COD")
		xticks(range(4), Selection.columns)
		axhline(0, color = "r")
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/COD")
		clf()
		p = 1 - norm.cdf(m / s)
		return DataFrame(array([m - e, m, m + e, p]).T, index = Selection.columns, columns = ["lower", "mean", "upper", "p"])
	@staticmethod
	def standard(STANDARDS):
		ps = STANDARDS.p.unstack()
		m = ps.mean(axis = 1)
		s = ps.std(axis = 1) / sqrt(76)
		e = s * norm.ppf(.975)
		errorbar(range(4), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("p-value")
		xticks(range(4), Selection.columns)
		axhline(0.05, color = "r")
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/Standard")
		clf()
		p = 1 - norm.cdf((m - 0.05) / s)
		return DataFrame(array([m - e, m, m + e, p]).T, index = Selection.columns, columns = ["lower", "mean", "upper", "p"])
	@staticmethod
	def quantile(QUANTILES, i):
		qs = QUANTILES.loc[:, i].unstack()
		m = qs.mean(axis = 1)
		s = qs.std(axis = 1) / sqrt(76)
		e = s * norm.ppf(.975)
		errorbar(range(4), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("Percentage")
		xticks(range(4), Selection.columns)
		axhline(2 * norm.cdf(i) - 1, color = "r")
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/Quantile{}".format(i))
		clf()
		p = (1 - norm.cdf(abs(m - (2 * norm.cdf(i) - 1)) / s)) * 2
		return DataFrame(array([m - e, m, m + e, p]).T, index = Selection.columns, columns = ["lower", "mean", "upper", "p"])
	@staticmethod
	def dump(SCORES, STANDARDS, QUANTILES):
		with open("./Downloads/Projects/NMV/Datasets/Scores.pkl", "wb") as fout:
			pickle.dump(SCORES, fout, pickle.HIGHEST_PROTOCOL)
		with open("./Downloads/Projects/NMV/Datasets/Standards.pkl", "wb") as fout:
			pickle.dump(STANDARDS, fout, pickle.HIGHEST_PROTOCOL)
		with open("./Downloads/Projects/NMV/Datasets/Quantiles.pkl", "wb") as fout:
			pickle.dump(QUANTILES, fout, pickle.HIGHEST_PROTOCOL)
	@staticmethod
	def load():
		with open("./Downloads/Projects/NMV/Datasets/Scores.pkl", "rb") as fin:
			SCORES = pickle.load(fin)
		with open("./Downloads/Projects/NMV/Datasets/Standards.pkl", "rb") as fin:
			STANDARDS = pickle.load(fin)
		with open("./Downloads/Projects/NMV/Datasets/Quantiles.pkl", "rb") as fin:
			QUANTILES = pickle.load(fin)
		return (SCORES, STANDARDS, QUANTILES)
