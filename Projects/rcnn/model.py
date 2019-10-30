#!/usr/bin/env python

import keras.applications
import keras.models
from keras.engine import Layer
from keras.layers import (Conv2D, Dense, GlobalAveragePooling2D)
from typing import List
from rcnn.optimizer import Optimizer
from rcnn.utils import Utils


class Model:
    def __init__(self, C):
        self.backbone = C["model"]["backbone"]
        self.input_shape = Utils.Shape(**C["data"]["input_shape"])
        self.units = len(C["data"]["classes"])
        self.activation = C["model"]["activation"]
        self.loss = C["model"]["loss"]
        self.optimizer = Optimizer(C).load()
        self.metrics = C["model"]["metrics"]

    def outputs(self, task: str, base: Layer) -> List[Layer]:
        outputs: List[Layer] = []
        if task == "classification":
            output = GlobalAveragePooling2D()(base)
            output = Dense(units=self.units, activation=self.activation)(output)
            outputs = [output]
        elif task == "detection":
            output = Conv2D(512, (3, 3), padding="same", activation="relu", kernel_initializer="normal")(base)
            output_cls = Conv2D(9, (1, 1), activation="sigmoid", kernel_initializer="uniform")(output)
            output_reg = Conv2D(9 * 4, (1, 1), activation="linear", kernel_initializer="zero")(output)
            outputs = [output_cls, output_reg]
        return outputs

    def load(self):
        model = getattr(keras.applications, self.backbone)(
            include_top=False,
            input_shape=self.input_shape,
        )
        outputs = self.outputs("detection", model.output)
        model = keras.models.Model(inputs=model.input, outputs=outputs)
        model.compile(
            loss=self.loss,
            optimizer=self.optimizer,
            metrics=self.metrics,
        )
        return model
