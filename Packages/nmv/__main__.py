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
	normality = Normality().fit(DF, GFA)
	for sex in Sex._fields:
		array(normality.p.loc[sex] < ALPHA / len(TRACTS) / len(AGES)).sum()
		normality.hist(sex)
		normality.pcolor(sex)
	MODELS = [RWR(), GPR()]
	SCORES = DataFrame(
		index = [
			repeat(Sex._fields, len(MODELS) * len(TRACTS)),
			tile(repeat([model.__class__.__name__ for model in MODELS], len(TRACTS)), len(Sex._fields)),
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
			tile(repeat([model.__class__.__name__ for model in MODELS], len(TRACTS)), len(Sex._fields)),
			tile(range(len(TRACTS)), len(Sex._fields) * len(MODELS)),
		],
		columns = ["p"],
		dtype = "float"
	)
	PERCENTAGES = DataFrame(
		index = [
			repeat(Sex._fields, len(MODELS) * len(TRACTS)),
			tile(repeat([model.__class__.__name__ for model in MODELS], len(TRACTS)), len(Sex._fields)),
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
				SCORES.loc[(sex, model.__class__.__name__, i)] = DataFrame(cross_validate(
					model, x, y,
					cv = KFold(5, True, 0),
					scoring = ("neg_mean_squared_error", "r2"),
					return_train_score = True
				)).mean(axis = 0)
				diagnostic = Diagnostics(sex, model, TRACTS[i].nickname).fit(x, y)
				STANDARDS.loc[(sex, model.__class__.__name__, i)] = diagnostic.standard()
				PERCENTAGES.loc[(sex, model.__class__.__name__, i)] = diagnostic.percentage()
				diagnostic.scatter()
	Selection.dump(SCORES, STANDARDS, PERCENTAGES)
	(SCORES, STANDARDS, PERCENTAGES) = Selection.load()
	Selection.mse(SCORES)
	Selection.cod(SCORES)
	Selection.standard(STANDARDS)
	Selection.percentage(PERCENTAGES)
	GPRS = Sex(*[[GPR().fit(DF.age.loc[DF.sex == sex.upper()], GFA[i].mean(axis = 0)[DF.sex == sex.upper()]) for i in range(len(TRACTS))] for sex in Sex._fields])
	with open("./Downloads/Projects/NMV/Datasets/GPRs.pkl", "wb") as fout:
		pickle.dump(GPRS, fout, pickle.HIGHEST_PROTOCOL)
	with open("./Downloads/Projects/NMV/Datasets/GPRs.pkl", "rb") as fin:
		GPRS = pickle.load(fin)
	SLOPES = DataFrame(
		index = [
			repeat(Sex._fields, len(TRACTS)),
			tile(range(len(TRACTS)), len(Sex._fields)),
		],
		columns = ["b1", "b2"],
		dtype = "float"
	)
	PVALUES = DataFrame(
		index = [
			repeat(Sex._fields, len(TRACTS)),
			tile(range(len(TRACTS)), len(Sex._fields)),
		],
		columns = ["p1", "p2"],
		dtype = "float"
	)
	for sex in Sex._fields:
		x = DF.age.loc[DF.sex == sex.upper()]
		for i in range(len(TRACTS)):
			y = GFA[i].mean(axis = 0)[DF.sex == sex.upper()]
			model = Regression(sex, getattr(GPRS, sex)[i], TRACTS[i].nickname).fit(x, y)
			SLOPES.loc[(sex, i)] = (model.b[1], model.b[2])
			PVALUES.loc[(sex, i)] = model.p()
			model.scatter()
		peaks = list(map(Regression.peak, getattr(GPRS, sex)))
		Regression.pcolor(SLOPES.loc[sex], PVALUES.loc[sex], peaks, sex)
	Regression.dump(SLOPES, PVALUES)
	(SLOPES, PVALUES) = Regression.load()
