# Inheritance

## Learning Objectives:

* Understand what is meant by “polymorphism” in relation to object-oriented programming
* Understand how to implement inheritance in Java
* Be able to override a method in Java using the `@Override` annotation
* Understand what an "abstract class" is
* Understand what an "inheritance chain" is
* Understand how to use the `protected` access modifier

## Introduction

Even with meticulous planning we will often find ourselves in situations where there is a lot of repetition in our code. Some of that may be down to subtle differences in what is needed from various methods, some of it may be due to strong similarities between two or more classes. In some cases we may be able to find a level of abstraction such that we can define a single class with those shared properties and behaviours, but this comes with its own problems.

Having a class with a suitable broad definition can lead to extra properties being required. For example, if we were to define a `Vehicle` class we would need a property telling us if it was a car, train or rowing boat. We would also have some difficulty with the behaviours; it makes sense for a boat to have a `row()` method, but that would be nonsense for a train. That suggests that one broad class is a bad idea, but it *does* make it possible to define shared attributes in one place. In an ideal world we would have a way to define each of our vehicles separately but also show that they share those attributes.

In object-oriented programming this ideal way is called **polymorphism**. From the Greek words *poly* and *morph* ("many" and "forms"), if we implement our classes in such a way as to make them *polymorphic* we make it possible to refer to them by **more than one data type**. In this lesson we will look at one of the common ways of doing this: **inheritance**.


## A World Without Inheritance

In this session we are going to model a zoo with classes representing the animals and one for the zoo itself. A suggested class diagram is shown below.

![UML for zoo start point](../../../assets/java/inheritance/zoo_animal.png)


Our zoo is pretty simple at the moment with the only property being a collection of `Animal`s. We can build our classes according to the diagram and write some tests to ensure the methods behave as expected.

```java title="Animal.java"
// Animal.java

public class Animal{

	private String name;
	private String type;
	
	public Animal(String name, String type){
		this.name = name;
		this.type = type;
	}
	
	public String makeNoise(){
		return "Hello, my name is " + this.name + ".";
	}
	
	public eat(){
		return "Mmm, that was tasty!";
	}

}
```

```java title="AnimalTest.java"
// AnimalTest.java

public class AnimalTest{

	Animal animal
	
	@BeforeEach
	void setUp(){
		animal = new Animal("Simba", "lion");
	}
	
	@Test
	void canMakeNoise(){
		assertThat(animal.makeNoise()}.isEqualTo("Hello, my name is Simba.");
	}
	
	@Test
	void canEat(){
		assertThat(animal.eat()).isEqualTo("Mmm, that was tasty!");
	}

}
```

```java title="Zoo.java"
// Zoo.java

public class Zoo{

	private ArrayList<Animal> animals;
	
	public Zoo(){
		this.animals = new ArrayList<>();
	}
	
	public int countAnimals(){
		return this.animals.size();
	}
	
	public void addAnimal(Animal animal){
		this.animals.add(animal);
	}

}
```

```java title="ZooTest.java"

public class ZooTest{

	Zoo zoo;
	
	@BeforeEach
	void setUp(){
		zoo = new Zoo();
	}
	
	@Test
	void canCountAnimals(){
		assertThat(zoo.countAnimals()).isEqualTo(0);
	}
	
	@Test
	void canAddAnimal(){
		Animal animal = new Animal("Simba", "lion");
		zoo.addAnimal(animal);
		assertThat(zoo.countAnimals()).isEqualTo(1);
	}

}
```

The only differences between any `Animal` objects we create are the values associated with their properties. That means we can represent different types of animal just by passing different arguments to the constructor.

```java title="ZooTest.java"

public class ZooTest{

	// ...
		
	@Test
	void canAddMultipleAnimals(){
		Animal lion = new Animal("Scar", "lion");
		Animal parrot = new Parrot("Zazu", "parrot");
		zoo.addAnimal(lion);
		zoo.addAnimal(parrot);
		assertThat(zoo.countAnimals()).isEqualTo(2);
	}

}
```

Things start to get muddled when we need to be more specific with properties or behaviours for our animals. Any method defined in `Animal` will be accessible to any object of that type, so if we want our parrot to have a `fly()` method then our lions (or anything else) will be able to `fly()` too. There are a few ways in which we can address this, one of which is **inheritance**



## Adding a Child Class

When we implement inheritance we are defining a relationship between two classes. One class becomes a **parent** (or **superclass**) and defines some baseline properties and behaviours, the other becomes a **child** (or **subclass**) and can either use those baselines, expand on them or replace them with its own definition. The child class *inherits* everything defined within the parent, crucially including the type.

We will add our first child class by defining a `Lion` using the `extends` keyword. By including this in the class declaration we specify that `Lion`will be a subclass of whatever it extends, in this case `Animal`.

