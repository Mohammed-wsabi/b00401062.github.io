#!/usr/bin/env python3

import os
from pandas import *

class Dataset:
	def __init__(self, DATASETDIR, RAWDIR):
		self.DATASETDIR = DATASETDIR
		self.RAWDIR = RAWDIR
	def load(self):
		BOXES = concat([
			read_table(self.DATASETDIR + "Single_Training_bbox.csv", sep = " "),
			read_table(self.DATASETDIR + "Single_Testing_bbox.csv", sep = " ")
		])
		if os.path.isfile("Labels.csv"):
			LABELS = read_csv(self.DATASETDIR + "Labels.csv")
		else:
			id = os.listdir(self.RAWDIR)
			labels = list(map(lambda f: f.split("_")[0], id))
			LABELS = DataFrame({"ImageID": id, "Label": labels})
			LABELS.to_csv(self.DATASETDIR + "Labels.csv", index = False)
		return (BOXES, LABELS)
