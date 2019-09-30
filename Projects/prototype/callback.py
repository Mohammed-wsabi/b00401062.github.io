#!/usr/bin/env python

import tensorflow.keras.callbacks
from tensorflow.keras.callbacks import TensorBoard
from typing import (Dict, Tuple)


class Callback:
    def __init__(self, C):
        self.log_dir = C["fitting"]["log"]["directory"]
        self.callbacks = C["fitting"]["callbacks"]

    def load(self):
        def callback(item: Tuple[str, Dict]):
            key: str = item[0]
            values: Dict = item[1]
            return getattr(tensorflow.keras.callbacks, key)(values)

        tensorboard: TensorBoard = TensorBoard(log_dir=self.log_dir)
        return list(map(callback, self.callbacks.items())).append(tensorboard)
