#!/usr/bin/env python3

from sklearn.gaussian_process import GaussianProcessClassifier
from sklearn.gaussian_process.kernels import RationalQuadratic
from sklearn.metrics import confusion_matrix

if __name__ == "__main__":
	DATASET = Dataset.get("GFA")
	model = GaussianProcessClassifier(kernel = RationalQuadratic())
	confusion_matrix(DATASET.test.y, model.fit(DATASET.training.X, DATASET.training.y.tolist()).predict(DATASET.test.X))
