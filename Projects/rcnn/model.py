#!/usr/bin/env python

import keras.applications
import keras.models
from keras.layers import GlobalAveragePooling2D
from keras.layers import Dense
from yolo.optimizer import Optimizer
from yolo.utils import Utils


class Model:
    def __init__(self, C):
        self.name = C["model"]["name"]
        self.input_shape = Utils.Shape(**C["data"]["input_shape"])
        self.units = len(C["data"]["classes"])
        self.activation = C["model"]["activation"]
        self.loss = C["model"]["loss"]
        self.optimizer = Optimizer(C).load()
        self.metrics = C["model"]["metrics"]

    def load(self):
        model = getattr(keras.applications, self.name)(
            weights="imagenet",
            include_top=False,
            input_shape=self.input_shape,
        )
        for layer in model.layers:
            layer.trainable = False
        output = GlobalAveragePooling2D()(model.output)
        output = Dense(units=self.units, activation="softmax")(output)
        model = keras.models.Model(inputs=model.input, outputs=output)
        model.compile(
            loss=self.loss,
            optimizer=self.optimizer,
            metrics=self.metrics,
        )
        return model
