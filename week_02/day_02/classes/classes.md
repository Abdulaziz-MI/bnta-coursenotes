# Using Classes

### Learning Objectives

* Understand the basics of the object-oriented programming paradigm
* Be able to define and instantiate classes
* Be able to define the properties of a class
* Be able to define the behaviours of a class
* Be able to draw a class diagram describing properties and behaviours
* Understand the principle of encapsulation and why we use access modifiers

## Introduction

Organising a project is hard. That won't ever change and doesn't just apply to big, enterprise-scale applications. It's true for programs with only a few files, it's even true for programs confined to a single file. In fact, it's even more challenging for smaller projects.

In our work so far we have written all of our code inside a single block in a single file. We have improved things slightly by introducing variables and then some data structures but we still have a monolithic, brittle chunk of code. If we want to modify any of our code, or include it as part of a larger application, we would have to edit that monolith or copy the whole thing across. That opens the door to a host of potential issues but it can be avoided by structuring our code in such a way to separate independent processes and facilitate reuse.

## Object-Oriented Programming

**Object-oriented programming**, or **OOP**, is a *programming paradigm* - it's a way of thinking about and structuring our code that reflects how we think about the real world. Instead of everything being messily defined in one place we have a specification for individual elements which are brought together to build something bigger. Consider a real-world example: a person leaves their house, gets on a bus and goes to the cinema. Each of those exists in isolation and we know what to expect from them and how they will behave, but they can also interact. Those interactions are also defined within a given scope and describe how (or even if) such an interaction happens.

None of those specifications can be applied in a blanket manner, but there will always be some common ground. Not every person has the same date of birth, for example, but everyone has *a* date of birth. Buses all have a destination, but they're not all going to the same place. There are many different representations of a single specification, but all of them follow the same rules. OOP takes this framework and applies it to our code.

We have seen some examples of this in practice already. If we were to create a bunch of different strings they could all have different values but we would be able to do the same things to each of them, capitalising them for example. In an object-oriented language such as Java every value is represented within the language as an **object**. All the values we have stored in variables or added to data structures are represented by objects. In Java each of those objects has a type and as we have already seen that type determines what we can do with each object. A natural question to ask at this point is 'what *defines* the **type** of an object'?

**Classes** will enable us to lay out the specification for our objects. We can use them as data types in the same way as `String` or `ArrayList` to denote that the associated variable will follow the rules laid down in the class definition. Those rules fall into two categories:

- **Properties**: These describe what an object *has*. Every object with this type will have an associated value, although those values can be different for each object.
- **Behaviours**: These describe what an object *does*. Every object can perform the specified actions and although the end result may vary between objects they will follow the same process.

In many ways a class can be thought of as a *blueprint* which can be used over and over again to create objects. Creating objects in this way is fundamental to many modern programming languages and will enable us to build large, complex applications in a way we simply couldn't if everything was defined within a single file as before.

## Creating a Class in Java

In some langauges, such as Python or JavaScript, classes are optional. They are still pretty much essential when building larger applications but for smaller programs we can manage without them. In statically-typed languages such as Java, however, *everything* has to be in a class. That might sound strange given that we're only just introducing them now, but if we start a new project and add our `main` method we'll see an example.

```java title="Runner.java"
// Runner.java

public class Runner {

    public static void main(String[] args) {

    }

}
```

Perhaps unsurprisingly, following the `new -> Java Class` path has created a class for us. The `class` keyword on line 1 indicates that the code enclosed in the following braces will define the class `Runner`. Our class name has to follow some rules: it should always begin with a capital letter and it should always match the name of the file, in this case `Runner.java`. While we can have more than one class defined in a file from a technical perpsective, from a practical one it is generally discouraged. 

There is nothing here we haven't seen already, since all of our programs so far have included a `main` method. However we're going to write some code which will define a person like we discussed above and we don't want that code to be incorporated in the main body of the program. We're going to create another class called `Person` to work in.

```java title="Person.java"
// Person.java

public class Person {

}
```

Note again that we have the `class` keyword followed by the name of the class then a pair of braces. Everything inside those braces will define what it is to be a `Person` (at least in this program). We're still missing something though - the ability to *create* a `Person`. Recall that when we created new lists and maps they didn't magically appear, we had to use the `new` keyword when we initialised them. This keyword calls a special method defined within the class called a **constructor**.

```java title="Person.java"
// Person.java

public class Person {

	public Person(){
	
	}

}
```

The constructor **must** have the same name as the class it is defined in and be preceded by the `public` keyword (more on this later). The brackets are currently empty but as we expand the definition of the class we will add code to them. Having a constructor means we can create `Person` objects though, and we can do this from any part of our code. Let's create an object inside our `main` method:

