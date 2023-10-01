# Interfaces

## Learning Objectives

* Understand the purpose of an interface
* Be able to implement an interface in a Java application
* Be able to implement method overriding
* Understand how interfaces enable polymorphism
* Understand the advantages and drawbacks of interfaces

## Introduction

Interfaces give us a flexible way to implement polymorphism in our code. Interfaces are typically described in a couple of ways: 

- as a fully abstract class
- as a 'contract' signed by the subclass

When we implement an interface, we're agreeing to add implementations of all its abstract methods.

As we code along, the key points to remember are:
- An interface is a set of method signatures for *yet-to-be* implemented functionality
- They specify a set of behaviours *without* prescribing how these behaviours should be implemented.

## How to Implement an Interface

> Take 5 minutes to download and review the start code. 

You can see that we have four different classes of athelete in a `models` package: `Cyclist`, `Runner`, `Swimmer` and `Triathlete`.

Let us first consider what sort of behaviours we might wish our classes to have. 

> Option to invite discussion from students

We might end up with a chart similar to the one below:

|Cyclist | Runner | Swimmer | Traithlete |
|:-------|:------:|:-------:|-----------:|
|`cycle()` |`run()` |`swim()` |`cycle()`|
| | | |`run()`|
| | | |`swim()`|

Let's implement these methods using interfaces.

### Cylcable
Let's begin by creating an interface for classes that we wish to have a `cycle()` method (i.e. `Cyclist` and `Triathlete`).

Right-click on your blue `java` folder and select `New > Package`. Call your package `interfaces` (note the lowercase 'i'). 

Right-click on your new `interfaces` package and select `New > Java Class`. 🚨 Before naming the class, ensure you select `Interface` from the menu.

### Naming Interfaces
The name of the interface should describe the abstract concept the interface represents. In our case, we want an interface that represents cycling behaviour.

Naming convention gives us two options, you will likely see both:
- the function of the interface made into an adjective, usually by adding `-able` or `-ible` to the end. In this case, we could call our interface `Cyclable`.
- alternatively, the interface can be named using `I` as a prefix. In this case we could call out interface `ICycle`.

Arguably, `Cyclable` more clearly denotes the purpose of the interface, so we will use this format where possible.

### The cycle() method
Let us now add an abstract method to the interface - a behaviour we want in any class that implements this `Cyclable` interface.

Note that there is no need to designate this method as `public` as all methods in an interface are implicitly public.

We are going to require that all our Cycleable classes will have a method called `cycle()` that takes in a distance as a parameter.

```java
// Cyclable.java

public interface Cyclable {

    String cycle(int distance);

}

```
We have agreed that we want the `Cyclist` and `Triathlete` classes to implement this method.

We implement the interface using the `implements` keyword.

```java
// Cyclist.java

public class Cyclist implements Cyclable { // MODIFIED

    private String name;

    public Cyclist(String name){
        this name = name;
    }

}
```

We can immediately see that the IDE highlights an issue with the code. If we hover over the red squiggle, we see: `Class 'Cyclist' must either be declared abstract or implement abstract method 'cycle(int)' in 'Cyclable'`. 

By implementing the `Cyclable` interface, the `Cyclist` class has now 'signed a contract` to say that it will implement the method(s) listed in the interface.

Add a `cycle()` method to the class, and note that the method must follow what is described in the interface (i.e. it must be named `cycle` and take in an `int` as a parameter and return a `String`).

```java
// Cyclist.java

public class Cyclist implements Cyclable { 

    private String name;

    public Cyclist(String name){
        this name = name;
    }

    public String cycle(int distance) {     // ADDED
        return this.name + "cycled " + distance + "m";
    }

}
```

You will notice a little green `I` symbol appears in the gutter. This handy button lets us toggle between the interface where the abstract method is listed and the classes where the method is implemented.

### Triathlete
Our next stop is the `Triathlete` class. This class also needs a `cycle()` method as triathlon includes cycing.

