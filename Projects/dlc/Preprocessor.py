#!/usr/bin/env python3

from skimage.transform import resize
from skimage.util import pad
from matplotlib.pyplot import *

class Preprocessor:
	def __init__(self, RAWDIR, BOXES, S):
		self.RAWDIR = RAWDIR
		self.BOXES = BOXES
		self.S = S
	def run(self):
		for f, (x, y, w, h) in self.BOXES.iterrows():
			(x, y, w, h) = (int(x), int(y), int(w), int(h))
			m = max(w, h)
			X = imread(RAWDIR + f)
			X = X[y:(y + h), x:(x + w)]
			X = pad(X, ((int((m - h)/2),) * 2, (int((m - w)/2),) * 2, (0,) * 2), mode = "edge")
			X = resize(X, (S, S, 3))
			imsave(PREPROCESSEDDIR + f[:-3] + "jpeg", X)
