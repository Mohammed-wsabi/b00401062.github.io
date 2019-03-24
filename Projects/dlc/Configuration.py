#!/usr/bin/env python3

from collections import namedtuple

## Named Tuples
Set = namedtuple("Set", ["training", "test"])

## Directories
DATASETDIR = "./"
RAWDIR = DATASETDIR + "Raw/"
PREPROCESSEDDIR = DATASETDIR + "Preprocessed/"

## Integers
S = 224
