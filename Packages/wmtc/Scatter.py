#!/usr/bin/env python3

import pickle
from pandas import *
from matplotlib.pyplot import *
from sklearn.gaussian_process import GaussianProcessRegressor
from sklearn.gaussian_process.kernels import RBF
from sklearn.gaussian_process.kernels import WhiteKernel

df = read_csv("/Users/luo/Downloads/Projects/WMTC/Datasets/Demographics.csv", index_col = 0)
with open("/Users/luo/Downloads/Projects/WMTC/Datasets/Indices/GFA.pkl", "rb") as fin:
	gfa = pickle.load(fin)

kernel = 1.0 * RBF() + WhiteKernel()
model = GaussianProcessRegressor(kernel = kernel, alpha = 0.0)

for i in range(76):
	scatter(df.Age, gfa[i].mean(axis = 0), s = 4)
	model.fit(array(df.Age).reshape(-1, 1), gfa[i].mean(axis = 0))
	x = arange(min(df.Age), max(df.Age) + 1)
	y_mean, y_cov = model.predict(x.reshape(-1, 1), return_cov = True)
	plot(x, y_mean)
	fill_between(x, y_mean - sqrt(diag(y_cov)), y_mean + sqrt(diag(y_cov)), alpha = 0.5)
	ylim([0, 1])
	savefig("/Users/luo/Downloads/Projects/WMTC/Figures/Scatters/{}".format(TRACTS[i].Nickname))
	clf()
