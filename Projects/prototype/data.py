#!/usr/bin/env python

from pandas import read_csv
from sklearn.model_selection import train_test_split
from tensorflow.keras.preprocessing.image import ImageDataGenerator

from prototype.utils import *


class Data:
    def __init__(self, C):
        self.directory = C["data"]["directory"]
        self.input_shape = Shape(**C["data"]["input_shape"])
        self.filepath = C["data"]["label"]["filepath"]
        self.x_col = C["data"]["label"]["x_col"]
        self.y_col = C["data"]["label"]["y_col"]
        self.class_mode = C["data"]["label"]["class_mode"]
        self.split_size = C["data"]["split_size"]
        self.batch_size = C["fitting"]["batch_size"]

    def load(self):
        split_size = Set(**self.split_size)
        training = read_csv(self.filepath, header=0, dtype=str)
        training, test = train_test_split(
            training,
            test_size=split_size.test,
            random_state=0,
            stratify=training[self.y_col],
        ) if split_size.test != 0 else (training, None)
        training, validation = train_test_split(
            training,
            test_size=split_size.validation / (1 - split_size.test),
            random_state=0,
            stratify=training[self.y_col],
        )
        return ImageDataGenerator(
            rotation_range=90,
            width_shift_range=0.2,
            height_shift_range=0.2,
            brightness_range=(0.5, 2),
            shear_range=30,
            zoom_range=0.2,
            horizontal_flip=True,
        ).flow_from_dataframe(
            dataframe=training,
            directory=self.directory,
            target_size=self.input_shape[:2],
            batch_size=self.batch_size,
            x_col=self.x_col,
            y_col=self.y_col,
            class_mode=self.class_mode,
        ), ImageDataGenerator().flow_from_dataframe(
            dataframe=validation,
            directory=self.directory,
            target_size=self.input_shape[:2],
            batch_size=self.batch_size,
            x_col=self.x_col,
            y_col=self.y_col,
            class_mode=self.class_mode,
        ), ImageDataGenerator().flow_from_dataframe(
            dataframe=test,
            directory=self.directory,
            target_size=self.input_shape[:2],
            batch_size=self.batch_size,
            x_col=self.x_col,
            y_col=self.y_col,
            class_mode=self.class_mode,
        ) if split_size.test != 0 else None
