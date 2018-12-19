#!/usr/bin/env python3

from numpy import *
from pandas import DataFrame
from sklearn.model_selection import cross_validate
from sklearn.model_selection import KFold

if __name__ == "__main__":
	(DF, GFA) = Sample.load()
	Sample.hist(DF)
	normality = Normality().fit(DF, GFA)
	sum(normality.p < 0.01) / 5396
	normality.hist()
	normality.pcolor()
	MODELS = [PE(), LLSR(), QLSR(), GPR()]
	models = [model.__class__.__name__ for model in MODELS]
	SCORES = DataFrame(
		index = [repeat(range(76), 4), tile(models, 76)],
		columns = [
			"fit_time",
			"score_time",
			"test_neg_mean_squared_error",
			"train_neg_mean_squared_error",
			"test_r2",
			"train_r2",
		],
		dtype = "float"
	)
	METRICS = DataFrame(
		index = [repeat(range(76), 4), tile(models, 76)],
		columns = ["mse", "cod", "cor"],
		dtype = "float"
	)
	RESIDUALS = DataFrame(
		index = [repeat(range(76), 4), tile(models, 76)],
		columns = range(616),
		dtype = "float"
	)
	STANDARDS = DataFrame(
		index = [repeat(range(76), 4), tile(models, 76)],
		columns = range(616),
		dtype = "float"
	)
	QUANTILES = DataFrame(
		index = [repeat(range(76), 4), tile(models, 76)],
		columns = arange(3) + 1,
		dtype = "float"
	)
	x = DF.Age
	for i in range(76):
		tract = TRACTS[i].Nickname
		y = GFA[i].mean(axis = 0)
		SCORES.loc[i] = [DataFrame(cross_validate(
			model, x, y,
			cv = KFold(5, True, 0),
			scoring = ("neg_mean_squared_error", "r2"),
			return_train_score = True
		)).mean(axis = 0) for model in MODELS]
		diagnostics = [Diagnostics(tract, model).fit(x, y) for model in MODELS]
		for diagnostic in diagnostics:
			model = diagnostic.model.__class__.__name__
			METRICS.loc[(i, model)] = diagnostic.metrics()
			RESIDUALS.loc[(i, model)] = diagnostic.residuals()
			STANDARDS.loc[(i, model)] = diagnostic.standards()
			QUANTILES.loc[(i, model)] = diagnostic.quantiles()
			diagnostic.scatter()
	Selection.dump(SCORES, METRICS, RESIDUALS, STANDARDS, QUANTILES)
	(SCORES, METRICS, RESIDUALS, STANDARDS, QUANTILES) = Selection.load()
	selection = Selection().fit(SCORES)
	selection.scores(SCORES)
	selection.metrics(METRICS)
	selection.standards(STANDARDS)
	for i in arange(3) + 1:
		selection.quantiles(QUANTILES, i)
