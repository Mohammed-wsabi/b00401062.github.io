#!/usr/bin/env python

import tensorflow.keras.callbacks
from tensorflow.keras.callbacks import TensorBoard


class Callback:
    def __init__(self, names):
        self.names = names

    def load(self):
        def callback(item):
            return getattr(tensorflow.keras.callbacks, item[0])(**item[1])

        tensorboard: TensorBoard = TensorBoard(log_dir="log")
        return list(map(callback, self.names.items())).append(tensorboard)
