# Benchmark for Deep Learning

## Script Files

- [config.yaml](config.yaml): Configuration file which lists available hyperparameters.
- [config.py](config.py): Loading configuration from [config.yaml](config.yaml).
- [utils.py](utils.py): Common functions used across the module.
- [data.py](data.py): Loading preprocessed and augmented images.
- [model.py](model.py): Loading compiled model which is ready for fitting.
- [visualizer.py](visualizer.py): Visualizing the training process and inference.
- [run.py](run.py): Streaming all functions to complete a benchmark.

## Execution

```bash
export PYTHONHASHSEED=0
export PYTHONPATH=$HOME/Documents/Projects/
python -B -m benchmark
```
