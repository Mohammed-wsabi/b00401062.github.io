#!/usr/bin/evn python3

class Denoising:
	def __init__(self, filepath, tool = "plotly"):
		from pandas import read_table
		self.filepath = filepath
		self.stats = read_table(self.filepath, index_col = 0, skiprows = [1])
		self.tool = tool
		assert self.tool in ["plotly", "matplotlib"]
	def plot(self):
		if self.tool == "plotly":
			from plotly.graph_objs import Scatter, Layout, Figure
			from plotly.io import write_image
			from plotly.offline import plot
			data = [Scatter(y = self.stats.iloc[i], name = self.stats.index[i]) for i in range(self.stats.shape[0])]
			layout = Layout(
				xaxis = dict(
					ticktext = self.stats.columns,
					showticklabels = True,
					tickvals = list(range(self.stats.shape[1])),
					zeroline = False
				),
				yaxis = dict(
					type = "log",
					title = "log(#Sequences)"
				)
			)
			plot(Figure(data = data, layout = layout), filename = "./Downloads/Researches/CGM/Figures/denoising")
		else:
			from matplotlib.pyplot import plot, ylabel, subplots_adjust, savefig, show
			for i in range(16):
				plot(self.stats.iloc[i, [1, 2, 4, 5]])
			ylabel("#Sequences")
			subplots_adjust(left = .15)
			savefig("./Downloads/Researches/CGM/Figures/denoising.png")
			show()
