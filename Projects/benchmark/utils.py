#!/usr/bin/env python

from collections import namedtuple

Set = namedtuple("Dataset", ["training", "validation", "test"])
Variable = namedtuple("Variable", ["x", "y"])
