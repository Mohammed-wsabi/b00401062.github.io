#!/usr/bin/env python3

from sklearn.model_selection import cross_validate
from sklearn.model_selection import KFold
from pandas import DataFrame

if __name__ == "__main__":
	DF, GFA = Sample().load()
	Normality().fit(DF, GFA).plot()
	MODELS = [PE(), LLSR(), QLSR(), GPR()]
	x = DF.Age
	for i in range(76):
		tract = TRACTS[i].Nickname
		y = GFA[i].mean(axis = 0)
		scores = DataFrame(
			[DataFrame(cross_validate(
				model, x, y,
				cv = KFold(5, True),
				scoring = ("r2", "neg_mean_squared_error"),
				return_train_score = True
			)).mean(axis = 0) for model in MODELS],
			index = [model.__class__.__name__ for model in MODELS]
		)
		quantiles = DataFrame(
			[Diagnostics(tract, model).quantile(x, y) for model in MODELS],
			columns = range(1, 4),
			index = [model.__class__.__name__ for model in MODELS]
		)
		for model in MODELS:
			Diagnostics(tract, model.fit(x, y)).metrics(x, y)
			Diagnostics(tract, model.fit(x, y)).scatter(x, y)
			Diagnostics(tract, model.fit(x, y)).residual(x, y)
