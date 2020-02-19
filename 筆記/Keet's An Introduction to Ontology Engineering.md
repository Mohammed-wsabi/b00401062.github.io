# Keet's An Introduction to Ontology Engineering

---

## Contents

- [Introduction](#introduction)

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
