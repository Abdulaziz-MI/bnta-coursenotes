# Multiple Classes

## Learning Objectives

* Understand how classes can relate to each other within an application
* Understand how to construct and read a UML diagram depicting an application with multiple classes and relationships
* Understand how best to construct an application with multiple interacting parts
* Appreciate the nuance surrounding where a method "belongs", recognising where best to create a novel method

## Introduction

Now we have been introduced to what a class is, we can take the concept further and consider how we would deal with multiple classes interacting with each other.

The fiction we are going to use for this codealong is for a Farmer and her Chicken Farm.

Our Chicken `Farm` will have a `name`, an arraylist of `chickens` and a certain number of `eggs` for sale. It should be possible to add and remove a `Chicken` from the farm.

Our `Farmer` will have a `name` and the ability to collect `eggs`, some of which will be for sale and some of which will be for her own family's consumption.

Each `chicken` on the `farm` has a `name` and a number of `eggs`.

## UML Diagrams
Every project should begin with a diagram. In computer science, these diagrams are often called UML (Unified Modeling Language) diagrams (sometimes we will refer to them as class diagrams). UML diagrams are a way to visually represent the architecture and design of an application.

They are an extremely useful tool for thinking through your applications and are worth remembering in a techncial test.

There are plenty of conventions to follow in UML diagrams, but at this stage it is more important that the diagram helps you write your program, so the diagrams we will draw with you will be somewhat simplified.

> Introduce students to diagramming tools. Suggested applications are: [Excalidraw](https://excalidraw.com/), [LucidChart](https://lucid.app) or [Draw IO](https://draw.io).

### Suggested UML Diagram

![Chicken Farm class diagram](../../../assets/java/multiple_classes/chicken_farm.png)

> talk students through the diagram, suggest start with the 'smallest' class: `Chicken`.

### Chicken
We now know we want a `Chicken` and that each chicken will need to have a `name` and a number of `eggs`.

Let's begin by looking at the tests in the start code to see what functionality might be required from our class.

> Don't be alarmed at the number of errors you may see in the test file (squiggly red lines), these will disappear as we write our class. You may wish to comment out those tests that are for as-yet-unwritten code as we go along.

```java
// ChickenTest.java

public class ChickenTest {

    Chicken ginger;

    @BeforeEach
    public void setUp(){
        ginger = new Chicken("Ginger", 3);
    }

    @Test
    public void hasName(){
        assertThat(ginger.getName()).isEqualTo("Ginger");
    }

    @Test
    public void hasEggs(){
        assertThat(ginger.getEggs()).isEqualTo(3);
    }
    
    @Test
    public void canSetEggs(){
        ginger.setEggs(5);
        assertThat(ginger.getEggs()).isEqualTo(5);
    }
    
}
```
We can also see that we need our `Chicken` object to be able to access their two properties (`name` and `eggs`) as well as have the ability to `setEggs()`.

Run the tests and use the errors to write the necessary code, until you arrive at the following


```java
// Chicken.java

public class Chicken {

    private String name;
    private int eggs;

    public Chicken(String name, int eggs){
        this.name = name;
        this.eggs = eggs;
    }

    public String getName(){
        return this.name;
    }

    public int getEggs(){
        return this.eggs;
    }

    public void setEggs(int amount){
        this.eggs = amount;
    }

}
```

Make sure all tests are passing before moving on to the next class.

### Farm
`Farm` and `Farmer` look to be classes of a similar size, but it seems likely that a `Farmer` will need a `Farm` to collect eggs from (i.e. we will need a farm for the farmer). It makes sense, then, for our next class to be `Farm`.

As before, let's look at the `FarmTest` to see what is required of the class.

You will notice immediately that this is a 'busier' class than `Chicken`. In the `BeforeEach`, our tester has created a `farm`, a number of `chickens` and added those chickens to the farm.

Run the tests and use the errors to build your class. You should end up with something like this:

```java
public class Farm {

    private String name;
    private ArrayList<Chicken> chickens; //this arraylist will start empty
    private int eggsForSale; // all new Farm objects will start with zero eggs for sale

    public Farm(String name){
        this.name = name;
        this.chickens = new ArrayList<Chicken>();
        this.eggsForSale = 0;
    }

    public String getName(){
        return this.name;
    }

    public int getChickenCount(){
        return this.chickens.size();
    }

    public int getEggsForSale(){
        return this.eggsForSale;
    }

    public void setEggsForSale(int amount){
        this.eggsForSale = amount;
    }

    public void addChicken(Chicken chicken){
        this.chickens.add(chicken);
    }

    public ArrayList<Chicken> getChickens(){
        return this.chickens;
    }

}
```

### Farmer
We know from creating our class diagram that the `Farmer` class is the most complicated and requires the most thinking. 

As well as looking at the FarmerTest class, it can be helpful to refer back to the class diagram to review how we plan to put the `Farmer` class together.

We can see that the first two tests are simple, just checking our objects have the properties of `name` and `collectedEggs`.

Once again we are going to run our tests and use our errors to create the code our class needs. The first two tests are straightforward: run your tests and use the errors to write the code you need in your class to make the tests pass. You should end up with something like this:

```java

public class Farmer {

//    properties
    private String name;
    private int collectedEggs;

//    constructor
    public Farmer(String name){
        this.name = name;
        this.collectedEggs = 0;
    }

    public String getName(){    // ADDED
        return this.name;
    }

    public int countCollectedEggs(){    // ADDED
        return this.collectedEggs;
    }

}
```

#### `collectEggs()`
However, when we get to the third test it is important to think about the steps our farmer needs to take:

- Our farmer needs access to a `Farm` in order to collect eggs. There is no `Farm` object accessible from a `Farmer` object (see class diagram), so we need to pass a `Farm` object to the method.
- Once we have access to a `Farm` object, within the `collectEggs()` method, our farmer can access the chickens and therefore their eggs.

Using our class diagram and some pseudo code, we could end up with something like this:

```java
// Farmer.java

...

public void collectEggs(Farm farm){     // ADDED
//        loop through the chickens on the farm
//        count the number of eggs available
//        add those eggs to the 'eggs for sale'
//        set all chicken eggs to zero (as they have now been collected)
        int totalEggs = 0;
        for (Chicken chicken : farm.getChickens()){
            totalEggs += chicken.getEggs();
            chicken.setEggs(0);
        }
        this.collectedEggs = totalEggs;

```


#### `assignEggsForSale()`
When we have arranged for our farmer to be able to `collectEggs()`, we can also allow her to assign some eggs for sale, and some eggs for her own kitchen.

For the purposes of this exercise we can assume that eggs that remain part of the `Farmer`'s `collectedEggs` property, are for personal consumption.

We can assumer that eggs that form part of the `Farm`'s `eggsForSale` property are for sale.

This means that we need access to both the `collectedEggs` in the `Farmer` object (no problem, we're working in the `Farmer` class) **and** the `eggsForSale` in the `Farm` object.

Let's assume that the `Farmer` always takes 2 eggs for herself.

```java
public void assignEggsForSale(Farm farm){
        farm.setEggsForSale(this.collectedEggs - 2);
        this.collectedEggs = 2;
    }

```

### A note on where methods/properties belong
It can seem reasonable to think that the `collectEggs()` and `assignEggsForSale()` methods should belong in the `Farm` class. After all, both methods require a `Farm` object.

However, the when considering where to place (in particular) methods, we should put the behaviour 'where it belongs'. The `Farmer` is responsible for collecting the eggs and deciding which are for sale and which are for the kitchen.

After all, one of the philosophies behind Object-Oriented Programming (OOP) is to create code that reflects the real world around us.
