#!/usr/bin/env python3

from pandas import DataFrame
from sklearn.decomposition import PCA
from sklearn.model_selection import LeaveOneOut
from sklearn.model_selection import GridSearchCV
from sklearn.gaussian_process import GaussianProcessClassifier
from sklearn.gaussian_process.kernels import RationalQuadratic
from sklearn.neural_network import MLPClassifier
from sklearn.metrics import confusion_matrix
from matplotlib.pyplot import *
from sdp.Constants import *
from sdp.Dataset import *

if __name__ == "__main__":
	DATASET = Dataset.get("GFA")
	## Dimensionality reduction
	plot(cumsum(PCA().fit(DATASET.training.X).explained_variance_ratio_))
	grid(axis = "y")
	xlabel("# Feature")
	ylabel("Percentage")
	savefig("./Downloads/Projects/SDP/Figures/PCA")
	show()
	reducer = PCA(4).fit(DATASET.training.X)
	## MLPClassifier
	validator = GridSearchCV(
		MLPClassifier(hidden_layer_sizes = (10,), max_iter = 10000),
		{"alpha": 2. ** arange(-4, 4, .1)},
		cv = LeaveOneOut()
	).fit(reducer.transform(DATASET.training.X), DATASET.training.y)
	DataFrame(validator.cv_results_).to_csv("./Downloads/Projects/SDP/Datasets/Validator.csv")
	model = MLPClassifier(hidden_layer_sizes = (10,), max_iter = 10000, alpha = 2. ** -2.2)
	model = model.fit(reducer.transform(DATASET.training.X), DATASET.training.y.tolist())
	confusion_matrix(DATASET.test.y, model.predict(reducer.transform(DATASET.test.X)))
	## GaussianProcessClassifier
	model = GaussianProcessClassifier(kernel = RationalQuadratic())
	model = model.fit(DATASET.training.X, DATASET.training.y.tolist())
	confusion_matrix(DATASET.test.y, model.predict(DATASET.test.X))
