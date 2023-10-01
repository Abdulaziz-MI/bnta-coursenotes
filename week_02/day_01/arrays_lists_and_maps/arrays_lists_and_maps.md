# Arrays, Lists & Maps

### Learning Objectives:

- Know how to create new arrays, lists and maps in Java
- Be able to add data to and recall data from arrays, lists and maps
- Know the differences between each data structure and when to use each

## Introduction

Hopefully by now everyone is convinced that variables are incredibly useful things. The ability to store pieces of data and refer back to them later is critical for many programs and ours are no exception. We have something to consider though - how can we best manage lots of related pieces of information without cluttering our code too much?

For the purposes of this lesson we'll be thinking about how to record the details of the trainers. We can do this at the moment but only by creating an individual variable for each.

```java
String trainer1 = "Colin";
String trainer2 = "Anna";
String trainer3 = "Iain";
```

Three variables is fine, but what's going to happen if we hire more people? As the team grows we will need to create a new variable for each new trainer and that will quickly get unworkable. Naming is going to become problematic - we'll need to keep track of what the next number should be, but what happens if someone quits? Do we reassign their variable to a new team member? Do we move everyone up a number? There are lots of questions to be answered and decisions to be made. We can pass some of these of to the program itself though by avoiding the use of individual variables and instead using one of Java's built-in **collections**.

## Working with Arrays

In this lesson we will look at **arrays**, **lists** and **maps**, three examples of **data structures** in the Java language. The concepts we will discuss here exist in some form in every programming language, however the naming of them and the precise details of their implementation will vary. In Java each has a distinct use case and limitations, although often a problem could be solved using one of a range of data structures.

There are some common patterns in the implementation of some data structures, but **arrays** sit apart from others. They are much more restrictive in terms of what we can with them and while that's not necessarily a bad thing we do need to be aware of what those limitations are.

We'll start off by creating a new project to work in and a new file for experimenting with arrays. Inside the `main` method we will declare a new variable to store an array. Just like before we need to define a data type and a name, but this time we need to indicate that our variable will hold multiple values. We can't mix-and-match with any data structures in Java: if an array is going to hold `String`s, for example, it can *only* hold `String`s. In the case of arrays we show that our variable will store more than one of them by adding square brackets (`[]`) after the data type. If we wanted to store all of our trainers' names in an array we would declare it as follows:

```java title="ArraysPlayground.java"
// ArraysPlayground.java

public static void main(String[] args) {

	String[] trainers;

}

```

Initialisation is done by wrapping all the values to be included in curly brackets `{}` on the right of the expression.

```java title="ArraysPlayground.java"
// ArraysPlayground.java

public static void main(String[] args) {

	String[] trainers = {"Colin", "Anna", "Iain"};

}
```

Technically the `trainers` variable still only contains a single value, the array's location in memory. We can see this if we try to print the array to the terminal.

```java title="ArraysPlayground.java"
// ArraysPlayground.java

public static void main(String[] args) {

	String[] trainers = {"Colin", "Anna", "Iain"};
	
	System.out.println(trainers);
	
	// Prints something like [Ljava.lang.String;@4617c264

}
```

The underlying structure of an array in Java means that this information is all we need, everything else can be derived at run-time if we have this start point to work from.

Our array has three names in it, so how can we see what they are? Each element of an array has an identifier which tells us where in the array it is, known as an **index**. We can extract the value at a given index in an array by writing the index number in square brackets after the variable name. For instance to find the first element in `trainers`:

```java title="ArraysPlayground.java"
// ArraysPlayground.java

public static void main(String[] args) {

	String[] trainers = {"Colin", "Anna", "Iain"};
	
	String firstTrainer = trainers[0]
	
	System.out.println(firstTrainer);
}
```

Note that we don't start counting from one! The overwhemling majority of programming languages start counting from zero as a consequence of how the computer tracks the count internally, meaning it often looks like we're off by one when counting. We can access any element in the array in this way, but we can only pass in an index which exists in the array. If we try to access an index greater than the maximum for the array, for example `trainers[10]`, our program will throw an `ArrayIndexOutOfBoundsException`. This is exactly what it sounds like - we don't have anything at index 10 to access, so the index is considered to be "out of bounds". Our compiler is only checking that we provide an `int` as the index, so these exceptions will only be seen at run time.

We have seen what happens if we try to access an index which doesn't exist, but what if there is no data available at the given index? This might sound like a strange situation to be in but it can easily occur as a consequence of how arrays are constructed. In the example above we provided some inital values to populate the array with, but we can actually create an array without them. The only thing we *have* to supply is the size of the array, since in Java arrays have a fixed size allocated in memory when they are created.

```java title="ArraysPlayground.java"
// ArraysPlayground.java

public static void main(String[] args) {

	String[] emptyTrainers = new String[3];

}
```

We can still access the value at a given index in the same way as before, but now there's no value there to retrieve. Instead we get the value `null`, a special value to indicate that the space is allocated in memory but doesn't have a value stored in it.

