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
		return (DF, GFA)
	@staticmethod
	def hist(DF):
		hist(
			[DF.Age.loc[DF.Sex == "FEMALE"], DF.Age.loc[DF.Sex == "MALE"]],
			bins = range(18, 89),
			label = ["Female", "Male"],
			stacked = True
		)
		legend()
		xlabel("Age")
		ylabel("Frequency")
		yticks(range(0, 20, 2))
		savefig("./Downloads/Projects/NMV/Figures/Sample")
		clf()
