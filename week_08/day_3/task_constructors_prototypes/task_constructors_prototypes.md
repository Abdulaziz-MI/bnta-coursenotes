# Exercise - Constructors & Prototypes

In this exercise we will explore some of the differences between how classes are defined in Java and JavaScript. We're going to model a car dealership with `Car`, `Dealership` and `Customer` modules including their properties and behaviours.

This lab does not have a start code, so you will need to create your project from scratch. Don't forget to install any dependencies you might need (hint: `npm install -D jest`).

## Task

- Create ` Car` module. The car should have properties representing manufacturer, price and engine type.
- Set up Jest and write tests to make sure you can access each of those properties in a `Car` object.
- Create a `Dealership` module. It should have properties representing its name, maximum number of cars it can have and the cars currently in stock.
- Add methods to `Dealership` to:
  - Count the number of cars in stock
  - Add a car to stock
  - Return an array containing each car's manufacturer
  - Find all the cars from a given manufacturer
  - Find the total value of all the cars in stock
- Write tests for `Dealership`'s methods.

## Extensions

- Modify and test the method for adding a car to stock to ensure we can't add more cars than we have space for.
- Add a `Customer` module with a name, a wallet representing the amount of money they have and a `car` property which is initialised to be `null`. 
- Write a `buyCar` method which checks the `Customer` wallet and updates their `Car` property.
- Write a `sellCar` method to `Dealership` which removes a car from the `stock`. This method will also call `buyCar()` from `customer`. 
- Write tests to ensure all possible conditions are met, eg. the customer can afford the car.
- Modify the method which searches for cars by manufacturer to let the user search by price or engine type instead.
