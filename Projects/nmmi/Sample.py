#!/usr/bin/env python3

import pickle
from numpy import array
from pandas import read_csv
from matplotlib.pyplot import *
from nmmi.Constants import *

class Sample:
	@staticmethod
	def load():
		DF = read_csv("./Downloads/Projects/NMMI/Datasets/Demographics.csv", index_col = 0)
		with open("./Downloads/Projects/NMMI/Datasets/Indices/GFA.pkl", "rb") as fin:
			GFA = pickle.load(fin)
		GFA = array(list(map(lambda i: GFA[i].mean(axis = 0), map(lambda tract: tract.indices, TRACTS))))
		return (DF, GFA)
	@staticmethod
	def hist(DF):
		hist(
			[DF.age.loc[DF.sex == "FEMALE"], DF.age.loc[DF.sex == "MALE"]],
			bins = range(18, 90),
			label = ["Female", "Male"],
			stacked = True
		)
		legend()
		xlabel("Age")
		ylabel("Frequency")
		yticks(range(0, 20, 2))
		savefig("./Downloads/Projects/NMMI/Figures/Sample")
		clf()