```java title="Lion.java"
// Lion.java

public class Lion extends Animal{
        
}

```

Our compiler immediately throws an error, telling us that we have "no default constructor" available to us. Previously we could have instantiated a new `Lion` without defining a constructor but since we are extending `Animal` the definition for the constructor is passed down to `Lion`. We can't use it explicitly though, that would just create another `Animal` object. First we will edit `Animal` to remove the `type` property (since that is now redundant) before using the constructor inside `Lion`.

```java title="Animal.java"
// Animal.java

public class Animal{

	private String name;
	
	public Animal(String name){			// MODIFIED
		this.name = name;
	}
	
	// ...

}
```

> Also need to update tests

```java title="Lion.java"
// Lion/java

public class Lion extends Animal{

	public Lion(String name){			// ADDED
		super(name);
	}

}

```

When we create a new `Lion` object this constructor will be called, but since `Lion` is extending `Animal` and taking on the additional data type we also need to call the `Animal` constructor to handle those properties. The `super` method enables us to pass certain values, either passed as arguments or hard-coded, up to the superclass' constructor. By doing this we give `Lion` all of the properties and all of the behaviours defined in `Animal`. We can confirm it by writing a test file specifically for `Lion`.

```java title="LionTest.java"
// LionTest.java

public class LionTest{

	Lion lion;
	
	@BeforeEach
	void setUp(){
		lion = new Lion("Simba");
	}
	
	@Test
	void canMakeNoise(){
		assertThat(lion.makeNoise()).isEqualTo("Hello, my name is Simba.");
	}
	
	@Test
	void canEat(){
		assertThat(lion.eat()).isEqualTo("Mmm, that was tasty!");
	}

}

```

The tests pass even though neither behaviour has been explicitly defined for `Lion`. Note also that `makeNoise()` depends on a property which hasn't been defined either. Everything defined for `Animal` works for `Lion` too. Giving objects multiple data types in this way sets up what is known as an "**is a**" relationship - a `Lion` *is an* `Animal`. This only works one way, we can't say that an `Animal` *is a* `Lion`.

We aren't limited to the properties defined in the parent class. A subclass can add its own properties and behaviours exclusive to it. We can add them in the same way as if there was no inheritance, the only restriction is that the `super()` call *must* be the first thing in the body of the constructor.

```java title="Lion.java"
// Lion/java

public class Lion extends Animal{

	private boolean canWaitToBeKing;			// ADDED

	public Lion(String name, boolean canWaitToBeKing){			// MODIFIED
		super(name);
		this.canWaitToBeKing = canWaitToBeKing;
	}

}

```

> Update `LionTest`

## Overriding a Parent's Behaviour

Often we will find ourselves in a scenario where we want a subclass to be able to do something defined in the superclass, but not necessarily in exactly the same way. Our `Lion` is a case in point, maybe we don't want it to greet us by saying its name? Maybe instead we want to return a more appropriate noise for all lions, but we can't redefine `makeNoise()` in `Animal` without affecting all our animals. Instead we will **override** the method in the subclass.

```java title="Lion.java"
// Lion.java

public class Lion extends Animal{

	// ...
	
	@Override
	public String makeNoise(){			// ADDED
		return "ROAR!";
	}
        
}
```

We define a method with a matching signature in the subclass, but we can redefine the method body however we wish (so long as the return type remains the same). Now when we call `makeNoise()` on a `Lion` object this version will be invoked instead of the default defined in `Animal`. That behaviour isn't lost, we can access it at any point by calling `super.makeNoise()` to call the superclass' definition. 

The `@Override` annotation here is slightly unusual when compared to other annotations. Usually an annotation indicates to the compiler and/or interpreter that some extra functionality is associated with a property or method but not in this case. `@Override` is purely cosmetic and acts as an indicator to users that the class is overriding a parent's method. If we omit it there will be no problem, however if we add it to a method which *doesn't* override anything we will see a warning at compile time.

When we re-run our tests we see that `LionTest` now fails, meaning the updated behaviour is being used. `AnimalTest` still passes, since the objects we create there have the `Animal` type and use the original definition. 

We have added a new class and so need to update our diagram to include it. When showing inheritance in UML the superclass and subclass(es) should be linked by an arrow with an empty arrowhead pointing from the subclass to the superclass. Any additional properties and methods should be included, along with any methods being overridden. Our updated diagram is shown below.

![UML for zoo with lion subclass](../../../assets/java/inheritance/zoo_with_lion.png)


## Abstract Classes

As things stand there's a bit of a disparity between the lions and the parrots. We have a `Lion` class for the lions but the parrots (and anything else) need to use the `Animal` superclass. We need to fix that by adding a `Parrot` class, including an override for `makeNoise`, plus some tests for it.

