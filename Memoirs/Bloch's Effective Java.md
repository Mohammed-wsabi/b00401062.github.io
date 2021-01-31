# Bloch's Effective Java

- **Publisher**: Addison-Wesley Professional
- **Author**: Joshua Bloch
- **Presenter**: Wen-Bin Luo
- **Link**: https://www.amazon.com/Effective-Java-Joshua-Bloch-ebook/dp/B078H61SCH

## Contents

- Introduction
- [Creating and Destroying Objects](#creating-and-destroying-objects)
- [Methods Common to All Objects](#methods-common-to-all-objects)

## Creating and Destroying Objects

- [Consider static factory methods instead of constructors](#consider-static-factory-methods-instead-of-constructors)
- [Consider a builder when faced with many constructor parameters](#consider-a-builder-when-faced-with-many-constructor-parameters)
- [Enforce the singleton property with a private constructor or an enum type](#enforce-the-singleton-property-with-a-private-constructor-or-an-enum-type)
- [Enforce noninstantiability with a private constructor](#enforce-noninstantiability-with-a-private-constructor)
- [Prefer dependency injection to hardwiring resources](#prefer-dependency-injection-to-hardwiring-resources)
- [Avoid creating unnecessary objects](#avoid-creating-unnecessary-objects)
- [Eliminate obsolete object references](#eliminate-obsolete-object-references)
- [Avoid finalizers and cleaners](#avoid-finalizers-and-cleaners)
- [Prefer try-with-resources to try-finally](#prefer-try-with-resources-to-try-finally)

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

### Enforce the singleton property with a private constructor or an enum type

- Making a class a singleton can make it difficult to test its clients.
- The singleton pattern keeps the constructor private and exports a public static member to provide access to the sole instance.
- To maintain the singleton guarantee, declare all instance fields transient and provide a `readResolve` method.
- Enum singleton provides an ironclad guarantee against multiple instantiation.
- A single-element enum type is often the best way to implement a singleton.

### Enforce noninstantiability with a private constructor

- A class can be made noninstantiable by including a private constructor.
- Such classes can be used to group static methods or methods on a final class.
- Attempting to enforce noninstantiability by making a class abstract does not work.
- The private constructor prevents the class from being subclassed.

### Prefer dependency injection to hardwiring resources

- Inappropriate use of singleton makes the class inflexible and untestable.
- Static utility classes and singletons are inappropriate for classes whose behavior is parameterized by an underlying resource.
- **Dependency injection**: pass the resource into the constructor when creating a new instance.
- Dependency injection provides flexibility and testability.
- Dependency injection is applicable to constructors, static factories, and builders.

### Avoid creating unnecessary objects

- Avoid creating unnecessary objects by using static factory methods.
- Lazy initialization would complicate the implementation with no measurable performance improvement.
- Autoboxing blurs but does not erase the distinction between primitive and boxed primitive types.
- Prefer primitives to boxed primitives, and watch out for unintentional autoboxing.

### Eliminate obsolete object references

- An obsolete reference is simply a reference that will never be dereferenced again.
- Memory leaks in garbage-collected languages (more properly known as **unintentional object retentions**) are insidious.
- Null out references once they become obsolete.
- Nulling out object references should be the exception rather than the norm.
- The best way to eliminate an obsolete reference is to let the variable that contained the reference fall out of scope.
- Whenever a class manages its own memory, the pro- grammer should be alert for memory leaks.
- A third common source of memory leaks is listeners and other callbacks.

### Avoid finalizers and cleaners

- Finalizers are unpredictable, often dangerous, and generally unnecessary.
- Cleaners are less dangerous than finalizers, but still unpredictable, slow, and generally unnecessary.
- There is no guarantee finalizers and cleaners will be executed promptly.
- Never do anything time-critical in a finalizer or cleaner.
- The promptness with which finalizers and cleaners are executed is primarily a function of the garbage collection algorithm.
- Never depend on a finalizer or cleaner to update persistent state.
- An uncaught exception thrown during finalization is ignored, and finalization of that object terminates.
- There is a severe performance penalty for using finalizers and cleaners.
- Finalizers open your class up to **finalizer attacks**.
- Unlike finalizers, cleaners do not pollute a class’s public API.
- Have your class implement `AutoCloseable`!
- The behavior of cleaners during `System.exit` is implementation specific.

### Prefer try-with-resources to try-finally

- Try-finally is no longer the best way to close resources!
- To be usable with the try-with-resources construct, a resource must implement the `AutoCloseable` interface.

## Methods Common to All Objects

- [Obey the general contract when overriding equals](#obey-the-general-contract-when-overriding-equals)
- [Always override hashCode when you override equals](#always-override-hashcode-when-you-override-equals)
- [Always override toString](#always-override-tostring)
- [Override clone judiciously](#override-clone-judiciously)

### Obey the general contract when overriding equals

- Each instance of the class is inherently unique.
- There is no need for the class to provide a "logical equality" test.
- A superclass has already overridden equals, and the superclass behavior is appropriate for this class.
- The class is private or package-private,and you arec ertain that its equals method will never be invoked.
- The equals method implements an *equivalence relation*.
- Once the equals contract is violated, the behavior of other objects is undefined when confronted with your object.
- There is no way to extend an instantiable class and add a value component while preserving the equals contract.
- A recipe for a high-quality equals method:
    - Use the == operator to check if the argument is a reference to this object.
    - Use the instanceof operator to check if the argument has the correct type.
    - Cast the argument to the correct type.
    - For each “significant” field in the class, check if the fields of the two objects match.
- Always override hashCode when you override equals.
- Don’t substitute another type for Object in the equals declaration.

### Always override hashCode when you override equals

- You must override hashCode in every class that overrides equals.
- General contract for `hashCode`: equal objects must have equal hash codes.
- If every object has the same hash code, hash tables degenerate to linked lists.
- Any fields that are not used in equals comparisons should be excluded.
- Never exclude significant fields from the hash code com- putation to improve performance.

### Always override toString

- The benefits of providing a good `toString` implementation:
    - Make your class much more pleasant to use.
    - Make systems using the class easier to debug.
- When practical, the `toString` method should return all of the interesting information contained in the object.
- Whether or not you decide to specify the format, you should clearly document your intentions.

### Override clone judiciously

- Behaviors of the `clone` method:
    - If a class implements `Cloneable`, `Object`’s `clone` method returns a field-by-field copy of the object
    - Otherwise, it throws `CloneNotSupportedException`.
- `Object`’s `clone` method is protected. Therefore, `clone` cannot be invoked on an object merely because it implements `Cloneable`.
- A class implementing `Cloneable` is expected to provide a properly functioning public clone method.
- The general contract for the clone method is weak:
    - `x.clone() != x`
    - `x.clone().getClass() == x.getClass()`
    - `x.clone().equals(x)`
- By convention, the object returned by the `clone` method should be obtained by calling `super.clone`
- The `Cloneable` architecture is incompatible with normal use of final fields referring to mutable objects,
- Public `clone` methods should omit the `throws` clause,
- A better approach to object copying is to provide a *copy constructor* or *copy factory*.
