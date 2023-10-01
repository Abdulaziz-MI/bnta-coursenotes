# Loops & Iteration

## Learning Objectives

* Understand what is meant by a logical "loop" in a programming language
* Be able to create a classic `for` loop within your Java application
* Understand the syntax of the classic `for` loop declaration
* Be able to define different bounds for a classic `for` loop within the loop declaration
* Appreciate how increment and decrement operators are used within `for` loop declarations
* Understand the difference between the "classic" and "enhanced" `for` loop in both syntax and common use-case
* Understand the use of the `break` keyword
* Understand the use of the `continue` keyword

## Introduction

Loops are a type of syntax we use to perform operations a certain number of times. They are used to **repeat** operations. In a similar vein, loops also allow us to iterate over **collections**, such as **arrays**, performing operations on *all of the elements* within. In this lesson, we'll be learning about the basics of looping and iterating in Java.

Before we learn about loops however, we need to understand how **incrementing** and **decrementing** works, since this is used in the structure of most loop syntax. 

## Increment (++) and Decrement (--) Operators

The increment (`++`) and decrement (`--`) operators are used to *increment* or *decrement* a value/variable inline:  

For example, given `int i = 0;`

- `i++` returns the value **first**, and *then* increments:

```java
int i = 0;

System.out.println(i++); 
// 0

System.out.println(i); 
// 1
```

- `++i` *increments* first, and *then* returns the value (opposite order): 

```java
int i = 0;

System.out.println(++i); 
// 1

System.out.println(i); 
// 1
```

- `i--` returns the value first, then decrements:

```java
int i = 0;

System.out.println(i--); 
// 0

System.out.println(i); 
// -1
```

- `--i` returns the value first, then increments:

```java
int i = 0;

System.out.println(i--); 
// -1

System.out.println(i); 
// -1
```

## **For** Loops
For loops are frequently used in conjunction with lists/arrays to **repeat a block of code** on every element in the list/array. They help us write DRY (Don't Repeat Yourself) code.

Loops in Java come in a couple of different formats: classic and enhanced.

### The Enhanced For Loop (aka For Each)

> right-click on your blue java folder and create a new class called `Loops`. Open the file and write a new `main` method.

The enhanced for loop has a few advantages over the classic for loop:
- more readable
- easier to use and understand

Suppose we have a list of objects, and we wish to perform on action on every object in the list. In our example, given a list of colours, we want to print each colour to the Terminal. We can begin by making a list and adding a handful of colours to it. We can check this has worked by viewing our arraylist with a `System.out.println`.

```java
// Loops.java

public static void main(String args[]) {

    List <String> colours = new ArrayList<>();
    colours.add("red");
    colours.add("silver");
    colours.add("green");
    colours.add("raspberry red");
    
    System.out.println(colours);

}
```

While the Terminal does render `colours`, it may be that we want each name printed out individually. We could do this using `get()`:

```
System.out.println(colours.get(0));
System.out.println(colours.get(1));
System.out.println(colours.get(2));

...etc
```

But, as you can see, there is a lot of repeated code. In a 'real-life' situation, we also may not know how long our arraylist is. It would be much better to do so using a for loop:

```java
// Loops.java

...

    System.out.println("----Using enhanced for loop:----") // a label for our terminal

    for (String colour: colours) {
        System.out.println(colours);
    }
```

Run your program to see the difference.

It may be also that we want to do more than print out the colours in our arraylist. Suppose we want to capitalise each colour as we print it out:

```java

// Loops.java

for (String colour: colours) {
        System.out.println(colour.toUpperCase());   // MODIFIED
    }

```

Or perpaps we only want a list of colours that start with `r`:

```java

// Loops.java

...

List<String> filteredColours = new ArrayList<>();   // ADDED

for (String colour: colours) {
        if (colour.charAt(0) == 'r'){     // ADDED
            filteredColours.add(colour)
        }
 }

System.out.println(filteredColours);

```

### The Classic For Loop

Our classic for loop consists of the following parts:
- the `for` keyword
- The **Initial Expression**: initialises the value of `i` for the first iteration
- The **Stop Condition**: as long as this condition evaluates a true, the loop will continue
- An expression that updates `i` on each iteration *until* the **stop condition** returns `false`

```java
// Loops.java

public static void main(String args[]) {

...

    System.out.println("----Using classic for loop:----") // a label for our terminal
    
    for (int i = 0; i < colours.size(); i++) {  // ADDED
        String colour = colours.get(i);
        System.out.println(colour);
    }

}

```
Note that we are initialising `i` as `0` as we are using `i` to track the index (which starts at 0). 

While the classic loop syntax can take a little getting used to, it can be very useful. Suppose for some reason you need to keep track of the index or need a little more control over what elements in the list are targeted. For example, we may want to only print out every other number in a list:

```java
// Loops.java

...

List<Integer> numbers = new ArrayList<>();  // ADDED

for (int i = 1; i <=10; i+=2){  //ADDED
    numbers.add(i);
}

System.out.println("numbers: " + numbers);  // ADDED

```


There are other types of loops syntax, such as `while` and `do while`, that we will explore later.



## The **Break** Statement

The break statement is used to **break out** of a loop or statement;

```java
for (int i = 0; i < 10; i++) {
  if (i == 4) {
    break;
  }
  System.out.println(i);
}
/* 
Output:
    0
    1
    2
    3
*/
```

## The **Continue** Statement

The continue statement is used to **skip** the rest of the code inside a loop for the *current iteration only*.

```java
for (int i = 0; i < 10; i++) {
    // if i is less than or equal to 4, do nothing...
    if (i <= 4) {
        continue;
    }
    System.out.println(i);
}
/* 
Output:
    5
    6
    7
    8
    9
    10
*/
```

## Video Recap
![type:video](../../../assets/java/loops/4-java-loops.mp4)
