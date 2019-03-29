#!/usr/bin/env python3

from skimage.transform import resize
from skimage.util import pad
from matplotlib.pyplot import *

class Preprocesser:
	def __init__(self, RAWDIR, PREPROCESSEDDIR, BOXES, S):
		self.RAWDIR = RAWDIR
		self.PREPROCESSEDDIR = PREPROCESSEDDIR
		self.BOXES = BOXES
		self.S = S
	def run(self):
		for i, (f, minr, minc, maxr, maxc) in self.BOXES.iterrows():
			if f.split("_")[1] is "IMG": continue
			h = maxr - minr
			w = maxc - minc
			m = max(w, h)
			X = imread(self.RAWDIR + f)[minr:maxr, minc:maxc]
			X = pad(X, ((int((m - h)/2),) * 2, (int((m - w)/2),) * 2, (0,) * 2), mode = "constant", constant_values = 0)
			X = resize(X, (self.S, self.S, 3))
			imsave(self.PREPROCESSEDDIR + f[:-4], X)
