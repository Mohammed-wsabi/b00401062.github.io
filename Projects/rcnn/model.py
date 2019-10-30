#!/usr/bin/env python

import keras.applications
import keras.models
from keras.engine import Layer
from keras.layers import Conv2D
from typing import List
from rcnn.optimizer import Optimizer
from rcnn.utils import (Utils, Grid)


class Model:
    def __init__(self, C):
        self.backbone = C["model"]["backbone"]
        self.input_shape = Utils.Shape(**C["data"]["input_shape"])
        self.units = len(C["data"]["classes"])
        self.optimizer = Optimizer(C).load()
        self.metrics = C["model"]["metrics"]

    @staticmethod
    def outputs(base: Layer) -> List[Layer]:
        num_anchors = len(Grid.ratios) * len(Grid.scales)
        output = Conv2D(
            kernel_size=512,
            strides=(3, 3), padding="same", activation="relu", kernel_initializer="normal")(base)
        output_cls = Conv2D(num_anchors, (1, 1), activation="sigmoid", kernel_initializer="uniform")(output)
        output_reg = Conv2D(num_anchors * 4, (1, 1), activation="linear", kernel_initializer="zero")(output)
        return [output_cls, output_reg]

    @staticmethod
    def losses() -> List:
        num_anchors = len(Grid.ratios) * len(Grid.scales)

        def loss_cls(y_true, y_pred):
            pass

        def loss_reg(y_true, y_pred):
            pass

        return [loss_cls, loss_reg]

    def load(self):
        model = getattr(keras.applications, self.backbone)(
            include_top=False,
            input_shape=self.input_shape,
        )
        outputs = Model.outputs(model.output)
        model = keras.models.Model(inputs=model.input, outputs=outputs)
        model.compile(
            loss=self.losses(),
            optimizer=self.optimizer,
            metrics=self.metrics,
        )
        return model
