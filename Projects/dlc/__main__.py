#!/usr/bin/env python3

from dlc.Parameters import *
from dlc.Dataset import *

if __name__ == "__main__":
	BOXES, LABELS = Dataset(DATASETDIR, RAWDIR).load()
	Preprocessor(RAWDIR, BOXES.set_index("ID"), S).run()
	LABELS.ImageID = list(map(lambda f: f[:-3] + "jpeg", LABELS.ImageID))
	Classifier(PREPROCESSEDDIR, LABELS, S).run()
