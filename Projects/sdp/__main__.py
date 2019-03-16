#!/usr/bin/env python3

from numpy import *
from pandas import *
from matplot.pyplot import *
from sklearn.decomposition import PCA
from sklearn.gaussian_process import GaussianProcessClassifier
from sklearn.metrics import confusion_matrix
from sklearn.metrics import roc_curve, auc
from sdp.Constants import *
from sdp.Dataset import *
from sdp.Reducer import *

if __name__ == "__main__":
	## Statistical analysis
	for i in range(len(TRACTS)):
		DATASET = Dataset.get(["GFA"], [TRACTS[i]])
		ttest_ind(DATASET.test.X.mean(axis = 1)[DATASET.test.y == "N"], DATASET.test.X.mean(axis = 1)[DATASET.test.y == "S"]).pvalue
	## Dataset
	DATASET = Dataset.get(["GFA"], list(map(lambda i: TRACTS[i], [5, 8])))
	## GaussianProcessClassifier
	model = GaussianProcessClassifier()
	## Dimensionality reduction
	reducer = Reducer().fit(DATASET.training.X, DATASET.training.y).fit(DATASET.training.X)
	## Fitting
	model.fit(reducer.transform(DATASET.training.X), DATASET.training.y.tolist())
	## Evaluation
	confusion_matrix(DATASET.test.y, model.predict(reducer.transform(DATASET.test.X)))
	plot(
		*roc_curve(DATASET.test.y == "S", model.predict_proba(reducer.transform(DATASET.test.X))[:, 1])[:2],
		label = "AUC: {:.2f}".format(roc_auc_score(DATASET.test.y == "S", model.predict_proba(reducer.transform(DATASET.test.X))[:, 1]))
	)
	plot([0, 1], [0, 1], linestyle = "--")
	xlabel("1 - Specificity")
	ylabel("Sensitivity")
	legend(loc = "lower right")
	axes().set_aspect("equal")
	savefig("./Downloads/Projects/SDP/Figures/ROC")
	clf()
