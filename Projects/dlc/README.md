# MATLAB Deep Learning Challenge

## Pipeline

- [Configuration.py](Configuration.py): Specifying folders and initial values of hyperparameters.
- [Dataset.py](Dataset.py): Load metadata and features into the scope.
- [Preprocessor.py](Preprocessor.py): Crop images to focus on object(s) of interest and resize for subsequent neural networks.
- [Classifier.py](Classifier.py): Feed training data to models and evaluate the performance.

## Execution

```bash
export PYTHONHASHSEED=0
export PYTHONPATH=$HOME/Documents/Projects/
python3 -B -m dlc
```
