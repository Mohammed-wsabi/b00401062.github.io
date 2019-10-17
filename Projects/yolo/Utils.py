#!/usr/bin/env python

from collections import namedtuple


class Utils:
    Set = namedtuple("Dataset", ["training", "test"])
    Variable = namedtuple("Variable", ["x", "y"])
    Shape = namedtuple("Shape", ["height", "width", "depth"])
