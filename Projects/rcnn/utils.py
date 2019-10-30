#!/usr/bin/env python

from collections import namedtuple
from itertools import product
from math import sqrt
from numpy import zeros
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
    def __init__(self):
        self.shape = (24, 24)
        self.stride = (1/25, 1/25)
        self.ratios = (1/2, 1, 2)
        self.sizes = (0.16, 0.32, 0.64)
        self.iou = (0.3, 0.7)

    def labels(self, boxes: List[Rectangle]):
        num_anchors = len(self.ratios) * len(self.sizes)
        labels_cls = zeros((*self.shape, len(self.ratios), len(self.sizes), 2))
        labels_reg = zeros((*self.shape, len(self.ratios), len(self.sizes), 4))
        for (x, y, i, j) in product(
                range(self.shape[1]),
                range(self.shape[0]),
                range(len(self.ratios)),
                range(len(self.sizes))
        ):
            cx = (x + 1) * self.stride[1]
            cy = (y + 1) * self.stride[0]
            ratio = self.ratios[i]
            size = self.sizes[j]
            w = size * sqrt(ratio)
            h = size / sqrt(ratio)
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
                if iou < self.iou[0]:
                    labels_cls[y, x, i, j] = (1, 0)
                elif self.iou[0] < iou < self.iou[1]:
                    labels_cls[y, x, i, j] = (0, 0)
                else:
                    labels_cls[y, x, i, j] = (1, 1)
                    lu, rl = shift.corners()
                    labels_reg[y, x, i, j] = (*lu, *rl)
        labels_cls = labels_cls.reshape((*self.shape, num_anchors * 2))
        labels_reg = labels_reg.reshape((*self.shape, num_anchors * 4))
        return labels_cls, labels_reg
