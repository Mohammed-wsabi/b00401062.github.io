# Keet's An Introduction to Ontology Engineering

---

## Contents

- [Introduction](#introduction)
- [First order logic and automated reasoning in a nutshell](#first-order-logic-and-automated-reasoning-in-a-nutshell)

---

## Introduction

- What does an ontology look like?
- [What is an ontology?](#what-is-an-ontology)
- [What is the usefulness of an ontology?](#what-is-the-usefulness-of-an-ontology)
- Outline and usage of the book

---

### What is an ontology?

- Comparison between conceptual data models and ontologies:
    - Conceptual data models provide an application-speciﬁc implementation-independent representation of the data that will be handled by the prospective application.
    - Ontologies provide an application-independent representation of a speciﬁc subject domain (re)usable by multiple applications.
- Comparison between relational databases and ontologies:
    - Ontologies (knowledge bases) include the representation of the knowledge explicitly to infer implicit knowledge and detect inconsistencies of the knowledge base.
- Comparison between the "semantic" side and the “pragmatic” side of ontologies:
    - Semantic: the meaning represented in the ontology.
    - Pragmatic: the practicalities of using ontologies.
- Definitions of ontology:
    - An ontology is a specification of a conceptualization.
    - An ontology is a formal, explicit specification of a shared conceptualization.
    - An ontology is a logical theory accounting for the in- tended meaning of a formal vocabulary.
    - An ontology being equivalent to a Description Logic knowledge base.
- Ontologies, like software code, can have bugs or not even compile:
    - 'Not compile': There is a violation of the syntax.
    - 'Bugs':
        - There can be errors in the sense that a class cannot have any instances due to conﬂicting constraints.
        - There can be semantic errors in that what has been represented is logically correct, but entirely unintended.
- Qualities of ontologies:
    - Good: high precision and maximum coverage.
    - Less good: low precision and maximum coverage.
    - Bad: maximum precision and limited coverage.
    - Worse: low precision and limited coverage.

---

### What is the usefulness of an ontology?

- Data integration: schema-based v.s. data-based.
- How to prevent the integration problems form happening in the first place? Through generating conceptual models for related new applications based on the knowledge represented in the ontology.

---

## First order logic and automated reasoning in a nutshell

- [First order logic syntax and semantics](#first-order-logic-syntax-and-semantics)

---

### First order logic syntax and semantics

- The lexicon of a ﬁrst order language contains the following: Connectives, Quantiﬁers, Variables, Constants, Functions, Relations.
- **Term** ($`t`$) is inductively deﬁned by two rules:
    - Every variable and constant is a term.
    - $`f(t_1,...,t_m)`$ is a term where $`f`$ is an $`n`$-ary function and $`t_1,...,t_n`$ are terms.
- **Formula** ($`φ`$) is define as:
    - $`R(t_1,...,t_n)`$ is a formula where $`R`$ is an $`n`$-ary relation and $`t_1,...,t_n`$ are terms.
    - $`¬φ`$ is a formula where $`φ`$ is a formula.
    - $`φ_1∧φ_2`$ is a formula where $`φ_1`$ and $`φ_2`$ are formulas.
    - $`∀xφ`$ and $`∃xφ`$ are formulas where $`φ`$ is a formula and $`x`$ is a variable.
- **Sentence** ($`φ`$): A formula having no free variables.
- **Structure** ($`M`$) consists of an underlying set together with an interpretation of functions, constants, and relations.
- **Vocabulary** ($`V`$): A set of function, relation, and constant symbols.
- **V-formula**: A formula in which every function, relation, and constant is in $`V`$.
- **V-sentence**: A $`V`$-formula that is a sentence.  
- **V-structure**: A structure that consists of a non-empty underlying set $`∆`$ along with an interpretation of $`V`$.
- $`M`$ is a structure if it is a $`V`$-structure of some vocabulary $`V`$.
- $`M`$ models $`φ`$ ($`M⊨φ`$): Given a $`V`$-sentence $`φ`$ and a $`V`$-structure $`M`$, the sentence $`φ`$ is true with respect to $`M`$.
- **Model theory** is about:
    - The interplay between $`M`$ and a set of first-order sentences.
    - Its inverse from a set of sentences Γ to a class of structures.
- **Theory** of $`M`$, or $`T(M)`$: For any $`V`$-structure $`M`$, $`T(M)`$ is the set of all $`V`$-sentences $`φ`$ such that $`M⊨φ`$.
- **Model** of $`Γ`$, or $`M(Γ)`$: For any set $`Γ`$ of $`V`$-sentences, $`M(Γ)`$ is the class of all $`V`$-structures $`M`$ such that $`M`$ models each sentence in $`Γ`$.
- **Complete V-theory**: Let $`Γ`$ be a set of $`V`$-sentences. Then $`Γ`$ is a complete $`V`$-theory if, for any $`V`$-sentence $`φ`$ either $`φ`$ or $`¬φ`$ is in $`Γ`$.
- Theorem: For any $`V`$-structure $`M`$, $`T(M)`$ is a complete $`V`$-theory.
- **Consistency**: A set of sentences $`Γ`$ is said to be **consistent** if no contradiction can be derived from $`Γ`$.
- A theory is a consistent set of sentences.

---
