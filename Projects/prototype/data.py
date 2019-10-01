#!/usr/bin/env python

from pandas import (read_csv, DataFrame)
from sklearn.model_selection import train_test_split
from tensorflow.keras.preprocessing.image import ImageDataGenerator

from prototype.utils import *


class Data:
    def __init__(self, C):
        self.directory = Set(**C["data"]["directory"])
        self.input_shape = Shape(**C["data"]["input_shape"])
        self.filepath = C["data"]["label"]["filepath"]
        self.x_col = C["data"]["label"]["x_col"]
        self.y_col = C["data"]["label"]["y_col"]
        self.class_mode = C["data"]["label"]["class_mode"]
        self.split_size = C["data"]["split_size"]
        self.batch_size = C["data"]["batch_size"]

    def load(self):
        split_size = Set(**self.split_size)
        label: DataFrame = read_csv(self.filepath, header=0, dtype=str)
        label = Set(*train_test_split(
            label,
            test_size=split_size.test,
            random_state=0,
            stratify=label[self.y_col],
        ))
        return Set(
            training=Set(
                training=ImageDataGenerator(
                    rotation_range=180,
                    width_shift_range=0.1,
                    height_shift_range=0.1,
                    brightness_range=(0.9, 1.1),
                    shear_range=10,
                    zoom_range=0.1,
                    horizontal_flip=True,
                    vertical_flip=True,
                    rescale=1/255,
                ).flow_from_dataframe(
                    dataframe=label.training,
                    directory=self.directory.training,
                    target_size=self.input_shape[:2],
                    batch_size=self.batch_size,
                    x_col=self.x_col,
                    y_col=self.y_col,
                    class_mode=self.class_mode,
                ),
                test=ImageDataGenerator(rescale=1/255).flow_from_dataframe(
                    dataframe=label.test,
                    directory=self.directory.training,
                    target_size=self.input_shape[:2],
                    batch_size=self.batch_size,
                    x_col=self.x_col,
                    y_col=self.y_col,
                    class_mode=self.class_mode,
                )
            ),
            test=ImageDataGenerator(rescale=1/255).flow_from_directory(
                directory=self.directory.test,
                target_size=self.input_shape[:2],
                batch_size=self.batch_size,
                class_mode=None,
            ) if self.directory.test is not None else None
        )
