import os
import pickle
import re
from numpy import *
from pandas import *
from scipy.io import loadmat
from sklearn.model_selection import train_test_split
from Constants import *

class Populator():
	def __init__(self):
		self.DATASETS = self.__datasets()
		self.BESTS = Model(
			LinearDiscriminantAnalysis = Best(Index = 0, Parameter = 0.0, Mean = 0.625, SD = 0.11692679333668567),
			QuadraticDiscriminantAnalysis = Best(Index = 0, Parameter = 0.0, Mean = 0.640625, SD = 0.13531646934131855),
			LogisticRegression = Best(Index = 0, Parameter = 0.0009765625, Mean = 0.625, SD = 0.11692679333668567),
			SVC = Best(Index = 73, Parameter = 0.15389305166811462, Mean = 0.59375, SD = 0.11267347735824966))
	def populate(self):
		return (self.DATASETS, self.BESTS)
	def __datasets(self):
		if os.path.isfile("./Downloads/Researches/ABMRI/Datasets/Datasets.pkl"):
			with open("./Downloads/Researches/ABMRI/Datasets/Datasets.pkl", "rb") as fin:
				return pickle.load(fin)
		datasets = {
			"Training": {
				"NR": None,
				"R": DataFrame(columns = COLUMNS)},
			"Validation": {
				"NR": read_excel("./Downloads/Researches/ABMRI/Datasets/Subjects/Subjects.xlsx", sheet_name = "Chronic", header = 0, index_col = 0)[COLUMNS + ["Category"]].groupby("Category").get_group("NR").drop(columns = "Category"),
				"R": read_excel("./Downloads/Researches/ABMRI/Datasets/Subjects/Subjects.xlsx", sheet_name = "Chronic", header = 0, index_col = 0)[COLUMNS + ["Category"]].groupby("Category").get_group("R").drop(columns = "Category")},
			"Test": {
				"NR": read_excel("./Downloads/Researches/ABMRI/Datasets/Subjects/Subjects.xlsx", sheet_name = "Early", header = 0, index_col = 0)[COLUMNS + ["Category"]].groupby("Category").get_group("NR").drop(columns = "Category"),
				"R": read_excel("./Downloads/Researches/ABMRI/Datasets/Subjects/Subjects.xlsx", sheet_name = "Early", header = 0, index_col = 0)[COLUMNS + ["Category"]].groupby("Category").get_group("R").drop(columns = "Category")}}
		datasets["Training"]["NR"], datasets["Validation"]["NR"] = train_test_split(datasets["Validation"]["NR"], train_size = 32, random_state = 0)
		cumulative = Category(
			NR = datasets["Training"]["NR"][["Duration", "Dose"]].prod(axis = 1).sort_values(),
			R = datasets["Validation"]["R"][["Duration", "Dose"]].prod(axis = 1).sort_values())
		for dose in cumulative.NR:
			unicode = cumulative.R.index[abs(cumulative.R - dose).values.argmin()]
			datasets["Training"]["R"].loc[unicode, :] = datasets["Validation"]["R"].loc[unicode, :]
			datasets["Validation"]["R"].drop(index = unicode, inplace = True)
			cumulative.R.drop(unicode, inplace = True)
		datasets["Training"]["R"] = datasets["Training"]["R"].infer_objects()
		for set in Set._fields:
			for category in Category._fields:
				self.__loadmat(datasets[set], category)
		datasets = Set(
			Training = Category(**datasets["Training"]),
			Validation = Category(**datasets["Validation"]),
			Test = Category(**datasets["Test"]))
		with open("./Downloads/Researches/ABMRI/Datasets/Datasets.pkl", "wb") as fout:
			pickle.dump(datasets, fout, pickle.HIGHEST_PROTOCOL)
		return datasets
	def __loadmat(self, dataset, category):
		## Tailor dataset
		dataset[category].Sex = dataset[category].Sex.astype("category")
		dataset[category] = dataset[category].reindex(columns = COLUMNS + FEATURES)
		## Check folder integrity
		assert all(isin(dataset[category].File, os.listdir("/Volumes/Transcend/ABMRI/")))
		## Load MAP-MRI indices
		for subject in dataset[category].index:
			print "== Session %s ==" % subject
			dname = "/Volumes/Transcend/ABMRI/%s/lddmm-dsi/" % dataset[category].File[subject]
			for index in INDICES:
				matched = filter(re.compile(".*\\.%s_Array\\.mat$" % index.lower()).match, os.listdir(dname))
				assert len(matched) == 1
				features = ["_".join([index, tract.Nickname, str(step)]) for tract in TRACTS for step in range(100)]
				dataset[category].loc[subject, features] = loadmat(dname + matched[0])["array"].reshape(7600)
