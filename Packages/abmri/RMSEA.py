from __future__ import division
import numpy as np
from numpy.linalg import det
from numpy.linalg import inv

def rmsea(analysis, predictor):
	n, p = predictor.shape
	m = analysis.n_components
	covariance = predictor.cov()
	loadings = analysis.components_
	dof = ((p - m) ** 2 - p - m) / 2
	estimate = loadings.T.dot(loadings) + np.diag(analysis.noise_variance_)
	np.fill_diagonal(estimate, np.diag(covariance))
	ratio = inv(estimate).dot(covariance)
	chi2 = (sum(np.diag(ratio)) - np.log(det(ratio)) - p) * (n - 1)
	return np.sqrt(max((chi2 - dof)/dof/(n - 1), 0))