```java title="Runner.java"
// Runner.java

public static void main(String[] args) {

	Person person = new Person();

}
```

By using the `new` keyword we have **instantiated** a new object! The name of the class gives the data type and we can call the constructor as often as we like to create multiple objects. We can use this new data type in conjunction with the data structures we have already seen, for example we could create a list of people with `ArrayList<Person>`. 

### An Introduction to UML

Classes are a great tool for developers but they do add a layer of complexity to our code, making it potentially harder to communicate our ideas. Diagrams will be very useful in addressing this and in this course we will make extensive use of them to illustrate our code. There are few strict rules around how diagrams should be constructed but the **Unified Modelling Language** (**UML**) gives some excellent guidelines and standards.

Possibly the most common diagram specified by the UML is the **class diagram**. A well-structured class diagram can tell a user about a class' properties, behaviours and even how it interacts with the other classes in the program. An example diagram for `Person` is shown below.

![class diagram for Person](../../../assets/java/classes/class_digram_title_only.png)

The diagram is split into three sections. The top section is mandatory and contains the name of the class. The second section is where we will define the class' properties and the third is where we define the methods. Either or both of these can be empty as appropriate. Within each section we can add further details as necessary to give us even more information about a class.

These diagrams are useful as a reference tool for future developers working on a project but also as a planning tool. Even if a class seems simple it can be useful to draw out a class diagram to confirm any assumptions and act as a check on the design.


## Defining Properties

It's great to be able to create `Person` objects whenever and whereever we want in our program but they're of limited use at the moment. Earlier we spoke about different people having different names or dates of birth but as things stand we can't represent that information in our program. 

A class stores this information as **properties**. A class can have as many properties as necessary and every object instantiated will have its own values for those properties. *Those values can differ between objects!* We define those properties by declaring variables before the constructor inside the class.

```java title="Person.java"
// Person.java

public class Person {

  	String name;
   	String town;

	public Person(){
	
	}

}
```

This specifies that every `Person` will have these properties but they don't have any values yet. We don't know what the values should be until we create the object, and that doesn't happen until we call the constructor. It's time for us to flesh out our constructor and have it assign some values to those properties.

In order to assign values to a given object we need to know what those values should be. The constructor is just like any other method we have worked with so far in that it we can pass information to it. We can tell the constructor what to expect by defining its **parameters**.

```java title="Person.java"
// Person.java

public class Person {

	// ...

	public Person(String name, String town){
	
	}

}
```

We can specify as many parameters as we need to, so long as we comma-separate them within the parentheses. Each parameter needs a data type and a name. The names can be anything we want them to be but it is good practice to make them descriptive and relevant to the value they represent. By defining these parameters we are placing some limitations on how our class can be used. Now whenever we want to instantiate a new `Person` we need to provide these two pieces of infromation (called **arguments**) in the correct order or the compilation will fail. To avoid this we need to update our object instantiation.

```java title="Runner.java"
// Runner.java

public static void main(String[] args) {

	Person person = new Person("Colin", "Bathgate");

}
```

We haven't quite closed the loop though - we pass the arguments to the constructor, but we're not doing anything with it. Inside the body of the constructor we can assign those arguments to the variables we declared earlier. As we do this we use the keyword `this` to indicate that the value is being set for the *specific object* which called the constructor.

```java title="Person.java"
// Person.java

public class Person {

	// ...

	public Person(String name, String town){
		this.name = name;
		this.town = town;
	}

}
```

Each `Person` object has its own value for `name` and each has it's own value for `town`, but *every* `Person` has *something* defined for each property. We can confirm this by creating another `Person`, accessing the properties and printing the values.

```java title="Runner.java"
// Runner.java

public static void main(String[] args) {

	Person person = new Person("Colin", "Bathgate");
	Person person2 = new Person("Anna", "Glasgow");
	
	System.out.println(person.name); 		// prints "Colin"
	System.out.println(person2.name);		// prints "Anna"

}
```

We can use the constructor to initialise any properties which should have a set value rather than something passed through the constructor. For example, everyone has an age. We don't all have the same birthday so that value will change at different times for each person, but we also don't pop into existing in our mid-twenties. If we give `Person` an `age` property it makes sense for it to start at `0` for every object we create. To do this we need to declare the variable and assign it in the body of the constructor, but we don't need to pass any additional arguments.

```java title="Person.java"
// Person.java

public class Person {

  	String name;
   	String town;
   	int age;

	public Person(String name, String town){
		this.name = name;
		this.town = town;
		this.age = 0;
	}

}
```

