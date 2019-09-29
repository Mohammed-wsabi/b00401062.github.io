#!/usr/bin/env python

import yaml


class Config:
    def __init__(self, filepath):
        self.filepath = filepath

    def load(self):
        with open(self.filepath, "r") as fd:
            return yaml.safe_load(fd)
