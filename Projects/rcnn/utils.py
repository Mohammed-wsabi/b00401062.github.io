#!/usr/bin/env python

from collections import namedtuple
from typing import Tuple


class Utils:
    Set = namedtuple("Dataset", ["training", "test"])
    Variable = namedtuple("Variable", ["x", "y"])
    Shape = namedtuple("Shape", ["height", "width", "depth"])
    Point = namedtuple("Point", ["x", "y"])

    class Rectangle:
        def __init__(self, x, y, w, h):
            self.x = x
            self.y = y
            self.w = w
            self.h = h
            self.lt = x
            self.rt = x + w
            self.ur = y
            self.lr = y + h

        def iou(self, that) -> float:
            w: float = max(min(self.rt, that.rt) - max(self.lt, that.lt), 0)
            h: float = max(min(self.lr, that.lr) - max(self.ur, that.ur), 0)
            return (w * h) / (self.w * self.h + that.w * that.h - w * h)

        def corners(self) -> Tuple:
            return Utils.Point(self.lt, self.ur), Utils.Point(self.rt, self.lr)
