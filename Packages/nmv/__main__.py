#!/usr/bin/env python3

from numpy import *
from pandas import DataFrame
from sklearn.model_selection import cross_validate
from sklearn.model_selection import KFold
from sklearn.preprocessing import MinMaxScaler

if __name__ == "__main__":
	DF, GFA = Sample.load()
	Sample.plot(DF)
	print(sum(Normality().fit(DF, GFA).p < 0.01)/5396)
	MODELS = [PE(), LLSR(), QLSR(), GPR()]
	models = [model.__class__.__name__ for model in MODELS]
	SCORES = DataFrame(
		index = [repeat(arange(76), 4), tile(models, 76)],
		columns = scores.columns,
		dtype = "float"
	)
	QUANTILES = DataFrame(
		index = [repeat(arange(76), 4), tile(models, 76)],
		columns = range(1, 4),
		dtype = "float"
	)
	x = DF.Age
	for i in range(76):
		tract = TRACTS[i].Nickname
		y = GFA[i].mean(axis = 0)
		SCORES.loc[i] = [DataFrame(cross_validate(
			model, x, y,
			cv = KFold(5, True, 0),
			scoring = ("r2", "neg_mean_squared_error"),
			return_train_score = True
		)).mean(axis = 0) for model in MODELS]
		machines = [Diagnostics(tract, model).fit(x, y) for model in MODELS]
		[machine.residual() for machine in machines]
		QUANTILES.loc[i] = [machine.quantile() for machine in machines]
		for machine in machines:
			machine.scatter()
			machine.metrics()
	Sample.dump(SCORES, QUANTILES)
	Selection.barplot(SCORES)
	Selection.errorbar(SCORES)
	best = Selection.best(SCORES)
	QUANTILES.loc[list(zip(best.index, best))].mean(axis = 0)
