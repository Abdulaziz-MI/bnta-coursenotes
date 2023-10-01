# Higher-Order Functions

## Learning Objectives

* Understand what is meant by a "higher-order function" in JavaScript
* Appreciate how higher-order functionality supports the creation of generic looping functionality
* Understand how JavaScript functions being first-order object enables higher-order functionality
* Understand what is meant by a "callback"
* Be able to recognise use of a higher-order function in an unfamiliar codebase
* Be able to create a bespoke higher-order function
* Appreciate the strength of exceptions being raised by failing functionality, instead of failing silently
* Be able to define a callback function in-line with a function call
* Recognise the use-case of the in-built `map` Arrays method in JavaScript
* Recognise the use-case of the in-built `filter` Arrays method in JavaScript
* Recognise the use-case of the in-built `reduce` Arrays method in JavaScript

## Introduction

Functions in JavaScript are a little different from those in other languages. We can replicate the behaviour we saw in statically-typed languages by attaching them to an object's prototype, but we can also define them in-line without being associated with a constructor.

We have also see that we can store them in a variable. This is because functions in JavaScript are **first-order objects**, just like strings, numbers or any other object. Anything we can do to objects like these, such as the aforementioned variable storage or assigning to an object's key, can be done to a function. Crucially **this extends to interacting with other functions**; any first order object can be passed as an argument to, or returned from, a function. A function which accepts or returns another function is known as a **higher-order function** and these form an important part of a JavaScript application's architecture.

## Using a Higher-Order Function

In our introduction to functions we wrote the functionality for a simple calculator. It's a long way from finished though, the only way for our user to specify what they want to do is to directly call the function. This isn't overly practical though, so we need a way to streamline the process.

```sh title="Terminal"
touch higher_order_calculator.js
```

Let's strip our calculator back and limit ourselves to just addition and subtraction for now.

```js title="higher_order_calculator.js"
const sum = (number1, number2) => {
  console.log(number1 + number2);
};

const subtract = (number1, number2) => {
  console.log(number1 - number2);
};
```

Let's make our calculator a little more user-friendly by adding a third function which will let the user input two numbers and what they want to do with them. **This function will determine which of the other functions to call based on the user's input.**

```js title="higher_order_calculator.js"
const doCalculation = (number1, number2, operation) => {
  if (operation === "sum") {
    sum(number1, number2);
  } else if (operation === "subtract") {
    subtract(number1, number2);
  }
};
```

This will do the job for us, but there are a couple of major flaws. We are tightly restricted not only in terms of _what_ we can ask our calculator to do, but also _how_ we ask for it. Consider these examples:

```js title="higher_order_calculator.js"
doCalculation(2, 3, "sum");

doCalculation("SUM");

doCalculation(2, 3, "subtract");

doCalculation(2, 3, "multiply");
```

The first and third will run correctly, but not the others. The second fails because the capitalised version of the function name isn't accepted, while the fourth is asking for something which hasn't yet been defined.

This isn't particularly extendable either. If we add multiplication, for example, we need to both write a new function to handle the operation and also modify `doCalculation`.

```js title="higher_order_calculator.js"
const multiply = (number1, number2) => {
  console.log(number1 * number2);
};

const doCalculation = (number1, number2, operation) => {
  if (operation === "sum") {
    sum(number1, number2);
  } else if (operation === "subtract") {
    subtract(number1, number2);
  } else if (operation === "multiply") {
    multiply(number1, number2);
  }
};
```

