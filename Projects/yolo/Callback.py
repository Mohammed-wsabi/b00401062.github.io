#!/usr/bin/env python

import tensorflow.keras.callbacks
from typing import (Dict, Tuple)


class Callback:
    def __init__(self, C):
        self.callbacks = C["fitting"]["callbacks"]

    def load(self):
        def callback(item: Tuple[str, Dict]):
            key: str = item[0]
            values: Dict = item[1]
            return getattr(tensorflow.keras.callbacks, key)(**values)

        return list(map(callback, self.callbacks.items()))
