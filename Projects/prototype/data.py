#!/usr/bin/env python

from pandas import read_csv
from sklearn.model_selection import train_test_split
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.preprocessing.image import ImageDataGenerator

from utils import *

class Data:
	def __init__(self, input_shape, label, split_size, batch_size):
		self.input_shape = input_shape
		self.label = label
		self.split_size = split_size
		self.batch_size = batch_size
	def load(self):
		split_size = Set(**self.split_size)
		training = read_csv(self.label["filepath"])
		training, test = train_test_split(
			training,
			test_size = split_size.test,
			random_state = 0,
			stratify = training["class"],
		) if split_size.test != 0 else (training, None)
		training, validation = train_test_split(
			training,
			test_size = split_size.validation / (1 - split_size.test),
			random_state = 0,
			stratify = training["class"],
		)
		return ImageDataGenerator(
			rotation_range = 90,
			width_shift_range = 0.2,
			height_shift_range = 0.2,
			brightness_range = (0.5, 2),
			shear_range = 30,
			zoom_range = 0.2,
			horizontal_flip = True,
		).flow_from_dataframe(
			dataframe = training,
			target_size = self.input_shape[:2],
			batch_size = self.batch_size,
		), ImageDataGenerator().flow_from_dataframe(
			dataframe = validation,
			target_size = self.input_shape[:2],
			batch_size = self.batch_size,
		), ImageDataGenerator().flow_from_dataframe(
			dataframe = test,
			target_size = self.input_shape[:2],
			batch_size = self.batch_size,
		) if split_size.test != 0 else None
