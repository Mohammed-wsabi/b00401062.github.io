# Alpaydin's Introduction to Machine Learning

- **Publisher**: The MIT Press
- **Author**: Ethem Alpaydın
- **Presenter**: Wen-Bin Luo
- **Link**: [https://mitpress.mit.edu/books/introduction-machine-learning-third-edition](https://mitpress.mit.edu/books/introduction-machine-learning-third-edition)

---

## Contents

- Introduction
- [Supervised Learning](#supervised-learning)
- [Bayesian Decision Theory](#bayesian-decision-theory)

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
	- There may be imprecision in recording the input attributes.
	- There may be errors in labeling the data points.
	- Some attributes may be hidden or latent in that they may be unobservable.
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
	- **Model**: Denoted as $`g(x|θ)`$ where $`g`$ is the model, $`x`$ is the input, and $`θ`$ are the parameters.
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

- **Bayes' rule**: $`P(y=i|x) = P(y=i)P(x|y=i)/P(x)`$ where
	- $`P(y=i|x)`$ is the **posterior probability**.
	- $`P(y=i)`$ is the **prior probability**.
	- $`P(x|y=i)`$ is the **likelihood**.
	- $`P(x)`$ is the **evidence**.
- **Bayesian classifier**: Given an observation $`x`$, the predicted class $`\hat{y}=\text{argmax}_iP(y=i|x)`$.

---

### Losses and Risks

- Let $`λ_{ik}`$ be the loss incurred for falsely assuming $`\hat{y}=i`$ when the input actually belongs to $`y=k`$.
- The *expected loss* for misclassification is $`L(y=i|x)=\sum_{k=1}^Kλ_{ik}P(y=k|x)`$.
- The class with the least expected loss is $`\text{argmin}_iL(y=i|x)`$.
- In Bayesian classifier, $`λ_{ik}`$ is 0 if $`i=k`$, or 1 if $`i≠k`$.
- $`\hat{y}`$ = $`\text{argmin}_iL(y=i|x)`$ = $`\text{argmin}_i\sum_{k=1}^Kλ_{ik}P(y=k|x)`$ = $`\text{argmin}_i1-P(y=i|x)`$ = $`\text{argmax}_iP(y=i|x)`$.

---

### Discriminant Functions

- Classification can be seen as implementing a set of *discriminant functions*, $`g_i(x)`$, $`i=1,...,K`$, such that $`\hat{y}=\text{argmax}_ig_i(x)`$.
- This divides the feature space into $`K`$ *decision regions* $`R_1,...,R_K`$.
- The regions are separated by *decision boundaries*.

---

### Association Rules

- An association rule is an implication of the form $`X→Y`$ where $`X`$ is the **antecedent** and $`Y`$ is the **consequent** of the rule.
- **Support**: $`\text{support}(X→Y):=P(X,Y)`$.
- **Confidence**: $`\text{confidence}(X→Y):=P(Y|X)`$.
- **Lift** (or **interest**): $`\text{lift}(X→Y):=\frac{P(X,Y)}{P(X)P(Y)}=\frac{P(Y|X)}{P(Y)}`$.
- Two steps of **Apriori** algorithm:
	1. Find frequent item sets, that is, those which have enough *support*.
	2. Convert them to rules with enough *confidence* by splitting the items into two, as items in the *antecedent* and items in the *consequent*.
- A rule $`X→Y`$ need not imply causality but just an association.
- In a problem, there may also be *hidden variables* whose values are never known through evidence.

---
