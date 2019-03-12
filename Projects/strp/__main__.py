from strp.Populator import *
from strp.Demographics import *
from strp.Pipeline import *

if __name__ == "__main__":
    DATASETS, BESTS = Populator().populate()
    Demographics().fit(DATASETS.Training, "Training")
    Demographics().fit(DATASETS.Test.Chronic, "Test 1")
    Demographics().fit(DATASETS.Test.Early, "Test 2")
    PIPELINE = Pipeline(BESTS).fit(DATASETS)
    PIPELINE.run(LinearDiscriminantAnalysis)
    PIPELINE.run(QuadraticDiscriminantAnalysis)
    PIPELINE.run(LogisticRegression)
    PIPELINE.run(SVC)
