#!/usr/bin/env python3

import pickle
from pandas import read_csv
from matplotlib.pyplot import *

class Sample:
	@staticmethod
	def load():
		DF = read_csv("./Downloads/Projects/NMV/Datasets/Demographics.csv", index_col = 0)
		with open("./Downloads/Projects/NMV/Datasets/Indices/GFA.pkl", "rb") as fin:
			GFA = pickle.load(fin)
		return DF, GFA
	@staticmethod
	def plot(DF):
		hist(DF.Age, bins = range(18, 89))
		xlabel("Age")
		ylabel("Frequency")
		yticks(range(20))
		savefig("./Downloads/Projects/NMV/Figures/Sample")
		show()
	@staticmethod
	def dump(SCORES, QUANTILES):
		with open("./Downloads/Projects/NMV/Datasets/Scores.pkl", "wb") as fout:
			pickle.dump(SCORES, fout, pickle.HIGHEST_PROTOCOL)
		with open("./Downloads/Projects/NMV/Datasets/Quantiles.pkl", "wb") as fout:
			pickle.dump(QUANTILES, fout, pickle.HIGHEST_PROTOCOL)
