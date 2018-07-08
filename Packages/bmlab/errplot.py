from __future__ import division
from numpy import *
from matplotlib.pyplot import *
from pandas import read_csv

df = read_csv("./Datasets/Cluster_Stats/strength_table.csv", header = 0, index_col = 0)

means = df[["MFCAREST", "MFCAEPSO", "MFCASELF", "MFCAVERB"]].mean()
stderrs = sqrt(df[["MFCAREST", "MFCAEPSO", "MFCASELF", "MFCAVERB"]].var() / 60)
bar(range(4), means, width = .5, yerr = stderrs, ecolor = "black", error_kw = { "elinewidth": 2 })
xticks(arange(4) + .25, ("Rest", "Episodic", "Self", "Verbal"))
xlim(-.5, 4)
xlabel("Task")
ylabel("Mean")
show()

means = df.iloc[46:60][["MFCAREST1", "MFCAREST2"]].mean()
stderrs = sqrt(df.iloc[46:60][["MFCAREST1", "MFCAREST2"]].var() / 14)
bar(range(2), means, width = .5, yerr = stderrs, ecolor = "black", error_kw = { "elinewidth": 2 })
xticks(arange(2) + .25, ("Rest 1", "Rest 2"))
xlim(-.5, 2)
xlabel("Task")
ylabel("Mean")
show()
