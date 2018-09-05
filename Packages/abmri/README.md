## Load Data
- [Constants.py](Constants.py): define a list of constants including tracts and features.
- [Populator.py](Populator.py): load diffusion indices of chronic and first-episode patients.

## Demographics
- [Demographics.py](Demographics.py): demographics of the participants.

## Standardized Pipeline
- [Pipeline.py](Pipeline.py): define standardized analysis pipeline for each model.
- [Preprocessor.py](Preprocessor.py): preprocess raw data with average and PCA.
- [Validator.py](Validator.py): perform cross validation to select the best hyperparameters and model.
- [Evaluator.py](Evaluator.py): evaluate the performance the final model.

## Helper Functions
- [RMSEA.py](RMSEA.py): implement root mean square error of approximation (RMSEA).

## Execution

```bash
export PYTHONHASHSEED=0
export PYTHONPATH=$HOME/Documents/Packages/abmri/
```

```python
from Populator import *
from Demographics import *
from Pipeline import *
DATASETS, BESTS = Populator().populate()
Demographics().fit(DATASETS.Training, "Training")
Demographics().fit(DATASETS.Test.Chronic, "Test 1")
Demographics().fit(DATASETS.Test.Early, "Test 2")
PIPELINE = Pipeline(BESTS).fit(DATASETS)
PIPELINE.run(LinearDiscriminantAnalysis)
PIPELINE.run(QuadraticDiscriminantAnalysis)
PIPELINE.run(LogisticRegression)
PIPELINE.run(SVC)
```
