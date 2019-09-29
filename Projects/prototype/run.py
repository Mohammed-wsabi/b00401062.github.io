#!/usr/bin/env python

import os

from config import *
from data import *
from model import *
from optimizer import *
from callback import *
from utils import *
from visualizer import *

if __name__ == "__main__":
    C = Config("config.yaml").load()
    os.environ["PYTHONHASHSEED"] = str(C["system"]["seed"])
    os.environ["CUDA_DEVICE_ORDER"] = "PCI_BUS_ID"
    os.environ["CUDA_VISIBLE_DEVICES"] = str(C["system"]["device"])
    data = Set(*Data(
        input_shape=Shape(**C["data"]["input_shape"]),
        label=C["data"]["label"],
        split_size=C["data"]["split_size"],
        batch_size=C["fitting"]["batch_size"],
    ).load())
    model = Model(
        name=C["model"]["name"],
        input_shape=Shape(**C["data"]["input_shape"]),
        units=len(C["data"]["classes"]),
        loss=C["model"]["loss"],
        optimizer=Optimizer(**C["model"]["optimizer"]).load(),
        metrics=C["model"]["metrics"],
    ).load()
    history = model.fit_generator(
        generator=data.training,
        epochs=C["fitting"]["epochs"],
        verbose=C["fitting"]["verbose"],
        callbacks=Callback(C["fitting"]["callback"]).load(),
        validation_data=data.validation,
    )
    Visualizer(history).show(["loss", "accuracy"])
    if data.test is not None:
        model.evaluate_generator(generator=data.test)
