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
	array(normality.p < 0.05).sum() / 5396
	for sex in Sex._fields:
		sum(sum(normality.p.loc[sex] < 0.05)) / 5396
		normality.hist(sex)
		normality.pcolor(sex)
	MODELS = [RWR(), GPR()]
	SCORES = DataFrame(
		index = [
			repeat(Sex._fields, len(MODELS) * 76),
			tile(repeat([model.__class__.__name__ for model in MODELS], 76), len(Sex._fields)),
			tile(range(76), len(Sex._fields) * len(MODELS)),
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
			repeat(Sex._fields, len(MODELS) * 76),
			tile(repeat([model.__class__.__name__ for model in MODELS], 76), len(Sex._fields)),
			tile(range(76), len(Sex._fields) * len(MODELS)),
		],
		columns = ["p"],
		dtype = "float"
	)
	QUANTILES = DataFrame(
		index = [
			repeat(Sex._fields, len(MODELS) * 76),
			tile(repeat([model.__class__.__name__ for model in MODELS], 76), len(Sex._fields)),
			tile(range(76), len(Sex._fields) * len(MODELS)),
		],
		columns = arange(3) + 1,
		dtype = "float"
	)
	for sex in Sex._fields:
		x = DF.Age.loc[DF.Sex == sex.upper()]
		for model in MODELS:
			for i in range(len(TRACTS)):
				y = GFA[i].mean(axis = 0)[DF.Sex == sex.upper()]
				SCORES.loc[(sex, model.__class__.__name__, i)] = DataFrame(cross_validate(
					model, x, y,
					cv = KFold(5, True, 0),
					scoring = ("neg_mean_squared_error", "r2"),
					return_train_score = True
				)).mean(axis = 0)
				diagnostic = Diagnostics(sex, model, TRACTS[i].nickname).fit(x, y)
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
	GPRS = Sex(*[[GPR().fit(DF.Age.loc[DF.Sex == sex.upper()], GFA[i].mean(axis = 0)[DF.Sex == sex]) for i in range(76)] for sex in Sex._fields])
	with open("./Downloads/Projects/NMV/Datasets/GPRs.pkl", "wb") as fout:
		pickle.dump(GPRS, fout, pickle.HIGHEST_PROTOCOL)
	with open("./Downloads/Projects/NMV/Datasets/GPRs.pkl", "rb") as fin:
		GPRS = pickle.load(fin)
	SLOPES = DataFrame(
		index = [
			repeat(Sex._fields, 76),
			tile(range(76), len(Sex._fields)),
		],
		columns = ["b1", "b2"],
		dtype = "float"
	)
	PVALUES = DataFrame(
		index = [
			repeat(Sex._fields, 76),
			tile(range(76), len(Sex._fields)),
		],
		columns = ["p1", "p2"],
		dtype = "float"
	)
	for sex in Sex._fields:
		x = DF.Age.loc[DF.Sex == sex.upper()]
		for i in range(len(TRACTS)):
			y = GFA[i].mean(axis = 0)[DF.Sex == sex.upper()]
			model = Regression(sex, getattr(GPRS, sex)[i], TRACTS[i].nickname).fit(x, y)
			SLOPES.loc[(sex, i)] = (model.b[1], model.b[2])
			PVALUES.loc[(sex, i)] = model.p()
			model.scatter()
		peaks = list(map(Regression.peak, getattr(GPRS, sex)))
		Regression.pcolor(SLOPES.loc[sex], PVALUES.loc[sex], peaks)
	Regression.dump(SLOPES, PVALUES)
	(SLOPES, PVALUES) = Regression.load()
