# Type Theory & Functional Programming

- **Publisher**: Addison-Wesley
- **Author**: Simon Thompson
- **Presenter**: Wen-Bin Luo
- **Link**: [https://www.cs.kent.ac.uk/people/staff/sjt/TTFP/](https://www.cs.kent.ac.uk/people/staff/sjt/TTFP/)

---

## Contents

- [Introduction](#introduction)
- [Introduction to Logic](#introduction-to-logic)
- [Functional Programming and λ-Calculi](#functional-programming-and-λ-calculi)
- [Constructive Mathematics](#constructive-mathematics)
- [Introduction-to Type Theory](#introduction-to-type-theory)
- [Exploring Type Theory](#exploring-type-theory)
- [Applying Type Theory](#applying-type-theory)
- [Augmenting Type Theory](#augmenting-type-theory)
- [Foundations](#foundations)
- [Conclusions](#conclusions)

---

## Introduction

- **Constructive type theory**: A system which is simultaneously a logic and a programming language, and in which propositions and types are *identical*.
- **Functional programming language**: A program is simply a value of a particular explicit type, rather than a state transformer.
- If the language allows general recursion, then every type contains at least one value, defined by the equation $`x=x`$.
- **Curry Howard isomorphism**: the propositions-as-types notion.
- $`p:P`$: $`p`$ is of type $`P`$, or $`p`$ is a proof of proposition $`P`$.
- Functions defined by recursion have their properties proved by induction.
- $`(a,b):(\exists{x}:A).B(x)`$: $`a`$ of type $`A`$ meets the specification $`B(x)`$, as proved by $`b:B(a)`$.
- The logic is an extension of many-sorted, first-order predicate logic.
- The system here integrates the process of program development and proof: to show that a program meets a specification we provide the program/proof pair.

---

## Introduction to Logic

- [Propositional Logic](#propositional-logic)
- [Predicate Logic](#predicate-logic)

---

### Propositional Logic

- Propositional **formula** ($`φ`$) are made up of propositional **atoms** ($`P`$) and **connectives** ($`\land|\lor|\implies`$).
- Backus-Naur form: $`φ::=P|(\lnotφ)|(φ\landφ)|(φ\lorφ)|(φ\impliesφ)`$.
- Natural deduction rules: ($`\land|\lor|\implies|\lnot|\bot`$) (introduction\|elimination)
- Propositional logic is a subset of the predicate logic.

---

### Predicate Logic

- Predicate **formula** ($`φ`$) are made up of **terms**, **predicates** ($`P`$), **quantifiers** ($`\forall|\exists`$), and **connectives** ($`\land|\lor|\implies`$).
	- Terms ($`t`$): **variables** ($`x`$), **constants** ($`c`$), **functions** ($`f`$).
- Backus-Naur form: $`φ::=P(t...)|\forall{x}.φ|\exists{x}.φ|(\lnotφ)|(φ\landφ)|(φ\lorφ)|(φ\impliesφ)`$.
- Natural deduction rules: ($`\forall|\exists|\land|\lor|\implies|\lnot|\bot`$) (introduction\|elimination)
- In a sense, $`\forall`$ is a combination of infinite $`\land`$, while $`\exists`$ is a combination of infinite $`\lor`$.

---

## Functional Programming and λ-Calculi

- [Functional Programming](#functional-programming)
- [The Untyped λ-Calculus](#the-untyped–λ-calculus)
- [Evaluation](#evaluation)
- [Convertibility](#convertibility)
- [Expressiveness](#expressiveness)
- [Typed λ-Calculus](#typed-λ-calculus)
- [Strong Normalization](#strong-normalization)
- [Further Type Constructors: The Product](#further-type-constructors-the-product)
- [Base Types: Natural Numbers](#base-types-natural-numbers)
- [General Recursion](#general-recursion)
- [Evaluation Revisited](#evaluation-revisited)

---

### Functional Programming

- FP is characterized by first-class functions, strong type systems, polymorphic types, algebraic types, and modularity.
	- **First-class functions**: Functions may be passed as arguments to and returned as results of other functions.
	- **Algebraic types**: Algebraic types generalizes enumerated types, (variant) records, certain sorts of pointer type definitions, and also permits type definitions to be parametrized over types.

---

### The Untyped λ-Calculus

- **λ-expression** ($`e`$) is made up of **variables** ($`x`$), **applications**, and **abstractions**.
	- **Applications** ($`e_1e_2`$): The application of expression $`e_1`$ to $`e_2`$.
	- **Abstractions** ($`λx.e`$): The function which returns the value $`e`$ when given formal parameter $`x`$.
- Backus-Naur form: $`e::=x|ee|λx.e`$.
- An expression is **closed** if it contains no free variables, otherwise it is **open**.
- The **substitution** of $`x'`$ for the free occurrences of $`x`$ in $`e`$ is written $`e[x'/x]`$.
- **β-reduction**: For all $`x`$, $`e`$ and $`e'`$, we can reduce the application $`(λx.e)e'\rightarrow_{β}e[e'/x]`$.
- **β-redex**: A sub-expression of a lambda expression of the form $`(λx.e)e'`$.

---

### Evaluation

- **Normal form**: An expression is in normal form if it contains no redexes.
- **Head normal form**: All expressions of the form $`λx_1...λx_n.ye_1...e_m`$ where $`x`$ and $`y`$ are variables and $`e`$ are expressions.
- **Weak head normal form**: All expressions which are either abstractions or of the form $`ye_1...e_m`$.
- A normal form can be thought of as the result of a computation.
- Evaluation of an expression fails to terminate if no sequence of reductions ends in a weak head normal form.
- **Church-Rosser Theorem**: For all expressions $`e`$, $`f`$, and $`g`$, if $`e\twoheadrightarrow f`$ and $`e\twoheadrightarrow g`$, then there exists an expression $`h`$ such that $`f\twoheadrightarrow h`$ and $`g\twoheadrightarrow h`$.
- The method of **structural induction**: To prove the result $`P(x)`$ for all λ-expressions $`e`$, it is sufficient to prove
	- $`\forall{x}.P(x)`$ holds.
	- If $`P(e_1)`$ and $`P(e_2)`$ hold, then $`P(e_1e_2)`$ holds.
	- If $`P(e)`$ holds, then $`P(λx.e)`$ holds.
- **Theorem**: If a term has a normal form, then it is unique.
- If an expression contains more than one redex, then we say that the **leftmost outermost** redex is that found by searching the parse tree top-down, going down the left hand subtree of a non-redex application before the right.
- **Normalization Theorem**: The reduction sequence formed by choosing for reduction at each stage the leftmost-outermost redex will result in a normal form, head normal form or weak head normal form if any exists.
- **Lazy** evaluation mechanism:
	- Corresponds to the strategy of choosing the leftmost-outermost redex at each stage.
	- Avoids duplication of evaluation caused by duplication of redexes.
- The strict or applicative order discipline will not always lead to termination, even when it is possible.
- **η-reduction**: For all $`x`$ and $`e`$, if $`x`$ is not free in $`e`$, then we can perform the reduction $`λx.(ex)\rightarrow_{η}e`$.
- It is not clear that η-reduction is strictly a rule of computation.
- The η-reduction rule identifies certain (terms for) functions which have the same behavior, yet which are represented in different ways.

---

### Convertibility

- **Convertibility** relations: equivalence relations which are also substitutive.
- **Definition**: $`e\leftrightarrow f`$ if and only if there is a sequence $`e_0,...,e_n`$ such that $`e\equiv e_0`$, $`e_n\equiv f`$ and for each $`i<n`$, $`e_i\twoheadrightarrow e_{i+1}`$ or $`e_{i+1}\twoheadrightarrow e_i`$.
- $`\leftrightarrow`$ is the smallest equivalence relation extending $`\twoheadrightarrow`$.
- As a consequence of the Church-Rosser theorems, two expressions $`e`$ and $`f`$ will be (βη-)convertible if and only if there exists a common (βη-)reduct of $`e`$ and $`f`$.
- Two functions with normal forms are convertible if and only if they have the *same* normal form.
- The convertibility relations are not necessary to explain the computational behavior of λ-expressions.

---

### Expressiveness

- The untyped λ-calculus is Turing-complete.
- Objects such as the natural numbers, booleans and so forth can be represented as λ-terms.
- To derive recursive functions, we need to be able to solve equations of the form $`f:=Rf`$ where $`R`$ is a λ-terms.
- **Fixed-point combinators** ($`F`$) solve the equation $`f:=Rf`$. Thus, $`FR\twoheadrightarrow R(FR)`$.

---

### Typed λ-Calculus

---

### Strong Normalization

---

### Further Type Constructors: The Product

---

### Base Types: Natural Numbers

---

### General Recursion

---

### Evaluation Revisited

---

## Constructive Mathematics

---

## Introduction to Type Theory

---

## Exploring Type Theory

---

## Applying Type Theory

---

## Augmenting Type Theory

---

## Foundations

---

## Conclusions

---
