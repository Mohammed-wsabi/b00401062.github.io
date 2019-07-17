# Benchmark for Deep Learning

## Script Files

- [config.py](config.py): Configuration file listing available hyperparameters.
- [utils.py](utils.py): Common functions used across the module.
- [data.py](data.py): Data loader which imports preprocessed and augmented images.
- [model.py](model.py): Model loader which is compiled and ready for fitting.
- [run.py](run.py): Streaming all functions to complete a benchmark.

## Execution

```bash
export PYTHONHASHSEED=0
export PYTHONPATH=$HOME/Documents/Projects/
python -B -m benchmark
```
