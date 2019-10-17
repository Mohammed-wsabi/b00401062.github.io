# You Only Look Once (YOLO)

## Configuration

- [conf.yaml](conf.yaml): Configuration file which lists available hyperparameters.

## Script Files

- [conf.py](conf.py): Loading configuration from [conf.yaml](conf.yaml).
- [utils.py](utils.py): Common functions used across the module.
- [data.py](data.py): Loading preprocessed and augmented images.
- [model.py](model.py): Loading compiled model which is ready for fitting.
- [optimizer.py](optimizer.py): Loading optimizer and passing to model.
- [callback.py](callback.py): Loading callbacks and passing to fitting function.
- [visualizer.py](visualizer.py): Visualizing training process and inference.

## Execution

```bash
export PYTHONHASHSEED=0
export PYTHONPATH=$HOME/Documents/Projects/
python -B -m prototype
```
