#!/usr/bin/env python

from yaml import safe_load


class Conf:
    def __init__(self, filepath):
        self.filepath = filepath

    def load(self):
        with open(self.filepath, "r") as fd:
            return safe_load(fd)
