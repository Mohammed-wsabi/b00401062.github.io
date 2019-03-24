#!/usr/bin/env python3

import os
from pandas import *

class Metadata:
	@staticmethod
	def load():
		if os.path.isfile("Labels.csv"):
			return read_csv(DATASETDIR + "Labels.csv")
		id = os.listdir(RAWDIR)
		labels = list(map(lambda f: f.split("_")[0], id))
		LABELS = DataFrame({"id": id, "label": labels})
		LABELS.to_csv(DATASETDIR + "Labels.csv", index = False)
		return LABELS
