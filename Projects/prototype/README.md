# Prototype of Deep Learning Pipeline

## Configuration

- [config.yaml](config.yaml): Configuration file which lists available hyperparameters.

## Script Files

- [config.py](config.py): Loading configuration from [config.yaml](config.yaml).
- [utils.py](utils.py): Common functions used across the module.
- [data.py](data.py): Loading preprocessed and augmented images.
- [model.py](model.py): Loading compiled model which is ready for fitting.
- [optimizer.py](optimizer.py): Loading optimizer and passing to model.
- [callback.py](callback.py): Loading callbacks and passing to fitting function.
- [visualizer.py](visualizer.py): Visualizing training process and inference.
- [run.py](run.py): Streaming all functions to complete a benchmark.

## Execution

```bash
export PYTHONHASHSEED=0
export PYTHONPATH=$HOME/Documents/Projects/
python -B -m prototype
```
