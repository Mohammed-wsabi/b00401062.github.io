#!/usr/bin/env python

import tensorflow.keras.optimizers


class Optimizer:
    def __init__(self, name, lr):
        self.name = name
        self.lr = lr

    def load(self):
        return getattr(tensorflow.keras.optimizers, self.name)(lr=self.lr)
