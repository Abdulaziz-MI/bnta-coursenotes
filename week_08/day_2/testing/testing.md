# Testing in JavaScript

## Learning Objectives

* Understand the purpose of unit testing
* Understand how to include an installed package as a development-only dependency
* Be able to set up a unit test using the Jest testing framework
* Appreciate how the Arrange-Act-Assert pattern is structured
* Be able to run a testing suite from the terminal when building a Node application
* Recognise how the `describe` method from Jest can be used to better organise related unit tests
* Appreciate what is meant by "test coverage" and why we may look for this value

## Introduction

Ensuring that our code functions as expected is a vital part of software engineering. While we could do this by manually checking everything, it just isn't practical when applications start getting bigger. Instead we will write tests to cover the various scenarios in which our application might find itself.

Writing tests in this way is called **unit testing**. We test the code in the smallest blocks possible before we test how those blocks interact with each other. By taking this methodical approach we ensure that every component is working as expected.

The advantages of using a testing framework over manual testing don't end there. One of the major benefits is in reusability. If we were testing manually then evey time we made a change to the code it would be necessary to re-do all of the tests, whereas when using a testing framework all we need to do is run a test file.

## Setting up a Testing Framework

There are a range of testing frameworks available for JavaScript, each with their own pros and cons. Most are available through [npm](https://www.npmjs.com/) and some are included within larger JavaScript frameworks.

We will be using the [Jest](https://jestjs.io/) framework to write our tests with. Before we install it we need to create a project and set it up to use npm. We will use the default values in `package.json` for now.

```sh title="Terminal"
mkdir jestCalculator
cd jestCalculator
npm init -y
```

Testing frameworks are not necessary in production code. By the time something is released it should be fully working, so including the unit tests would be redundant. We _do_ need them while developing an application though, so how can we make this distinction in our code?

We can indicate that a package should only be used during development by adding the flag `-d` when we install it. It will be listed under a separate key in `package.json` and will not be included in a production build, but crucially _will_ be picked up when someone else installs the dependencies locally.

```sh title="Terminal"
npm install -D jest
```

## Writing a Test

Tests can be very simple, very complex or anything in between. Typically we will test code in small blocks then build up to more complex tests.

For this example we will build a simple calculator which can perform basic arithmetic. Some separation of concerns is required, so we will keep the code for the calculator's functionality separate from the tests. We'll start with the logic.

```sh title="Terminal"
touch calculator.js
```

Generally we want to avoid writing too much code without testing it. By following this practice it is easier for us to isolate problems as they occur and figure out where bugs are coming from. With that in mind, while we _could_ write out all the functionality for our calculator now we will instead only write the code for addition, then test it.

Our calculator functions will each take two arguments, perform the operation and return the result.

```js title="calculator.js"
const Calculator = function() {
  this.sum = function(a, b){
    return a + b;
  };
}

```

We will need to access this function in the test file in order to make sure it works. In some languages this can be done just by importing it into the test file, but in JavaScript we need to explicitly export the function first.

```js title="calculator.js"
//...

module.exports = {Calculator};
```

The tests will be written in their own file. Each testing framework has its own naming conventions, but most will involve the word "test" somewhere in the file name. With Jest we follow the convention of including the name of the class under test followed by "test" and then the extension.

```sh title="Terminal"
touch calculator.test.js
```

In order to use the functions defined in `calculator.js` we need to import them here. To import from a file we use the `require` keywork in the same way as we would if importing from a package, but with the distinction that here we need to supply a relative filepath instead of a package name. The `.js` extension is not necessary here.

```js title="calculator.test.js"
const {Calculator} = require('./calculator');
```

Testing frameworks are slightly different from other packages in that we do not need to import them explicitly. Instead when we run Jest the `.test.js` extension flags the appropriate files for it to execute.

Let's also create a variable store our `calculator` instance in, and use the `beforeEach()` method to reset the calculator before each individual test. While it doesn't change much in this example, because it's so simple, it's best practice to re-initialise reference variables before every test (to avoid the outcome of an earlier test polluting the outcome of a later one), and this is the most DRY way of doing so:

```js
let calculator;

beforeEach(()=> {
  calculator = new Calculator();
})
```

Jest provides a `test()` function for us to use. It takes two arguments: a string describing what is being tested and a definition for the test. The test works by comparing the result of calling the function under test with our expected result. We begin by testing our addition function.

```js title="calculator.test.js"
test('can add two small positive numbers', () => {
  expected = 5;
  actual = calculator.sum(2, 3);
  expect(actual).toBe(expected);
});
```

We follow a pattern know as **Arrange-Act-Assert**:

- We **arrange** any prerequisites for our function. There are none in this case, but examples include creating necessary objects or populating arrays
- We **act** - calling the function under test. We should only ever act **once** in a unit test.
- We **assert** that the result of our action matches the expected value.

The `toBe()` method is an example of a **matcher** and defines how we are comparing the result with the expected value. In this instance the matcher takes an argument, however some others (such as `toBeNull()`) do not as they are checking for a specific condition. We can also negate any matcher by adding `not` to the call, eg. `expect(actual).not.toBe(expected)`.

Our tests are run in a slighlty different way to other JavaScript programs. If we try to run the tests using `node calculator.test.js` we will get an error saying the `test` function is undefined. This is expected since we haven't defined it anywhere and it isn't a core part of the JavaScript language. It is defined within Jest however, meaning we need to run Jest itself rather than a specific file in order to run our tests.

Since Jest was installed using npm, we can actually use npm to run and manipulate it within our program. Within `package.json` there is a key which we have so far ignored called `scripts`, which contains a default script called `test`.

```json title="package.json"
"scripts": {
  "test": "echo \"Error: no test specified\" && exit code 1"
}
```

We can define scripts here to perform specific tasks while developing our applications, any of which can be run using the command `npm run scriptName`. Some keywords can be run without including the `run` part of the command, including `test`. If we run the script as currently defined we will see the message defined in `package.json` printed in terminal before the application terminates.

```sh title="Terminal"
npm test
```

That's not particularly useful to us - we want to run our tests with this command, not print an error message. We can edit the script to include the command to run the framework, in this case just the command "jest".

```json title="package.json"
"scripts": {
    "test": "jest"
  }
```

Now when we run `npm test` we will see the output of the test telling us if it has passed or failed.

## Managing Multiple Tests

We have written a test to show that we can add two small numbers together, but we can't say that we have tested our function yet. We need to cover all the possible arguments which could be passed in (big numbers, zero, negative numbers) in order to ensure nothing breaks unexpectedly. That means writing more tests, and that in turn means a more cluttered file and a denser output. The extra code is unavoidable but Jest has a built-in tool to help us manage it.

We can add a `describe` block to our code inside which we can group together related tests. It follows a similar structure to the `test` function: A string describing the block then a definition of what will be done. We will add a block to gather our addition tests together.

```js title="calculator.test.js"
describe("addition functionality", () => {
  test("can add two small positive numbers", () => {
    expected = 5;
    actual = calculator.sum(2, 3);
    expect(actual).toBe(expected);
  });
});
```

Running our tests with `npm test` still shows the output but now shows the test result under the heading "addition functionality". We can add as many `describe` blocks as we like to a test file and each block can include as many tests as necessary, however we should still remember the Single Repsonsibility Principle and avoid covering too much within the same file. As a rule of thumb each JavaScript file should have its own test file with relevant tests.

## Test Coverage

With more complicated functions it can be very difficult to make sure we have fully tested every aspect of it. It's very easy to forget about edge cases, especially when we start including compelx conditional logic. Jest has another built-in tool which will tell us what percentage of our code has been tested, known as the **test coverage**.

To do this, we will add another script to **package.json**:

```json title="package.json"
"scripts": {
    "test": "jest",
    "test:coverage": "jest --coverage"
  }
```

We can now run this script to show us not only which tests pass and fail, but also which lines of code have been covered by them. The results are broken down by function, branch and even individual line.

```sh title="Terminal"
npm run test:coverage
```
