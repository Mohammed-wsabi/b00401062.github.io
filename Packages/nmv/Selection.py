#!/usr/bin/env python3

from scipy.stats import shapiro
from scipy.stats import norm
from matplotlib.pyplot import *

class Selection:
	def fit(self, SCORES):
		self.best = SCORES.test_neg_mean_squared_error.unstack().idxmax(axis = 1)
		self.models = ["PE", "LLSR", "QLSR", "GPR"]
		return self
	def scores(self, SCORES):
		mses = -SCORES.test_neg_mean_squared_error.unstack()[self.models]
		mses["Best"] = mses.stack().loc[list(zip(range(76), self.best))].tolist()
		m = mses.mean(axis = 0)
		s = mses.std(axis = 0) / sqrt(76) * norm.ppf(.975)
		e = s * norm.ppf(.975)
		errorbar(range(5), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("Average MSE")
		xticks(range(5), mses.columns)
		yticks(rotation = 90)
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/Scores")
		clf()
		return DataFrame(array([m - e, m + e]).T, index = mses.columns, columns = ["lower", "upper"])
	def metrics(self, METRICS):
		rs = METRICS.cor.unstack()[self.models]
		rs["Best"] = rs.stack().loc[list(zip(range(76), self.best))].tolist()
		m = rs.mean(axis = 0)
		s = rs.std(axis = 0) / sqrt(76)
		e = s * norm.ppf(.975)
		errorbar(range(5), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("Correlation")
		xticks(range(5), rs.columns)
		axhline(0)
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/Metrics")
		clf()
		return DataFrame(array([m - e, m + e]).T, index = rs.columns, columns = ["lower", "upper"])
	def standards(self, STANDARDS):
		ps = STANDARDS.apply(lambda x: shapiro(x)[1], axis = 1).unstack()[self.models]
		ps["Best"] = ps.stack().loc[list(zip(range(76), self.best))].tolist()
		m = ps.mean(axis = 0)
		s = ps.std(axis = 0) / sqrt(76)
		e = s * norm.ppf(.975)
		errorbar(range(5), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("Average p")
		xticks(range(5), ps.columns)
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/Standards")
		clf()
		return DataFrame(array([m - e, m + e]).T, index = ps.columns, columns = ["lower", "upper"])
	def quantiles(self, QUANTILES, i):
		qs = QUANTILES.loc[:, i].unstack()[self.models]
		qs["Best"] = qs.stack().loc[list(zip(range(76), self.best))].tolist()
		m = qs.mean(axis = 0)
		s = qs.std(axis = 0) / sqrt(76)
		e = s * norm.ppf(.975)
		errorbar(range(5), m, e, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("Percentage")
		xticks(range(5), qs.columns)
		axhline(2 * norm.cdf(i) - 1)
		grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/Quantiles{}".format(i))
		clf()
		return DataFrame(array([m - e, m + e]).T, index = qs.columns, columns = ["lower", "upper"])
	@staticmethod
	def dump(SCORES, METRICS, RESIDUALS, STANDARDS, QUANTILES):
		with open("./Downloads/Projects/NMV/Datasets/Scores.pkl", "wb") as fout:
			pickle.dump(SCORES, fout, pickle.HIGHEST_PROTOCOL)
		with open("./Downloads/Projects/NMV/Datasets/Metrics.pkl", "wb") as fout:
			pickle.dump(METRICS, fout, pickle.HIGHEST_PROTOCOL)
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
		with open("./Downloads/Projects/NMV/Datasets/Metrics.pkl", "rb") as fin:
			METRICS = pickle.load(fin)
		with open("./Downloads/Projects/NMV/Datasets/Residuals.pkl", "rb") as fin:
			RESIDUALS = pickle.load(fin)
		with open("./Downloads/Projects/NMV/Datasets/Standards.pkl", "rb") as fin:
			STANDARDS = pickle.load(fin)
		with open("./Downloads/Projects/NMV/Datasets/Quantiles.pkl", "rb") as fin:
			QUANTILES = pickle.load(fin)
		return (SCORES, METRICS, RESIDUALS, STANDARDS, QUANTILES)
