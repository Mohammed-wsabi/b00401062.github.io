#!/usr/bin/env python3

import pickle
from numpy import array
from pandas import read_csv
from matplotlib.pyplot import *

class Sample:
	@staticmethod
	def load():
		DF = read_csv("./Downloads/Projects/NMV/Datasets/Demographics.csv", index_col = 0)
		with open("./Downloads/Projects/NMV/Datasets/Indices/GFA.pkl", "rb") as fin:
			GFA = pickle.load(fin)
		GFA = array(list(map(lambda i: GFA[i].mean(axis = 0), map(lambda tract: tract.index, TRACTS))))
		return (DF, GFA)
	@staticmethod
	def hist(DF):
		hist(
			[DF.Age.loc[DF.Sex == "FEMALE"], DF.Age.loc[DF.Sex == "MALE"]],
			bins = range(18, 90),
			label = ["Female", "Male"],
			stacked = True
		)
		legend()
		xlabel("Age")
		ylabel("Frequency")
		yticks(range(0, 20, 2))
		savefig("./Downloads/Projects/NMV/Figures/Sample")
		clf()
