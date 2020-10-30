# Bloch's Effective Java

- **Publisher**: Addison-Wesley Professional
- **Author**: Joshua Bloch
- **Presenter**: Wen-Bin Luo
- **Link**: https://www.amazon.com/Effective-Java-Joshua-Bloch-ebook/dp/B078H61SCH

## Contents

- Introduction
- [Creating and Destroying Objects](#creating-and-destroying-objects)

## Creating and Destroying Objects

- [Consider static factory methods instead of constructors](#consider-static-factory-methods-instead-of-constructors)
- [Consider a builder when faced with many constructor parameters](#consider-a-builder-when-faced-with-many-constructor-parameters)

### Consider static factory methods instead of constructors

- Static factory methods have names.
- Static factory methods are not required to create a new object each time they’re invoked.
- Static factory methods can return an object of any subtype of their return type.
- The class of the returned object can vary from call to call as a function of the input parameters.
- The class of the returned object need not exist when the class containing the method is written.
- Classes providing only static factory methods (without public or protected constructors) cannot be subclassed.
- Static factory methods are hard for programmers to find.

### Consider a builder when faced with many constructor parameters

- The **telescoping constructor pattern** does not scale well to large numbers of optional parameters.
- A **JavaBean pattern** may be in an inconsistent state partway through its construction.
- The JavaBeans pattern precludes the possibility of making a class immutable.
- The **builder pattern** combines the safety of the telescoping constructor pattern with the readability of the JavaBeans pattern.
- The Builder pattern simulates named optional parameters.
- To detect invalid parameters as soon as possible, check parameter validity in the builder’s constructor and methods.
- The Builder pattern is well suited to class hierarchies.
- **Covariant return typing**: a subclass method is declared to return a subtype of the return type declared in the superclass.
- The builder pattern is a good choice when designing classes whose constructors or static factories would have more than a handful of parameters.
