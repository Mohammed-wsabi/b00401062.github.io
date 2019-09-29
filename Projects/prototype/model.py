#!/usr/bin/env python

import tensorflow.keras.applications
import tensorflow.keras.models.Model
from tensorflow.keras.layers import GlobalAveragePooling2D
from tensorflow.keras.layers import Dense

from prototype.optimizer import *
from prototype.utils import *


class Model:
    def __init__(self, C):
        self.name = C["model"]["name"]
        self.input_shape = Shape(**C["data"]["input_shape"])
        self.units = len(C["data"]["classes"])
        self.activation = C["model"]["activation"]
        self.loss = C["model"]["loss"]
        self.optimizer = Optimizer(**C["model"]["optimizer"]).load()
        self.metrics = C["model"]["metrics"]

    def load(self):
        model = getattr(tensorflow.keras.applications, self.name)(
            weights="imagenet",
            include_top=False,
            input_shape=self.input_shape,
        )
        for layer in model.layers:
            layer.trainable = False
        output = GlobalAveragePooling2D()(model.output)
        output = Dense(units=self.units, activation="softmax")(output)
        model = tensorflow.keras.Model(inputs=model.input, outputs=output)
        model.compile(
            loss=self.loss,
            optimizer=self.optimizer,
            metrics=self.metrics,
        )
        return model
