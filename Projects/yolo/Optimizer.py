#!/usr/bin/env python

import keras.optimizers


class Optimizer:
    def __init__(self, C):
        self.name = C["model"]["optimizer"]["name"]
        self.lr = C["model"]["optimizer"]["lr"]

    def load(self):
        return getattr(keras.optimizers, self.name)(lr=self.lr)
