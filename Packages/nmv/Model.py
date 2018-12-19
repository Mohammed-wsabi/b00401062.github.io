#!/usr/bin/env python3

from sklearn.base import BaseEstimator

## Point estimation
class PE(BaseEstimator):
	def fit(self, x, y):
		self.m = array([y[bitwise_and(age - 1 <= x, x <= age + 1)].mean() for age in range(18, 89)])
		self.s = array([y[bitwise_and(age - 1 <= x, x <= age + 1)].std() for age in range(18, 89)])
		return self
	def predict(self, x):
		return self.m[x - 18]

## Linear least squares regression
from statsmodels.api import OLS
from sklearn.preprocessing import PolynomialFeatures
from statsmodels.sandbox.regression.predstd import wls_prediction_std
class LLSR(BaseEstimator):
	def fit(self, x, y):
		x = array(x).reshape(-1, 1)
		model = OLS(y, PolynomialFeatures(1).fit_transform(x)).fit()
		self.m = model.predict(PolynomialFeatures(1).fit_transform(arange(18, 89).reshape(-1 ,1)))
		self.s = wls_prediction_std(model, PolynomialFeatures(1).fit_transform(arange(18, 89).reshape(-1 ,1)))[0]
		return self
	def predict(self, x):
		return self.m[x - 18]

## Quadratic least squares regression
from statsmodels.api import OLS
from sklearn.preprocessing import PolynomialFeatures
from statsmodels.sandbox.regression.predstd import wls_prediction_std
class QLSR(BaseEstimator):
	def fit(self, x, y):
		x = array(x).reshape(-1, 1)
		model = OLS(y, PolynomialFeatures(2).fit_transform(x)).fit()
		self.m = model.predict(PolynomialFeatures(2).fit_transform(arange(18, 89).reshape(-1 ,1)))
		self.s = wls_prediction_std(model, PolynomialFeatures(2).fit_transform(arange(18, 89).reshape(-1 ,1)))[0]
		return self
	def predict(self, x):
		return self.m[x - 18]

## Gaussian process regression
from sklearn.gaussian_process import GaussianProcessRegressor
from sklearn.gaussian_process.kernels import RBF
from sklearn.gaussian_process.kernels import WhiteKernel
class GPR(BaseEstimator):
	def fit(self, x, y):
		x = array(x).reshape(-1, 1)
		model = GaussianProcessRegressor(kernel = RBF() + WhiteKernel(), alpha = 0.0).fit(x, y)
		self.m, self.s = model.predict(arange(18, 89).reshape(-1 ,1), return_std = True)
		return self
	def predict(self, x):
		return self.m[x - 18]
