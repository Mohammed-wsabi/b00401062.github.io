#!/usr/bin/env python

import tensorflow.keras.applications
import tensorflow.keras.models
from tensorflow.keras.layers import GlobalAveragePooling2D
from tensorflow.keras.layers import Dense

class Model:
	def __init__(self, name, input_shape, units, loss, optimizer, metrics):
		self.name = name
		self.input_shape = input_shape
		self.units = units
		self.loss = loss
		self.optimizer = optimizer
		self.metrics = metrics
	def load(self):
		model = getattr(tensorflow.keras.applications, self.name)(
			weights = "imagenet",
			include_top = False,
			input_shape = self.input_shape,
		)
		for layer in model.layers:
			layer.trainable = False
		output = GlobalAveragePooling2D()(model.output)
		output = Dense(units = self.units, activation = "softmax")(output)
		model = tensorflow.keras.models.Model(inputs = model.input, outputs = output)
		model.compile(
			loss = self.loss,
			optimizer = self.optimizer,
			metrics = self.metrics,
		)
		return model
