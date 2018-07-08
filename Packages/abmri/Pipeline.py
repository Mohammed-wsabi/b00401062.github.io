from numpy import *
from pandas import *
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis
from sklearn.discriminant_analysis import QuadraticDiscriminantAnalysis
from sklearn.linear_model import LogisticRegression
from sklearn.svm import SVC
from Constants import *
from Preprocessor import *
from Validator import *
from Evaluator import *

class Pipeline:
	def __init__(self, bests, validate = True, evaluate = True):
		self.bests = bests
		self.validate = validate
		self.evaluate = evaluate
		self.datasets = None
	def fit(self, datasets):
		assert isinstance(datasets, Set)
		self.datasets = datasets
		self.preprocess(datasets.Training)
		self.bests = Model(
			LinearDiscriminantAnalysis = self.LinearDiscriminantAnalysis(),
			QuadraticDiscriminantAnalysis = self.QuadraticDiscriminantAnalysis(),
			LogisticRegression = self.LogisticRegression(),
			SVC = self.SVC())
		return self
	def preprocess(self, dataset):
		X = concat((dataset.NR, dataset.R))
		y = Series(
			["NR"] * dataset.NR.shape[0] + ["R"] * dataset.R.shape[0],
			index = dataset.NR.index.append(dataset.R.index),
			dtype = "category"
		).rename("Category")
		Preprocessor(output = True).preprocess(X, y)
	def LinearDiscriminantAnalysis(self):
		## Machine learning model: linear discriminant analysis (LDA)
		best = Validator(
			preprocessor = Preprocessor(LinearDiscriminantAnalysis(solver = "lsqr")),
			param_name = "shrinkage",
			param_range = linspace(0, 1, 101),
			xlabel = "shrinkage",
			xtickslabels = linspace(0, 1, 11)
		).fit(self.datasets.Training).best if self.validate else self.bests.LinearDiscriminantAnalysis
		if not self.evaluate: return best
		evaluator = Evaluator(
			preprocessor = Preprocessor(LinearDiscriminantAnalysis(solver = "lsqr", shrinkage = best.Parameter))
		).fit(self.datasets.Training)
		evaluator.predict(self.datasets.Training, "Training")
		evaluator.predict(self.datasets.Validation, "Test 1")
		evaluator.predict(self.datasets.Test, "Test 2")
		return best
	def QuadraticDiscriminantAnalysis(self):
		## Machine learning model: quadratic discriminant analysis (QDA)
		best = Validator(
			preprocessor = Preprocessor(QuadraticDiscriminantAnalysis()),
			param_name = "reg_param",
			param_range = linspace(0, 1, 101),
			xlabel = "shrinkage",
			xtickslabels = linspace(0, 1, 11)
		).fit(self.datasets.Training).best if self.validate else self.bests.QuadraticDiscriminantAnalysis
		if not self.evaluate: return best
		evaluator = Evaluator(
			preprocessor = Preprocessor(QuadraticDiscriminantAnalysis(reg_param = best.Parameter))
		).fit(self.datasets.Training)
		evaluator.predict(self.datasets.Training, "Training")
		evaluator.predict(self.datasets.Validation, "Test 1")
		evaluator.predict(self.datasets.Test, "Test 2")
		return best
	def LogisticRegression(self):
		## Machine learning model: logistic regression (LR)
		best = Validator(
			preprocessor = Preprocessor(LogisticRegression()),
			param_name = "C",
			param_range = 2 ** linspace(-10, 10, 201),
			xlabel = "log2(C)",
			xtickslabels = arange(21) - 10
		).fit(self.datasets.Training).best if self.validate else self.bests.LogisticRegression
		if not self.evaluate: return best
		evaluator = Evaluator(
			preprocessor = Preprocessor(LogisticRegression(C = best.Parameter))
		).fit(self.datasets.Training)
		evaluator.predict(self.datasets.Training, "Training")
		evaluator.predict(self.datasets.Validation, "Test 1")
		evaluator.predict(self.datasets.Test, "Test 2")
		return best
	def SVC(self):
		## Machine learning model: support vector classifier (SVC)
		best = Validator(
			preprocessor = Preprocessor(SVC(kernel = "linear")),
			param_name = "C",
			param_range = 2 ** linspace(-10, 10, 201),
			xlabel = "log2(C)",
			xtickslabels = arange(21) - 10
		).fit(self.datasets.Training).best if self.validate else self.bests.SVC
		evaluator = Evaluator(
			preprocessor = Preprocessor(SVC(kernel = "linear", C = best.Parameter))
		).fit(self.datasets.Training)
		evaluator.predict(self.datasets.Training, "Training")
		evaluator.predict(self.datasets.Validation, "Test 1")
		evaluator.predict(self.datasets.Test, "Test 2")
		return best
