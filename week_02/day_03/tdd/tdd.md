# Test Driven Development

We've already seen the power of testing - we should never, ever use or trust only our eyes when it comes to methods fulfilling their purpose. What we need is an automated system to see the tests passing, especially as the test suite's size keeps increasing. Unit testing with different frameworks are amazing for this, and useful for simple apps or small classes. As we improve as developers though, we might find ourselves in a situation where a more complex method needs testing. 

In a situation like this, writing our tests once we think the method or class is finished is still a solid option, as we can see our code performing as intended. There are a couple of problems with writing tests after the method is completed however:

* We can have confirmation bias, ie. we solved the problem, and now we might tailor the tests to pass.
* Or you might not test every outcome, or every line of code, since getting a passing test could convince you that your code is failproof.

Even if right now this does not seem plausible, you could find yourself soon in a situation where a more robust approach is needed when it comes to writing tests.

## TDD

Test Driven Development kind of does what it says on the tin - Tests are written to drive our development, not the other way around.

At its core, TDD is super simple - we write our tests first, then we write our code to pass the tests. THe steps are as follows:

1. Write a test
2. Run all the tests to see it fail (since we did not implement code to pass this new test)
3. Write code to pass said test
4. Run tests (all of them!) again to see everything pass
5. Repeat

This helps us write better code. First, we are forced to think through our problem more thoroughly, as we are not jumping into problem solving. We can think about the edge cases, cases where our tests should fail, and where can things go wrong. Second, since we test every new code/outcome written, we can make sure our test coverage is high - in some companies, the product won't even allow itself to be deployed until a high level of coverage (think 90-95%) is reached.

This also forces us to solve problems piecemeal, which in turn improves readability. Slower, gradual buildup makes sure we won't write spaghetti code with simply solving the problem in our minds.

As an added benefit, since our codebase becomes more robust, adding further functionality is faster and safer, since we know our existing codebase is tested and easy to read.

But how do we do TDD? Let's see it through a real life example.

## FizzBuzz

FizzBuzz is a task often given to juniors to solve as an introductory problem to solve. Let's see the task's description:

A method should take in a number. 
* If the number is divisible by 3, it should return the string "Fizz"
* If the number is divisible by 5, it should return "Buzz"
* If it's divisible by both, it should return "FizzBuzz"
* If it's NOT divisible by either, it should return the number (as a string)

The task itself sounds straightforward, but there are ways to write this code well or to write it badly. Let's see a good solution, with a TDD approach!

> Hand out start code

We already have a `getFizzBuzz()` method, so now we need to start writing the tests.

In `FizzBuzz`:

```java
    public String getFizzBuzz(Integer number) {
        if(number % 3 == 0){
            return "Fizz";
        }
        return null;
    }
```

Lovely, this passes our test! But now we need to drive our tests - although sometimes you might have a test suite already created and ready for you, most of the time you yourself need to write tests.

Let's write our second test to meet our second criterion!

We go to `FizzBuzzTest`:

```java 
    @Test
    public void canReturnBuzz(){
        String result = fizzBuzz.getFizzBuzz(5);
        assertThat(result).isEqualTo("Buzz");
    }
```

Once we have it, we run our code, to see it fail - it will return `null` for us, not `"Buzz"`, since we haven't written our code yet. But this does tell us something important - our code still works! No new errors are appearing, and if we were to write something that can accidentally overwrite the already passed solution, we could catch it here!

Next, the code in `FizzBuzz`:

```java
    public String getFizzBuzz(Integer number) {
        if(number % 3 == 0){
            return "Fizz";
        }
        if(number % 5 == 0){ //ADDED
            return "Buzz";   //ADDED
        }                    //ADDED
        return null;
    }
```

We could write an `if-else` statement as well, but this might be easier to read - since the 2 cases are not connected, we could mislead the future code readers, be it us or one of our colleagues.

Fantastic, now our test passes! Let's move to the next task, what happens when we can divide our input by 3 AND 5?

In `FizzBuzzTest`:

```java
    //as before
    
    @Test
    public void canReturnFizzBuzz(){
        String result = fizzBuzz.getFizzBuzz(15);
        assertThat(result).isEqualTo("FizzBuzz");
    }
```

Now the code, based on what we've built so far:

```java
    public String getFizzBuzz(Integer number) {
        if(number % 3 == 0){
            return "Fizz";
        }
        if(number % 5 == 0){
            return "Buzz";
        }
        if(number % 3 == 0 && number % 5 == 0){
            return "FizzBuzz";
        }
        return null;
    }
```

But our code fails now! 

No reason to panic - we do know for a fact that anything we've added so far is fine, already reducing the lines we need to check for broken code - we can focus on the newest additions only! 

Reading the error message we can see that the return value actually became `"Fizz"`, since the condition of the input number being divisible by 3 is met! So if we want to check both, we need to check both first, then the individual ones after!

```java
    public String getFizzBuzz(Integer number) {
        if(number % 3 == 0 && number % 5 == 0){ //MODIFIED
            return "FizzBuzz";                  //MODIFIED
        }                                       //MODIFIED
        if(number % 3 == 0){
            return "Fizz";
        }
        if(number % 5 == 0){
            return "Buzz";
        }
        return null;
    }
```

And now our tests are passing!

Lastly, let's solve the issue of returning the number as a string in case none of the criteria are met!

The test:

```java
    @Test
    public void canReturnNumberAsStringIfDivisibleByNeither(){
        String result = fizzBuzz.getFizzBuzz(2);
        assertThat(result).isEqualTo("2");
    }
```

This will require us to change the last return statement in our `FizzBuzz` class:

```java
    public String getFizzBuzz(Integer number) {
        if(number % 3 == 0 && number % 5 == 0){
            return "FizzBuzz";
        }
        if(number % 3 == 0){
            return "Fizz";
        }
        if(number % 5 == 0){
            return "Buzz";
        }
        return number.toString(); //MODIFIED
    }
```

Lovely, everything should be passing no problem!

## Conclusion

Hopefully we can see the benefits of writing our tests first. The more complex our problems get, the harder it will be to jump straight into coding. We always want to make sure that we thought through the problem, we considered the edge cases, and thw code we write remains robust and extendable easily. TDD enables us to do this, and it's a habit well worth picking up!