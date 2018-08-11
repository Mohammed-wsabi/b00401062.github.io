from __future__ import division
from matplotlib.pyplot import *
from numpy import *
from pandas import *
from sklearn.metrics import roc_curve, auc
from sklearn.metrics import confusion_matrix
from Constants import *
from Preprocessor import *

class Evaluator:
	def __init__(self, preprocessor):
		self.preprocessor = preprocessor
		self.title = "".join([c for c in preprocessor.estimator.__class__.__name__ if c.isupper()])
		self.threshold = None
	def fit(self, dataset):
		assert hasattr(self.preprocessor, "decision_function")
		print("\n## Evaluation session: %s\n" % self.title)
		X = concat((dataset.NR, dataset.R))
		y = Series(
			["NR"] * dataset.NR.shape[0] + ["R"] * dataset.R.shape[0],
			index = dataset.NR.index.append(dataset.R.index),
			dtype = "category"
		).rename("Category")
		self.preprocessor.fit(X, y)
		scores = self.preprocessor.decision_function(X)
		fpr, tpr, thresholds = roc_curve(y == "R", scores)
		print("- Receiver operating characteristic (ROC): %f" % auc(fpr, tpr))
		self.__plot_roc_curve(tpr, fpr)
		return self
	def predict(self, dataset, title = None):
		print("- Confusion matrix: %s" % title)
		X = concat((dataset.NR, dataset.R))
		y = Series(
			["NR"] * dataset.NR.shape[0] + ["R"] * dataset.R.shape[0],
			index = dataset.NR.index.append(dataset.R.index),
			dtype = "category"
		).rename("Category")
		prediction = self.preprocessor.predict(X)
		self.__print_confusion_matrix(y, prediction)
		return prediction
	def __plot_roc_curve(self, tpr, fpr):
		plot(fpr, tpr, lw = 2)
		plot([0, 1], [0, 1], lw = 2, linestyle = "--")
		title("%s ROC Curve (area = %.4f)" % (self.title, auc(fpr, tpr)))
		xlabel("1-Specificity")
		ylabel("Sensitivity")
		axes().set_aspect("equal")
		savefig("./Downloads/Researches/ABMRI/Figures/ROC/ROC %s.png" % self.title)
		close()
	def __print_confusion_matrix(self, y, prediction):
		table = confusion_matrix(y, prediction)
		table[0, 0], table[1, 1] = table[1, 1], table[0, 0]
		table = DataFrame(table)
		table.columns = ["True R", "True NR"]
		table.index = ["Predicted R", "Predicted NR"]
		tn = table.loc["Predicted NR", "True NR"]
		tp = table.loc["Predicted R", "True R"]
		fn = table.loc["Predicted NR", "True R"]
		fp = table.loc["Predicted R", "True NR"]
		acc = (tp + tn) / (tp + fp + tn + fn)
		sen = tp / (tp + fn)
		spe = tn / (tn + fp)
		ppv = tp / (tp + fp)
		npv = tn / (tn + fn)
		for k, v in Result(acc, sen, spe, ppv, npv)._asdict().iteritems():
			print("\t- %s: %.4f" % (k, v))
		lines = table.to_csv(sep = "|").strip().split("\n")
		lines.insert(1, ":-:|:-:|:-:")
		print
		for line in lines: print("|%s|" % line)
		print
		return Result(acc, sen, spe, ppv, npv)
