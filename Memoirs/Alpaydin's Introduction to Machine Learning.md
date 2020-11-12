# Alpaydin's Introduction to Machine Learning

- **Publisher**: The MIT Press
- **Author**: Ethem Alpaydin
- **Presenter**: Wen-Bin Luo
- **Link**: [https://mitpress.mit.edu/books/introduction-machine-learning-third-edition](https://mitpress.mit.edu/books/introduction-machine-learning-third-edition)

---

## Contents

- Introduction
- [Supervised Learning](#supervised-learning)
- [Bayesian Decision Theory](#bayesian-decision-theory)
- [Parametric Methods](#parametric-methods)
- [Multivariate Methods](#multivariate-methods)

---

## Supervised Learning

- Learning a Class from Examples
- [Vapnik-Chervonenkis Dimension](#vapnik-chervonenkis-dimension)
- [Probably Approximately Correct Learning](#probably-approximately-correct-learning)
- [Noise](#noise)
- Learning Multiple Classes
- [Regression](#regression)
- [Model Selection and Generalization](#model-selection-and-generalization)
- [Dimensions of a Supervised Machine Learning Algorithm](#dimensions-of-a-supervised-machine-learning-algorithm)
- Notes

---

### Vapnik-Chervonenkis Dimension

- Given a dataset containing $`N`$ points, a hypothesis $`H`$ shatters $`N`$ points if it separates the positive examples from the negative.
- **Vapnik-Chervonenkis (VC) dimension** of a hypothesis $`H`$, denoted as $`\text{VC}(H)`$: The maximum number of points that can be shattered by $`H`$.

---

### Probably Approximately Correct Learning

- **Probably approximately correct (PAC) learning**: Given a class $`C`$, and examples drawn from some unknown but fixed probability distribution $`p(x)`$, we want to find the number of examples, $`N`$, such that with probability at least $`1-δ`$, the hypothesis $`H`$ has error at most $`ε`$, for arbitrary $`δ≤\frac{1}{2}`$ and $`ε>0`$, i.e., $`P(C\Delta H≤ε)≥1-δ`$, where $`C\Delta H`$ is the region of difference between $`C`$ and $`H`$.

---

### Noise

- Noise can result from:
	- Imprecision in recording the input attributes.
	- Errors in labeling the data points.
	- Hidden or latent attributes that may be unobservable.
- **Occam's razor**: simpler explanations are more plausible and any unnecessary complexity should be shaved off.

---

### Regression

- If there is no noise, the task is *interpolation*/*extrapolation*.
- In regression, there is noise added to the output of the unknown function.

---

### Model Selection and Generalization

- An **ill-posed problem** is where the data by itself is not sufficient to find a unique solution.
- **Model selection**: Choosing between possible hypothesis.
- **Generalization**: How well a model trained on the training set predicts the right output for new instances.
- **Triple trade-off**:
	- The complexity of the hypothesis we fit to data.
	- The amount of training data.
	- The generalization error on new examples.
- In general, as the complexity of a model class increases, the generalization error decreases first and then starts to increase.
- Datasets:
	- **Training set**: To train the model.
	- **Validation set**: To test the generalization ability.
	- **Test set**, or **publication set**: To report the error to give an idea about the expected error of our best model.
- In *cross-validation*, the hypothesis that is the most accurate on the validation set is the best one.

---

### Dimensions of a Supervised Machine Learning Algorithm

- There are three decisions we must make:
	- **Model**: Denoted as $`\hat{f}(x|θ)`$ where $`\hat{f}`$ is the model, $`x`$ is the input, and $`θ`$ are the parameters.
	- **Loss function** ($`L`$): To compute the difference between the desired output and our approximation to it.
	- **Optimization procedure**: To find $`θ^*`$ that minimizes the total error.

---

## Bayesian Decision Theory

- Introduction
- [Classification](#classification)
- [Losses and Risks](#losses-and-risks)
- [Discriminant Functions](#discriminant-functions)
- [Association Rules](#association-rules)
- Notes

---

### Classification

- **Bayes' rule**: $`P(y=i|x)`$ = $`P(y=i)P(x|y=i)/P(x)`$ where
	- $`P(y=i|x)`$ is the **posterior probability**.
	- $`P(y=i)`$ is the **prior probability**.
	- $`P(x|y=i)`$ is the **likelihood**.
	- $`P(x)`$ is the **evidence**.
- **Bayes' classifier**: Given an observation $`x`$, the predicted class $`\hat{y}`$ = $`\text{argmax}_iP(y=i|x)`$.

---

### Losses and Risks

- Let $`λ_{ik}`$ be the loss incurred for falsely assuming $`\hat{y}`$ = $`i`$ when the input actually belongs to $`y`$ = $`k`$.
- The *expected loss* for misclassification is $`L(y=i|x)`$ = $`\sum_{k=1}^Kλ_{ik}P(y=k|x)`$.
- The class with the least expected loss is $`\text{argmin}_iL(y=i|x)`$.
- In Bayesian classifier, $`λ_{ik}`$ is 0 if $`i`$ = $`k`$, or 1 if $`i`$ ≠ $`k`$.
- $`\hat{y}`$ = $`\text{argmin}_iL(y=i|x)`$ = $`\text{argmin}_i\sum_{k=1}^Kλ_{ik}P(y=k|x)`$ = $`\text{argmin}_i1-P(y=i|x)`$ = $`\text{argmax}_iP(y=i|x)`$.

---

### Discriminant Functions

- Classification can be seen as implementing a set of *discriminant functions*, $`g_i(x)`$, $`i\in\{1,...,K\}`$, such that $`\hat{y}`$ = $`\text{argmax}_ig_i(x)`$.
- This divides the feature space into $`K`$ *decision regions* $`R_i`$, $`i\in\{1,...,K\}`$.
- The regions are separated by *decision boundaries*.

---

### Association Rules

- An association rule is an implication of the form $`X→Y`$ where $`X`$ is the **antecedent** and $`Y`$ is the **consequent** of the rule.
- **Support**: $`\text{support}(X→Y)`$ := $`P(X,Y)`$.
- **Confidence**: $`\text{confidence}(X→Y)`$ := $`P(Y|X)`$.
- **Lift** (or **interest**): $`\text{lift}(X→Y)`$ := $`\frac{P(X,Y)}{P(X)P(Y)}`$ = $`\frac{P(Y|X)}{P(Y)}`$.
- Two steps of **Apriori** algorithm:
	1. Find frequent item sets, that is, those which have enough *support*.
	2. Convert them to rules with enough *confidence* by splitting the items into two, as items in the *antecedent* and items in the *consequent*.
- A rule $`X→Y`$ need not imply causality but just an association.
- In a problem, there may also be *hidden variables* whose values are never known through evidence.

---

## Parametric Methods

- Introduction
- [Maximum Likelihood Estimation](#maximum-likelihood-estimation)
- [Evaluating an Estimator: Bias and Variance](#evaluating-an-estimator-bias-and-variance)
- [The Bayes' Estimator](#the-bayes-estimator)
- [Parametric Classification](#parametric-classification)
- [Regression](#regression)
- [Tuning Model Complexity: Bias/Variance Dilemma](#tuning-model-complexity-biasvariance-dilemma)
- [Model Selection Procedures](#model-selection-procedures)

---

### Maximum Likelihood Estimation

- Let $`X`$ = $`\{x_i\}_{i=1}^N`$ be a set of $`N`$ independent and identically distributed (iid) samples drawn from some known probability density family.
- The **likelihood** of parameter $`θ`$ given sample $`X`$ is the product of the likelihoods of the individual points: $`I(θ|X)`$ = $`P(X|θ)`$ = $`\prod_{i=1}^NP(x_i|θ)`$.
- **Log likelihood**: $`L(θ|X)`$ = $`\log I(θ|X)`$ = $`\log P(X|θ)`$ = $`\sum_{i=1}^NP(x_i|θ)`$.
- **Maximum likelihood estimation (MLE)**: $`\hat{θ}`$ = $`\text{argmax}_θI(θ|X)`$ = $`\text{argmax}_θL(θ|X)`$.
- **Bernoulli density**:
	- $`X\sim B(N,θ)`$.
	- $`P(x_i|θ)`$ = $`θ^{x_i}(1-θ)^{1-x_i}`$.
	- $`L(θ|X)`$ = $`\log\prod_{i=1}^Nθ^{x_i}(1-θ)^{1-x_i}`$ = $`\sum_ix_i\logθ+(N-\sum_ix_i)\log(1-θ)`$.
	- $`\hat{θ}`$ = $`\sum_ix_i/N`$.
- **Multinomial density**:
	- $`X\sim\text{multinomial}(N,θ)`$, where $`θ`$ = $`\{θ_i|i=1,...,K\}`$.
	- $`P(x_i|θ)`$ = $`\prod_{k=1}^Kθ_i^{x_{ik}}`$ where $`x_{ik}`$ is 1 if $`x_i`$ = $`k`$, or 0 if $`x_i`$ ≠ $`k`$.
	- $`\hat{θ_k}`$ = $`\sum_ix_{ik}/N`$, $`k\in\{1,...,K\}`$.
- **Gaussian density**:
	- $`X\sim N(μ,σ^2)`$.
	- $`P(x_i|μ,σ^2)`$ = $`\frac{1}{\sqrt{2πσ^2}}\exp(-\frac{(x_i-μ)^2}{2σ^2})`$.
	- $`\hat{μ}`$ = $`\sum_ix_i/N`$.
	- $`\hat{σ^2}`$ = $`\sum_i(x_i-\hat{μ})^2/N`$.

---

### Evaluating an Estimator: Bias and Variance

- Let $`\hat{θ}`$ be an estimator of $`θ`$ based on $`N`$ observations.
- **Bias** of an estimator: $`b_θ(\hat{θ})`$ := $`E[θ-\hat{θ}]`$.
- **Mean square error (MSE)** of an estimator: $`r_θ(\hat{θ})`$ := $`E[(θ-\hat{θ})^2]`$.
- **Unbiased estimator**: $`\hat{θ}`$ is an *unbiased* estimator of $`θ`$ if $`b_θ(\hat{θ})`$ = $`0`$ or $`E[\hat{θ}]`$ = $`θ`$.
- **Consistent estimator**: $`\hat{θ}`$ is a *consistent* estimator of $`θ`$ if $`r_θ(\hat{θ})→0`$ as $`N→0`$.
- $`m`$ = $`\sum_i{x_i}/N`$ is an unbiased and consistent estimator of $`μ`$.
- $`s^2`$ = $`\sum{(x_i-m)^2}/N`$ is a biased but consistent estimator of $`σ^2`$ since $`E[s^2]`$ = $`\frac{N-1}{N}σ^2`$ ≠ $`σ^2`$.
- **Asymptotically unbiased estimator**: $`\hat{θ}`$ is an *asymptotically unbiased* estimator of $`θ`$ if $`b_θ(\hat{θ})→0`$ or $`E[\hat{θ}]→θ`$ as $`N→0`$.
- MSE = $`r_θ(\hat{θ})`$ = $`b_θ^2(\hat{θ})+\text{variance}(\hat{θ})`$ = bias<sup>2</sup> + variance.

---

### The Bayes' Estimator

- The estimation of $`θ`$ can be exploited by prior information on the distribution of $`θ`$.
- **Bayes' rule**: $`P(θ|X)`$ = $`P(θ)P(X|θ)/P(X)`$ where
	- **Posterior density** $`P(θ|X)`$: the likely $`θ`$ values after looking at the sample.
	- **Prior density** $`P(θ)`$: the likely values that $`θ`$ may take before looking at the sample.
- **Maximum likelihood estimate (MLE)**: $`\hat{θ}`$ = $`\text{argmax}_θP(X|θ)`$.
- **Maximum a posteriori (MAP) estimate**: $`\hat{θ}`$ = $`\text{argmax}_θP(θ|X)`$.
- **Bayes' estimate**: $`\hat{θ}`$ = $`E[θ|X]`$ = $`\int θP(θ|X)dθ`$.
- The Bayes' estimator for posterior mean $`\hat{μ}`$ is a weighted average of the prior mean $`μ`$ and the sample mean $`m`$.

---

### Parametric Classification

- In the case of *Bayes' classification*, the discriminant function for class $`i\in\{1,...,K\}`$ is<br>$`g_i(x)`$ = $`P(x|y=i)P(y=i)`$, or $`g_i(x)`$ = $`\log P(x|y=i)+\log P(y=i)`$.
- In the case of *Gaussian Bayes' classification*:
	- $`P(x|y=i)`$ = $`\frac{1}{\sqrt{2πσ_i^2}}\exp(-\frac{(x-μ_i)^2}{2σ_i^2})`$.
	- $`g_i(x)`$ = $`-\frac{1}{2}\log2π-\logσ_i-\frac{(x-μ_i)^2}{2σ_i^2}+\log P(y=i)`$.
	- $`μ_i`$ and $`σ_i^2`$ are estimated based on $`N`$ observations.
	- If the priors and the variance are equal, then $`\text{argmax}_ig_i(x)`$ = $`\text{argmax}_i-(x-μ_i)^2`$ = $`\text{argmin}_i|x-μ_i|`$.
	- The threshold of decision is the midpoint between the two means.

---

### Regression

- $`y`$ = $`f(x)+ε`$: The numeric output is the sum of a deterministic function of the input and random noise.
- $`f(x)`$, the unknown function, is approximated by the estimator $`\hat{f}(x|θ)`$.
- Assume that $`ε`$ is zero mean Gaussian with constant variance $`σ^2`$, namely, $`ε\sim N(0,σ^2)`$.
- By placing $`\hat{f}(x|θ)`$ in place of $`f(x)`$, we have $`P(y|x)\sim N(\hat{f}(x|θ),σ^2)`$.
- $`P(x,y)`$ = $`P(y|x)P(x)`$, where $`P(y|x)`$ is the output given the input, and $`P(x)`$ is the input density.
- $`L(θ|X)`$ = $`\log \prod_{i=1}^NP(x_i,y_i)`$ = $`\log\prod_{i=1}^NP(y_i|x_i)+\log\prod_{i=1}^NP(x_i)`$.
- In the case of *linear regression*:
	- $`\hat{f}(x|w_0,w_1)`$ = $`w_0+w_1x`$.
	- $`\text{argmax}_{w_0,w_1}L(w_0,w_1|X)`$ = $`\text{argmax}_{w_0,w_1}\log\prod_{i=1}^NP(y_i|x_i)`$ = $`\text{argmax}_{w_0,w_1}\sum_{i=1}^N(y_i-\hat{f}(x_i|w_0,w_1))^2`$.
	- Assuming Gaussian distributed error and maximizing likelihood corresponds to minimizing the sum of squared errors.
- **Relative square error (RSE)**: RSE = residual sum of squares (RSS) / total sum of squares (TSS) = $`\sum_i(y_i-\hat{y}_i)^2/\sum_i(y_i-\bar{y})^2`$.
- **Coefficient of determination**: $`R^2`$ = 1 - RSE.

---

### Tuning Model Complexity: Bias/Variance Dilemma

- Consider $`y`$ = $`f(x)+ε`$, where $`ε\sim N(0,σ^2)`$ and $`\hat{f}`$ is an estimator of $`f`$.
- **Mean square error (MSE)** of a model: $`E[(y-\hat{f})^2]`$ = $`E[(f-\hat{f}+ε)^2]`$ = $`E[(f-\hat{f})^2]+2E[(f-\hat{f})ε]+E[ε^2]`$ = $`E[(f-\hat{f})^2]+E[ε^2]`$ = $`b_f(\hat{f})^2+r_f(\hat{f})+σ^2`$ = bias<sup>2</sup> + variance + noise.
- **Bias/variance dilemma**: Models with a lower bias in parameter estimation have a higher variance of the parameter estimates across samples, and vice versa.
- In a sense, high bias implies **underfitting** and high variance implies **overfitting**.

---

### Model Selection Procedures

- In practice, we cannot calculate the bias and variance for a model, but we can calculate the total error.
- **Cross-validation**:
	- The validation error is an estimate of the total error except that it also contains the variance of the noise.
	- Cross-validation makes no prior assumption about the model or parameters.
- **Regularization** introduce an **augmented error function** to penalizes complex models with large variance.
- The augmented error function can be seen as an **optimism** estimating the discrepancy between training and test error.
- The weight of the penalty $`λ`$ is optimized using cross-validation.
- **Akaike’s information criterion (AIC)** and **Bayesian information criterion (BIC)** work by estimating the optimism and adding it to the training error to estimate test error, without any need for validation.
- **Structural risk minimization (SRM)** uses a set of models ordered in terms of their complexities.
- **Minimum description length (MDL)** is based on an information theoretic measure.
- **Bayesian model selection** is used when we have some prior knowledge about the appropriate class of approximating functions.

![](https://www.kdnuggets.com/wp-content/uploads/bias-variance-total-error.jpg)

---

## Multivariate Methods

- Multivariate Data
- Parameter Estimation
- [Estimation of Missing Values](#estimation-of-missing-values)
- Multivariate Normal Distribution
- Multivariate Classification
- Tuning Complexity
- Multivariate Regression


---

### Estimation of Missing Values

- **Imputation**: the process of replacing missing data with substituted values.
	- **Mean imputation** substitutes the mean (average) of the available data for that variable in the sample.
	- **Imputation by regression** predicts the value of a missing variable from other variables whose values are known for that case.

---