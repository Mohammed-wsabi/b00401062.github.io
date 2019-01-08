#!/usr/bin/env python3

import pickle
from numpy import *
from pandas import DataFrame
from scipy.stats import shapiro
from scipy.stats import t
from matplotlib.pyplot import *
from nmv.Constants import *

class Selection:
	columns = ["GPR (Female)", "RWR (Female)", "GPR (Male)", "RWR (Male)"]
	@staticmethod
	def stats(x):
		n = len(TRACTS)
		m = x.mean(axis = 1)
		sd = x.std(axis = 1)
		sp = sqrt(((n-1) * sd[0] ** 2 + (n-1) * sd[1] ** 2) / (2 * n - 2))
		se = sp * sqrt(2/n)
		e = repeat(se * t(2*n-2).ppf(.975), 2)
		p = 2 - 2 * t(2*n-2).cdf(abs(m[0] - m[1]) / sp * sqrt(2/n))
		return (m, sd, sp, se, e, p)
	@staticmethod
	def mse(SCORES):
		mses = -SCORES.test_neg_mean_squared_error.unstack()
		stats = Sex(Selection.stats(mses.loc["female"]), Selection.stats(mses.loc["male"]))
		m = stats.female[0].tolist() + stats.male[0].tolist()
		e = stats.female[4].tolist() + stats.male[4].tolist()
		errorbar(range(4), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("MSE")
		xticks(range(4), Selection.columns)
		yticks(rotation = 60)
		axvline(1.5, color = "black")
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/MSE")
		clf()
		return (stats.female[5], stats.male[5])
	@staticmethod
	def cod(SCORES):
		cods = SCORES.test_r2.unstack()
		stats = Sex(Selection.stats(cods.loc["female"]), Selection.stats(cods.loc["male"]))
		m = stats.female[0].tolist() + stats.male[0].tolist()
		e = stats.female[4].tolist() + stats.male[4].tolist()
		errorbar(range(4), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("COD")
		xticks(range(4), Selection.columns)
		axhline(0, color = "r")
		axvline(1.5, color = "black")
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/COD")
		clf()
		return (stats.female[5], stats.male[5])
	@staticmethod
	def standard(STANDARDS):
		ps = STANDARDS.p.unstack()
		stats = Sex(Selection.stats(ps.loc["female"]), Selection.stats(ps.loc["male"]))
		m = stats.female[0].tolist() + stats.male[0].tolist()
		e = stats.female[4].tolist() + stats.male[4].tolist()
		errorbar(range(4), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("p-value")
		xticks(range(4), Selection.columns)
		axvline(1.5, color = "black")
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/Standard")
		clf()
		return (stats.female[5], stats.male[5])
	@staticmethod
	def quantile(QUANTILES, i):
		qs = QUANTILES.loc[:, i].unstack()
		m = qs.mean(axis = 1)
		s = qs.std(axis = 1) / sqrt(len(TRACTS))
		e = s * norm.ppf(.975)
		errorbar(range(4), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("Percentage")
		xticks(range(4), Selection.columns)
		axhline(2 * norm.cdf(i) - 1, color = "r")
		axvline(1.5, color = "black")
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/Quantile{}".format(i))
		clf()
		p = (1 - norm.cdf(abs(m - (2 * norm.cdf(i) - 1)) / s)) * 2
		return DataFrame(array([m, m - e, m + e, p]).T, index = Selection.columns, columns = ["mean", "lower", "upper", "p"])
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
