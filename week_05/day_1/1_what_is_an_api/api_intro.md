# What is an API?

## Learning Objectives

* Understand what Application Programming Interfaces (APIs) are
* Understand how APIs are used
* Understand the RESTful route paradigm
* Understand the different types of RESTful routes

## Introduction

API stands for Application Programming Interface - another scary-sounding acronym! Let's dissect it, and see what the original idea behind this could be.

We've been building applications up to this point - apps that can take input from the user through the terminal using the `Scanner` class, then print out some feedback. Or apps that represent a zoo with animals and enclosures, and the interactions between different classes.

These apps were built for our personal use. After a while, one of the following might happen:

- Our app might start storing some data that could be extremely useful for our colleagues at the same company we work at. Maybe you want to display the data and prices of a bunch of items for your website. You can store marks for exams, bus or flight schedules, results of sports matches. This data is not easy to consume for the average user unless it's displayed nicely by another app, like a website.
- The app you've built might be doing some fancy calculations - could be useful for the scientific community, budgeting for personal use, or maybe your chances of success for getting a coveted item in your favorite online game.
- It could consume some images, documents, or videos, and manipulate them in some way - convert them to different data types (`.avi` to `.mp4`, `.wav` to `.mp3`), or resize them.

We can share the source code of our app with interested parties, but this has some problems:

- They need to understand the programming language the app is written in. Even with extensive documentation, this might be time-consuming and difficult.
- We could potentially be giving up a revenue stream since owning the source code means they can use it free of charge
- We could write an app around it, but what if the end users of the app would like to utilise it differently?

Luckily we have an alternative: We could turn our app into an API!

An API can take a couple of forms, but most of the time, it is either web-based or packaged code for a certain language that can be downloaded and used in other people's apps.
This gives us the missing parts of the acronym puzzle: We are creating a programmable interface for our application!

## How do APIs work?

Imagine you are going to a restaurant. The restaurant is split up into two parts:

1. The dining area (The part you see and interact with)
2. The kitchen (The part you don't see, but it's where the magic happens 🪄)

The main action happens in the kitchen, it's where your food is cooked, but you don't communicate directly with the cook. Instead, waiters act as the middleman between you and the cook.

The waiter's main goal is to handle communication between you and the chef. Firstly, the waiter gives you information on what food is available, and second;y, the waiter informs the chef about what you have ordered.

This process is very similar to how an API works. The frontend (waiter), displays information and allows you to action something, and those actions take place on the backend (cook) 👩‍🍳

Let's see some examples of APIs:

## Data API

Data drives the world. It is estimated that in 2018, the total amount of data created, captured, copied, and consumed in the world was 33 zettabytes (ZB) – the equivalent of 33 trillion gigabytes.

Not surprisingly, this data needs to be consumed in some form for it to be useful. One of the ways we can do that is to create APIs that provide data to the consumers.

This data can be in the form of videos, images, audio, different document types like `.pdf`, `.xls`, etc., or in a more raw format suitable for consuming it via another application - like XML (a bit outdated) or JSON (more on this later).

> Show an API serving up some JSON data, for example: [Countries of the world by restcountries.com](https://restcountries.com/v3.1/all)

## Java APIs

Java has a lot of APIs for specific purposes - using these APIs enables us to use code that someone else has written, kind of like a package, but in a way where communication between apps is standardized, and we have control over who has access to our API.

Some examples of APIs in Java:

- Android API for android development
- JavaOpenGL, a wrapper for the OpenGL API to render 2D and 3D vector graphics
- JPA (Jakarta (formerly Java) Persistence API) for storing Java objects in databases (more on this later)

## Other APIs

APIs can have other purposes as well!

- Google Maps - chances are you've seen websites that render a small map on a page with some markers, details, and maybe routes. This is possible because of the Google Maps API
- Pixelencounter - [Random Pixel Monster generator ](https://app.pixelencounter.com/api/basic/monsters/random), similar to how GitHub creates their automatic profile pictures
- and many more

## REST API

In the following couple of weeks, we are going to build a RESTful Data API.
To demystify another acronym: REST stands for Representational State Transfer. This doesn't tell us much, so let's see what REST is all about!

REST is a set of constraints and conventions to follow, not a protocol. For an API to be RESTful the following criteria need to be met:

- Client-server architecture made up of clients, servers, and resources, with requests managed through HTTP. Responses should be sent in one of the following formats: HTML, JSON, XML, or plain text (there are a couple of others, but we don't need to worry about them)
- It needs to deliver data that is stateless and representational

> `stateless` means it must not keep track of previous transactions between the client and the server for scalability/speed and to make sure any server can handle any response (no need for knowledge of past transactions). Every transaction is a single request/response cycle.

> `representational` because the data sent over represents the resource, in a format that is easily readable and digestible by different clients.

- The data sent through must be uniform across the API

As far as we are concerned, we just need to focus on following the correct conventions when we are sending data from our server in response to client requests.

## Resources

We call the data we are sending over with our API resource. Depending on what we are working on within our API, a resource can be many things: a collection of `Animal` objects, `ClothingItem`s, `Profile`s, etc. RESTful architecture follows a couple of conventions to make sure that anyone working with our API can easily understand what type of requests result in what type of actions and responses.

> Give a quick refresher on the client-server/request-response cycle

Every request must be sent using one of the HTTP methods or verbs. The most important ones are the following:

- GET - to receive data from the server
- POST - to submit data to the server
- PUT - to update a full entity/resource
- PATCH - to partially update entity/resource
- DELETE - to destroy entity/resource

Following RESTful conventions, we can use the above methods, combine them with URIs, and create routes to serve up data!

As a general rule, most APIs that serve up data and allow the modification of data tend to have at least the following 5 RESTful routes:

| HTTP METHOD | PATH/URI     | NAME OF ACTION | RESULT                            |
| ----------- | ------------ | -------------- | --------------------------------- |
| GET         | /animals     | INDEX          | Receive a list of animal data     |
| GET         | /animals/:id | SHOW           | Receive data of a single animal   |
| POST        | /animals     | CREATE         | Create an animal                  |
| PUT/PATCH   | /animals/:id | UPDATE         | Update/partially update an animal |
| DELETE      | /animals/:id | DELETE         | Delete an animal                  |

> Note: `:id` represents a dynamic value, the rest is static

One thing you've probably realised is that these map nicely to our `CRUD` actions for SQL! These routes align with the creation, reading, updating, and deleting of data.
