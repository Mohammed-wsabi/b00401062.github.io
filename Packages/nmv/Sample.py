#!/usr/bin/env python3

import pickle
from pandas import read_csv
from matplotlib.pyplot import *

class Sample:
	def load(self):
		self.DF = read_csv("./Downloads/Projects/NMV/Datasets/Demographics.csv", index_col = 0)
		with open("./Downloads/Projects/NMV/Datasets/Indices/GFA.pkl", "rb") as fin:
			self.GFA = pickle.load(fin)
		return self.DF, self.GFA
	def plot(self):
		hist(self.DF.Age, bins = range(18, 89))
		xlabel("Age")
		ylabel("Frequency")
		yticks(range(20))
		savefig("./Downloads/Projects/NMV/Figures/Sample")
		show()
