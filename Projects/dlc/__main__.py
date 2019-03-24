#!/usr/bin/env python3

from dlc.Configuration import *
from dlc.Dataset import *
from dlc.Preprocessor import *
from dlc.Classifier import *

if __name__ == "__main__":
	BOXES, LABELS = Dataset(DATASETDIR, RAWDIR).load()
	BOXES = BOXES.set_index("ID")
	Preprocessor(RAWDIR, BOXES, S).run()
	LABELS.ImageID = list(map(lambda f: f[:-3] + "jpeg", LABELS.ImageID))
	LABELS.Label = LABELS.Label.astype("category")
	model = Classifier(PREPROCESSEDDIR, S)
	model.fit(LABELS)
