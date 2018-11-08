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
- [Introduction to Type Theory](#introduction-to-type-theory)
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

- **λ-expression** ($`e`$) is made up of *variables*, *applications*, and *abstractions*.
	- **Variables** ($`x`$)
	- **Applications** ($`e_1e_2`$): The application of expression $`e_1`$ to $`e_2`$.
	- **Abstractions** ($`λx.e`$): The function which returns the value $`e`$ when given formal parameter $`x`$.
- Backus-Naur form: $`e::=x|ee|λx.e`$.
- An expression is **closed** if it contains no free variables, otherwise it is **open**.
- The **substitution** of $`x'`$ for the free occurrences of $`x`$ in $`e`$ is written $`e[x'/x]`$.
- **β-reduction**: For all $`x`$, $`e`$ and $`e'`$, we can reduce the application $`(λx.e)e'→_{β}e[e'/x]`$.
- **β-redex**: A sub-expression of a lambda expression of the form $`(λx.e)e'`$.

---

### Evaluation

- Normal form variants:
	- **Normal form**: An expression is in normal form if it contains no redexes.
	- **Head normal form**: All expressions of the form $`λx_1...λx_n.ye_1...e_m`$ where $`x`$ and $`y`$ are variables and $`e`$ are expressions.
	- **Weak head normal form**: All expressions which are either abstractions or of the form $`ye_1...e_m`$.
- A normal form can be thought of as the result of a computation.
- Evaluation of an expression fails to terminate if no sequence of reductions ends in a weak head normal form.
- **Church-Rosser Theorem**: For all expressions $`e`$, $`e_1`$, and $`e_2`$, if $`e↠e_1`$ and $`e↠e_2`$, then there exists an expression $`e'`$ such that $`e_1↠e'`$ and $`e_2↠e'`$.
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
- **η-reduction**: For all $`x`$ and $`e`$, if $`x`$ is not free in $`e`$, then we can perform the reduction $`λx.(ex)→_{η}e`$.
- It is not clear that η-reduction is strictly a rule of computation.
- The η-reduction rule identifies certain (terms for) functions which have the same behavior, yet which are represented in different ways.

---

### Convertibility

- **Convertibility** relations: equivalence relations which are also substitutive.
- **Definition**: $`e↔f`$ if and only if there is a sequence $`e_0,...,e_n`$ such that $`e≡e_0`$, $`e_n≡f`$ and for each $`i<n`$, $`e_i↠e_{i+1}`$ or $`e_{i+1}↠e_i`$.
- $`↔`$ is the smallest equivalence relation extending $`↠`$.
- As a consequence of the Church-Rosser theorems, two expressions $`e_1`$ and $`e_2`$ will be (βη-)convertible if and only if there exists a common (βη-)reduct of $`e_1`$ and $`e_2`$.
- Two functions with normal forms are convertible if and only if they have the *same* normal form.
- The convertibility relations are not necessary to explain the computational behavior of λ-expressions.

---

### Expressiveness

- The untyped λ-calculus is Turing-complete.
- Objects such as the natural numbers, booleans and so forth can be represented as λ-terms.
- To derive recursive functions, we need to be able to solve equations of the form $`f:=Rf`$ where $`R`$ is a λ-terms.
- **Fixed-point combinators** ($`F`$) solve the equation $`f:=Rf`$. Thus, $`FR↠R(FR)`$.

---

### Typed λ-Calculus

- The untyped λ-calculus is characterized by
	- Powerful representatives of all the common base types and their combinations under standard type-forming operations.
	- The presence of non-termination since not every term has even a weak head normal form.
- Given a set $`B`$ of base types, we form the set $`S`$ of **simple types** closing under the rule of function type formation - If $`σ`$ and $`τ`$ are types, then so is $`(σ\implies τ)`$.
- The expressions ($`e`$) of the typed λ-calculus have three forms:
	- **Variables** ($`x`$)
	- **Applications**: If $`e_1:(σ\implies τ)`$ and $`e_2:σ`$, then $`(e_1e_2):τ`$.
	- **Abstractions**: If $`x:σ`$ and $`e:τ`$, then $`(λx.e):(σ\implies τ)`$.
- **Strong Normalization Theorem**: Every reduction sequence terminates.
	- The system is less expressive than the untyped calculus.
- **Type assumption (declaration)**: When a variable is used, it is associated with a type.
- **Type context** ($`Γ`$): Types are assigned to expressions in the type context of a number of type assumption.
- All contexts $`Γ`$ are *consistent* in containing at most one occurrence of each variable.

---

### Strong Normalization

- **Reducibility** method involves an induction over the complexity of the types, rather than over syntactic complexity.
- **Strong Normalization Theorem**: For all expressions $`e`$ of the simply typed λ-calculus, all reduction sequences beginning with $`e`$ are finite.
- The method of **induction over types** states that to prove the result $`P(τ)`$ for all types $`τ`$ it is sufficient to prove
	- *Base case*: For all base types $`σ\in B`$, $`P(σ)`$ holds.
	- *Induction step*: If $`P(σ)`$ and $`P(τ)`$ hold, then $`P(σ\implies τ)`$ holds.
- An expression $`e`$ of type $`τ`$ is **stable** (denoted by $`e\in\|τ\|`$) if either
	- $`e`$ is of base type and $`e`$ is strongly normalizing.
	- $`e`$ is of type $`σ\implies τ`$ and for all $`e\in\|σ\|`$, $`(ee)\in\|τ\|`$.
- Stability for a function type is defined in terms of stability for its domain and range types.
- **Lemma**:
	- $`x\in\text{SN}`$.
	- If $`e_1,...,e_n\in\text{SN}`$, then $`xe_1...e_n\in\text{SN}`$.
	- If $`ex\in\text{SN}`$, then $`e\in\text{SN}`$.
	- If $`e\in\text{SN}`$, then $`(λx.e)\in\text{SN}`$.
- **Lemma**:
	- If $`e\in\|τ\|`$, then $`e\in\text{SN}`$.
	- If $`xe_1...e_n:τ`$ and $`e_1,...,e_n\in\text{SN}`$, then $`xe_1...e_n\in\|τ\|`$.
	- If $`x:τ`$, then $`x\in\|τ\|`$.
- **s-instance**: A **s-instance** $`e'`$ of an expression $`e`$ is a substitution instance $`e'≡e[e_1/x_1,...,e_n/x_n]`$ where $`e_1,...,e_n`$ are stable expressions.
- **Lemma**:
	- If $`e_1`$ and $`e_2`$ are stable, then so is $`(e_1e_2)`$.
	- For all $`n≥0`$, if $`e[e'/x]e_1...e_n\in\|τ\|`$ and $`e'\in\text{SN}`$, then $`(λx.e)e'e_1...e_n\in\|τ\|`$.
	- All s-instances $`e'`$ of expressions $`e`$ are stable.

---

### Further Type Constructors: The Product

- **Product**:
	- $`σ\times τ`$ is a type if $`σ`$ and $`τ`$ are.
	- **Pairs**: If $`x:σ`$ and $`y:τ`$, then $`(x,y):σ\times τ`$.
- **Projections**: If $`p:σ\times τ`$, then
	- $`\text{first}\ p:σ`$ where $`\text{first}`$ returns the first element of $`p`$.
	- $`\text{second}\ p:τ`$ where $`\text{second}`$ returns the second element of $`p`$.
- The rules of reduction:
	- *Computation (β-reduction) rules*: $`\text{first}(x,y)→x`$ and $`\text{second}(x,y)→y`$.
	- *Equivalence (η-reduction) rules*: $`(\text{first}\ p,\text{second}\ p)→p`$.
- **Extensionality**: An element of a product type is characterized by its components.
- The operations $`\text{first}`$ and $`\text{second}`$ as primitives:
	- $`\text{first}:(σ\times τ)\implies σ`$.
	- $`\text{second}:(σ\times τ)\implies τ`$.

---

### Base Types: Natural Numbers

- **Numbers**:
	- $`\mathbb{N}`$ is in the set of base types $`B`$.
	- $`0:\mathbb{N}`$, and if $`n:\mathbb{N}`$, then $`\text{successor}\ n:\mathbb{N}`$.
- **Primitive recursion**: For all types $`τ`$, if $`e_0:τ`$ and $`f:(\mathbb{N}\implies τ\implies τ)`$, then $`Re_0f:\mathbb{N}\implies τ`$ where $`R`$ is the **primitive recursor**.
- The rules of reduction:
	- $`Re_0f0→e_0`$.
	- $`Re_0f(n+1)→fn(Re_0fn)`$.
- $`R`$ that represents a natural number $`n:\mathbb{N}`$ is a function that maps any function $`f`$ to its $`n`$-fold composition.

---

### General Recursion

- **General recursion**: A **general recursor** $`R`$ also has the property that $`Rf→f(Rf)`$.
- $`Rf`$ is a *fixed point* of the functional $`f`$.

---

### Evaluation Revisited

- Final results of programs are non-functional.
- **Order** ($`∂`$) of a type $`τ`$ (denoted as $`∂(τ)`$) is defined as:
	- $`∂(τ)=0`$ if $`τ\in B`$.
	- $`∂(σ\times τ)=\max(∂(σ),∂(τ))`$.
	- $`∂(σ\implies τ)=\max(∂(σ)+1,∂(τ))`$.
- The terms we evaluate are not only zeroth-order (**ground types**), they also have the second property of being closed containing as they do no free variables. The results will thus be closed (β-)normal forms of zeroth-order type. It is these that we call the *printable* values.

---

## Constructive Mathematics

- [Existence and Logic](#existence-and-logic)
- [Mathematical Objects](#mathematical-objects)
- Formalizing Constructive Mathematics
- [Conclusion](#conclusion)

---

### Existence and Logic

- Systems of constructive logic do not include the *law of the excluded middle* and *double negation elimination*.
- Sanction for proof by contradiction is given by the *law of the excluded middle*.
- An idealistic view of truth: every statement is seen as true or false, independently of any evidence either way.
- Bishops states that the classical theorem that every bounded non-empty set of reals has a least upper bound not only seems to depend for its proof upon non-constructive reasoning, it implies certain cases of the law of the excluded middle which are *not* constructively valid.
- Not only will a constructive mathematics depend upon a different logic, but also it will not consist of the same results.
- The negation of a formula $`\lnot A`$ can be defined to be an implication $`A\implies\bot`$.
- A proof of a negated formula has no computational content.
- To give a proof of an existential statement $`\exists x.P(x)`$, we have to give a **witness** $`a`$ and the proof of $`P(a)`$.
- A constructive proof of $`\exists x.P(x)\lor\lnot\exists x.P(x)`$ constitutes a demonstration of the *limited principle of omniscience*.

---

### Mathematical Objects

- The nature of objects in classical mathematics is simple: everything is a set.
- Every object in constructive mathematics is either finite or has a finitary description.
- Constructive mathematics is naturally typed.
- Two algorithms are deemed equal if they give the same results on every input (the *extensional* equality on the function space).
- **Principle of Complete Presentation**: If an object is supposed to have a certain type, then that object should contain sufficient witnessing information so that the assertion can be verified.
- Negative assertions should be replaced by positive assertions whenever possible.

---

### Conclusion

- Objects are given by rules, and the validity of an assertion is guaranteed by a proof from which we can extract relevant computational information, rather than on idealist semantic principles.

---

## Introduction to Type Theory

- [Propositional Logic: An Informal View](#propositional-logic-an-informal-view)
- Judgements, Proofs and Derivations
- [The Rules for Propositional Calculus](#the-rules-for-propositional-calculus)
- [The Curry Howard Isomorphism](#the-curry-howard-isomorphism)
- Some Examples
- [Quantifiers](#quantifiers)
- [Base Types](#base-types)
- [The Natural Numbers](#the-natural-numbers)
- [Well-founded Types: Trees](#well-founded-types-trees)
- [Equality](#equality)
- [Convertibility](#convertibility)

---

- Central to type theory is the duality between propositions and types, proofs and elements: a proof of a proposition $`T`$ can be seen as a member of the type $`T`$, and conversely.
- Infinite data types are characterized by principles of definition by recursion and proof by induction.
- A proof by induction is nothing other than a proof object defined using recursion.
- Our system gives an *integrated* treatment of programming and verification.

---

### Propositional Logic: An Informal View

- $`A\land B`$: A proof of $`A\land B`$ will be a pair of proofs $`p`$ and $`q`$, $`p:A`$ and $`q:B`$.
- $`A\lor B`$: A proof of $`A\lor B`$ will either be a proof of $`A`$ or be a proof of $`B`$, together with an indication of which formula the proof is of.
- $`A\implies B`$: A proof of $`A\implies B`$ consists of a method or function which transforms any proof of $`A`$ into a proof of $`B`$.

---

### The Rules for Propositional Calculus

- Each connective has its formation, introduction, elimination, and computation rule.
- Rules for $`\land`$:
	- Formation: If $`A`$ is a formula and $`B`$ is a formula, then $`(A\land B)`$ is a formula.
	- Introduction: If $`p:A`$ and $`q:B`$, then $`(p,q):(A\land B)`$.
	- Elimination:
		- If $`r:(A\land B)`$, then $`\text{first}\ r:A`$.
		- If $`r:(A\land B)`$, then $`\text{second}\ r:B`$.
	- Computation:
		- $`\text{first}(p,q) → p`$.
		- $`\text{second}(p,q) → q`$.
- Rules for $`\lor`$:
	- Formation: If $`A`$ is a formula and $`B`$ is a formula, then $`(A\lor B)`$ is a formula.
	- Introduction:
		- If $`q:A`$, then $`\text{inl}\ q:(A\lor B)`$.
		- If $`r:B`$, then $`\text{inr}\ r:(A\lor B)`$.
	- Elimination: If $`p:(A\lor B)`$, $`f:(A\implies C)`$, and $`g:(B\implies C)`$, then $`\text{cases}\ pfg:C`$.
	- Computation:
		- $`\text{cases}(\text{inl}\ q)fg→fq`$.
		- $`\text{cases}(\text{inr}\ r)fg→gr`$.
- Rules for $`\implies`$:
	- Formation: If $`A`$ is a formula and $`B`$ is a formula, then $`(A\implies B)`$ is a formula.
	- Introduction: If from the assumption $`x:A`$ the conclusion $`e:B`$ is derived, then $`(λx:A)e:(A\implies B)`$.
	- Elimination: If $`q:(A\implies B)`$ and $`a:A`$, then $`(qa):B`$.
	- Computation: $`((λx:A)e)a→e[a/x]`$.
- Rules for $`\bot`$:
	- Formation: $`\bot`$ is a formula.
	- Elimination: If $`p:A`$, then $`\text{abort}\ p:A`$.
- Rule of Assumption: If $`A`$ is a formula, then $`x:A`$.

---

### The Curry Howard Isomorphism

- Under the isomorphism, types correspond to propositions and members of those types to proofs.
- The rules are seen to explain:
	- Formation rule: What the types of the system are.
	- Introduction and Elimination rules: Which expressions are members of which types.
	- Computation rule: How these objects can be reduced to simpler forms, i.e. how we can evaluate expressions.
- Rules for $`\land`$:
	- Formation: If $`A`$ is a type and $`B`$ is a type, then $`(A\land B)`$ is a type.
	- Introduction: If $`p:A`$ and $`q:B`$, then $`(p,q):(A\land B)`$.
	- Elimination:
		- If $`r:(A\land B)`$, then $`\text{first}\ r:A`$.
		- If $`r:(A\land B)`$, then $`\text{second}\ r:B`$.
	- Computation:
		- $`\text{first}(p,q) → p`$.
		- $`\text{second}(p,q) → q`$.
- Rules for $`\lor`$:
	- Formation: If $`A`$ is a type and $`B`$ is a type, then $`(A\lor B)`$ is a type.
	- Introduction:
		- If $`q:A`$, then $`\text{inl}\ q:(A\lor B)`$.
		- If $`r:B`$, then $`\text{inr}\ r:(A\lor B)`$.
	- Elimination: If $`p:(A\lor B)`$, $`f:(A\implies C)`$, and $`g:(B\implies C)`$, then $`\text{cases}\ pfg:C`$.
	- Computation:
		- $`\text{cases}(\text{inl}\ q)fg→fq`$.
		- $`\text{cases}(\text{inr}\ r)fg→gr`$.
- Rules for $`\implies`$:
	- Formation: If $`A`$ is a type and $`B`$ is a type, then $`(A\implies B)`$ is a type.
	- Introduction: If from the assumption $`x:A`$ the conclusion $`e:B`$ is derived, then $`(λx:A)e:(A\implies B)`$.
	- Elimination: If $`q:(A\implies B)`$ and $`a:A`$, then $`(qa):B`$.
	- Computation: $`((λx:A)e)a→e[a/x]`$.
- Rules for $`\bot`$:
	- Formation: $`\bot`$ is a type.
	- Elimination: If $`p:A`$, then $`\text{abort}\ p:A`$.
- Rule of Assumption: If $`A`$ is a type, then $`x:A`$.

---

### Quantifiers

- Rules for $`\forall`$:
	- Formation: If $`A`$ is a formula and from the assumption $`x:A`$ the conclusion $`P`$ is a formula, then $`(\forall x:A).P`$ is a formula.
	- Introduction: If from the assumption $`x:A`$ the conclusion $`p:P`$ is derived, then $`(λx:A)e:(\forall x:A).P`$.
	- Elimination: If $`a:A`$ and $`f:(\forall x:A).P`$, then $`fa:P[a/x]`$.
	- Computation: $`((λx:A)p)a→p[a/x]`$.
- Rules for $`\exists`$:
	- Formation: If $`A`$ is a formula and from the assumption $`x:A`$ the conclusion $`P`$ is a formula, then $`(\exists x:A).P`$ is a formula.
	- Introduction: If $`a:A`$ and $`p:P[a/x]`$, then $`(a,p):(\exists x:A).P`$.
	- Elimination:
		- If $`p:(\exists x:A).P`$, then $`\text{first}\ p:A`$.
		- If $`p:(\exists x:A).P`$, then $`\text{second}\ p:P[\text{first}\ p/x]`$.
	- Computation:
		- $`\text{first}(p,q) → p`$.
		- $`\text{second}(p,q) → q`$.

---
