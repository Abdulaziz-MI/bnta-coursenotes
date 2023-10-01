# Control Flow Lab

We can now practise creating your own projects in Intellij and writing some code which uses control flow.

## MVP

Open Intellij and create a new project (`File > New > Project`). 

Please ensure that you have selected `New Project` in your left-hand nav,  `Java` as your language, and `IntelliJ` as your build system. Your JDK should be version 17.

Create a `GuessingGame` class inside the `src` folder and add a `main` method to it. Your file should look like this:

```java
public class GuessingGame {

	public static void main(String[] args){
	
	}

}
```

In this file we are going to write the logic behind a number guessing game. Ordinarily we would ask the user to make a guess but for now we will assume this has already happened and we have the value.

- Create two variables
	- `secretNumber` to represent the secret number
	- `guess` to represent the user's guess
- Give the variables any value you like
- Use conditional logic to compare the numbers and print an appropriate message
	- Tell the user if their guess is correct
	- Tell the user if their guess is too high
	- Tell the user if their guess is too low
- Test your logic by changing the value of `guess` and re-running the program.  


## Extension

- Create a new class called `WeatherCheck` with its own `main` method
- Initialise two variables:
	-  `temperature` to represent the current temperature
	-  `currentlyRaining` to say if it is raining or not
- Use conditional logic to print outfit suggestions based on the temperature and weather. For example, if it's hot and it isn't raining you may want to print "wear shorts and sunscreen". If it's cold and wet you might say "wear a waterproof coat".
- Test your code by changing the values of `temperature` and `currentlyRaining`



## Important Note

Please don't be tempted to copy/paste! It's *very* easy to accidentally paste the wrong thing and introduce a bug, plus it's good to get the practice!

## Pushing to Git

Create a new Git repository in the directory your are working in and push your code to GitHub. We recommend making regular commits, for example make a commit after finishing the MVP but before starting the extension task.
