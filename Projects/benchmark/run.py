#!/usr/bin/env python

import os
from tensorflow.keras.callbacks import ModelCheckpoint
from tensorflow.keras.callbacks import EarlyStopping
from tensorflow.keras.callbacks import ReduceLROnPlateau

from config import *
from data import *
from model import *
from utils import *
from visualizer import *

if __name__ == "__main__":
	C = Config.load()
	os.environ["CUDA_DEVICE_ORDER"] = "PCI_BUS_ID"
	os.environ["CUDA_VISIBLE_DEVICES"] = str(C["system"]["device"])
	data = Set(*DataLoader(
		**C["model"]["input_shape"],
		label = C["data"]["label"],
		split_size = C["data"]["split_size"],
		batch_size = C["fitting"]["batch_size"],
	).load())
	model = ModelLoader(
		backbone = C["model"]["backbone"],
		**C["model"]["input_shape"],
		units = len(C["data"]["classes"]),
		loss = C["model"]["loss"],
		**C["model"]["optimization"],
		metrics = C["model"]["metrics"],
	).load()
	history = model.fit_generator(
		generator = data.training,
		epochs = C["fitting"]["epochs"],
		verbose = C["fitting"]["verbose"],
		callbacks = [
			ModelCheckpoint(**C["fitting"]["callbacks"]["model_checkpoint"]),
			EarlyStopping(**C["fitting"]["callbacks"]["early_stopping"]),
			ReduceLROnPlateau(**C["fitting"]["callbacks"]["reduce_lr_on_plateau"]),
		],
		validation_data = data.validation,
	)
	Visualizer(history).show(["loss", "accuracy"])
	if data.test is not None:
		model.evaluate_generator(generator = data.test)
