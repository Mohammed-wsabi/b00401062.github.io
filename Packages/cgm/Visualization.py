#!/usr/bin/evn python3

class Visualization:
	def __init__(self, infile, outfile):
		from pandas import read_table
		self.infile = infile
		self.outfile = outfile
		self.table = read_table(self.infile, index_col = 0, skiprows = [1])
	def plot(self):
		from matplotlib.pyplot import plot, yscale, ylabel, savefig, show
		for i in range(self.table.shape[0]):
			plot(self.table.iloc[i])
		ylabel("log(#Sequences)")
		yscale("log")
		savefig(self.outfile)
		show()