```java title="Parrot.java"
// Parrot.java

public class Parrot extends Animal{

	public Parrot(String name){
		super(name);
	}
	
	@Override
	public String makeNoise(){
		return "Squawk!";
	}

}

```

```java title="ParrotTest.java"
// ParrotTest.java

public class ParrotTest{

	Parrot parrot;
	
	@BeforeEach
	void setUp(){
		parrot = new Parrot("Zazu");
	}
	
	@Test
	void canMakeNoise(){
		assertThat(parrot.makeNoise()).isEqualTo("Squawk!");
	}
	
	@Test
	void canEat(){
		assertThat(parrot.eat()).isEqualTo("Mmm, that was tasty!");
	}

}

```

We can continue this process for any new kind of animal we want to add. That leaves `Animal` in a strange position - we need the class to act as a superclass, but we never want to make `Animal` objects. In the real world we can't picture something we would describe as simply "animal", instead we would talk about "lions", "parrots" or anything else. In programming terms we have similar scenarios where we only want to think in terms of a *concrete* implementation of something rather than an *abstract* concept.

In the real world we can't produce an abstract "animal", but in our code it's still possible to create an instance of the `Animal` class. To prevent a user from being able to do this we can modify the class declaration by adding the `abstract` keyword. This will prevent us calling the constructor from anywhere in the program other than via the `super()` method in a child class, effectively making it a "non-access" modifier.

```java title="Animal.java"
// Animal.java

public abstract class Animal {			// MODIFIED

    // ...

}

```

We have made `Animal` an **abstract class**, resulting in our compiler throwing errors in our test files telling us that "Animal is abstract, cannot be instantiated". To fix this we will need to replace the instances of `Animal` with either `Lion` or `Parrot` as appropriate. In `ZooTest` we will replace the `Animal` constructor with the appropriate sub-class but this won't be possible in `AnimalTest`. In fact, the correct course of action here is to delete `AnimalTest` completely! Since we can't instantiate an abstract class we can't create objects to test, at least not with the tools currently at our disposal. Instead we will test the subclasses to ensure all functionality behaves as expected.

```java title="ZooTest.java"

public class ZooTest{

	Zoo zoo;
	
	@BeforeEach
	void setUp(){
		zoo = new Zoo();
	}
	
	@Test
	void canCountAnimals(){
		assertThat(zoo.countAnimals()).isEqualTo(0);
	}
	
	@Test
	void canAddAnimal(){
		Animal animal = new Lion("Simba", false);			// MODIFIED
		zoo.addAnimal(lion);
		assertThat(zoo.countAnimals()).isEqualTo(1);
	}
	
	@Test
	void canAddMultipleAnimals(){
		Animal lion = new Lion("Scar", true);			// MODIFIED
		Animal parrot = new Parrot("Zazu");			// MODIFIED
		zoo.addAnimal(lion);
		zoo.addAnimal(parrot);
		assertThat(zoo.countAnimals()).isEqualTo(2);
	}

}
```

Our compiler errors have gone, but note that we have only changed the constructors being used. Our variables are being declared with the `Animal` data type but initialised with a sub-class of `Animal`. Because `Lion` and `Parrot` extend `Animal` any objects created using their constructors are **polymorphic** and take on *both* data types - a `Lion` *is an* `Animal`.

Polymorphism is useful for more than just storing objects, we can take advantage of it when passing data round our application as well. We can revisit our `canAddMultipleAnimals` test to declare the variables using the child classes instead of `Animal`.

```java title="ZooTest.java"

public class ZooTest{

	// ...
	
	@Test
	void canAddMultipleAnimals(){
		Lion lion = new Lion("Scar", true);			// MODIFIED
		Parrot parrot = new Parrot("Zazu");			// MODIFIED
		zoo.addAnimal(lion);
		zoo.addAnimal(parrot);
		assertThat(zoo.countAnimals()).isEqualTo(2);
	}

}
```

The test still passes! The `lion` and `parrot` objects are no longer explicitly declared as `Animal` objects but they are still polymorphic, so still have `Animal` as a datatype. The `addAnimal` method's signature requires the arguments passed to it to have the `Animal` type but doesn't care about any others the object may or may not have. Our objects satisfy that criteria, so the test will pass.

We can indicate that a class is abstract in UML in a couple of ways. In printed diagrams the name of the class is often italicised, but this doesn't translate well into hand-drawn diagrams where a double set of angle brackets is often seen. The two are used interchangeably and sometimes together.

![UML for zoo with abstract animal](../../../assets/java/inheritance/zoo_abstract_animal.png)


## Chaining Inheritance

Polymorphism doesn't just mean "two forms", remember it comes from the Greek for "*many* forms". In our code we aren't limited to a class having two data types, they can have as many as they need to within the constraints of our design. As we add more and more animals to our zoo the need for this becomes more apparent.

Let's expand our selection and add another type of bird.