We can use the index notation in the same way to store data in our array using `=` for assignment. We can also use it to replace something with a new value.

```java title="ArraysPlayground.java"
// ArraysPlayground.java

public static void main(String[] args) {

	String[] emptyTrainers = new String[3];

   	emptyTrainers[0] = "Colin";
	emptyTrainers[1] = "Anna";
	emptyTrainers[2] = "Iain";

   System.out.println(emptyTrainers[2]);
   
   // prints "Iain"

   emptyTrainers[2] = "Zsolt";

   System.out.println(emptyTrainers[2]);
   
   // prints "Zsolt"

}
```

We have a small array here so we can easily keep track of how many items it includes, but that can quickly become problematic as the array grows larger. Arrays are reference types though, meaning just like the other reference types discussed previously they have properties and behaviours we can use to find this information. Arrays have a lot fewer than some other types, but the `length` property is particularly useful, especially when we start to look at loops later in the course.

```java title="ArraysPlayground.java"
// ArraysPlayground.java

public static void main(String[] args) {

	String[] trainers = {"Colin", "Anna", "Iain"};

  	int trainerCount = trainers.length;

	System.out.println(trainerCount);

}
```


## Working with Lists

Hopefully we can agree that arrays are great. In fact they're more widespread than we might think and are underpinning some other very common programming constructs, for example a `String` is fundamentally an array of `char` primitives. They have some significant downsides however and won't always be appropriate in every scenario.

A significant potential downside is the fixed size of an array. Consider our example above - we are limited to three trainers on the team, meaning we have to replace someone if we make a new hire. Obviously that's not great, so an array is probably not the best tool to use here. Instead we can consider using a **list** which gives an extra degree of flexibility compared to an array.

Part of that flexibility lies in the fact there are many different types of list available to us in Java. The fundamentals are the same, but each has its own methods available to manipulate the data. In this example we will use an `ArrayList`. We will create a new file with a `main` method and initialise a new variable to store our trainers' names.

```java title="ListPlayground.java"
// ListPlayground.java

public static void main(String[] args) {

	ArrayList<String> trainers = new ArrayList<>();

}

```

