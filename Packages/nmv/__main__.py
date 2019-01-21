#!/usr/bin/env python3

import pickle
from numpy import *
from pandas import DataFrame
from sklearn.model_selection import cross_validate
from sklearn.model_selection import KFold
from nmv.Constants import *
from nmv.Sample import *
from nmv.Normality import *
from nmv.Models import *
from nmv.Diagnostics import *
from nmv.Selection import *
from nmv.Regression import *

if __name__ == "__main__":
	(DF, GFA) = Sample.load()
	Sample.hist(DF)
	MODELS = [RWR, GPR]
	SCORES = DataFrame(
		index = [
			repeat(Sex._fields, len(MODELS) * len(TRACTS)),
			tile(repeat([model.__name__ for model in MODELS], len(TRACTS)), len(Sex._fields)),
			tile(range(len(TRACTS)), len(Sex._fields) * len(MODELS)),
		],
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
	STANDARDS = DataFrame(
		index = [
			repeat(Sex._fields, len(MODELS) * len(TRACTS)),
			tile(repeat([model.__name__ for model in MODELS], len(TRACTS)), len(Sex._fields)),
			tile(range(len(TRACTS)), len(Sex._fields) * len(MODELS)),
		],
		columns = ["p"],
		dtype = "float"
	)
	PERCENTAGES = DataFrame(
		index = [
			repeat(Sex._fields, len(MODELS) * len(TRACTS)),
			tile(repeat([model.__name__ for model in MODELS], len(TRACTS)), len(Sex._fields)),
			tile(range(len(TRACTS)), len(Sex._fields) * len(MODELS)),
		],
		columns = ["p"],
		dtype = "float"
	)
	for sex in Sex._fields:
		x = DF.age.loc[DF.sex == sex.upper()]
		for model in MODELS:
			for i in range(len(TRACTS)):
				y = GFA[i].mean(axis = 0)[DF.sex == sex.upper()]
				SCORES.loc[(sex, model.__name__, i)] = DataFrame(cross_validate(
					model(), x, y,
					cv = KFold(5, True, 0),
					scoring = ("neg_mean_squared_error", "r2"),
					return_train_score = True
				)).mean(axis = 0)
				diagnostic = Diagnostics(sex, model(), TRACTS[i]).fit(x, y)
				diagnostic.residual()
				STANDARDS.loc[(sex, model.__name__, i)] = diagnostic.standard()
				PERCENTAGES.loc[(sex, model.__name__, i)] = diagnostic.percentage()
	Selection.dump(SCORES, STANDARDS, PERCENTAGES)
	(SCORES, STANDARDS, PERCENTAGES) = Selection.load()
	Selection.mse(SCORES)
	Selection.cod(SCORES)
	Selection.standard(STANDARDS)
	Selection.percentage(PERCENTAGES)
	for model in MODELS:
		for i in range(len(TRACTS)):
			Model(model(), TRACTS[i]).scatter(DF.age, GFA[i].mean(axis = 0))
