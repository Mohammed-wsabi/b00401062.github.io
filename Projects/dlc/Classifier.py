#!/usr/bin/env python3

from keras.preprocessing.image import ImageDataGenerator
from keras.applications.vgg16 import VGG16
from keras.layers import Dense, Flatten
from keras.models import Model

class Classifier:
	def __init__(self, LABELS, PREPROCESSEDDIR):
		self.LABELS = LABELS
		self.PREPROCESSEDDIR = PREPROCESSEDDIR
	def run(self):
		## Augmentation
		generator = ImageDataGenerator(
			rotation_range= 90,
			zoom_range = 0.2,
			brightness_range = (0.5, 2),
			width_shift_range = 0.2,
			height_shift_range = 0.2,
			horizontal_flip = True,
			validation_split = 0.2
		)
		## Classifier
		model = VGG16(weights = "imagenet", include_top = False, input_shape = (S, S, 3))
		for layer in model.layers:
			layer.trainable = False
		model = Model(inputs = model.input, outputs = Dense(y_train.shape[1], activation = "softmax")(Flatten()(model.output)))
		model.compile(loss = "categorical_crossentropy", optimizer = "adam", metrics = ["accuracy"])
		model.summary()
		## Fitting
		model.fit_generator(
			generator = generator.flow_from_dataframe(
				dataframe = self.LABELS,
				directory = self.PREPROCESSEDDIR,
				target_size = (S, S),
				x_col = "ImageID",
				y_col = "Label",
				subset = "training"
			),
			validation_data = generator.flow_from_dataframe(
				dataframe = self.LABELS,
				directory = self.PREPROCESSEDDIR,
				target_size = (S, S),
				x_col = "ImageID",
				y_col = "Label",
				subset = "validation"
			),
			epochs = 1,
			verbose = 1
		)