The way in which the type of the data stored in the list is specified is slightly different to that used for arrays, but we still need to indicate that in this case our list will hold `String`s. As a consequence of how lists are constructed in Java we can only use reference types, eg. `Integer` instead of `int`. If we *have* to use a primitive data type (which shouldn't be the case given auto-boxing is available to us) we need to use an array. Also note that we need to include empty angle brackets (`<>`) in the constructor. This is a hangover from older versions of Java when it was necessary to specify the type on both sides. Modern compilers can infer the missing value from the type declaration.

While it is possible to initialise a list with values already there it's much less common to see in practice. Instead we generally create a new list and add or remove elements as necessary. The location of each element in the list is tracked using indices as before but we no longer use the square bracket notation to access an index directly. Instead we interact with a list exclusively using the methods available to us. For example, to add new elements to our list:

```java title="ListPlayground.java"
// ListPlayground.java

public static void main(String[] args) {

	ArrayList<String> trainers = new ArrayList<>();
	
	trainers.add("Colin");
   	trainers.add("Anna");
  	trainers.add("Iain");

}
```

Instead of specifying exactly where to add the new value we use the `add` method which adds the value to the end of the list. There are no restrictions on available space (up to the available system memory) meaning we can add as many values as we need to.

Accessing data is again done using indices, but using the `.get()` method this time. As with an array we can pass in any numerical value, but if we try to access a non-existant index we will hit a run-time error

```java title="ListPlayground.java"
// ListPlayground.java

public static void main(String[] args) {

	ArrayList<String> trainers = new ArrayList<>();
	
	trainers.add("Colin");
   	trainers.add("Anna");
  	trainers.add("Iain");
  	
  	String firstTrainer = trainers.get(0);
  	
  	System.out.println(firstTrainer);
  	
  	// prints "Colin"
  	
  	String mysteryTrainer = trainers.get(10);
  	
  	// throws IndexOutOfBoundsException at run time

}
```

We can still get a count of how many elements there are in a list, although instead of a `length` property we have a `.size()` method. We can also check if there are any elements at all using the `isEmpty()` method. A significant difference between lists and arrays, though, is the ability to remove elements from a list. Since arrays have a fixed size we can't just take something out, instead we need to replace it with `null` in order to maintain the size. This isn't a problem with lists, we can just take an element out.

```java title="ListPlayground.java"
// ListPlayground.java

public static void main(String[] args) {

	ArrayList<String> trainers = new ArrayList<>();
	
	trainers.add("Colin");
   	trainers.add("Anna");
  	trainers.add("Iain");
  	
  	trainers.remove(0);
  	
  	System.out.println(trainers)

}
```

Note that we can also remove a specific element instead of passing an index, eg. `trainers.remove("Colin")`. Regardless of the method use, removing an element will affect the indices of the remaining elements. In an array we could replace an element with `null` and it would not affect the rest of the array - `trainers[2]` would still return the value `"Iain"`. In a list, on the other hand, the indices change to reflect the current state of the list. If we try `trainers.get(2)` now we will get an out-of-bounds exception, while `trainers.get(0)` still returns a value. This dynamic nature means lists are *much* more flexible than arrays, but there is a trade-off in efficiency. 


## Working with Maps

The data structures we have looked at so far have their differences, but also have something in common which we haven't looked at yet. Both arrays and lists have some concept of *ordering*, that is the concept of a "first" and "last" thing in the collection. This may not seem obvious in an array but it is clearer in a list, where a new element is assigned an index one higher than its predecessor. This is useful in some scenarios, for example if we want to iterate through the collection's contents. The downside is that every element is automatically assigned a numerical label - what if we want that label to represent something different?

When ordering doesn't matter to us, or when we want to associate two pieces of information in a structured way, we can use a **map** to store the data. Maps are another type of data structure, just like arrays and lists, and just like lists they come in many different types. In this example we will use a `HashMap` but others are available, depending on the situation. 

To understand why a map may be useful, let's consider a scenario where we want to add more information about our trainers. If we wanted to know how long each of them had worked for Bright Network then tools we currently have would limit us to storing that information in an array or a list, for example:

```java
String[] trainers = {"Colin", "Anna", "Iain"};

int[] monthsAtCompany = {11, 4, 9};
```

This works so long as the ordering is maintained and we know which index we are looking for at any given time. In this case we can look up a given index in both arrays and find the information, but as we have seen those arrays can be edited and we are relying on the user doing it correctly. Imagine we hire someone new:

```java
trainers[0] = "Richard";

monthsAtCompany[1] = 0;
```

The data is in each array, but not in the corresponding places. We need a tool to help us associate the two pieces of information. Working in a new file again, let's create a map and see how it can help us out here.

```java title="MapPlayground.java"
// MapPlayground.java

public static void main(String[] args) {

	HashMap<String, Integer> trainerEmploymentLengths = new HashMap<>();
        
}
```

Maps store data in **key-value pairs** - labels and their associated values - and we need to provide a type for each, with the key data type going first. Note that we need to use reference types here again. Every time we add something to our map we will need to provide both of these values.

Inserting something into a map is done with the `.put()` method. We provide a *key* matching the first data type which we will use in place of an index when retrieving data and a *value* which will be associated with it.

```java title="MapPlayground.java"
// MapPlayground.java

public static void main(String[] args) {

	HashMap<String, Integer> trainerEmploymentLengths = new HashMap<>();
        
   trainerEmploymentLengths.put("Colin", 11);
   	trainerEmploymentLengths.put("Anna", 4);
   	trainerEmploymentLengths.put("Iain", 9);
}
```

Retrieving a value is a matter of using the `.get()` method again, but this time instead of passing an index we pass one of the keys. The method will return the corresponding value.

```java title="MapPlayground.java"
// MapPlayground.java

public static void main(String[] args) {

	HashMap<String, Integer> trainerEmploymentLengths = new HashMap<>();
        
   	trainerEmploymentLengths.put("Colin", 11);
   	trainerEmploymentLengths.put("Anna", 4);
   	trainerEmploymentLengths.put("Iain", 9);
   	
   	int annaEmploymentLength = trainerEmploymentLengths.get("Anna");

   	System.out.println(annaEmploymentLength);
}
```

Note that we can revert to the primitive type once we have retrieved the value, Java handles the conversion automatically. 

Removing an element from a map is done similarly to lists, although again passing the key to the method. Replacing a value is slightly different, though. With an array we could simply overwrite the value but with a map we need to use a method again. The method takes two arguments: the key associated with the old value and the new value.

```java title="MapPlayground.java"
// MapPlayground.java

public static void main(String[] args) {

	HashMap<String, Integer> trainerEmploymentLengths = new HashMap<>();
        
   	trainerEmploymentLengths.put("Colin", 11);
   	trainerEmploymentLengths.put("Anna", 4);
   	trainerEmploymentLengths.put("Iain", 9);
   	
   	trainerEmploymentLengths.replace("Colin", 12);

   	System.out.println(trainerEmploymentLengths.get("Colin"));
}
```

There is one notable restriction on keys: they must be unique within a map. This ensures that we can still access the correct piece of information using `.get()`. A compiler will not flag errors here, however. Our IDE may provide a warning but the compiler will be happy so long as the types match. We won't even get a run-time error, instead the original value will be overwritten by the one associated with the duplicate key.

Keeping track of the keys used in a map can be tricky, but the `.keySet()` method will show us what we have already used. This returns a `Set`, another type of data structure which ensures each element is unique. We can also see all the values stored in a map using `.values()` but this information is less useful without the identifying keys.