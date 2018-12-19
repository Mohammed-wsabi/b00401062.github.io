#!/usr/bin/env python3

from scipy.stats import shapiro
from scipy.stats import norm
from matplotlib.pyplot import *

class Selection:
	@staticmethod
	def best(SCORES):
		return SCORES.test_neg_mean_squared_error.unstack().idxmax(axis = 1)
	@staticmethod
	def score(SCORES):
		best = Selection.best(SCORES)
		models = ["PE", "LLSR", "QLSR", "GPR"]
		mses = -SCORES.test_neg_mean_squared_error.unstack()[models]
		mses["Best"] = mses.stack().loc[list(zip(best.index, best))].tolist()
		m = mses.mean(axis = 0)
		s = mses.std(axis = 0) / sqrt(76)
		errorbar(range(5), m, s, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("Average MSE")
		xticks(range(5), mses.columns)
		yticks(rotation = 90)
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/Score")
		clf()
		return DataFrame(array([m - s, m + s]).T, index = mses.columns, columns = ["lower", "upper"])
	@staticmethod
	def standard(STANDARDS):
		best = Selection.best(SCORES)
		models = ["PE", "LLSR", "QLSR", "GPR"]
		ps = STANDARDS.apply(lambda x: shapiro(x)[1], axis = 1).unstack()[models]
		ps["Best"] = ps.stack().loc[list(zip(best.index, best))].tolist()
		m = ps.mean(axis = 0)
		s = ps.std(axis = 0) / sqrt(76)
		errorbar(range(5), m, s, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("Average p")
		xticks(range(5), ps.columns)
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/Standard")
		clf()
		return DataFrame(array([m - s, m + s]).T, index = ps.columns, columns = ["lower", "upper"])
	@staticmethod
	def quantile(QUANTILES):
		best = Selection.best(SCORES)
		models = ["PE", "LLSR", "QLSR", "GPR"]
		for i in arange(3) + 1:
			qs = QUANTILES.loc[:, i].unstack()[models]
			qs["Best"] = qs.stack().loc[list(zip(best.index, best))].tolist()
			m = qs.mean(axis = 0)
			s = qs.std(axis = 0) / sqrt(76)
			errorbar(range(5), m, s, fmt = "o", capsize = 4)
			xlabel("Model")
			ylabel("Percentage")
			xticks(range(5), qs.columns)
			grid(axis = "y")
			axhline(2 * norm.cdf(i) - 1)
			savefig("./Downloads/Projects/NMV/Figures/Selection/Quantile{}".format(i))
			clf()
	@staticmethod
	def dump(SCORES, RESIDUALS, STANDARDS, QUANTILES):
		with open("./Downloads/Projects/NMV/Datasets/Scores.pkl", "wb") as fout:
			pickle.dump(SCORES, fout, pickle.HIGHEST_PROTOCOL)
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
		with open("./Downloads/Projects/NMV/Datasets/Residuals.pkl", "rb") as fin:
			RESIDUALS = pickle.load(fin)
		with open("./Downloads/Projects/NMV/Datasets/Standards.pkl", "rb") as fin:
			STANDARDS = pickle.load(fin)
		with open("./Downloads/Projects/NMV/Datasets/Quantiles.pkl", "rb") as fin:
			QUANTILES = pickle.load(fin)
		return (SCORES, RESIDUALS, STANDARDS, QUANTILES)
