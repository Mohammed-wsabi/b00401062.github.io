# Output

## Demographics: Training

||R (n=32)|NR (n=32)|p value|
|-|-|-|-|
|Age (year)|34.33±8.82|33.85±9.58|0.83|
|Sex (% male)|14 (44%)|17 (53%)|0.62|
|Duration of illness (year)|10.64±7.38|10.68±7.93|0.98|
|Antipsychotic dose (mg/d)|281.14±144.91|461.42±312.74|<0.01*|
|Cumulative dose (mg/d-year)|3077.57±2684.50|5112.33±5441.07|0.06|
|P1+P2+P3|5.31±1.86|9.34±3.25|<0.01*|
|N1+N4+N6|4.94±1.68|8.69±3.26|<0.01*|
|G5+G9|2.50±0.76|4.16±1.97|<0.01*|

## Demographics: Test 1

||R (n=13)|NR (n=15)|p value|
|-|-|-|-|
|Age (year)|28.52±5.39|37.47±10.57|0.01*|
|Sex (% male)|3 (23%)|11 (73%)|0.02*|
|Duration of illness (year)|4.07±2.93|11.36±6.83|<0.01*|
|Antipsychotic dose (mg/d)|111.94±63.96|382.18±176.66|<0.01*|
|Cumulative dose (mg/d-year)|516.05±506.81|4872.06±4528.11|<0.01*|
|P1+P2+P3|5.08±1.32|8.73±2.99|<0.01*|
|N1+N4+N6|5.23±2.05|9.47±4.24|<0.01*|
|G5+G9|2.31±0.63|3.73±1.75|0.01*|

## Demographics: Test 2

||R (n=11)|NR (n=12)|p value|
|-|-|-|-|
|Age (year)|26.57±5.44|26.66±7.37|0.98|
|Sex (% male)|4 (36%)|7 (58%)|0.52|
|Duration of illness (year)|1.03±0.76|0.88±0.45|0.57|
|Antipsychotic dose (mg/d)|206.27±177.21|426.84±420.96|0.12|
|Cumulative dose (mg/d-year)|255.11±307.58|335.70±440.01|0.62|
|P1+P2+P3|4.18±1.33|9.42±3.20|<0.01*|
|N1+N4+N6|4.18±2.04|8.92±2.57|<0.01*|
|G5+G9|2.09±0.30|4.92±2.23|<0.01*|

## Preprocessing Session

- Feature selection: analysis of covariance (ANCOVA)
	- 1061 significant steps (alpha = 0.05) are found.
	- 144 segments are found.
	- 17 significant segments (>= 18 steps) are found.
- Feature extraction: average
- Feature extraction: principal component analysis (PCA)
	- 3 components explain 0.6516 of variance.

## Significant Segments

||Index|NR|Tract|Steps|
|-|-|-|-|-|
|1|GFA|↓|Left frontal aslant tract|6 ~ 26|
|2|GFA|↓|Left fornix|18 ~ 40|
|3|GFA|↓|Left inferior fronto-occipital fasciculus|76 ~ 95|
|4|GFA|↓|Right inferior fronto-occipital fasciculus|23 ~ 43|
|5|GFA|↓|Right inferior fronto-occipital fasciculus|68 ~ 98|
|6|GFA|↓|Right perpendicular fasciculus|44 ~ 67|
|7|GFA|↓|Left frontostriatal tract of dorsolateral prefrontal cortex|27 ~ 49|
|8|GFA|↓|Left frontostriatal tract of dorsolateral prefrontal cortex|75 ~ 93|
|9|GFA|↓|Left thalamic radiation of dorsolateral prefrontal cortex|45 ~ 62|
|10|GFA|↓|Left thalamic radiation of dorsolateral prefrontal cortex|75 ~ 96|
|11|GFA|↓|Right thalamic radiation of precentral gyrus|73 ~ 90|
|12|GFA|↓|Left thalamic radiation of optic radiation|78 ~ 97|
|13|GFA|↓|Corpus callosum of dorsolateral prefrontal cortex|0 ~ 17|
|14|GFA|↓|Corpus callosum of dorsolateral prefrontal cortex|56 ~ 74|
|15|GFA|↓|Corpus callosum of dorsolateral prefrontal cortex|82 ~ 99|
|16|GFA|↓|Corpus callosum of supplementary motor area|54 ~ 87|
|17|GFA|↓|Corpus callosum of splenium|2 ~ 21|

## Validation Session: LDA

- Best parameter: 0.000000
- Best mean (sd) of accuracy: 0.6250 (0.1169)

## Evaluation Session: LDA

- Receiver operating characteristic (ROC): 0.833984
- Confusion matrix: Training
	- Accuracy: 0.7656
	- Sensitivity: 0.7812
	- Specificity: 0.7500
	- PPV: 0.7576
	- NPV: 0.7742

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|25|8|
|Predicted NR|7|24|

- Confusion matrix: Test 1
	- Accuracy: 0.7500
	- Sensitivity: 0.8462
	- Specificity: 0.6667
	- PPV: 0.6875
	- NPV: 0.8333

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|11|5|
|Predicted NR|2|10|

