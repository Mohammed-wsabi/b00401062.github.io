from numpy import *
from numpy.linalg import det
from numpy.linalg import inv

def rmsea(analysis, predictor):
	n, p = predictor.shape
	m = analysis.n_components
	covariance = predictor.cov()
	loadings = analysis.components_
	dof = ((p - m) ** 2 - p - m) / 2
	estimate = loadings.T.dot(loadings) + diag(analysis.noise_variance_)
	fill_diagonal(estimate, diag(covariance))
	ratio = inv(estimate).dot(covariance)
	chi2 = (sum(diag(ratio)) - log(det(ratio)) - p) * (n - 1)
	return sqrt(max((chi2 - dof)/dof/(n - 1), 0))
