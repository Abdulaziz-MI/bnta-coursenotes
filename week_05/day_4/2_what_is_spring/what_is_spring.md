# What is Spring
​
## Learning Objectives:
​
* Understand the high-level structure of Spring and its purpose
* Appreciate the difference between Spring and Spring Boot
* Be able to use the Spring Boot Initialiser to create a new Spring Boot project
* Be able to build a Spring Boot app with at least one implemented route
​
## Intro

Spring is an application Framework for Java. It is one of the most popular Java frameworks for developing enterprise web applications and services. It allows for faster and more productive web development in Java.

One of the classic problems with any framework is that once it increases in popularity, it also increases in complexity. This can make it overwhelming to understand what is going on, and it can be a lengthy process to even start a project.

We can think of Spring as an 'umbrella' framework. That is, Spring is not just one library that we bring into our code to add new features, rather Spring gives us many libraries to choose from.

## Spring Boot
For that reason, **Spring Boot** was introduced, which takes away a lot of the "boilerplate configurations" for setting up a Spring application. This essentially means it saves us a few headaches and time as the developer.

Spring Boot is a kind of 'one-stop-shop' under the Spring umbrella that lets us quickly create projects that can:
-connect our java application and our database by mapping our objects to databases using SQL statements
- Create controllers to define our routes
- Write methods that read/write to our database in order to create CRUD functionality

This will all be done for us (or at least greatly assisted) by selecting the right dependencies.

Our first applications won't necessarily use all these projects, but we will build on the functionality as we go through the lessons this week.

## Creating our first Spring Boot app
The easiest way to understand the structure of Spring is to jump right into the deep end. So we're going to make a simple "Hello World" app using Spring Boot.
​
First things first, we can use the (Spring Boot Initializr)[https://start.spring.io/] to boot up a project quickly.

Spring Boot Initializr (or Spring Generator) is an easy to use tool for creating Spring based projects. As Spring is huge, the Initializr lets us generate the boilerplate for a project with any dependencies pre-installed. 
​
> Click the link above and select the following options:
> 
> - Project: Maven
> - Language: Java
> - Spring Boot Version: 3.0.0
> - Update "artifact" in the meta-data to "hello_world". Add a description if you wish.
> - Packaging: Jar
> - Java: 17
> 
> Add the dependencies:
> 
> - Spring Web
> - Spring Boot DevTools
>

We have installed two dependencies for this project:
- **Web**: allows us to do RESTful routes abd create controllers
- **DevTools**: helps with development ​

Once the details above are correct, click *GENERATE*. It should download a file named `hello_world.zip`. Extract the file and open it in Intellij.
> students may find it helpful to move their folder to `bnta_work`
> Once Intellij is open, students can create a command-line laucher (allowing them to open projects in Intellij directly from the Terminal. `Tools` > `Create Command-line Launcher`. Click `OK` to create the suggested script.
​
Your project structure should look something like this. (We have replaced some files with "..." as they are not so for this lesson.) What is important for you to know is what is in the `src` directory, which will contain all the code that we will run.
​
```
├── HELP.md
├── bin
│   └── ...
├── build.gradle
├── gradle
│   └── ...
├── gradlew
├── gradlew.bat
├── settings.gradle
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── example
    │   │           └── hello_world
    │   │               └── HelloWorldApplication.java
    │   └── resources
    │       └── ...
    └── test
        └── ...
```
​
So we have our app, but it doesn't really do anything. You can run the app from here but you won't see anything exciting. So let's make it a bit more interesting.
​
We want to extend our app to contain a simple API, with one endpoint (also known as a route). Let's start by making a directory called `controllers` within `src/main/java/com/example/hello_world`. Create a file in `controllers` called `GreetingController.java`.
​
So the `hello_world` directory should now look like this:
```
.
└── hello_world
    ├── HelloWorldApplication.java
    └── controllers
        └── GreetingController.java
```
​
Let's move forward and build up our controller. Open up the `GreetingController.java` file and add the following code:
​
```java
// GreetingController.java
​
@RestController
public class GreetingController {
​
    @GetMapping("/greeting")
    public String greeting() {
        return "Hello World";
    }
}
```
​
This looks very similar to plain ol' Java, but you'll notice some new keywords here:
- `@RestController` tells Spring that this class will be a RESTful API controller (See previous lesson if you don't know what this means).
- `@GetMapping("/greeting")` creates a new route in our app, allowing clients to send `GET` requests to it. 
​
Run the application via the `main` method in `HelloWorldApplication`. Open your browser and head to `http://localhost:8080/greeting`. We should see "Hello World".

### Params

We're almost there, let's add one more piece of functionality to our app. Let's add a `parameter` to our requests, so we can see custom messages like "Hello John" or "Hello Jebediah".
​
Open up the GreetingsController.java file again, and we're going to modify the `greeting()` function.
​
```java
// GreetingController.java

@RestController
public class GreetingController {
    @GetMapping("/greeting")
    // MODIFIED
    public String greeting(@RequestParam(defaultValue = "world") String name) {
        return String.format("Hello %s", name);
    }
}
```
Notice we've added a parameter to our `greeting()` function. We've added the `@RequestParam` annotation which contains a `value` and `defaultValue`. 

The `value` will be what we specify when sending a request, e.g. `http://localhost:8080/greeting?name=John`. So if our `value` was `bananas`, then the request URL would look like this `http://localhost:8080/greeting?bananas=John`
​
The `defaultValue` is a String type attribute and the default value to use as a fallback when the request parameter is not provided or has an empty value. In this case our `defaultValue` is `world`.
​
So if we send `http://localhost:8080/greeting?name=Alex`, we will get back "Hello Alex".
But if we send `http://localhost:8080/greeting`, we will get back the default value, "Hello world".
​
