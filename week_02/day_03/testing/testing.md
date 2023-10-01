# Testing

## Learning Objectives

* Understand that there are different ways in which we can test our code
* Be able to write unit tests using the JUnit 5 framework
* Be aware of the test-driven development paradigm

## Manual Testing

When you are first learning to program, the most obvious thing you'll think of when you hear the word testing is manual testing, which is where you run the program, play around with it, and see if it breaks, then make some changes, and run it again. Manual testing is a **valid** part of the overall testing process. However, it is **not enough** in itself. Why not? 

## Automated Unit Testing
Because, as your code base grows, becoming more complex and interconnected, if something goes wrong it becomes very **confusing**, cumbersome and difficult to debug when using manual testing **alone**. What you need is a form of *isolation* testing - treat each class as a **stand-alone unit**, and test it. And, since we're programmers, we can write code to **automate** this process - that's what **unit testing** is all about.

## Junit: The Defacto Java Testing Framework
In Java, the defacto testing framework is called JUnit. However, there are 2 major **versions** of this in use today.
### JUnit Versions
- **JUnit 4**: still in use because **legacy code** depends on it, and because not every developer with legacy code in JUnit 4 is ready to make the jump to version 5. 
- **JUnit 5 (aka JUnit Jupiter)**: The **next generation** of JUnit. We'll be learning **this** version, since it's the latest. Also includes a module called *JUnit Vintage*, to support code migration from JUnit 4. We can **ignore vintage**, because we're not migrating legacy code.

## JUnit 5
### The Architecture
JUnit 5, unlike it's predecessors, is composed of 3 core components:

- **JUnit Platform**: This essentially *runs* everything. Jupiter and Vintage are basically *assertion libraries* built on this.
- **JUnit Jupiter**: The **new** way of writing JUnit tests. We **will** be using this.
- **JUnit Vintage**: The **old** way of writing JUnit tests. We **won't** be using this. You know it exists, now forget about it.

Remember the **component architecture** like this:
> `JUnit` 5 **=** JUnit `Platform` + (JUnit `Jupiter` + JUnit `Vintage`)

