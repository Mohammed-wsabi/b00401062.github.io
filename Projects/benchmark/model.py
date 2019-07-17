#!/usr/bin/env python

import tensorflow.keras.applications
import tensorflow.keras.optimizers
from tensorflow.keras.layers import GlobalAveragePooling2D
from tensorflow.keras.layers import Dense
from tensorflow.keras.models import Model

class ModelLoader:
	def __init__(self, backbone, height, width, depth, units, loss, optimizer, lr, metrics):
		self.backbone = backbone
		self.height = height
		self.width = width
		self.depth = depth
		self.units = units
		self.loss = loss
		self.optimizer = optimizer
		self.lr = lr
		self.metrics = metrics
	def load(self):
		model = getattr(tensorflow.keras.applications, self.backbone)(
			weights = "imagenet",
			include_top = False,
			input_shape = (self.height, self.width, self.depth),
		)
		for layer in model.layers:
			layer.trainable = False
		output = GlobalAveragePooling2D()(model.output)
		output = Dense(units = self.units, activation = "softmax")(output)
		model = Model(inputs = model.input, outputs = output)
		model.compile(
			loss = self.loss,
			optimizer = getattr(tensorflow.keras.optimizers, self.optimizer)(lr = self.lr),
			metrics = self.metrics,
		)
		return model
