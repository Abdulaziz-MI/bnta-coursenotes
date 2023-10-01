# The SOLID Principles

Although software engineering can be a wonderful, free-form creative experience that doesn't meant it should always be that way. Beyond the rules imposed by compilers and interpreters there are actually very few restrictions on how we need to structure our code, either within classes or when classes interact. 

Take the code block below as an example. It will compile and run, and we could use it as part of a larger program, but it's an unholy mess and would be a nightmare for any developer (including the author) to work with.

```java

public String newmethod(int param_1, int param_2){

	Integer tempVariable = new Random().nextInt();
	
	if (param_1 >= tempVariable){return "Error";}
	
	String x = Integer.toString(param_2);
	
	return String.format("Magic number is %s", x);

}
``` 

There are a multitude of problems here: the naming is inconsistent and doesn't follow Java standards; the conditional isn't indented correctly; it's doing many different things within the same method. While this is valid code from a purely technical viewpoint, it's a long way from being *good* code. Developers will never agree on what exactly constitutes "good code" but we can make huge strides towards it by following some existing design patterns which lay down guidelines for structring our projects.

One such set of guidelines have been codified as the **SOLID Principles**. These are **not** rules which have to be followed at all costs, rather they give a suggested structure for our code. As a reminder they are:

- The **Single Responsibility Principle**
- The **Open/Closed Principle**
- The **Lyskov Substitution Principle**
- The **Interface Segregation Principle**
- The **Dependency Inversion Principle**

## Task

You are going to spend some time in groups researching **one** of these principles. When the class comes back together each group will give a short summary of:

- What the principle means
- The benefits of following the principle
- An example (either from our work so far or an online example) of where the principle has been applied

