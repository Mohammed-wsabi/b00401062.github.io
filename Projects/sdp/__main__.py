#!/usr/bin/env python3

from sklearn.gaussian_process import GaussianProcessClassifier
from sdp.Constants import *
from sdp.Dataset import *
from sdp.Reducer import *
from sdp.Evaluator import *

if __name__ == "__main__":
	## Statistical analysis
	for i in range(len(TRACTS)):
		DATASET = Dataset.get(["GFA"], [TRACTS[i]])
		Selector(TRACTS[i]).fit(*DATASET.test)
	## Dataset
	DATASET = Dataset.get(["GFA"], list(map(lambda i: TRACTS[i], [5, 8])))
	## Dimensionality reduction
	reducer = Reducer().fit(*DATASET.training)
	## Classifier
	classifier = GaussianProcessClassifier()
	## Fitting
	classifier.fit(reducer.fit_transform(DATASET.training.X), DATASET.training.y)
	## Evaluation
	Evaluator(reducer, classifier).fit(*DATASET.test)
