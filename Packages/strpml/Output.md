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
- Best mean (sd) of accuracy: 0.0000 (0.0000)

## Evaluation Session: LDA

- Receiver operating characteristic (ROC): 0.833984
- Contingency table: Training
	- Accuracy: 0.7656
	- Sensitivity: 0.7812
	- Specificity: 0.7500
	- PPV: 0.7576
	- NPV: 0.7742

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|25|8|
|Predicted NR|7|24|

- Contingency table: Test 1
	- Accuracy: 0.7500
	- Sensitivity: 0.8462
	- Specificity: 0.6667
	- PPV: 0.6875
	- NPV: 0.8333

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|11|5|
|Predicted NR|2|10|

- Contingency table: Test 2
	- Accuracy: 0.5217
	- Sensitivity: 0.4545
	- Specificity: 0.5833
	- PPV: 0.5000
	- NPV: 0.5385

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|5|5|
|Predicted NR|6|7|

- Contingency table: Transfer Test 2 Training
	- Accuracy: 0.7580
	- Sensitivity: 0.7406
	- Specificity: 0.7744
	- PPV: 0.7554
	- NPV: 0.7605

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|2010|651|
|Predicted NR|704|2235|

- Contingency table: Transfer Test 2 Test
	- Accuracy: 0.5931
	- Sensitivity: 0.5617
	- Specificity: 0.6207
	- PPV: 0.5660
	- NPV: 0.6165

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|947|726|
|Predicted NR|739|1188|

## Validation Session: QDA

- Best parameter: 0.000000
- Best mean (sd) of accuracy: 0.5000 (0.5000)

## Evaluation Session: QDA

- Receiver operating characteristic (ROC): 0.828125
- Contingency table: Training
	- Accuracy: 0.7656
	- Sensitivity: 0.8438
	- Specificity: 0.6875
	- PPV: 0.7297
	- NPV: 0.8148

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|27|10|
|Predicted NR|5|22|

- Contingency table: Test 1
	- Accuracy: 0.6429
	- Sensitivity: 0.7692
	- Specificity: 0.5333
	- PPV: 0.5882
	- NPV: 0.7273

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|10|7|
|Predicted NR|3|8|

- Contingency table: Test 2
	- Accuracy: 0.5217
	- Sensitivity: 0.3636
	- Specificity: 0.6667
	- PPV: 0.5000
	- NPV: 0.5333

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|4|4|
|Predicted NR|7|8|

- Contingency table: Transfer Test 2 Training
	- Accuracy: 0.8277
	- Sensitivity: 0.8058
	- Specificity: 0.8482
	- PPV: 0.8331
	- NPV: 0.8229

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|2187|438|
|Predicted NR|527|2448|

- Contingency table: Transfer Test 2 Test
	- Accuracy: 0.4953
	- Sensitivity: 0.4810
	- Specificity: 0.5078
	- PPV: 0.4626
	- NPV: 0.5263

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|811|942|
|Predicted NR|875|972|

## Validation Session: LR

- Best parameter: 0.535887
- Best mean (sd) of accuracy: 0.4219 (0.4939)

## Evaluation Session: LR

- Receiver operating characteristic (ROC): 0.830078
- Contingency table: Training
	- Accuracy: 0.7812
	- Sensitivity: 0.7812
	- Specificity: 0.7812
	- PPV: 0.7812
	- NPV: 0.7812

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|25|7|
|Predicted NR|7|25|

- Contingency table: Test 1
	- Accuracy: 0.7143
	- Sensitivity: 0.7692
	- Specificity: 0.6667
	- PPV: 0.6667
	- NPV: 0.7692

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|10|5|
|Predicted NR|3|10|

- Contingency table: Test 2
	- Accuracy: 0.4783
	- Sensitivity: 0.4545
	- Specificity: 0.5000
	- PPV: 0.4545
	- NPV: 0.5000

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|5|6|
|Predicted NR|6|6|

- Contingency table: Transfer Test 2 Training
	- Accuracy: 0.7639
	- Sensitivity: 0.7550
	- Specificity: 0.7721
	- PPV: 0.7522
	- NPV: 0.7747

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|2022|666|
|Predicted NR|656|2256|

- Contingency table: Transfer Test 2 Test
	- Accuracy: 0.5997
	- Sensitivity: 0.5552
	- Specificity: 0.6406
	- PPV: 0.5861
	- NPV: 0.6110

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|956|675|
|Predicted NR|766|1203|

## Validation Session: SVC

- Best parameter: 0.615572
- Best mean (sd) of accuracy: 0.4844 (0.4998)

## Evaluation Session: SVC

- Receiver operating characteristic (ROC): 0.827148
- Contingency table: Training
	- Accuracy: 0.7656
	- Sensitivity: 0.7500
	- Specificity: 0.7812
	- PPV: 0.7742
	- NPV: 0.7576

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|24|7|
|Predicted NR|8|25|

- Contingency table: Test 1
	- Accuracy: 0.7143
	- Sensitivity: 0.7692
	- Specificity: 0.6667
	- PPV: 0.6667
	- NPV: 0.7692

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|10|5|
|Predicted NR|3|10|

- Contingency table: Test 2
	- Accuracy: 0.4783
	- Sensitivity: 0.3636
	- Specificity: 0.5833
	- PPV: 0.4444
	- NPV: 0.5000

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|4|5|
|Predicted NR|7|7|

- Contingency table: Transfer Test 2 Training
	- Accuracy: 0.7536
	- Sensitivity: 0.7364
	- Specificity: 0.7693
	- PPV: 0.7453
	- NPV: 0.7610

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|1972|674|
|Predicted NR|706|2248|

- Contingency table: Transfer Test 2 Test
	- Accuracy: 0.5689
	- Sensitivity: 0.5186
	- Specificity: 0.6150
	- PPV: 0.5526
	- NPV: 0.5822

||Actual R|Actual NR|
|:-:|:-:|:-:|
|Predicted R|893|723|
|Predicted NR|829|1155|