Once again we should implement the `Cyclable` interface in the class and add a concrete implementation of the `cycle()` method. This time, however, as it is a triathlete, and the cycle is the first of three consecutive events, we will write the method slightly differently. 

All the items in the 'contract' (i.e what has been described in the interface) will have to be fulfilled (so it will still be called `cycle`, take an `int` as a parameter and return a string), but we are otherwise free to do as we wish. We will write a similar method to the one in `Cyclist`, but add a sutble difference to demonstrate that each class can have its own implementation.

```java
// Triathlete.java

public class Triathlete implements Cyclable {

    private String name;

    public Triathlete(String name) {
        this.name = name;
    }

    public String cycle(int distance) {     // ADDED
        return this.name + " cycled " + distance + "m after swimming";
    }

}
```

### IRun
We can next look at where we want a `run()` method to exist: in `Runner` and `Triathlete`. Again, we can ensure both classes implement this method using an interface. 

This time, we are going to name our interface `IRun`. Typically, you would chose a naming method for your interfaces and stick to it. However, there already exists an interface in Java called 'Runnable' so we will avoid using that name.

Right-click on your `interfaces` package, select `New` > `Java Class`. Ensure 
`interface` is selected and name the interface `IRun` (note the capital 'I' and 'R').

Again, we will write an abstract method in the interface, which could look like this:

```java
// IRun.java

public interface IRun {

    String run(int distance);

}
```

Again we should implement this interface in `Runner` and `Triathlete`.

```java
// Runner.java

public class Runner implements IRun {   // MODIFIED

    ...

    public string run(int distance){     // ADDED
        return this.name + " ran " + distance + "m";
    }

}
```

You may notice that we are able to implement more than one interface in the Triathlete class. This is one of the big advantages to interfaces, their flexibility.

```java
// Triathlete.java

public class Triathlete implements Cyclable, IRun {

    ...

    public String run(int distance){     // ADDED
        return this.name + " ran " + distance + "m after cycling";
    }

}
```


### TASK - `swim()`
- Create an interface called `Swimable` containing a method signature for `swim`
- Implement your interface in the classes we want to have that `swim` behaviour: `Swimmer` and `Triathlete`
- Write a concrete implementation for `swim()` in each class

Possible solution:
```java
// Swimmable.java

public interface Swimable {

        String swim(int distance);

}
```

```java
// Swimmer.java

public class Swimmer implements Swimable {

    private String name;

    public Swimmer (String name){
        this.name = name;
    }

    public String swim(int distance){   // ADDED
        return this.name + " swam " + distance + "m";
    }

}
```

```java
// Triathlete.java

public class Triathlete implements Cyclable, IRun, Swimable {

...

    public String cycle(int distance){
        return this.name + " cycled " + distance + "m after swimming";
    }

    public String swim(int distance){
        return this.name + " swam " + distance + "m from the start";
    }

    public String run(int distance){ // ADDED
        return this.name + " ran " + distance + "m after cycling";
    }   

}
```

We can run our tests to check all our implementations are working as expected. Note you should run your test files individually as our `ChampionshipTest` file will not work (more on this in a moment!).

## Polymorphism
Let's remind ourselves why polymorhpism is so useful to programmers. Most students easily learn how to implement an interface - the trickier part is usually understanding the why and when.

If our objects have the ability to take on different forms, they can adapt to different situations and contexts. This makes our code more flexible and reduces complexity.

Our examples are necessarily simple, but it will be helpful to remember that as applications get larger, the need for this becomes greater.

### How do interfaces help us use polymorphism?
Not only do interfaces provide a contract that ensures all sub-classes implement certain behaviour, they also provide that class with a type. We can demonstrate this with a `Championship` class.

Happily, our start code contains a class called `Championship`.

Let us suppose that within our championship, we want to be able to group atheletes according to their activity. There could be several reasons why we might want to do this.

