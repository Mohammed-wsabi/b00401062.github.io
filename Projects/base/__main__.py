#!/usr/bin/env python

from pandas import (DataFrame, concat)
from base.callback import Callback
from base.conf import Conf
from base.data import Data
from base.model import Model
from base.utils import Utils
from base.shower import Shower

if __name__ == "__main__":
    C = Conf("../conf/conf.yaml").load()
    data: Utils.Set = Data(C).load()
    model = Model(C).load()
    history = model.fit_generator(
        generator=data.training.training,
        epochs=C["fitting"]["epochs"],
        verbose=C["fitting"]["verbose"],
        callbacks=Callback(C).load(),
        validation_data=data.training.test,
    )
    Shower(history).show()
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