This is mostly academic, so we won't dwell on it any more than we already have - for a more verbose discussion of JUnit's architecture, please [see the JUnit documentation here](https://junit.org/junit5/docs/current/user-guide/). Let's move on.

## Maven Dependencies
As we said above, we'll be using **Jupiter**, since that is the **modern way** of writing JUnit tests. You can add the maven *dependency* to your POM file like so:

```xml title="pom.xml"
<!-- pom.xml -->

<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.8.2</version>
    <scope>test</scope>
</dependency>
```

## Create your first test

You already know that, by convention, your application code is stored in `src/main/java` - well our testing code mirrors that closely - it's stored in `src/test/java`. Beyond this, copy the package names and folder structure exactly. 

The *name* of the testing class *must* be the **same** as the class you are testing, **but** with `Test` at the end, as a suffix. i.e `Main.java` -> `MainTest.java`, `Dog.java` -> `DogTest.java` etc.

![Screenshot 2021-08-31 at 19 21 04](https://user-images.githubusercontent.com/40702606/131556052-62ee741e-3053-4b8e-a1f8-4d49eb40a5f7.png)


Follow the above guidelines to create your first test class, then write the following:

```java
import org.junit.jupiter.api.Test;


class MainTest {

    @Test
    void myFirstTest() {

    }
}
```

The `@Test` annotation in the above code tells JUnit to **run** this method as a test. **Run** the project and you should see an output as follows:

![Screenshot 2021-08-31 at 19 25 24](https://user-images.githubusercontent.com/40702606/131556226-2beb2626-f7cb-4bad-bec3-20c32fa05302.png)

Hold on, don't get too excited. The test doesn't actually do anything yet. To make it meaningful, we need to add some *assertions*...

## Assertion Libraries
Unit testing is all about making assertions: you **assert** that, **given** certain *input*, **when** something *happens*, **then** you should get a particular *result*. If it works as expected, the test *passes*. Otherwise, it *fails*.

Jupiter includes a range of built-in assertion methods which can be used to check various conditions. There are additional libraries which add their own versions of these assertions, such as [**AssertJ**](https://assertj.github.io/doc/#assertj-overview). AssertJ provides a more fluent approach - that just means it's much more **readable**, and so, ultimately, **more intuitive** to work with.

To use AssertJ we need the following Maven dependency in our POM file:

```xml title="pom.xml"
<!-- pom.xml -->

<dependency>
    <groupId>org.assertj</groupId>
    <artifactId>assertj-core</artifactId>
    <version>3.22.0</version>
    <scope>test</scope>
</dependency>
```

## Format of Tests - Making 'Assertions'

Here is the basic format of making assertions with AssertJ:

```java
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class MainTest {

    @Test
    void myFirstTest() {
        assertThat("actual").isEqualTo("expected");
    }
}
```
This particular test will *fail*, because, as you can see, `"actual"` is **not** equal to `"expected"`.

Refer to [https://assertj.github.io/doc/](https://assertj.github.io/doc/) for full api documentation.


### Given... When... Then...
The process of making assertions can be described with the given/when/then formula:
- **Given** some input value(s).
- **When** a certain **operation** is performed (method is called).
- **Then** expect a certain **result**.

```java
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MainTest {
    @Test
    void myFirstTest() {
        // Given
        String input = "HELLO"
        // When
        int actual = input.toLowerCase();
        // Then
        String expected = "hello";
        assertThat(actual).isEqualTo(expected);
    }
}
```
The above code example is written in such a way as to illustrate this conceptual 3 step process clearly for you - in practice, you may write all 3 steps into a **single line**. We may also see them written as:

- **Arrange** - do any initial set-up
- **Act** - call the method under test
- **Assert** that the code was executed as expected.

Now, let's start looking at some example cases...

## Calculator Class

We're going to model a calculator which can do basic arithmetic. Our class won't need any properties but we will need to define a method for each operation we want to carry out.

```java title="Calculator.java"
// Calculator.java

public class Calculator {

   	int add(int a, int b) {
   		return a + b;
   	}

   	int subtract(int a, int b) {
   		return a - b;
   	}
}
```

As you can see, the above class has two methods. Let's write a test class with some assertions, to make sure that they work as intended/expected:

## Calculator Test

```java title="CalculatorTest.java"
// CalculatorTest.java

public class CalculatorTest {

   	@Test
   	public void canAddNumbers(){
   		Calculator calculator = new Calculator();
      	int expected = 5;
      	int actual = calculator.add(3, 2);
      	assertThat(actual).isEqualTo(expected);
  	}

   	@Test
   	public void canSubtractNumbers(){
   		Calculator calculator = new Calculator();
      	int expected = 1;
      	int actual = calculator.subtract(3,2);
      	assertThat(actual).isEqualTo(expected);
   	}
}
```

Both our tests pass! We're not quite done though, we have only demonstrated that our methods work in very specific scenarios. For example, do we know that `subtract` will still work if we swap the order of the arguments? Will `add` be able to add two negative numbers? What happens if the arguments are really bug, or really small? We don't need to write tests for every possible combination of arguments, but we should consider common patterns, edge and corner cases and ensure each has been tested.

As we write more tests we will start to see some repetition in our code. In fact we can already see it in the two tests we have now - we need to create a new `Calculator` object for every test. This isn't very DRY but it can be averted through the use of another annotation - `@BeforeEach`. We declare a `Calculator` property for the test class - which can be accessed from any method - and define a method (by convention named `setUp`) which we annotate with `@BeforeEach`. The annotation tells JUnit to call the method before *every* test, creating a brand new `Calculator` every time.

```java title="CalculatorTest.java"
// CalculatorTest.java

public class CalculatorTest {

	private Calculator calculator;
	
	@BeforeEach
	public void setUp(){
		calculator = new Calculator();
	}

   // Remove Calculator instantiation from tests
   
   // ...
}
```

It is important to create the new object using the annotated method instead of instantiating it when it is declared. An important benefit of this is that it re-creates the object before each test runs, meaning any changes made to the object by a previous test are discarded and therefore cannot affect any subsequent tests.


## Test Driven Development (TDD)
So far, we've examined how to implement a testing library -- in this case JUnit -- to **unit test** our application automatically. This is **standard practice** in industry, you **have** to know how to do it, and make it a habit. However, so far, we've approached it from the point of view of writing our classes and methods, **then** testing them. Is there another way?

What happens if we turn that process on its head and write our tests **first**, then our application/classes? That is the concept behind test driven development, or **TDD**. Truth be told, there are *no hard and fast rules* on this; some developers swear by the TDD approach, others are less rigid about it. But wherever you stand on this, it is **essential** that you *understand* what it is, *how* to do it and, most importantly, *why* we do it.

Let's extend our calculator with a couple more methods.

```java title="Calculator.java"
// Calculator.java

public int multiply(int a, int b){
	return a ^ b;
}

public boolean isDivisibleBy(int a, int b){
	return b % a == 0;
}

public int doubleIfDivisibleBy(int a, int b){
	if (isDivisibleBy(a, b)){
   		return multiply(a, 2);
   	} else {
   		return a;
   	}
}

```

`multiply` will return the product of two numbers, `isDivisibleBy` will check if one is divisible by another and `doubleIfDivisibleBy` will combine the two to multiply the first argument by 2 if it is divisible by the second argument. Let's test it with a couple of small numbers we can easily check manually and make sure it works.

```java title="CalculatorTest.java"
// CalculatorTest.java

@Test
public void canDoubleIfDivisible(){
	int expected = 8;
   	int actual = calculator.doubleIfDivisibleBy(4, 2);
   	assertThat(actual).isEqualTo(expected);
}
```

If we run our test we see that it fails! Something must have gone wrong in our logic somewhere... but where? We have written so many extra methods without testing them that we can't be certain which bit has broken - there could even be a problem in all of them! If we practice test-driven development, however, we would actually be in an ideal situation right now. In TDD we *want* to see the tests fail the first time they run, but not because something is broken. Instead we want them to fail because we *haven't written any code yet*, so they couldn't possibly pass. We would then write the absolute minimum amount of code needed to make the test pass (in this case `return 8` would be all we need) and move on to the next test. 

If we reach a point where we need a helper method (a method called from inside another) then it needs tests of its own. In our example we should have tested `isDivisibleBy` before `doubleIfDivisibleBy`.

```java title="CalculatorTest.java"
// CalculatorTest.java

@Test
public void fourDivisibleByTwo(){
	boolean actual = calculator.isDivisibleBy(4, 2);
   	assertThat(actual).isTrue();
}

@Test
	public void twoNotDivisibleByFour(){
   	boolean actual = calculator.isDivisibleBy(2, 4);
   	assertThat(actual).isFalse();
}
```

Both of these tests fail, suggesting there is a problem with this method (Note that we write two tests to ensure that the method returns `true` when it is supposed to and also `false` when it is supposed to). If we check the logic we see that our expression is actually the wrong way round, it should read `return a % b == 0`. If we make this change and rerun the two tests we just wrote both will now pass.

We can't assume that everything is solved though, in fact if we rerun *all* of our tests we see that `doubleIfDivisibleBy` still isn't behaving. We need to test our `multiply` method too.

```java title="CalculatorTest.java"
// CalculatorTest.java

@Test
public void canMultiply(){
	int expected = 8;
	int actual = calculator.multiply(4, 2);
   	assertThat(actual).isEqualTo(expected);
}
```

Our test fails, so there's a problem here too. We've used the incorrect operator (`^` instead of `*`) so we aren't multiplying the numbers correctly. Fixing this and rerunning the tests results in them all passing, meaning we have working code!

We wouldn't have saved ourselves any work in terms of writing code if we had followed a TDD approach here since the tests all need to be completed anyway, but we would have been able to slowly build up our program and deal with the bugs one by one. This has also illustrated the importance of breaking code down into small, reusable blocks - it would have been a nightmare to debug `doubleIfDivisibleBy` without being able to test the helper methods individually.
