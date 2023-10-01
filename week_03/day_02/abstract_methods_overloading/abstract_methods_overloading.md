# Casting, Abstract Methods & Overloading

## Learning Objectives

* Be able to cast an object from one data type to another
* Understand what makes a method an “abstract method”
* Be able to use method overloading to define contextual behaviours for a class

## Introduction

Inheritance is a great tool, but as we saw in the last lesson it isn't a solution to all of our problems. It can also inadvertently lead to bloated code as methods are defined and then overridden, or indeed the opposite as we are constrained by a structure which may not offer the flexibility we need.

## Casting

Assigning multiple types to an object is a wonderful part of polymorphism but it has its complications. In particular properties or behaviours defined for a subclass can cause problems for a compiler when the object is referred to by its superclass.

Let's imagine a scenario where we want our `Zoo` objects to be able to count all of the eggs laid by their birds. We will initialise a counter then loop through the `animals` collection. For each object in the collection we will call the `layEgg()` method defined in the `Bird` class and then increment the counter by one, returning the counter's value when the loop completes. One possible definition for the method is given below:

```java title="Zoo.java"
// Zoo.java

public class Zoo{

	// ...
	
	public int countBirdEggs(){
		int totalEggs = 0;
		for (int i = 0; i < this.animals.size(); i++){
			Animal animal = this.animals.get(i);
			animal.layEgg();
			totalEggs += 1;
		}
		return totalEggs;
	}

}

```

The compiler immediately throws an error telling us that it "cannot resolve layEgg in Animal". The `animals` property has a declared data type, in this case `Animal`, so as we loop through it we must use that declared type when referring to the objects. That means that only the methods declared in that class can be called. In our case we are limited to using `Animal` methods, but `layEgg()` is declared in `Bird`.

Sometimes this is a good thing since it prevents us trying to call an unimplemented method, but sometimes it can prevent us doing what we need to do with our code. For example, as we iterate we could be passing each object into another method which needs a `Bird` parameter. In this scenario we need to make use of a tool called **casting**.

When we *cast* an object we modify the type declaration on the fly. Although a polymorphic object has more than one type, when we store it in a variable we need to declare one of them. The compiler can read *up* the inheritance tree to make sure a method called has been declared on a superclass but can't read *down* the way to check any subclasses. We know that a `Bird` is an `Animal`, but once a `Bird` object has been declared as an `Animal` the compiler loses the knowledge that it was once a `Bird`. 

We cast our object by adding the desired type in parentheses before the method call which returns the object. We are telling our compiler to treat that object as if it were the type we cast to, giving it access to anything defined for that class. We need to declare a new variable to hold this cast value in, which we can then manipulate as if it were any other.

```java title="Zoo.java"
// Zoo.java

public class Zoo{

	// ...
	
	public int countBirdEggs(){
		int totalEggs = 0;
		for (int i = 0; i < this.animals.size(); i++){
			Bird bird = (Bird) this.animals.get(i);			// MODIFIED
			bird.layEgg();			// MODIFIED
			totalEggs += 1;
		}
		return totalEggs;
	}

}

```

Casting needs to be managed carefully. In our program a zoo has a collection of type `Animal` which means *anything* which extends `Animal` can be added. We could still end up with `Lion`s sharing with `Bird`s. If we call the `countBirdEggs` method and try to cast all our `Animal` objects to `Bird` our compiler doesn't know that not every object started out as a `Bird`. In a scenario like this we will end up with a run-time error, with our interpreter telling us that `Lion` cannot be cast to `Bird`. In general we shouldn't rely on casting to solve our problems, instead we should make sure we plan thoroughly to ensure we have the right data types in the right place. 


## Abstract Methods

The `Animal` class in our zoo is a great example of a class with more code in it than is necessary. Recall that we defined a method for making noise, but every class which extends `Animal` is overriding it with its own implementation.

```java title="Animal.java"
// Animal.java

	// ...
	
	public String makeNoise(){
		return "Hello, my name is " + this.name + ".";
	}

```

There is no need for this to be here if it is just going to be overridden and so we could, if we wanted to, remove it without affecting our code. That's fine so long as we only interact with concrete implementations of the various animals where the method is defined, but as we saw in the previous section there may be times when we need to use the abstract type. Let's add a method to our zoo which will look at each enclosure in turn and get all the animals to make a noise.

