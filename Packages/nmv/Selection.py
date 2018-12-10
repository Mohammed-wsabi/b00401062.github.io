#!/usr/bin/env python3

from matplotlib.pyplot import *

class Selection:
	@staticmethod
	def best(SCORES):
		return SCORES.test_neg_mean_squared_error.unstack().mean(axis = 0).idxmax()
	@staticmethod
	def barplot(SCORES):
		models = ["PE", "LLSR", "QLSR", "GPR"]
		mses = -SCORES.test_neg_mean_squared_error.unstack()[models]
		legend([
			bar(range(76), mses.PE, 1),
			bar(range(76), mses.LLSR, 1, mses.PE),
			bar(range(76), mses.QLSR, 1, mses.PE + mses.LLSR),
			bar(range(76), mses.GPR, 1, mses.PE + mses.LLSR + mses.QLSR)
		], models)
		xlabel("Tract")
		ylabel("MSE")
		savefig("./Downloads/Projects/NMV/Figures/Selection/Barplot")
		show()
	@staticmethod
	def errorbar(SCORES):
		models = ["PE", "LLSR", "QLSR", "GPR"]
		mses = -SCORES.test_neg_mean_squared_error.unstack()[models]
		errorbar(range(4), mses.mean(axis = 0), mses.std(axis = 0)/76, fmt = "o", capsize = 4)
		xlabel("Model")
		ylabel("Average MSE")
		xticks(range(4), models)
		savefig("./Downloads/Projects/NMV/Figures/Selection/Errorbar")
		show()