- the championship organisers may wish to issue all atheletes with merchandise specific to their sport (e.g. goggles for swimmers, helmets for cyclists, etc)
- They may wish to communicate pool availablity to swimmers, and track availabilty to runners/cyclists for training sessions
etc.

Whatever the reason, it sounds like we might want to have arraylists in our `Championship` class that hold these atheletes in three collections - those that swim (Swimmers and Triathletes), those that run (Runners and Triathletes) and those that cycle (Cyclists and Triathletes).

This may not seem possible. As we now know, Java requires us to specify what type of object an arraylist will hold. Fortunately, we have implemented interfaces in our classes and we can use these types to overcome this problem. In other words, the objects of each class are polymorphic - they can take on the shape of both the class and the interface.

Thus, when we initialise our arraylists we can describe the type according to the interface those classes implement. 

```java

public class Championship {

    private String name;
    private List<Cyclable> cyclists;
    private List<IRun> runners;
    private List<Swimable> swimmers;

    public Championship (String name){
        this.name = name;
        this.swimmers = new ArrayList<>();
        this.cyclists = new ArrayList<>();
        this.runners = new ArrayList<>();
    }

}
```

### Swimmer and Triathlete
To demonstrate this, we will use `Swimmer` and `Triathlete` as our example. Both classes implement the Swimable interface, and we want to add any object made from either class to the `swimmers` arraylist. We will need a method to do this:

```java
// Championship.java

public class Championship {

    ...

    public void addSwimmer(Swimable swimmer){    // ADDED
        this.swimmers.add(swimmer);
    }

}
```

In a moment we will want to run a test to check that this method does in fact add both `Swimmer` objects and `Triathlete` objects to the same arraylist. To do this let's add another method that we can call in the test to check it has worked.

```java
// Championship.java

public class Championship {

    ...

    public void addSwimmer(Swimable swimmer){    
        this.swimmers.add(swimmer);
    }

    public List<Swimable> getSwimmers(){     // ADDED
        return swimmers;
    }

}
```
`getSwimmers()` will return the arraylist to us so we can see which objects have been added. More typically we would create a method that counts the objects in an arraylist, but in this instance it would be helpful to visually see that two different objects can be added to the same collection.

### ChampionshipTest
To see this in action, let's create a test in `ChampionshipTest`. In our test we will add a swimmer and a triathlete to `swimmers` arraylist, then print out the arraylist to view the contents.

```java
public class ChampionshipTest {

    Championship championship;
    Swimmer swimmer;
    Triathlete triathlete;

    @BeforeEach
    public void setUp(){
        championship = new Championship("World Athletics");
        swimmer = new Swimmer("Jane");
        triathlete = new Triathlete("Kevin");
    }

    @Test   // ADDED
    public void canAddSwimmingAthleteToChampionship(){
        championship.addSwimmer(swimmer);
        championship.addSwimmer(triathlete);
        System.out.println(championship.getSwimmers());
    }

}
```
When you run the test, you will hopefully see some output in the terminal that looks similiar to that below. This clearly shows two objects of the type `Swimmer` and `Triathlete` living happily in the same arraylist.

```shell
[models.Swimmer@2ddc8ecb, models.Triathlete@229d10bd]
```


## Abstract Class vs Interface
A common question is: when should we use an abstract class and when should we use an interface?

We should use **abstract classes** primarily for objects that are closely related. **Interfaces** are best used for providing common functionality to unrelated classes.

Because you can implement as many interfaces as you wish in Java (compared to extending only one via inheritance), within a structure that allows for looser coupling, many programmers promote using interfaces over inheritance for their flexibility.

Inhertiance can force you to 'shoe-horn' a class into a space it shouldn't be in. Even a slight change in the structure can cause you to have to re-think your entire program. 

### Why not use inhertiance in this example?
If we used inheritance in this example, the structure would be awkward as we would need to have all three behaviours (`cycle()`, `swim()`, `run()`) inherited by all classes. 



