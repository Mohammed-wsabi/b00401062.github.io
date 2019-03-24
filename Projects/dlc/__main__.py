#!/usr/bin/env python3

from dlc.Configuration import *
from dlc.Dataset import *
from dlc.Preprocessor import *
from dlc.Classifier import *

if __name__ == "__main__":
	BOXES, LABELS = Dataset(DATASETDIR, RAWDIR).load()
	Preprocessor(RAWDIR, BOXES.set_index("ID"), S).run()
	LABELS.ImageID = list(map(lambda f: f[:-3] + "jpeg", LABELS.ImageID))
	LABELS.Label = LABELS.Label.astype("category")
	Classifier(PREPROCESSEDDIR, LABELS, S).run()
