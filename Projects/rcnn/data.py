#!/usr/bin/env python

import cv2
from glob import glob
from numpy import array, ndarray
from os import listdir
from sklearn.model_selection import train_test_split
from typing import Tuple, List
from xml.etree import ElementTree
from rcnn.utils import (Utils, Rectangle, Grid)


class Data:
    def __init__(self, C):
        self.image_directory = Utils.Set(**C["data"]["image_directory"])
        self.input_shape = Utils.Shape(**C["data"]["input_shape"])
        self.label_directory = C["data"]["label_directory"]
        self.classes = C["data"]["classes"]
        self.batch_size = C["data"]["batch_size"]

    def load(self) -> Utils.Set:
        instances: Utils.Set = Utils.Set(*train_test_split(
            list(map(lambda x: x[:-4], listdir(self.label_directory))),
            random_state=0,
        ))
        return Utils.Set(
            training=Utils.Set(
                training=Generator.flow_from_dataframe(
                    instances=instances.training,
                    image_directory=self.image_directory.training,
                    label_directory=self.label_directory,
                    target_size=self.input_shape[:2],
                    batch_size=self.batch_size,
                ),
                test=Generator.flow_from_dataframe(
                    instances=instances.test,
                    image_directory=self.image_directory.training,
                    label_directory=self.label_directory,
                    target_size=self.input_shape[:2],
                    batch_size=self.batch_size,
                )
            ),
            test=Generator.flow_from_directory(
                directory=self.image_directory.test,
                target_size=self.input_shape[:2],
                batch_size=self.batch_size,
            ) if self.image_directory.test is not None else None
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
        instances: List[str],
        image_directory: str,
        label_directory: str,
        target_size: Tuple[int, int],
        batch_size: int
    ) -> Tuple[ndarray, ndarray]:
        batch_idx: int = 0
        epoch_size: int = (len(instances) - 1) // batch_size + 1
        while True:
            batch = Utils.Variable([], [])
            idx = (
                batch_idx * batch_size,
                min((batch_idx + 1) * batch_size, len(instances))
            )
            for instance in instances[idx[0]:idx[1]]:
                paths = (
                    f"{image_directory}/{instance}.jpg",
                    f"{label_directory}/{instance}.xml"
                )
                batch.x.append(cv2.resize(cv2.imread(paths[0]), target_size))
                boxes = Generator.boxes(ElementTree.parse(paths[1]))
                batch.y.append(Grid.labels(boxes))
            batch_idx = (batch_idx + 1) % epoch_size
            yield batch.x, batch.y

    @staticmethod
    def flow_from_directory(
        directory: str,
        target_size: Tuple[int, int],
        batch_size: int
    ) -> Tuple[ndarray, ndarray]:
        paths: List[str] = glob(f"{directory}/*.jpg")
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
