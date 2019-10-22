#!/usr/bin/env python

from pandas import (DataFrame, concat)
from yolo.Callback import Callback
from yolo.Conf import Conf
from yolo.Data import Data
from yolo.Model import Model
from yolo.Utils import Utils
from yolo.Shower import Shower

if __name__ == "__main__":
    C = Conf("../conf/Conf.yaml").load()
    data = Utils.Set(*Data(C).load())
    model = Model(C).load()
    history = model.fit_generator(
        generator=data.training,
        epochs=C["fitting"]["epochs"],
        verbose=C["fitting"]["verbose"],
        callbacks=Callback(C).load(),
        validation_data=data.validation,
    )
    Shower(history).show(["loss", "accuracy"])
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