- Confusion matrix: Test 2
	- Accuracy: 0.5217
	- Sensitivity: 0.4545
	- Specificity: 0.5833
	- PPV: 0.5000
	- NPV: 0.5385

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|5|5|
|Predicted NR|6|7|

- Confusion matrix: Transfer Test 2 Training
	- Accuracy: 0.7636
	- Sensitivity: 0.7450
	- Specificity: 0.7799
	- PPV: 0.7485
	- NPV: 0.7767

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|488|164|
|Predicted NR|167|581|

- Confusion matrix: Transfer Test 2 Test
	- Accuracy: 0.5956
	- Sensitivity: 0.5326
	- Specificity: 0.6571
	- PPV: 0.6031
	- NPV: 0.5897

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|237|156|
|Predicted NR|208|299|

## Validation Session: QDA

- Best parameter: 0.000000
- Best mean (sd) of accuracy: 0.6406 (0.1353)

## Evaluation Session: QDA

- Receiver operating characteristic (ROC): 0.828125
- Confusion matrix: Training
	- Accuracy: 0.7656
	- Sensitivity: 0.8438
	- Specificity: 0.6875
	- PPV: 0.7297
	- NPV: 0.8148

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|27|10|
|Predicted NR|5|22|

- Confusion matrix: Test 1
	- Accuracy: 0.6429
	- Sensitivity: 0.7692
	- Specificity: 0.5333
	- PPV: 0.5882
	- NPV: 0.7273

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|10|7|
|Predicted NR|3|8|

- Confusion matrix: Test 2
	- Accuracy: 0.5217
	- Sensitivity: 0.3636
	- Specificity: 0.6667
	- PPV: 0.5000
	- NPV: 0.5333

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|4|4|
|Predicted NR|7|8|

- Confusion matrix: Transfer Test 2 Training
	- Accuracy: 0.8200
	- Sensitivity: 0.7974
	- Specificity: 0.8420
	- PPV: 0.8311
	- NPV: 0.8100

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|551|112|
|Predicted NR|140|597|

- Confusion matrix: Transfer Test 2 Test
	- Accuracy: 0.4967
	- Sensitivity: 0.4988
	- Specificity: 0.4949
	- PPV: 0.4513
	- NPV: 0.5424

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|204|248|
|Predicted NR|205|243|

## Validation Session: LR

- Best parameter: 0.000977
- Best mean (sd) of accuracy: 0.6250 (0.1169)

## Evaluation Session: LR

- Receiver operating characteristic (ROC): 0.833984
- Confusion matrix: Training
	- Accuracy: 0.7656
	- Sensitivity: 0.7812
	- Specificity: 0.7500
	- PPV: 0.7576
	- NPV: 0.7742

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|25|8|
|Predicted NR|7|24|

- Confusion matrix: Test 1
	- Accuracy: 0.7500
	- Sensitivity: 0.8462
	- Specificity: 0.6667
	- PPV: 0.6875
	- NPV: 0.8333

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|11|5|
|Predicted NR|2|10|

- Confusion matrix: Test 2
	- Accuracy: 0.5217
	- Sensitivity: 0.4545
	- Specificity: 0.5833
	- PPV: 0.5000
	- NPV: 0.5385

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|5|5|
|Predicted NR|6|7|

- Confusion matrix: Transfer Test 2 Training
	- Accuracy: 0.7521
	- Sensitivity: 0.7425
	- Specificity: 0.7609
	- PPV: 0.7392
	- NPV: 0.7641

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|496|175|
|Predicted NR|172|557|

- Confusion matrix: Transfer Test 2 Test
	- Accuracy: 0.5956
	- Sensitivity: 0.5579
	- Specificity: 0.6303
	- PPV: 0.5821
	- NPV: 0.6070

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|241|173|
|Predicted NR|191|295|

## Validation Session: SVC

- Best parameter: 0.153893
- Best mean (sd) of accuracy: 0.5938 (0.1127)

## Evaluation Session: SVC

- Receiver operating characteristic (ROC): 0.826172
- Confusion matrix: Training
	- Accuracy: 0.7969
	- Sensitivity: 0.7500
	- Specificity: 0.8438
	- PPV: 0.8276
	- NPV: 0.7714

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|24|5|
|Predicted NR|8|27|

- Confusion matrix: Test 1
	- Accuracy: 0.7143
	- Sensitivity: 0.7692
	- Specificity: 0.6667
	- PPV: 0.6667
	- NPV: 0.7692

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|10|5|
|Predicted NR|3|10|

- Confusion matrix: Test 2
	- Accuracy: 0.4783
	- Sensitivity: 0.3636
	- Specificity: 0.5833
	- PPV: 0.4444
	- NPV: 0.5000

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|4|5|
|Predicted NR|7|7|

- Confusion matrix: Transfer Test 2 Training
	- Accuracy: 0.7443
	- Sensitivity: 0.7258
	- Specificity: 0.7618
	- PPV: 0.7432
	- NPV: 0.7452

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|495|171|
|Predicted NR|187|547|

- Confusion matrix: Transfer Test 2 Test
	- Accuracy: 0.5389
	- Sensitivity: 0.4952
	- Specificity: 0.5768
	- PPV: 0.5036
	- NPV: 0.5685

||True R|True NR|
|:-:|:-:|:-:|
|Predicted R|207|204|
|Predicted NR|211|278|
