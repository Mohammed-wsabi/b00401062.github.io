#!/usr/bin/env python3

import os
from pandas import *
from sklearn.model_selection import train_test_split

class Dataset:
	def __init__(self, DATASETDIR, PREPROCESSEDDIR):
		self.DATASETDIR = DATASETDIR
		self.PREPROCESSEDDIR = PREPROCESSEDDIR
	def load(self):
		if os.path.isfile("Labels.csv"):
			LABELS = read_csv(self.DATASETDIR + "Labels.csv")
		else:
			fs = os.listdir(self.PREPROCESSEDDIR)
			fs = list(filter(lambda f: f.split("_")[1] == "IMG", fs))
			labels = list(map(lambda f: f.split("_")[0], fs))
			LABELS = DataFrame({"ImageID": fs, "Label": labels})
			LABELS.to_csv(self.DATASETDIR + "Labels.csv", index = False)
		LABELS.Label = LABELS.Label.astype("category")
		LABELS = Set(*train_test_split(LABELS, stratify = LABELS.Label))
		LABELS.training.index = list(range(LABELS.training.index))
		LABELS.test.index = list(range(LABELS.test.index))
		return Set(*train_test_split(LABELS, stratify = LABELS.Label))
