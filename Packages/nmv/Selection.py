#!/usr/bin/env python3

import pickle
from numpy import *
from pandas import DataFrame
from scipy.stats import shapiro
from scipy.stats import t
from scipy.stats import norm
from matplotlib.pyplot import *
from nmv.Constants import *

class Selection:
	MODELS = ["GPR", "RWR"]
	@staticmethod
	def stats(x):
		n = len(TRACTS)
		m = x.mean(axis = 1)
		se = sqrt(sum(square(x.std(axis = 1)))) / sqrt(n)
		e = se * t(2*n-2).ppf(.975)
		p = 2 - 2 * t(2*n-2).cdf(abs(m[0] - m[1]) / se)
		return (m, se, e, p)
	@staticmethod
	def display(sex, label, m, se, e, p):
		print("- {}".format(sex.capitalize()))
		for i in range(2):
			print("\t- {}: {:.2E} ± {:.2E} (95% CI = [{:.2E},{:.2E}])".format(Selection.MODELS[i], m[i], se, m[i]-e, m[i]+e))
		print("\t- P-value: {}".format(p))
		bar(range(2), m)
		errorbar(range(2), m, e, fmt = "o", capsize = 4, color = "k")
		xticks(range(2), Selection.MODELS)
		title(sex.capitalize())
		xlabel("Model")
		ylabel(label)
	@staticmethod
	def mse(SCORES):
		x = -SCORES.test_neg_mean_squared_error.unstack()
		for sex, _ in zip(Sex._fields, subplots(1, 2, sharey = True)[1]):
			sca(_)
			Selection.display(sex, "MSE", *Selection.stats(x.loc[sex]))
		savefig("./Downloads/Projects/NMV/Figures/Selection/MSE")
		close()
	@staticmethod
	def cod(SCORES):
		x = SCORES.test_r2.unstack()
		for sex, _ in zip(Sex._fields, subplots(1, 2, sharey = True)[1]):
			sca(_)
			Selection.display(sex, "COD", *Selection.stats(x.loc[sex]))
		savefig("./Downloads/Projects/NMV/Figures/Selection/COD")
		close()
	@staticmethod
	def standard(STANDARDS):
		x = STANDARDS.p.unstack()
		for sex, _ in zip(Sex._fields, subplots(1, 2, sharey = True)[1]):
			sca(_)
			Selection.display(sex, "P-value", *Selection.stats(x.loc[sex]))
		savefig("./Downloads/Projects/NMV/Figures/Selection/Standard")
		close()
	@staticmethod
	def quantile(QUANTILES, i):
		x = QUANTILES.loc[:, i].unstack()
		n = len(TRACTS)
		for sex, _ in zip(Sex._fields, subplots(1, 2, sharey = True)[1]):
			sca(_)
			m = x.loc[sex].mean(axis = 1)
			se = x.loc[sex].std(axis = 1) / sqrt(n)
			e = se * t(2*n-2).ppf(.975)
			p = (1 - t(2*n-2).cdf(abs(m - (2 * norm.cdf(i) - 1)) / se)) * 2
			print("- {}".format(sex.capitalize()))
			for i in range(2):
				print("\t- {}: {:.2E} ± {:.2E} (95% CI = [{:.2E},{:.2E}]; p = {:.2E})".format(Selection.MODELS[i], m[i], se[i], m[i]-e[i], m[i]+e[i], p[i]))
			errorbar(range(2), m, e, fmt = "o", capsize = 4)
			xticks(range(2), Selection.MODELS)
			title(sex.capitalize())
			xlabel("Model")
			ylabel("Percentage")
			xlim((-0.49, 1.49))
			axhline(2 * norm.cdf(i) - 1, color = "r")
			grid(axis = "y")
		savefig("./Downloads/Projects/NMV/Figures/Selection/Quantile{}".format(i))
		close()
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
