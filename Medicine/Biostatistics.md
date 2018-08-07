# Biostatistics

## Bias & Study Errors

- Sampling bias
- Selection bias
- Recall bias
- Measurement bias
- Procedure bias
- Observer-expectancy bias
- Pygmalion effect
- Hawthorne effect
- Confounding bias
- Lead-time bias
- Late-look bias

## Observational Studies

||Cross Section|Case Control|Cohort|
|-|-|-|-|
|Time|Time point|Retrospective|Perspective|
|Incidence|-|-|+|
|Prevalence|+|-|-|
|Causality|-|+|+|
|Analysis|Chi-squared|Odds ratio (OR)|Relative risk (RR)|

## Statistical Tests

|Independent Variable|Dependent Variable|Sampling|Parametric|Test|
|-|-|-|-|-|
|-|Dichotomous|Independent|-|Goodness of fit & Binomial test|
|-|Polytomous|Independent|-|Goodness of fit & Multinomial test|
|-|Continuous|Independent|+|Z-test & T-test|
|Dichotomous|Dichotomous|Independent|-|Pearson Chi-squared test & Fisher exact test|
|Dichotomous|Dichotomous|Dependent|-|McNemar Chi-squared test|
|Dichotomous|Polytomous|Independent|-|Pearson Chi-squared test|
|Dichotomous|Continuous|Independent|-|Wilcoxon rank-sum test|
|Dichotomous|Continuous|Independent|+|Z-test & T-test|
|Dichotomous|Continuous|Dependent|-|Wilcoxon signed-rank test|
|Dichotomous|Continuous|Dependent|+|Paired Z-test & T-test|
|Polytomous|Dichotomous|Independent|-|Pearson Chi-squared test|
|Polytomous|Polytomous|Independent|-|Pearson Chi-squared test|
|Polytomous|Continuous|Independent|-|Kruskal-Wallis test|
|Polytomous|Continuous|Independent|+|ANOVA F-test|
|Polytomous|Continuous|Dependent|-|Friedman test|
|Polytomous|Continuous|Dependent|+|Repeated measures ANOVA F-test|
|Continuous|Dichotomous|Independent|+|Logistic regression: binomial|
|Continuous|Polytomous|Independent|+|Logistic regression: multinomial|
|Continuous|Continuous|Independent|-|Spearman correlation|
|Continuous|Continuous|Independent|+|Pearson correlation|

## Inferences for Means ![](https://latex.codecogs.com/gif.latex?\overline{X})

|Samples|Confidence Interval|Hypothesis Test|
|-|-|-|
|One|![](https://latex.codecogs.com/gif.latex?\overline{X}&space;\pm&space;t_\text{df}\frac{S}{\sqrt{n}},&space;\text{df}&space;=&space;n-1)|![](https://latex.codecogs.com/gif.latex?t_\text{df}&space;=&space;(\overline{X}-\mu)/\frac{S}{\sqrt{n}},&space;\text{df}&space;=&space;n-1)|
|Two|![](https://latex.codecogs.com/gif.latex?(\overline{X}_1-\overline{X}2)&space;\pm&space;t_\text{df}\sqrt{\frac{S_1^2}{n_1}&plus;\frac{S_2^2}{n_2}},&space;\text{df}&space;=&space;\min\{n_1-1,&space;n_2-1\})|![](https://latex.codecogs.com/gif.latex?t_\text{df}&space;=&space;(\overline{X}_1-\overline{X}_2)/\sqrt{\frac{S_1^2}{n_1}&plus;\frac{S_2^2}{n_2}},&space;\text{df}&space;=&space;\min\{n_1-1,&space;n_2-1\})|

## Inferences for Proportions ![](https://latex.codecogs.com/gif.latex?\hat{p})

|Samples|Confidence Interval|Hypothesis Test|
|-|-|-|
|One|$\hat{p} ± z\sqrt{\frac{\hat{p}(1-\hat{p})}{n}}$|$z = (\hat{p}-p)/\sqrt{\frac{p(1-p)}{n}}$|
|Two|$(\hat{p}_1-\hat{p}_2) ± z\sqrt{\frac{\hat{p}_1(1-\hat{p}_1)}{n_1}+\frac{\hat{p}_2(1-\hat{p}_2)}{n_2}}$|$z = (\hat{p}_1-\hat{p}_2)/\sqrt{p_\textrm{pooled}(1-p_\textrm{pooled})(\frac{1}{n_1}+\frac{1}{n_2})}, p_\textrm{pooled} = \frac{n_1\hat{p}_1+n_2\hat{p}_2}{n_1+n_2}$|

## Sensitivity & Specificity

|Sensitivity|Specificity|
|-|-|
|Ture positive|True negative|
|Rule out when negative|Rule in when positive|
|Screening test|Confirmation test|
|High type 1 error|Low type 1 error|
|Low type 2 error|High type 2 error|

## Supervised Learning Steps

- Collection
- Splitting
- Preprocessing
- Modeling
- Validation
- Evaluation
- Prediction
