#!/usr/bin/evn python3

class Denoising:
	def __init__(self, filepath):
		from pandas import read_table
		self.filepath = filepath
		self.stats = read_table(self.filepath, index_col = 0, skiprows = [1])
	def plot(self):
		from matplotlib.pyplot import plot, yscale, ylabel, subplots_adjust, savefig, show
		for i in range(self.stats.shape[0]):
			plot(self.stats.iloc[i])
		yscale("log")
		ylabel("log(#Sequences)")
		subplots_adjust(left = .15)
		savefig("./Downloads/Researches/CGM/Figures/denoising.png")
		show()