If we want to follow the [Open/Closed Principle](https://www.digitalocean.com/community/conceptual_articles/s-o-l-i-d-the-first-five-principles-of-object-oriented-design#open-closed-principle) then we shouldn't be opening up the `doCalculation` function to add an extra `if-else` clause; however if we strictly adhere to this principle then we would be unable to add extra functionality down the line. There is a solution though: instead of passing in a string describing what we want to do to the numbers, we could instead pass in **the definition of what we want to do**.

In order to do this we need to make our `doCalculation` function a **higher-order function**. We will still take two numbers as arguments, but we will replace the string naming the operation with a _function_. Functions passed as arguments in this way are known as **callbacks** and can be called from within the higher-order function. We will replace the `operation` parameter with this `callback` and replace the `if-else` statements to instead call this same callback:

```js title="higher_order_calculator.js"
const doCalculation = (number1, number2, callback) => {
  callback(number1, number2);
};
```

We also need to modify how we call the function as shown below:

```js title="higher_order_calculator.js"
doCalculation(2, 3, sum);

doCalculation(2, 3, subtract);

doCalculation(2, 3, multiply);
```

Our functions are stored in variables, so instead of passing in a string describing the function **we pass in the function itself**. If we pass in a function which exists within our program everything is fine, but if we _don't_ then things will behave a little differently. Recall what happened earlier when we passed in an unrecognised string:

```js title="higher_order_calculator.js"
doCalculation("SUM");
```

It didn't work, but it also didn't break completely. Instead we printed `undefined`—we got to the end of the `if` statement but didn't match any of the conditions, so nothing was returned. Now, however, our application throws an error. Instead of a _string_ we expect a _function_ and we will hence attempt to call whatever we pass in.

When we pass something in which isn't a function, an exception is thrown. At first, this may seem like a bad thing, but in fact it's likely to be desirable behaviour. In general we don't want things to fail silently as we want to know if something goes wrong. Now we have an exception to **catch** and deal with instead of an `undefined` sneaking into another part of the application!

## Defining a Callback In-Line

At first glance our calculator still appears to be a little lacking in functionality. So far, it doesn't look like we've solved the problem of adding new functionality—for how can we make our calculator divide two numbers?

One option would be to write a `divide` function which could be stored in a variable and passed as an argument, however, we don't even need to go that far. Just like we don't need to store our numbers in variables before we pass them in, we can apply the same logic to the callback by defining the function at the **same time as when we call** `doCalculation`.

```js title="higher_order_calculator.js"
doCalculation(2, 3, (number1, number2) => {
  console.log(number1 / number2);
});
```

The function we have defined here is anonymous and only exists inside the scope of the function call. It is passed in as the `callback` argument and is executed when we call the callback inside the function's body.

This style of passing a callback is common, but it's not particularly dominant. As always, we should try to avoid repetition as much as possible: if we are defining the same callbacks anonymously multiple times then we should consider moving the code into a function of its own.

Remember: Don't Repeat Yourself!

## Higher-Order Functions in Practice - Enumerators

Higher-order functions really come into their own when it's time to start doing things over and over again. True, we've already got loops to help us out with that, but when we bring the two of them together we can write much more elegant and powerful code. Let's start by creating a new file and defining an array of numbers inside it.

```sh title="Terminal"
touch enumeration.js
```

```js title="enumeration.js"
const numbers = [1, 2, 3, 4, 5];
```

We've already seen how to loop over this array and print each number to the console:

```js title="enumeration.js"
for (let i = 0; i < numbers.length; i++) {
  console.log(numbers[i]);
}
```

As soon as we want to print any other array, though, we have to duplicate the code. We can get part of the way to a solution by **abstracting** the printing code into another function and calling it from inside the loop:

```js title="enumeration.js"
const printElement = (arrayElement) => {
  console.log(arrayElement);
};

for (let i = 0; i < numbers.length; i++) {
  printElement(numbers[i]);
}
```

That's slightly better, but it's still not ideal. To do anything else we would need to change both a condition in the loop and something inside the second code-block. In an ideal world we wouldn't need to alter any of that, and a higher-order function can help us get there.

Instead of using a `for` loop we can use a method defined on the `Arrays` prototype - `forEach`. This method takes a callback as an argument and applies it to each element of the array in turn. We can replace the bulk of our loop with a single method call.

```js title="enumeration.js"
numbers.forEach(printElement);
```

Hypothetically we could now print the elements from _any_ array.

```js title="enumeration.js"
numbers.forEach(printElement);

letters.forEach(printElement);

otherStuff.forEach(printElement);
```

As before, the callback can be as complex as we like and can be defined anonymously when we call the function. `forEach` can also take [additional arguments](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/forEach#syntax) to enable even more functionality in the callback.

## Building on `forEach`

There are some operations which are performed on arrays so often that the JavaScript specification now includes specific methods for them. Each of the methods below can be recreated using `forEach`, but these versions cut out some of the low-level steps.

### Mapping

The `map` enumerator is used to perform some operation to every element of an array and retain the results _without_ mutating the original array. The operation is defined in the callback and applied to each element in turn, with the returned value being added to the new array. The new array is returned from `array.map()` and must be stored in a variable before we can use it.

```js title="enumeration.js"
const numbers = [1, 2, 3, 4, 5];

const doubledNumbers = numbers.map((number) => number * 2);

console.log(doubledNumbers);

// [2, 4, 6, 8, 10];
```

### Filtering

Like mapping, the `filter` enumerator returns a new array without mutating the original. This time the body of the call back is a conditional, usually describing some condition the array element must satisfy. If the callback returns `true` for a given element, then the element is added to the output array.

```js title="enumeration.js"
const numbers = [1, 2, 3, 4, 5];

const evenNumbers = numbers.filter((number) => number % 2 == 0);

console.log(evenNumbers);

// [2, 4];
```

### Reducing

The `reduce` enumerator is slightly different from `map` and `filter` in that it returns a single value instead of an array. It still loops through the array but "reduces" the values it finds to that value, modifying as it goes. In addition to the callback we need to provide a _reducer_, an initial value which will be updated through the iteration. The callback determines how the reducer is modified on each iteration.

```js title="enumeration.js"
const numbers = [1, 2, 3, 4, 5];

const total = numbers.reduce((reducer, number) => reducer + number, 0);

console.log(total);

// 15;
```