```java title="Zoo.java"
// Zoo.java

public class Zoo{

	// ...
	
	public void greetAnimals(){			// ADDED
		for(Animal animal : this.animals){
			animal.makeNoise();
		}
	}

}

```

The `animals` property contains `Animal` objects, we don't know more about them than that. If we remove the `makeNoise` method from `Animal` then we have a problem, since the compiler doesn't know where to find a definition for that method. Recall what we said in the previous section - the compiler can't look *down* the inheritance chain. We're left in a position where cleaning up our code has broken it instead.

We can find a halfway point between these two extremes by defining an **abstract method**. Recall that an abstract class provided a template which laid out the minimum properties and behaviours required by a class but which couldn't be instantiated without further definition. Abstract methods work in a similar way - we define a method *signature* inside an abstract class, but the method *body* is defined for each class extending it. We can make `makeNoise` abstract by adding the `abstract` keyword and removing the body.

```java title="Animal.java"
// Animal.java

public abstract class Animal{

	// ...
	
	public abstract String makeNoise();			// MODIFIED
	
	// ...

}

```

The compiler no longer has a problem with the `greetAnimals` method since we are now saying that anything which extends `Animal` *must* have an implementation of the `makeNoise` method. That implementation can be done by the concrete class at the end of the chain or by another abstract class somewhere in the middle. For example, if we implement `makeNoise` in `Bird` then the requirement is satisfied for `Parrot` and `Seagull`. Since `Bird` is itself abstract and can never be instantiated we don't need to provide an implementation here, but if we want the behaviour to be the same for all birds it can save some time to do so.


## Overloading

There is another way to implement polymorphism, one which doesn't require the addition of extra types to an object. We can modify the behaviour of an object contextually, according to the information passed to a given method. We can do this just by adding additional method signatures in a process called **overloading**.

So far each method name has been unique and has had a specified list of parameters. When we add an animal to the zoo we need to pass an `Animal` object to teh method, no exceptions. Not all the methods we have used have followed this pattern though. If we compare our test files we see that every test uses the same assertion methods, but not every test is using them in the same way. In `LionTest` we pass them two `String` objects, while in `ZooTest` we are passing two `int`s. If we tried to do this with our own methods we would run into all sorts of compiler errors, so why is it working here?

The assertion methods have been *overloaded* when defined in the Junit framework. A developer has defined a version which can handle `String` inputs, but also a version which can handle `int`s (or `char`s, or `double`s, or anything which extends `Object`). This is in fact a lot easier than it sounds and we can overload a method in our own project by adding a new method signature to one of our classes.

We'll take the `makeNoise` method in the `Lion` class as a starting point. Currently it is providing an implementation for the abstract `makeNoise` method required in order to extend `Animal`, taking no arguments but returning a string. Sometimes we might want our lions to make a *specific* noise, but also retain that ability to simply roar. One solution could be to define a brand new method. Alternatively, we can define a second version of `makeNoise`.

```java title="Lion.java"
// Lion.java

public class Lion extends Animal{

	// ...
	
	@Override
	public String makeNoise(){
		return "ROAR!";
	}
	
	public String makeNoise(String message){
		return "In my opinion, " + message + ".";
	}

}

```

The methods have the same name and the same return type, but crucially they have different parameters. That means that when we call one of them the JVM can compare the method signatures, determine which version is needed and execute the code defined in the body. We can verify this by adding an extra test for the new version.

```java title="LionTest.java"
// LionTest.java

public class LionTest{

	// ...
	
	@Test
	void canMakeNoise__noArgument(){			// MODIFIED
		String expected = "ROAR!";
		String actual = lion.makeNoise();
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	void canMakeNoise__withArgument(){			// ADDED
		String expected = "In my opinion, Toy Story was overrated.";
		String actual = lion.makeNoise("Toy Story was overrated");
		assertThat(actual).isEqualTo(expected);
	}

}

```

We can overload a method as many times as we wish, although lots of overloads can be an indicator of a design flaw somewhere. Note that we do still need to retain the original version of `makeNoise` as it matches the abstract signature specified by `Animal`. We can overload at any level and we can even override an overload if we need to.