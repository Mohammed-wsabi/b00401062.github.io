#!/usr/bin/env python

import cv2
import tensorflow
from numpy import array
from numpy import maximum
from tensorflow.python.keras.backend import gradients
from tensorflow.python.keras.backend import function
from matplotlib.pyplot import *


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
    def cam(model, index: int, inpath: str, outpath="activation.png", alpha=0.5):
        raw = cv2.imread(inpath)
        resized = cv2.resize(raw, tuple(model.input.shape[1:3].as_list()))
        resized = array([resized])
        loss = model.output[0, model.predict(resized).argmax()]
        feature = model.get_layer(index=index).output
        with tensorflow.compat.v1.Session() as __:
            gradient = gradients(loss, feature)[0]
        feature, gradient = function([model.input], [feature, gradient])([resized])
        feature, gradient = feature.squeeze(), gradient.squeeze()
        weight = gradient.mean(axis=(0, 1))
        activation = feature.dot(weight)
        activation = cv2.resize(activation, tuple(reversed(raw.shape[:2])))
        activation = maximum(activation, 0)
        activation = activation / activation.max() * 255
        activation = activation.astype("uint8")
        activation = cv2.applyColorMap(activation, cv2.COLORMAP_JET)
        cv2.imwrite(outpath, cv2.addWeighted(raw, alpha, activation, 1 - alpha, 0))
        cv2.imshow(outpath, cv2.addWeighted(raw, alpha, activation, 1 - alpha, 0))
        cv2.waitKey(0)
        cv2.destroyAllWindows()
