# You Only Look Once (YOLO)

## Configuration

- [Conf.yaml](Conf.yaml): Configuration file which lists available hyperparameters.

## Script Files

- [Conf.py](Conf.py): Loading configuration from [Conf.yaml](Conf.yaml).
- [Utils.py](Utils.py): Common functions used across the module.
- [Data.py](Data.py): Loading preprocessed and augmented images.
- [Model.py](Model.py): Loading compiled model which is ready for fitting.
- [Optimizer.py](Optimizer.py): Loading optimizer and passing to model.
- [Callback.py](Callback.py): Loading callbacks and passing to fitting function.
- [Visualizer.py](Visualizer.py): Visualizing training process and inference.

## Execution

```bash
export PYTHONHASHSEED=0
export PYTHONPATH=$HOME/Documents/Projects/
python -B -m yolo
```
