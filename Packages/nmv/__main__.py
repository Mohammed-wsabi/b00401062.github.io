#!/usr/bin/env python3

import pickle
from numpy import *
from pandas import DataFrame
from sklearn.model_selection import cross_validate
from sklearn.model_selection import KFold

if __name__ == "__main__":
	(DF, GFA) = Sample.load()
	Sample.hist(DF)
	normality = Normality().fit(DF, GFA)
	sum(normality.p < 0.05) / 5396
	normality.hist()
	normality.pcolor()
	SEXES = ["FEMALE", "MALE"]
	MODELS = [RWR(), GPR()]
	SCORES = DataFrame(
		index = [
			repeat(SEXES, len(MODELS) * 76),
			tile(repeat([model.__class__.__name__ for model in MODELS], 76), len(SEXES)),
			tile(range(76), len(SEXES) * len(MODELS)),
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
			repeat(SEXES, len(MODELS) * 76),
			tile(repeat([model.__class__.__name__ for model in MODELS], 76), len(SEXES)),
			tile(range(76), len(SEXES) * len(MODELS)),
		],
		columns = ["p"],
		dtype = "float"
	)
	QUANTILES = DataFrame(
		index = [
			repeat(SEXES, len(MODELS) * 76),
			tile(repeat([model.__class__.__name__ for model in MODELS], 76), len(SEXES)),
			tile(range(76), len(SEXES) * len(MODELS)),
		],
		columns = arange(3) + 1,
		dtype = "float"
	)
	for sex in SEXES:
		x = DF.Age.loc[DF.Sex == sex]
		for model in MODELS:
			for i in range(len(TRACTS)):
				y = GFA[i].mean(axis = 0)[DF.Sex == sex]
				SCORES.loc[(sex, model.__class__.__name__, i)] = DataFrame(cross_validate(
					model, x, y,
					cv = KFold(5, True, 0),
					scoring = ("neg_mean_squared_error", "r2"),
					return_train_score = True
				)).mean(axis = 0)
				diagnostic = Diagnostics(sex, model, TRACTS[i].nickname).fit(x, y)
				diagnostic.residual()
				STANDARDS.loc[(sex, model.__class__.__name__, i)] = diagnostic.standard()
				QUANTILES.loc[(sex, model.__class__.__name__, i)] = diagnostic.quantile()
				diagnostic.scatter()
	Selection.dump(SCORES, STANDARDS, QUANTILES)
	(SCORES, STANDARDS, QUANTILES) = Selection.load()
	Selection.mse(SCORES)
	Selection.cod(SCORES)
	Selection.standard(STANDARDS)
	for i in arange(3) + 1:
		Selection.quantile(QUANTILES, i)
	GPRS = Sex(*[[GPR().fit(DF.Age.loc[DF.Sex == sex], GFA[i].mean(axis = 0)[DF.Sex == sex]) for i in range(76)] for sex in SEXES])
	with open("./Downloads/Projects/NMV/Datasets/GPRs.pkl", "wb") as fout:
		pickle.dump(GPRS, fout, pickle.HIGHEST_PROTOCOL)
	with open("./Downloads/Projects/NMV/Datasets/GPRs.pkl", "rb") as fin:
		GPRS = pickle.load(fin)
	map(lambda gpr: argmax(gpr.m) + 18, GPRS.female)
	map(lambda gpr: argmax(gpr.m) + 18, GPRS.male)
