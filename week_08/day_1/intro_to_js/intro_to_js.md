# JavaScript - An Introduction

## Learning Objectives

* Appreciate the great prevalence of JavaScript on the web
* Understand how and why JavaScript is used to create dynamic web elements, bolstering interactivity
* Understand what NodeJS is and its purpose
* Know how to declare and make use of a variable in JavaScript
* Appreciate what is meant by "Dynamically Typed Programming Language"
* Understand how to recognise the different types of data in JavaScript and their respective common use-cases
* Know how to create some simple JavaScript code within a file to be run through NodeJS
* Appreciate the difference between a "Compiled" and "Interpreted" Programming Language and how this allows us to contrast JavaScript against Java

## Introduction

Of all the programming languages in existence, JavaScript probably has more regular users than any other. The thing is, people probably don't realise they're using it. That's because JavaScript isn't what comes to mind when we first think of the internet even though it's what drives most of the functionality that makes the internet what it is today. Either through a simple HTML `<script>` or through a complex framework, JavaScript is everywhere online.

Since its creation JavaScript has grown to be used in many different environments and today it's used for much more than just powering websites. There is very little that cannot be done in JavaScript, although it is often done in a slightly different way. Like any language JavaScript has its own quirks and rules which must be followed to make the most of it.

## A Brief History

In 1995 the internet was a much simpler place. We typed a URL into our browser's address bar and (eventually) got some HTML back from the server. There wasn't anything dynamic about what we got back, it was simply a static page. That meant that if we wanted to interact with the page at all we needed to send another request to the server and wait for it to respond with updated HTML. In the days of dial-up that could take a long time.

Enter JavaScript, or **LiveScript** as it was known at the time. LiveScript could be embedded within the HTML using `<script>` tags and then executed by the browser when the user performed certain actions. For example, list could now be filtered by the browser without having to tell the server how to do the filtering then wait for a response. It hugely improved the web-browsing experience.

There were some changes, starting with the name. The developers wanted to piggy-back on the popularity of the recently-released Java and so ripped off the name, which is the _only_ connection between the two languages. There was also a need to standardise the language as it became more popular, with different browser developers putting their own spin on different aspects of the implementation. [ECMA](https://www.ecma-international.org/) took responsibility for maintaining an agreed specification for JavaScript (known as ECMAScript) which all browsers now implement. ECMA release annual updates to the specification to introduce new features.

## JavaScript Outside the Browser

Web browsers include a tool known as a **JavaScript engine** which enables them to execute the code embedded in web pages. We can interact with it by opeing the developer tools in our browser and clicking the "console" tab. [NodeJS](https://nodejs.org/en/) was created to enable developers to run JavaScript code outside of the browser and be used to run files or as a tool to test snippets of code. It can be started from anywhere in the terminal by typing `node`. The two interfaces work in the same fundamental way, but the browser console includes more auto-complete functionality.

From the interface we can write JavaScript and watch it run, but we're a little restricted in what we can do. We have more than enough functionality to let us get to grips with the basics. Let's start by writing some text, also known as a **String**:

```js title="node"
"hello world";
```

JavaScript is an **object oriented** language, meaning that every piece of data is represented by a data structure known as an **object**. When we type "hello world" an object is created to represent it in node. Every object has a **type** which determines how we can interact with it. For example, we can make a String upper case:

```js title="node"
"hello world".toUpperCase();
// 'HELLO WORLD'
```

Would it make sense to make a number upper case? We can find out the type of an object (and therefore what we can do with it) using the `typeof` function.

```js title="node"
typeof "hello world";
// 'string'

typeof 42;
// 'number'

typeof true;
// 'boolean'
```

## Variables

A critical part of any program is the manipulation of data. We will usually be provided with some sort of input which our program needs to act on, for example by saving it to a database. Keeping track of data is hard though. Let's add two numbers together:

```js title="node"
2 + 3;
// 5
```

As soon as we perform the calculation we get the answer, which is great, but what if we want to refer back to that value at a later point? The value isn't being stored anywhere, meaning if we want to use it again we have to do the calculation again. We can avoid this problem by storing the result in a **variable**.

```js title="node"
sum = 2 + 3;
```

Now we can refer back to this value by typing the name of the variable in our code.

```js title="node"
4 * sum;
//20
```

Variables can be reassigned to a different value (unless we specifically say otherwise, more on that in a future lesson)

```js title="node"
sum = 10 + 10;
4 * sum;
//80
```

We can store objects of any type in a variable. Some languages are **statically typed**, meaning that any reassignment has to be to an object of the same type. JavaScript is **dynamically typed**, meaning we can reassign a variable to an object which has a different type.

```js title="node"
myVariable = 42;
myVariable = "hello world";
```

## Writing a JavaScript File

Node is a great tool for testing JavaScript code but anything we write there is lost as soon as we close the REPL. In day-to-day use we'll be writing our code in files which we run using Node, enabling us to write more complex programs. We signify that a file will contain JavaScript by giving it a `.js` extension.

```sh title="Terminal"
touch hello_world.js
```

We have our first JavaScript file! Inside the file we'll declare some variables and print them to the terminal using the `console.log()` function.

```js title="hello_world.js"
name = "Colin";
temperature = 18;

console.log("Hello " + name);
console.log("The temperature is " + temperature + " degrees today.");
```

```sh title="Terminal"
node hello_world.js
```

Success, we've written our first JavaScript program!

## Compiled Languages vs Interpreted Languages

When we were writing Java running our programs was a two-stage process: before we could run them they had to be compiled. As we have just seen, the compilation step isn't necessary for running a JavaScript program.

That's because JavaScript is an **interpreted** language. The conversion from the code we write to bytecode which can be understood by the CPU still happens but is now done as the interpreter reads the file. This has some advantages, for example dynamic typing is possible and it is much easier to get started with a language quickly. However, compiled languages have their own benefits. In general programs written using compiled languages such as Java are more efficient as they give finer control over things like memory management. They also have the advantage of better exception handling.

With a compiled language we can open up the files containing the bytecode and inspect it before running (although it won't make much sense to us without learning some low-level languages first). We don't have those files with an interpreted language but we can still access the bytecode by adding a flag when we run the file.

```sh title="Terminal"
node --print-bytecode hello_world.js
```

### JIT JS Compilers

While the above rule of languages being either compiled or interpreted generally holds true, the line has become more blurred in recent years with the introduction of just-in-time (JIT) compilers for JavaScript. As the name suggests, JIT compilers compile your JS to binary just before execution. Examples of JS engines that use this JIT technique are V8 (Chrome, Node) and SpiderMonkey (Firefox) - this is the primary reason why these engines perform so much faster than their traditional, interpreter-based counterparts.
