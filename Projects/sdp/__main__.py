#!/usr/bin/env python3

from numpy import *
from pandas import *
from sklearn.decomposition import PCA
from sklearn.model_selection import LeaveOneOut
from sklearn.model_selection import GridSearchCV
from sklearn.svm import SVC
from sklearn.gaussian_process import GaussianProcessClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.neural_network import MLPClassifier
from sklearn.metrics import confusion_matrix
from sklearn.metrics import roc_auc_score
from sdp.Constants import *
from sdp.Dataset import *

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
	roc_auc_score(DATASET.test.y == "S", model.predict_proba(reducer.transform(DATASET.test.X))[:, 1])
