#!/usr/bin/env python

import yaml

class Config:
	@staticmethod
	def load():
		with open("config.yaml", "r") as fd:
			return yaml.safe_load(fd)