### Properties in UML

We can now add our properties to the class diagram. There are two pieces of information we absolutely must include when we add a property: its name and its type. If the data type is defined in another class built by us we can link the two in the diagram and we will see what this looks like in a future lesson. We write the property name first and separate it from the type with a colon, with each property having its own line.

![class diagram for Person including properties](../../../assets/java/classes/class_digram_with_properties.png)


## Defining Behaviours

We've seen how to define what our people *have*, now we need to define what they *do*. A class' **behaviours** differ from its properties in that every instance of the class will follow the same process. While our people may have different names and addresses they will all walk, talk and sleep the same way. 

We have already leveraged this in previous examples, such as when we were working with data structures. We referred to **methods** defined for each data type, which is the technical name for the specified behaviours. Taking `HashMap` as an example, somewhere within the core Java language the class has been defined with a `.put()` method in it, which we used to add new elements to a map. We can define our own methods here which can be used to interact with and manipulate our `Person` objects.

Our methods consist of two parts:

- The method **signature** which names the method and defines its structure.
- The method **body** which defines what happens when the method is called.

The signature is what's most important to the compiler and tells us what (if anything) we need to pass in but also what we might get back. Let's add a method to our `Person` class and see how we can put it in to practice. Ususally we add methods after the contructor.

```java title="Person.java"
// Person.java

public class Person {

  	// ...
  	
  	void greet(){
  		
  	}

}
```

The `void` keyword is new here and indicates the **return type** of the method. This tells us the data type of any information we might get back from the method, for example a calculator performing a calculation and giving us some numeric value back. If we give a method the `void` retrun type it means we won't get anything back. `greet` is the name we have given our method. It should always start with a lower-case letter and follow the camel-case naming convention - `capitalLettersInsteadOfSpaces`. Just like with the constructor the parentheses will contain any parameters for the method and the method body will be within the braces. Both are required even if they will be left empty.

