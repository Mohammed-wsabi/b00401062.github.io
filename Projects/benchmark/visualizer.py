#!/usr/bin/env python

import cv2
from numpy import array
from matplotlib.pyplot import *
from tensorflow.python.keras.backend import gradients
from tensorflow.python.keras.backend import function

class Visualizer:
	def __init__(self, history):
		self.history = history
	def show(self, metrics):
		if "loss" in metrics: self.__loss()
		if "accuracy" in metrics: self.__accuracy()
	def __loss(self):
		plot(self.history.history["loss"])
		plot(self.history.history["val_loss"])
		grid()
		ylabel("Loss")
		xlabel("Epoch")
		legend(["Training", "Validation"])
		savefig("loss")
		close()
	def __accuracy(self):
		plot(self.history.history["acc"])
		plot(self.history.history["val_acc"])
		grid()
		ylabel("Accuracy")
		xlabel("Epoch")
		legend(["Training", "Validation"])
		savefig("accuracy")
		close()
	@staticmethod
	def cam(model, index, inpath, outpath = "activation"):
		i = cv2.imread(inpath)
		x = cv2.resize(i, tuple(reversed(tuple(map(int, model.input.shape[1:3])))))
		x = array([x])
		loss = model.output[0, model.predict(x).argmax()]
		o = model.get_layer(index = index).output
		g = gradients(loss, o)[0]
		o, g = function([model.input], [o, g])([x])
		o, g = o.squeeze(), g.squeeze()
		w = g.mean(axis = (0, 1))
		m = o.dot(w)
		m = cv2.resize(m, tuple(reversed(i.shape[:2])))
		imshow(i)
		imshow(m, alpha = 0.8)
		axis("off")
		savefig(outpath, bbox_inches = "tight", pad_inches = 0)
		close()
