#!/usr/bin/env python

from keras.preprocessing.image import ImageDataGenerator
from pandas import (DataFrame, Series, read_csv)
from sklearn.model_selection import train_test_split
from base.utils import Utils


class Data:
    def __init__(self, C):
        self.directory = Utils.Set(**C["data"]["directory"])
        self.input_shape = Utils.Shape(**C["data"]["input_shape"])
        self.filepath = C["data"]["label"]["filepath"]
        self.x_col = C["data"]["label"]["x_col"]
        self.y_col = C["data"]["label"]["y_col"]
        self.class_mode = C["data"]["label"]["class_mode"]
        self.classes = C["data"]["classes"]
        self.split_size = C["data"]["split_size"]
        self.batch_size = C["data"]["batch_size"]

    def load(self) -> Utils.Set:
        split_size = Utils.Set(**self.split_size)
        label: DataFrame = read_csv(self.filepath, header=0, dtype=str)
        counts: DataFrame = label[label.columns[1:]].apply(Series.value_counts, axis=0)
        weights: DataFrame = DataFrame(index=label.index, columns=label.columns[1:])
        for column in weights.columns:
            weights[column] = label[column].map({
                self.classes[0]: counts[column][1] * 2 / label.shape[0],
                self.classes[1]: counts[column][0] * 2 / label.shape[0]
            })
        label["weight"] = weights.mean(axis=1)
        df: Utils.Set = Utils.Set(*train_test_split(
            label,
            test_size=split_size.test,
            random_state=0,
            stratify=label[self.y_col],
        ))
        return Utils.Set(
            training=Utils.Set(
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
                    dataframe=df.training,
                    directory=self.directory.training,
                    target_size=self.input_shape[:2],
                    batch_size=self.batch_size,
                    x_col=self.x_col,
                    y_col=self.y_col,
                    weight_col="weight",
                    class_mode=self.class_mode,
                ),
                test=ImageDataGenerator(rescale=1/255).flow_from_dataframe(
                    dataframe=df.test,
                    directory=self.directory.training,
                    target_size=self.input_shape[:2],
                    batch_size=self.batch_size,
                    x_col=self.x_col,
                    y_col=self.y_col,
                    weight_col="weight",
                    class_mode=self.class_mode,
                )
            ),
            test=ImageDataGenerator(rescale=1/255).flow_from_directory(
                directory=self.directory.test,
                target_size=self.input_shape[:2],
                batch_size=self.batch_size,
                class_mode=None,
                shuffle=False,
            ) if self.directory.test is not None else None
        )
