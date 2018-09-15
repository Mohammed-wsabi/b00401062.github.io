#!/usr/bin/evn python3

from matplotlib.pyplot import *
from pandas import *

class DenoisingStats:
	def __init__(self, filepath):
		self.filepath = filepath
	def plot(self):
		stats = read_table(self.filepath)
		for i in range(16):
			plot(stats.iloc[i, [1, 2, 4]])
		ylabel("Number of sequences")
		subplots_adjust(left = .15)
		savefig("./Downloads/Researches/CGM/Figures/DenoisingStats.png")
		show()
