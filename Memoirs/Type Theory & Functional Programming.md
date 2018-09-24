# Type Theory & Functional Programming

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
- $`p:P`$: $`p`$ is of type $`P`$. $`p`$ is a proof of proposition $`P`$.
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

- **Propositions** (φ) are made up of **propositional atoms** (p) and **logical connectives**.
- Backus-Naur form: φ ::= p\|(¬φ)\|(φ∧φ)\|(φ∨φ)\|(φ⇒φ)
- Natural deduction rules: (∧\|∨\|⇒\|¬\|⊥) (introduction\|elimination)

---

### Predicate Logic

---

## Functional Programming and λ-Calculi

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
