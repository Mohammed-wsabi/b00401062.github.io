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

---

### Functional Programming

- FP is characterized by first-class functions, strong type systems, polymorphic types, algebraic types, and modularity.
	- **First-class functions**: Functions may be passed as arguments to and re- turned as results of other functions.
	- **Algebraic types**: Algebraic types generalizes enumerated types, (variant) records, certain sorts of pointer type definitions, and also permits type definitions to be parametrized over types.

---

### The Untyped λ-Calculus

- **λ-expression** is made up of **variables** ($`x`$), **applications**, and **abstractions**.
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
