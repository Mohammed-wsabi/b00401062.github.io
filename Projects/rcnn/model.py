#!/usr/bin/env python

import keras.applications
import keras.models
from keras import backend as K
from keras.engine import Layer
from keras.layers import Conv2D
from numpy import ndarray
from tensorflow import float32
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
            strides=(3, 3),
            padding="same",
            activation="relu",
            kernel_initializer="normal"
        )(base)
        output_cls = Conv2D(
            kernel_size=num_anchors,
            strides=(1, 1),
            activation="sigmoid",
            kernel_initializer="uniform"
        )(output)
        output_reg = Conv2D(
            kernel_size=num_anchors * 4,
            strides=(1, 1),
            activation="linear",
            kernel_initializer="zero"
        )(output)
        return [output_cls, output_reg]

    @staticmethod
    def losses() -> List:
        epsilon = 1e-4

        def loss_cls(y_true, y_pred):
            y_box = y_true[0]
            y_obj = y_true[1]
            return K.sum(
                y_box * K.binary_crossentropy(y_pred, y_obj)
            ) / K.sum(epsilon + y_box)

        def loss_reg(y_true, y_pred):
            y_obj: ndarray = y_true[0]
            y_reg: ndarray = y_true[1]
            r: ndarray = y_pred - y_reg
            r_abs = K.abs(r)
            r_bool = K.cast(K.less_equal(r_abs, 1.0), float32)
            return K.sum(y_obj * (
                r_bool * (0.5 * r * r) + (1 - r_bool) * (r_abs - 0.5)
            )) / K.sum(epsilon + y_obj)

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
