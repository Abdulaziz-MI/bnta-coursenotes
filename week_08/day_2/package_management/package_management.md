# Package Management in JavaScript

## Learning Objectives

* Understand the purpose of NPM and the `package.json` file
* Be able to initialise a new application `package.json` file using `npm`
* Be able to install a new package to an application with NPM
* Understand the structure of a `package.json` file, including where to identify application dependencies
* Appreciate the meaning between the three values of a package version number
* Be able to make use of code from an external package by first importing it using the `require` keyword
* Appreciate why `node_modules` is often included in the `.gitignore` file of a project
* Be able to install dependencies for a cloned project from the command line
* Understand the purpose of the `package-lock.json` file

## Introduction

As we develop our skills as engineers the applications we build will become more and more complex. There will be a lot of new features which we want to add, some more complex than others. It's also pretty likely that someone else will already have had the same idea, meaning it wouldn't be particularly DRY of us to re-implement the same thing.

In our Java applicaitons we saw how we could use Maven to add _dependencies_ to our applications. The dependencies we listed in the `pom.xml` file specified where we were getting the code from and the desired version, enabling the complier to add what was needed at compile time. This ensured that we couldn't try to run our program without the dependency being installed, since it would throw an error at compile time if the code was unavailable for some reason. We can take broadly the same approach of lisitng our dependencies with JavaScript, but since it is an interpreted language rather than a compiled one we need to follow a different process to do so.

## Setting up an Application to Include a Dependency

Since we don't have the safety net of a compiler, we need to ensure that all the dependencies we need are installed before we run our program. In some languages this is done globally so that once a dependency has been added to a system it is available to all programs running on that system, for example pip in Python or using Ruby's gems. For JavaScript things are slightly different: while we can configure the package management to run globally, the default behaviour is to define things individually for each application.

In JavaScript we use the **N**ode **P**ackage **M**anager (or **[npm](https://www.npmjs.com/)**) to manage our dependencies. Similar to other package managers, we can use a CLI to install and maintain our applications' dependencies through the terminal. When we installed Node npm came with it, meaning we can quickly set it up for our project.

```sh title="Terminal"
mkdir npm_playground
cd npm_playground
npm init
```

The `npm init` command indicates that we want to use npm to manage dependencies within this project and immediately prompts us to enter some information about our project. We can press `enter` to set the default for each of these for now, alternatively we can run the command `npm init -y` to set all these at once. We can edit any of this information later.

A file has ben created for us called `package.json`. In this file we can see the information we just entered in a format called **json** - short for **J**ava**S**cript **O**bject **N**otation. We can edit this file as we see fit, so long as we follow the key-value pair syntax.

## Adding a Dependency

The dependencies we add can vary in size and complexity, from tiny quality of life improvements all the way up to large frameworks which change the way in which we structure our programs. In this example we'll keep it simple and add a small tool which will give our application access to information about our laptops.

The package we're going to use is called [systeminformation](https://www.npmjs.com/package/systeminformation) and we'll add it using the `install` command.

```sh title="Terminal"
npm install systeminformation
```

In newer versions of npm we can shorten this to `npm i systeminformation`. We can also add flags to specify which version of a package to install, or even to denote that a package should only be used in a development environment and not included in a production build.

A couple of things have changed in our application. Starting with `package.json` we can see a new `dependencies` key has been added. The corresponding value is itself a json object where the keys represent our dependencies and the values represent their version numbers. Depending on the additional flags we pass to `npm install` they may be listed separately, for example any flagged as being for development only appear under `devDependencies`.

### A note on version numbers

When working with software it's unusual to simply see something marked as "version 1". A typical application goes through many changes over its lifecycle and adding one to a number every time something is added or removed would get unmanageable very quickly. Instead we follow a convention known as **semantic versioning** which gives some indication of the nature of a change alongsie the version number.

Usually a program's version number is split into three parts:

- The **major version number** - `1.X.X`. A change in the major version number indicates **breaking changes**, ie. features in another package which depend on this one may no longer work due to some significant change in the code.
- The **minor version number** - `X.1.X`. A change here indicates **non-breaking changes** - new features have been added but not in a way which will affect any existing uses
- The **patch version number** - `X.X.1`. This is the most common number to change and usually indicates a **bug fix**.

## Using a Package

Now we have added the dependency to our package we need somewhere to use it.

```sh title="Terminal"
touch info.js
```

In order to use code from a package we need to add it to our own using the `require` keyword.

```js title="info.js"
const si = require("systeminformation");
```

Exactly what is imported depends on how the package is structured. In this case we get an object with a number of methods we can call to retrieve specific details of our systems. For example, to print our battery status we can add the following line:

```js title="info.js"
const si = require("systeminformation");

si.battery().then((data) => console.log(data));
```

We aren't concerned with _how_ the `battery()` method is getting hold of this information, that's why we used a package to add it. By adding packages to our code in this way we can add a huge amount of extra functionality such as this without duplicating the work of others.

## Setting Up Dependencies in a Cloned Project

At some point we'll need to work together with someone on a project, meaning we'll need to clone it from GitHub. If that project includes any dependencies we need to set them up locally.

When we installed the `systeminformation` package earlier a new directory appeared in our file structure: `node_modules`. If we open this directory we see a sub-directory with the name of the package we installed and inside that a lot of JavaScript files. When we installed the package these files were downloaded for us and added to the project. Often we will find additional folders which contain packages our installed package depends on.

Usually the `node_modules` folder is added to the `.gitignore` file and not pushed to GitHub. The number of dependencies can grow quite big, meaning lots of third-party files being included in our projects. That means that when someone clones our project the code will be written expecting to find a dependency which isn't there. We can recreate this scenario for ourselves by deleting the `node_modules` folder and attempting to run the code.

```sh title="Terminal"
rm -rf node_modules
node info.js
```

This will throw a "module not found" error with the stack trace pointing to the `require` call on line 1. We can't run our code without adding that package.

We don't need to completely re-install it, though. The `package.json` file has been pushed to GitHub and includes the details of the packages we added. If we run the `npm install` command without any arguments it will scan `package.json` to find what needs to be installed and download it. If we run that command now we will see that the `node_modules` folder reappears with all the needed code.

```sh title="Terminal"
npm install
ls
```

There is one more file which was added for us: `package-lock.json`. This follows a similar structure to `package.json` but goes into much greater detail about each package. This helps us ensure that anyone running our project has _exactly_ the right version of any packages used, or indeed the dependencies of those packages. Running `npm install` will search for the most recent version of the dependencies it finds, which has the potential to introduce breaking changes if we don't specify which version it was built with.
