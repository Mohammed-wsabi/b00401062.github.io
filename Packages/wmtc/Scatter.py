#!/usr/bin/env python3

df = read_csv("/Users/luo/Downloads/Projects/WMTC/Datasets/Demographics.csv", index_col = 0)
gfa = pickle.load("/Users/luo/Downloads/Projects/WMTC/Datasets/Indices/GFA.pkl")

for i in range(76):
	scatter(df.Age, gfa[i].mean(axis = 0), s = 4)
	ylim([0, 1])
	savefig("/Users/luo/Downloads/Projects/WMTC/Figures/Scatters/%s" % TRACTS[i].Nickname)
	clf()
