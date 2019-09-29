#!/usr/bin/env python

from os import environ

from prototype.config import *
from prototype.data import *
from prototype.model import *
from prototype.callback import *
from prototype.utils import *
from prototype.visualizer import *

if __name__ == "__main__":
    C = Config("config.yaml").load()
    environ["PYTHONHASHSEED"] = str(C["system"]["seed"])
    environ["CUDA_DEVICE_ORDER"] = "PCI_BUS_ID"
    environ["CUDA_VISIBLE_DEVICES"] = str(C["system"]["device"])
    data = Set(*Data(C).load())
    model = Model(C).load()
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
