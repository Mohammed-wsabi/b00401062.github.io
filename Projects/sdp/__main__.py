#!/usr/bin/env python3

from pandas import DataFrame
from sklearn.decomposition import PCA
from sklearn.model_selection import LeaveOneOut
from sklearn.model_selection import GridSearchCV
from sklearn.svm import SVC
from sklearn.gaussian_process import GaussianProcessClassifier
from sklearn.gaussian_process.kernels import RationalQuadratic
from sklearn.ensemble import RandomForestClassifier
from sklearn.neural_network import MLPClassifier
from sklearn.metrics import confusion_matrix
from sdp.Constants import *
from sdp.Dataset import *

if __name__ == "__main__":
	DATASET = Dataset.get(["GFA", "RD"], [16, 17])
	## Dimensionality reduction
	reducer = PCA(.7).fit(DATASET.training.X)
	## SVC
	validator = GridSearchCV(
		SVC(gamma = "scale"),
		{"C": 2. ** arange(-10, 10)},
		cv = LeaveOneOut()
	).fit(reducer.transform(DATASET.training.X), DATASET.training.y)
	model = validator.best_estimator_
	## GaussianProcessClassifier
	model = GaussianProcessClassifier(kernel = RationalQuadratic())
	## RandomForestClassifier
	validator = GridSearchCV(
		RandomForestClassifier(n_estimators = 100),
		{"max_depth": range(2, 8)},
		cv = LeaveOneOut()
	).fit(reducer.transform(DATASET.training.X), DATASET.training.y)
	model = validator.best_estimator_
	## MLPClassifier
	validator = GridSearchCV(
		MLPClassifier(hidden_layer_sizes = (int(sqrt(reducer.n_components_)),), solver = "lbfgs", max_iter = 10000),
		{"alpha": 2. ** arange(-10, 10)},
		cv = LeaveOneOut()
	).fit(reducer.transform(DATASET.training.X), DATASET.training.y)
	model = validator.best_estimator_
	## Fitting
	model.fit(reducer.transform(DATASET.training.X), DATASET.training.y.tolist())
	confusion_matrix(DATASET.test.y, model.predict(reducer.transform(DATASET.test.X)))
