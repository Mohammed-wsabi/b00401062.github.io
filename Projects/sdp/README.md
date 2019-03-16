# Schizophrenia Development Prediction (SDP)

## Importing Data

- [Constants.py](Constants.py): Define a list of constants including tracts and features.
- [Dataset.py](Dataset.py): Load training/test sets populated with diffusion indices.

## Analysis Pipeline

- [Reducer.py](Reducer.py): Determine # components for the principal component analysis.

## Execution

```bash
export PYTHONHASHSEED=0
export PYTHONPATH=$HOME/Documents/Projects/
python3 -B -m sdp
```
