#!/usr/bin/env python

from collections import namedtuple
from itertools import product
from math import sqrt
from numpy import (array, concatenate, repeat, zeros)
from typing import (List, Tuple)


class Utils:
    Set = namedtuple("Dataset", ["training", "test"])
    Variable = namedtuple("Variable", ["x", "y"])
    Shape = namedtuple("Shape", ["height", "width", "depth"])
    Point = namedtuple("Point", ["x", "y"])


class Rectangle:
    def __init__(self, lt: float, ur: float, rt: float, lr: float):
        self.lt = lt
        self.ur = ur
        self.rt = rt
        self.lr = lr
        self.cx = (lt + rt) / 2
        self.cy = (ur + lr) / 2
        self.w = rt - lt
        self.h = lr - ur

    def iou(self, that) -> float:
        w: float = max(min(self.rt, that.rt) - max(self.lt, that.lt), 0)
        h: float = max(min(self.lr, that.lr) - max(self.ur, that.ur), 0)
        return (w * h) / (self.w * self.h + that.w * that.h - w * h)

    def corners(self) -> Tuple[Utils.Point, Utils.Point]:
        lu = Utils.Point(self.lt, self.ur)
        rl = Utils.Point(self.rt, self.lr)
        return lu, rl

    def minus(self, anchor):
        return Rectangle(
            self.lt - anchor.cx,
            self.ur - anchor.cy,
            self.rt - anchor.cx,
            self.lr - anchor.cy
        )

    def __str__(self):
        return f"{self.corners()}"


class Grid:
    shape = (15, 15)
    strides = (1/16, 1/16)
    ratios = (1/2, 1, 2)
    scales = (0.16, 0.32, 0.64)
    iou = (1/4, 1/2)

    @staticmethod
    def labels(boxes: List[Rectangle]):
        num_anchors = len(Grid.ratios) * len(Grid.scales)
        labels_box = zeros((*Grid.shape, len(Grid.ratios), len(Grid.scales)))
        labels_obj = zeros((*Grid.shape, len(Grid.ratios), len(Grid.scales)))
        labels_reg = zeros((*Grid.shape, len(Grid.ratios), len(Grid.scales), 4))
        for (x, y, i, j) in product(
            range(Grid.shape[1]),
            range(Grid.shape[0]),
            range(len(Grid.ratios)),
            range(len(Grid.scales))
        ):
            cx = (x + 1) * Grid.strides[1]
            cy = (y + 1) * Grid.strides[0]
            ratio = Grid.ratios[i]
            scale = Grid.scales[j]
            w = scale * sqrt(ratio)
            h = scale / sqrt(ratio)
            if not (w/2 < cx < 1 - w/2 and h/2 < cy < 1 - h/2):
                continue
            anchor = Rectangle(
                lt=(cx - w/2),
                ur=(cy - h/2),
                rt=(cx + w/2),
                lr=(cy + h/2)
            )
            for box_idx, box in enumerate(boxes):
                iou = box.iou(anchor)
                shift = box.minus(anchor)
                if iou < Grid.iou[0]:
                    labels_box[y, x, i, j] = 1
                    labels_obj[y, x, i, j] = 0
                elif Grid.iou[0] < iou < Grid.iou[1]:
                    labels_box[y, x, i, j] = 0
                    labels_obj[y, x, i, j] = 0
                else:
                    labels_box[y, x, i, j] = 1
                    labels_obj[y, x, i, j] = 1
                    labels_reg[y, x, i, j] = array(shift.corners()).flatten()
        labels_cls = concatenate([
            labels_box.reshape((*Grid.shape, num_anchors)),
            labels_obj.reshape((*Grid.shape, num_anchors)),
        ], axis=2)
        labels_reg = concatenate([
            repeat(labels_obj.reshape((*Grid.shape, num_anchors)), repeats=4, axis=2),
            labels_reg.reshape((*Grid.shape, num_anchors * 4)),
        ], axis=2)
        return labels_cls, labels_reg
