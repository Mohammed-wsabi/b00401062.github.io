#!/usr/bin/evn python3

from matplotlib.pyplot import *
from pandas import *

class Denoising:
	def __init__(self, filepath):
		self.filepath = filepath
	def plot(self):
		stats = read_table(self.filepath, skiprows = [1])
		for i in range(16):
			plot(stats.iloc[i, [1, 2, 4, 5]])
		ylabel("Number of sequences")
		subplots_adjust(left = .15)
		savefig("./Downloads/Researches/CGM/Figures/denoising.png")
		show()
