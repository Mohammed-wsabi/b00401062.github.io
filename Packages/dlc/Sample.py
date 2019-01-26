#!/usr/bin/env python3

from pandas import read_csv

class Sample:
	@staticmethod
	def load():
		COUNTS = read_csv("./Downloads/Projects/DLC/Datasets/ObjectNumber.csv", index_col = 0)
		LABELS = read_csv("./Downloads/Projects/DLC/Datasets/Label.csv", index_col = 0)
		return (COUNTS, LABELS)
