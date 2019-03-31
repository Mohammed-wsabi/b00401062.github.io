#!/usr/bin/env python3

from pandas import *
from dlc.Configuration import *
from dlc.Cropper import *
from dlc.Preprocesser import *
from dlc.Dataset import *
from dlc.Classifier import *

if __name__ == "__main__":
	Cropper(DATASETDIR, RAWDIR).run()
	BOXES = read_csv(DATASETDIR + "Boxes.csv")
	Preprocesser(RAWDIR, PREPROCESSEDDIR, BOXES, S).run()
	LABELS = Dataset(DATASETDIR, PREPROCESSEDDIR).load()
	model = Classifier(PREPROCESSEDDIR, S).fit(LABELS)