Although we *can* leave the method body empty, the method won't actually do anything if we do. There are pretty much no restrictions on what we can do inside a method body though, they can be as simple or as complex as we wish. That said, we usually don't want them to be doing too much. In software engineering we often talk about the [**Single Responsibility Principle**](https://stackify.com/solid-design-principles/) which, simply put, means that a method (or even class) should only be responsible for one thing. Although it may seem counter-intuitive, breaking our code down into small chunks makes it easier to maintain and debug if something goes wrong. We will discuss the Single Responsibility Principle and other related design principles later in the course.

We're going to keep our method pretty basic for now and just have it print something to the terminal. We have already seen how to print text from `main` and we don't need to do anything special to do it in a different class.

```java title="Person.java"
// Person.java

public class Person {

  	// ...
  	
  	void greet(){
  		System.out.println("Good morning!");
  	}

}
```

Our `greet()` method now has some functionality associated with it. To execute this code we need to **call** or **invoke** the method using one of our `Person` objects from earlier. 

```java title="Runner.java"
// Runner.java

public static void main(String[] args) {

	// ...
	
	person.greet();
	person2.greet();

}
```

We **must** include the parentheses after the method name, even if we don't pass any arguments. In this context they are often referred to as **invocation brackets** and they act as an indicator to the interpreter that the method should be called. If we omit them the interpreter will read `person.greet` as an attempt to access a property which doesn't exist, resulting in a compiler error. Note that both method calls have the same result - our greeting being printed to the terminal. The behaviour is specified for all objects, so we expect to see the same process followed both times.

It's not a particularly interesting behaviour, though. There are plenty of use cases for a method which does the same thing every time but often we want them to be *dynamic*, with the outcome dependent on the input. To get those inputs into a method we return to the idea of defining parameters, just as we did with the constructor, following the same conventions as before.

```java title="Person.java"
// Person.java

public class Person {

  	// ...
  	
  	void greet(String timeOfDay){
  		System.out.println("Good morning!");
  	}

}
```

We have used `timeOfDay` as the parameter name here because it gives an idea of what we expect the user to pass in, but we could use any name we like here. When we call the method we now need to pass in a `String` which will be assigned to `timeOfDay` at run time, meaning it is essentially a variable which only exists within the scope of the method. We can do whatever we need to with this variable, within the bounds of its data type. In this case we will replace "morning" with whatever time of day we pass in.

```java title="Person.java"
// Person.java

public class Person {

  	// ...
  	
  	void greet(String timeOfDay){
  		System.out.println("Good " + timeOfDay + "!");
  	}

}
```

Our compiler won't be happy since our method calls in `Runner` aren't passing any arguments. We can fix that by passing each one a string.

```java title="Runner.java"
// Runner.java

public static void main(String[] args) {

	// ...
	
	person.greet("afternoon");
	// prints "Good afternoon!"
	
	person2.greet("evening");
	// prints "Good evening!"

}
```

Note that each method call prints something different, depending on the value passed in. As things are currently *any* `String` we pass in will be printed but we could, if we wanted to, add some logic inside the method to make sure we only print the input if it makes sense to do so.

Our method is doing something now but it's still quite limited in its interaction with other parts of the program. Sometimes we will want our methods to give us some information back rather than printing it, meaning we need to make use of the `return` keyword. We're going to add a new method which will combine two of an object's properties to tell us what their name is and where they live.

This method's signature will include a return type, in this case `String`. We will name it `generateBio` and it won't have any parameters. The biggest change will be in the method's body where we will use `return` to explicitly state a value which will be returned from the method. This value will be available at the point the method is called, either to be stored in a variable or passed on into another method.

```java title="Person.java"
// Person.java

public class Person {

  	// ...
  	
  	String generateBio(){
   		return "My name is " + this.name + " and I live in " + this.town + ".";
   	}

}
```

If we call this method from `Runner` our program will execute without error but we won't see anything printed. The value is returned from the method but then immediately discarded unless we do something to capture it, for example storing it in a variable.

```java title="Runner.java"
// Runner.java

public static void main(String[] args) {

	// ...
	
	String personBio = person.generateBio();

  	System.out.println(personBio);
}
```

### Behaviours in UML

The last piece of the UML jigsaw (for now) is our methods. Just as we do with properties, we need to provide details of the data types being used. We still have colon-separation between name and data type, but this time the data type is that of the returned value. We included parentheses after the method name and list the type of any parameters inside them. We don't need to specify a name for the parameter here, since the compiler doesn't care about the internal naming we use so long as it is consistent.

![class diagram for Person including behaviours](../../../assets/java/classes/class_digram_methods_added.png)

## Encapsulating Our Data

We've already seen that we can access a property of an object using dot notation.

```java title="Runner.java"
// Runner.java

public static void main(String[] args) {

	Person person = new Person("Colin", "Bathgate");
	
	System.out.println(person.name); 		// prints "Colin"

}
```

Being able to access a property like this has knock-on effects though - it means we can also modify it!

```java title="Runner.java"
// Runner.java

public static void main(String[] args) {

	Person person = new Person("Colin", "Bathgate");
	
	person.name = "Iain";
	
	System.out.println(person.name); 		// prints "Iain"

}
```

Maybe not a big deal in every scenario, but a major risk in others. If we are designing a program we don't want users to be able to come along and change things however they like, it would be near-impossible to predict every possible interaction and guard against it. Imagine we were designing banking software - would it be a good thing if someone could write code which could come along and modify account balances however they wanted to? We can take steps to prevent this by following a process called [**encapsulation**](https://stackify.com/oop-concept-for-beginners-what-is-encapsulation).

In a nutshell, encapsulation helps us keep an object's properties secret and safe from modification by restricting access to them. By encapsulating our properties we make it impossible to directly access them in the way we have above and restrict the user's ability to read or overwrite them. This is achieved using **access modifiers** - Java keywords which dictate which values are accessible outside of the class.

We will start by marking all of our properties as `private`. This means that they can only be accessed from within the class, making it impossible to directly access and reassign anything from `Runner` as we did above.

```java title="Person.java"
// Person.java

public class Person {

  	private String name;
   	private String town;
   	private int age;

	// ...

}
```

Our compiler will now throw an error telling us that the `name` property has private access. Success, we have stopped anyone editing the property! But what if we have a genuine need to? We can't even access the property to *read* the value, never mind edit it.

We can solve this problem through the use of **getters** and **setters** - special methods which will return a given property or allow a user to pass a value in which we can use to update the property. By doing this we retain the functionality but on our terms, no more free-form editing. We can't make those methods `private` though, otherwise we would lose the functionality again. Instead we will mark them as `public`.

```java title="Person.java"
// Person.java

public class Person {

	// ...
	
	public String getName() {
   		return this.name;
   	}

  	public void setName(String name) {
   		this.name = name;
  	}
  	
  	// Similar for town and age

}
```

By making these methods `public` we are making them accessible from anywhere in our program. That means that so long as we have a `Person` object we can call `getName` to find the value of it's name property and `setName` to update it with a new value. We can fix our compiler error by updating `Runner` to use these new methods.

```java title="Runner.java"
// Runner.java

public static void main(String[] args) {

	Person person = new Person("Colin", "Bathgate");
	
	person.setName("Iain");
	
	System.out.println(person.getName()); 
}
```

In general we will make all of our properties `private` and our methods `public` but there are exceptions to this, for example we could define a `private` method which only exists as a helper for someother method defined in the class. We *must*, however, ensure that the constructor is `public`. If it was `private` we would only be able to access it from inside the class, making it impossible to instantiate the class anywhere. Adding access modifiers to the remaining methods leaves our class looking like this: 

```java title="Person.java"
// Person.java

public class Person {

	private String name;
   	private String town;
   	private int age;

   	public Person(String name, String town) {
   		this.name = name;
       this.town = town;
       this.age = 0;
   	}

   	public void greet(String timeOfDay){
   		System.out.println("Good " + timeOfDay + "!");
   	}

   	public String generateBio(){
   		return "My name is " + this.name + " and I live in " + this.town + ".";
  	}
  	
  	// getters & setters

}
```

We also need to update our class diagram. Although not mandatory it's not uncommon to see access modifiers annotated on daigrams using a `-` to denote a `private` field and `+` for a `public` one.

![class diagram for Person including modifiers](../../../assets/java/classes/class_digram_access_modifiers.png)


## `static` Properties and Methods

So far we have learned how to create classes, how to define their properties and methods, how to instantiate those classes by creating new objects and how to call methods on those objects. We also saw that *everything* in a Java program must be defined inside a class. That includes our `Runner` class with the `main` method inside it. That raises an important question though: we haven't instantiated `Runner` anywhere in our program, so how are we able to call `main` and start it up?

The answer lies in the `static` keyword. By declaring a method to be `static` we associate it with the class itself rather than the instantiated objects, meaning we don't need to create an object in order to call it. Our `main` method *must* be `static`, otherwise the JVM would need to somehow create and manage an instance of `Runner` in order to call it. 

`static` methods are otherwise defined in the same way as other methods with access modifiers, return types and parameters. We can define one in our `Person` class to demonstrate.

```java title="Person.java"
// Person.java

public class Person {

	// ...

  	public static void staticMethodExample(){
   		System.out.println("I'm a static method in Person!");
   	}

}
```

We can call it from within `main` using just the name of the class, there is no need to instantiate an object first.

```java title="Runner.java"
// Runner.java

public static void main(String[] args) {

	Person.staticMethodExample();
	
}
```

Since `static` methods don't require an object to be instantiated there are some restrictions on how we can use them. We can't access any properties belonging to the class, since those properties are only initialised when an object is instantiated. Nor can we call a non-static method from within one, since those methods need to be called on an object. As a result `static` methods are best used in a scenario where we have some logic common to all instances of a class which does not depend on any of its properties or existing behaviours.

We will be able to access a property from within a `static` method if that property is also declared to be `static`. That doesn't limit us to only accessing the property from a `static` method though, we can access it from instances of the class too. To demonstrate we will update `Person` again with a new `static` property and some more methods.

```java title="Person.java"
// Person.java

public class Person {

	private static sharedValue = 0;

	// ...

  	public static void staticMethodExample(){
   		System.out.println("I'm a static method in Person!");
   	}

  	public void incrementSharedValue(){
   		sharedValue += 1;
   	}

}
```

Note that we initialise the property at the point of declaration since we won't be calling a constructor to do it. This happens once when the class is defined and won't be overwritten when we create any new objects. Also note that `incrementSharedValue` is *not* declared `static` - we will need a `Person` object to be able to call this method, but every object will be accessing the same `static` property. To demonstrate this we will return to `Runner`.

```java title="Runner.java"
// Runner.java

public static void main(String[] args) {

	Person person = new Person("Colin", "Bathgate");
	Person person2 = new Person("Anna", "Glasgow");

	Person.printSharedValue();			// sharedValue is currently 0

   	person.incrementSharedValue();

  	Person.printSharedValue();			// sharedValue is currently 1

   	person2.incrementSharedValue();

   	Person.printSharedValue();			// sharedValue is currently 2
	
}
```

`sharedValue` is not directly associated with either object, but both are able to access it. Once each has updated it we are able to use `printSharedValue` to display the new value without tying that behaviour to a particular object. 

Although we may not write many of them we will likely use `static` methods frequently in our programs. For example, there are many methods for searching and sorting data structures defined as static methods in the `Collections` class which we will need often. With the exception of `main` there is nothing we will write in this course which *must* use a static method, but there may be situations where they help us a lot.