```java title="Vulture.java"
// Vulture.java

public class Vulture extends Animal{

	public Vulture(String name){
		super(name);
	}
	
	public String makeNoise(){
		return "Grunt!";
	}

}

```

So far so good, but eventually we're going to get back to a situation where we are repeating a lot of code between `Parrot`, `Vulture` and any other bird classes we create. We have a method for abstracting away shared methods, but we can't move any bird-specific behaviour up to `Animal` or *every* subclass of `Animal` will inherit that behaviour. We can, however, add another superclass between our birds and `Animal` which every bird can inherit from.

We'll call this class `Bird` and have it extend `Animal`. `Parrot` and `Vulture` will in turn extend `Bird`. At first we will notice no difference but we now have an extra place in which to define properties and behaviours. For example, we could have our birds lay eggs.

```java title="Bird.java"
// Bird.java

public abstract class Bird extends Animal{

	public Bird(String name){
		super(name);
	}
	
	public String layEgg(){
		return "I laid an egg!";
	}

}

```

```java title="Parrot.java"
// Parrot.java

public class Parrot extends Bird{			// MODIFIED

	// ...

}

```

```java title="Vulture.java"
// Vulture.java

public class Vulture extends Bird{			// MODIFIED

	// ...

}

```

`Bird` has been declared `abstract` since it makes no sense for us to be able to create birds in the same way as it makes no sense to create animals. Anything extending `Bird` has access to this new method *and* everything defined in `Animal`, as we can demonstrate through tests.

```java title="ParrotTest.java"
// ParrotTest.java

public class ParrotTest {

	// ...
	
	@Test
	void canLayEgg(){
		assertThat(parrot.layEgg()).isEqualTo("I laid an egg!");
	}

}

```

Our diagram can be updated with as many layers of inheritance as necessary.

![UML for zoo with abstract animal](../../../assets/java/inheritance/zoo_bird_class.png)

Repeated extension of classes like this is called an **inheritance chain** and is quite common, in fact it underpins the relationship between some of the data structures we have already worked with. It should be handled with care though, as long chains can become difficult to manage and inadvertently lead to bugs.


## Limitations of Inheritance

Inheritance isn't a cure-all for our type-related problems. It is a very powerful tool but is limited in its application, particularly if we find ourselves in a scenario where multiple parent classes could be useful.

Consider a scenario where we add a `Carnivore` abstract class into the mix. Some animals are meat-eaters, some are not, so we can't have any `Carnivore` behaviours directly inside our existing inheritance chain. Vultures are carnivores so our `Vulture` class should extend both `Bird` and our new `Carnivore` class but doing so could lead to conflicts. Consider this class diagram:

![UML for zoo with abstract animal](../../../assets/java/inheritance/diamond_problem.png)

`Vulture` needs to extend both of those classes - it's both a `Bird` and a `Carnivore` - but extending both means inheriting *everything* from both, meaning two implementations of the `eat()` method! In Java this is far from ideal, we want our programs to have a single unambiguous definition for each method. This is known as the **diamond problem** and Java's solution to it is to limit the number of parents a child can have to **one**. Some other languages permit multiple inheritance and require much stricter management of their objects than Java does. While we may not be able to inherit from multiple classes, we can use **interfaces** as a partial solution to the problem and we will discuss these in a future lesson.

We also have to consider the interaction between properties defined in a superclass and methods defined in a child. Let's try adding a `sayName()` method to our parrots.

```java title="Parrot.java"
// Parrot.java

public class Parrot extends Animal{

	// ...
	
	public String sayName(){
		return "My name is " + this.name + ".";
	}

}
```

Our compiler is throwing an error, but why? We know that `Parrot` objects have a name, if we have a getter we can call `parrot.getName()` and see a result, but accessing the property is the problem here. The name property has `private` access in `Animal`, which means it can only be accessed from within an `Animal` object. Although a `Parrot` *is an* `Animal` the access modifier means the property can only be *directly* accessed by an object created using the `Animal` constructor, which means it won't have access to the `Parrot` methods. 

There are two ways in which we can solve the problem. The first is to use a getter to retrieve the value, but while this will work in this case with a String it may lead to issues with more complex data structures, or indeed lead to some very messy code. The alternative is to change the access modifier and that it the approach we will take here. Instead of `name` being `private` we will make it `protected` in `Animal`:

```java title="Animal.java"
// Animal.java

public abstract class Animal {

	protected String name;			// MODIFIED
	
	// ...

}
```

The `protected` modifier sits between `private` and `public` in terms of visibility. A property (or method) with it can be accessed from other classes, but **only those which inherit** from where the value is defined. We need to make a small adjustment to our UML diagram as well, replacing the `-` symbol with a `#`.

![Diagram showing protected property](../../../assets/java/inheritance/protected_property.png)