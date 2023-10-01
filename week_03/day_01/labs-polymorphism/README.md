# Java Polymorphism Exercises

We've learned about polymorphism in class, now to put your newly gained knowledge to use! This lab will take place over two afternoons (today and tomorrow). We would expect you to tackle MVP today and either continue with MVP or start on extensions tomorrow.

Your task is to create a project that uses multiple classes. Each project should contain an abstract class, subclasses, a collection and - should you get to extensions - method overloading, an interface and an abstract method. Please think through your project carefully and plan what you are going to do using UML diagrams. **You will need to show your UML diagrams to a trainer before you start coding**.

If you would like to see an example of an MVP UML diagram for a similar project, you can do so [here](./code/shop_UML_MVP.pdf).

## Example Themes

Please create your project using one of the themes below:

### Baking

We could consider all cakes to have a `name` and `baseFlavour`. More specific examples, such as `Cupcakes`, `TieredCakes` or `Traybakes` may include their own individual properties such as a `Sting[] toppings`, `int tiers` or `Boolean healthier`.

### Buildings

Our abstract building will likely have the properties of `numberOfRooms` and `dateOfConstruction`. Example sub-classes could be: `Bungalow` which has a `Boolean central_heating` property; `StudentFlat` which has a `long numberOfPartiesHosted` property or; `Office` with has a `String CompanyName`.

### Vehicles

A `Vehicle` class could have `numberOfWheels` and `maximumOccupancy` properties. Things may start to get more diverse when we consider air vs terrestial vehicles though. For instance, a `Helicopter` is going to vary significantly from a `Car` or `Train`. Have a think about properties such as `fuelType`, `travelType` and `publicTravel`. 

It may be that a series of `Boolean` properties on the abstract class define the majority of differences between your vehicles. Perhaps an `enum` type would work well for some of these properties.

### Media

Our last example is that of media. We're all familiar with books, movies and games. Can you think of their shared characteristics? Does our use of language, grouping these items together, perhaps not lend itself to a tight logical system of properties? Have a think about how a `Book` and `Film` are similar to eachother—paying special attention to the **type** of the properties being created. 

Does an abstract class of `Media` made sense to create? Perhaps a `Publication` class, focussing on different types of printed media may lend itself to a neater system...


## MVP (Monday)

### 1. Please create a new project, with multiple classes

Using one of the themes above, start thinking about what properties could be defined on an abstract parent class, and that which should be created on the child sub-class

This task focuses on the first step of visualising your logical system of classes, so please remember to create a UML **before** coding.

### 2. Create an abstract class

Your example doesn't have to be real-world, but it should have some logic to it.

### 3. Extend the abstract class with at least 2 sub-classes

The relationship should make sense (see examples given above).

### 4. Create a collection which accepts objects of either of those 2 types

This can be an ArrayList, a Set, a HashMap; whatever makes sense in your concept.

## Extensions (Tuesday)

### 5. Implement at least 1 method overload

Demonstrate how you can have more than one implementation of the same method in a class.

### 6: Define an abstract method in the super-class and implement it in each of the sub-classes

### 7 Create an interface and implement it on at least 1 class

Also, use the interface you create as the type of a variable somewhere in your project. New up an object from a class that implements the interface.
