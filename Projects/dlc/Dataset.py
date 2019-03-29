#!/usr/bin/env python3

import os
from pandas import *

class Dataset:
	def __init__(self, DATASETDIR, PREPROCESSEDDIR):
		self.DATASETDIR = DATASETDIR
		self.PREPROCESSEDDIR = PREPROCESSEDDIR
	def load(self):
		if os.path.isfile("Labels.csv"):
			LABELS = read_csv(self.DATASETDIR + "Labels.csv")
		else:
			id = os.listdir(self.PREPROCESSEDDIR)
			labels = list(map(lambda f: f.split("_")[0], id))
			LABELS = DataFrame({"ImageID": id, "Label": labels})
			LABELS.to_csv(self.DATASETDIR + "Labels.csv", index = False)
		return LABELS
