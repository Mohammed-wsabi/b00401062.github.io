#!/usr/bin/env python

from os import (environ)
from pandas import (DataFrame, concat)

from prototype.conf import *
from prototype.data import *
from prototype.model import *
from prototype.callback import *
from prototype.utils import *
from prototype.visualizer import *

if __name__ == "__main__":
    C = Conf("conf.yaml").load()
    environ["PYTHONHASHSEED"] = str(C["system"]["seed"])
    environ["CUDA_DEVICE_ORDER"] = "PCI_BUS_ID"
    environ["CUDA_VISIBLE_DEVICES"] = str(C["system"]["device"])
    data = Set(*Data(C).load())
    model = Model(C).load()
    history = model.fit_generator(
        generator=data.training,
        epochs=C["fitting"]["epochs"],
        verbose=C["fitting"]["verbose"],
        callbacks=Callback(C).load(),
        validation_data=data.validation,
    )
    Visualizer(history).show(["loss", "accuracy"])
    if data.test is not None:
        files = data.test.filenames
        y = model.predict_generator(
            generator=data.test,
            verbose=C["evaluation"]["verbose"],
        )
        concat([
            DataFrame(files, columns=["id"]),
            DataFrame(y, columns=C["data"]["classes"]),
        ], axis=1).to_csv(C["evaluation"]["filepath"], index=False)
