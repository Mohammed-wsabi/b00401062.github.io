#!/usr/bin/env python

import cv2
from glob import glob
from numpy import array, ndarray
from pandas import (DataFrame, read_csv)
from sklearn.model_selection import train_test_split
from typing import Tuple, List
from xml.etree import ElementTree
from rcnn.utils import (Utils, Rectangle, Grid)


class Data:
    def __init__(self, C):
        self.directory = Utils.Set(**C["data"]["directory"])
        self.input_shape = Utils.Shape(**C["data"]["input_shape"])
        self.filepath = C["data"]["label"]["filepath"]
        self.classes = C["data"]["classes"]
        self.split_size = C["data"]["split_size"]
        self.batch_size = C["data"]["batch_size"]

    def load(self) -> Utils.Set:
        split_size = Utils.Set(**self.split_size)
        df: Utils.Set = Utils.Set(*train_test_split(
            read_csv(self.filepath, header=0),
            test_size=split_size.test,
            random_state=0,
        ))
        return Utils.Set(
            training=Utils.Set(
                training=Generator.flow_from_dataframe(
                    dataframe=df.training,
                    directory=self.directory.training,
                    target_size=self.input_shape[:2],
                    batch_size=self.batch_size,
                ),
                test=Generator.flow_from_dataframe(
                    dataframe=df.test,
                    directory=self.directory.training,
                    target_size=self.input_shape[:2],
                    batch_size=self.batch_size,
                )
            ),
            test=Generator.flow_from_directory(
                directory=self.directory.test,
                target_size=self.input_shape[:2],
                batch_size=self.batch_size,
            ) if self.directory.test is not None else None
        )


class Generator:
    def __init__(self, augmentation=None):
        self.augmentation = augmentation

    @staticmethod
    def boxes(tree: ElementTree) -> List[Rectangle]:
        root = tree.getroot()
        shape = Utils.Shape(
            int(root.find("size").find("height").text),
            int(root.find("size").find("width").text),
            int(root.find("size").find("depth").text),
        )
        return [Rectangle(
            int(box.find("xmin").text) / shape.width,
            int(box.find("ymin").text) / shape.height,
            int(box.find("xmax").text) / shape.width,
            int(box.find("ymax").text) / shape.height,
        ) for _ in root.iter("object") for box in _.findall("bndbox")]

    @staticmethod
    def flow_from_dataframe(
        dataframe: DataFrame,
        directory: Tuple[str, str],
        target_size: Tuple[int, int],
        batch_size: int
    ) -> Tuple[ndarray, ndarray]:
        batch_idx: int = 0
        epoch_size: int = (dataframe.shape[0] - 1) // batch_size + 1
        while True:
            batch = Utils.Variable([], [])
            idx = (
                batch_idx * batch_size,
                min((batch_idx + 1) * batch_size, dataframe.shape[0])
            )
            for filename in dataframe.filename[idx[0]:idx[1]]:
                paths = (
                    directory[0] + filename + ".jpg",
                    directory[1] + filename + ".xml"
                )
                batch.x.append(cv2.resize(cv2.imread(paths[0]), target_size))
                boxes = Generator.boxes(ElementTree.parse(paths[1]))
                batch.y.append(Grid.labels(boxes))
            batch_idx = (batch_idx + 1) % epoch_size
            yield array(batch.x), array(batch.y)

    @staticmethod
    def flow_from_directory(
        directory: str,
        target_size: Tuple[int, int],
        batch_size: int
    ) -> Tuple[ndarray, ndarray]:
        paths: List[str] = glob(directory + "*.jpg")
        batch_idx: int = 0
        epoch_size: int = (len(paths) - 1) // batch_size + 1
        while True:
            batch = Utils.Variable([], None)
            idx = (
                batch_idx * batch_size,
                min((batch_idx + 1) * batch_size, len(paths))
            )
            for path in paths[idx[0]:idx[1]]:
                batch.x.append(cv2.resize(cv2.imread(path), target_size))
            batch_idx = (batch_idx + 1) % epoch_size
            yield array(batch.x), None
