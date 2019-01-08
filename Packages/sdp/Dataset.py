#!/usr/bin/env python3

from numpy import *
from pandas import read_csv
from pandas import DataFrame
from re import compile
from scipy.io import loadmat

class Dataset:
	@staticmethod
	def get(index):
		assert index in INDICES
		raw = loadmat("./Downloads/Projects/SDP/Datasets/all_array_{}.mat".format(index.lower()))
		values = array(list(map(lambda x: x.flatten(), raw["array"][0])))
		pattern = "F:\\\\[^\\\\]+\\\\[^\\\\]+\\\\([^\\\\]+)\\\\.*"
		folders = array(list(map(compile(pattern).findall, map(lambda x: x[0][0], raw["index_name"])))).flatten()
		dataset = {"training": {"X": None, "y": None}, "test": {"X": None, "y": None}}
		for set in Set._fields:
			file = "./Downloads/Projects/SDP/Datasets/{}.csv".format(set.capitalize())
			metadata = read_csv(file, index_col = 0)
			mask = isin(folders, metadata.index)
			dataset[set]["X"] = DataFrame(values[mask], index = folders[mask]).dropna(axis = 1)
			dataset[set]["y"] = metadata.final[folders[mask]].astype("category")
		return Set(training = Data(**dataset["training"]), test = Data(**dataset["test"]))
