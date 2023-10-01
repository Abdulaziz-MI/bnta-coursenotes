# Algorithms, Big O and Time/Space Complexity

## Learning Objectives

* Understand what is meant by an "algorithm" in the context of software development
* Understand Big O notation and how that ties to the scalability of constructed logic
* Understand what is meant by both space and time complexity, and how we measure them
* Understand how to identify the dominant term between the space and time complexity terms

## What is an Algorithm?
Likely, no matter your background, you've heard or read the word algorithm being thrown around, especially in Hollywood, or in commentary on big tech companies and the ethics of how they use them to analyse and monitize user data and behavior. But, what exactly is an algorithm?

![A Beautiful Mind Spinning with Algorithms](https://media.giphy.com/media/AXorq76Tg3Vte/giphy.gif)

One definition Webster offers for an algorithm is "a step-by-step **procedure** for **solving a problem** or accomplishing some end". These procedures are **reusable**. Does that sound like any code construct you've worked with so far? Yes, functions! A **function**, or method, is essentially an *algorithm implementation*. Algorithms can be written to handle virtually any computational task, and their purposes range from simple calculations to data mining and analysis, advanced AI, and everything in between. 

Generally speaking, though *not necessarily* in every case, algorithms take in **value(s)**, and return some **result** after processing **input**. 

### Algorithm Example: Calculate Factorial (**n!**)
Here is an example of an algorithm which calculates the *factorial* of a number. For your reference, in mathematics the factorial of a number is often represented with the symbol **n!**. The factorial is the **product** (value returned after multiplication) of a natural number, and every other positive number lower than it. For example, the factorial of 5 is equal to `5 * 4 * 3 * 2 * 1`, which happens to be 120.
```java
// calculate factorial of integer n. 
// Example input:  5 -> 120. 3 -> 6 etc
public static int factorial(int n) {
    int result = 1;
    while(n > 1){
        result = n * result;
        n--;
    }
    return result;
}
```

## Why Think (Or Care) About Algorithms
You may think to yourself: 'If my code works, *it works*. Why should I even think about algorithms'? Here's the answer: Developing algorithmic thinking is an important step in becoming a capable, real-world programmer. It is arguably **one of the most valuable skills you can acquire** as a developer, since it enhances the way that your mind evaluates and solves problems, adding immense value to your employers/clients. Since algorithms and data structures knowledge (often abbreviated as DSA) is not tied to any particular language or framework, DSA is equally applicable in all development environments and technology stacks. It's *transferable* knowledge. 

Because of this, many employers are now testing DSA knowledge and skills in their technical interviews; hence, besides making you a better programmer and problem solver, acquiring a working knowledge of key DSA concepts and principles **will enhance your employability**. 

## Scaleablity of Code
DSA is also closely related to application **scalability**. Scalability is the capacity to take an app which has been built with a relatively small user-ship (say a startup), and bring it to a much larger audience. So, for example, think of an API that, at first, only has to handle a few dozen HTTP requests per hour. It gains popularly very quickly, and is soon receiving hundreds or even thousands of concurrent HTTP requests coming in from various users. That is a scaling scenario: how do we accommodate the increased demand with the code and resources we have?

One way is by using **design patterns** to structure our code well, making it more maintainable. We can then make changes more easily in the future, as needed. **Modularity** of code is an important concept here. But another, very important way of enabling scaling, is to make sure your algorithms are as a **efficient** in their use of **time** and **space** as they can possibly be.

## Time & Space Complexity
So, how does one distinguish, *objectively*, between one algorithm and another? We do so by *analyzing and comparing* their performance. One approach to doing this to use a benchmarking library to measure how long it takes for an algorithm to execute with given input - you can then **compare** the results of multiple algorithms which perform the same fundamental operation. 

The problem with this approach, however, is that it doesn't account for other factors which may influence the performance, such as your CPU, available resources at the time of execution, or the fact that one user is executing the algorithm on a more or less powerful machine than another. It's not generalized enough, or abstract enough to be a reliable system. So, we need a machine and OS **agnostic approach**. Does such exist? Queue Big O...

## Big O Notation
In computer science, every algorithm has an associated cost of execution - this cost can be expressed in time (how **long** it takes to run, or more precisely, **how many operations are performed** during execution) and space (how much **memory** is utilized during execution). In Mathematics and CS, Big O notation is the standard method of analyzing and expressing the **efficiency** of an algorithm in relation to the *magnitude* of **input** it takes - it answers these paramount questions: 

- "how does the time and/or space **complexity** of this algorithm **grow** as the magnitude of **input increases**?
- "how does the **performance** of this algorithm **scale** as the *magnitude* of input *grows*"? 

Among other things, understanding the most common notational terms of Big O is **essential** if you want to be able to have an intelligent conversation with another programmer, or **pass a technical interview**, so lets examine the most common notation now, and what it all means for us as coders.

Presented from *most* to *least* efficient, here are the common notational terms used to express algorithm efficiency in *Big O* (as the name suggests, the notational system is based around the **O** character):

### Common Notational Terms
- **O(1)**: Expressed as '*O of 1*', **constant time** conveys the idea that the algorithm has the same (or, *constant*) complexity, no matter what input you provide.
- **O(Log N)**: Expressed as '*O of Log N*', this describes an algorithms complexity as having a logarithmic relationship with it's input - the input being represented by the symbol **N**, as is standard in mathematical notation. *In computer science, a logarithm is a number which 2 must be raise by in order to reach a given value*. For instance, `2 * 2 * 2` is `8` (we must multiply it by itself 3 times to reach the target value), so the *logarithm* of 8 is *3*. Logarithmic time is *more efficient* than linear time, but obviously, *less efficient* than constant time.
- **O(N)**: Expressed as '*O of N*', **linear time** depicts a linear growth relationship between input and complexity. It says that an algorithms efficiency is directly proportional to the magnitude of its input. 
- **O(N<sup>2</sup>)**: Expressed as '*O of N Squared*', this notation depicts **quadratic time**. Every effort should be made to find a more efficient solution, as an algorithm with O(N<sup>2</sup>) is inefficient, and not very scalable. Examples are given below.
- **O(N<sup>3</sup>)**: Expressed as '*O of N Cubed*', cubic time is **extremely inefficient** and not at all scalable - if you calculate that your algirithm is O(N<sup>3</sup>), you need to go back to the drawing board, since it would rarely be necessary to implement an algorithm as inefficient as this. There's a better way.

## Analysing Algorithms with Big O
Now we'll start using Big O to approximate the time complexity of some example algorithms! Let's look at the factorial example again, but this time, try to figure out what Big O **term** it should be assigned:

### Linear time (Factorial)

```java
// calculate factorial of integer n. 
// Example input:  5 -> 120. 3 -> 6 etc
public static int factorial(int n) {
    int result = 1;
    while(n > 1){
        result = n * result;
        n--;
    }
    return result;
}
```
Can you determine it? A helpful clue is to look for **loops** (may indicate **O(n)**) and **nested loops** (May indicated **O(n<sup>2</sup>)**) - a nested loop is a loop *inside* another loop, just in-case that concept is new to you. What kind of loop do you see in the factorial algorithm?

```java
while(n > 1){
    result = n * result;
    n--;
}
```
There is just one loop present in this algorithm. How many times does it execute? `while(n > 1)` is the key here; `n` is the input magnitude (size) - this loop shows us that the time complexity of our algorithm is **linear**, which is expressed as **O(N)**, since the number of times the loop runs is essentially the same as the *magnitude* of input, and hence, the order of magnitude of operations performed during execution of the algorithm has a *linear relationship* to the magnitude of input. 

Now that we know our factorial algorithm is in *linear time*, we can ask ourselves the most important question when it comes to DSA: 

> "Can I make this algorithm *more* efficient"? - A Good Programmers Brain

Is **O(Log N)** or even **O(1)** possible? If it is, and we can implement it, then we'll be achieving a very substantial boost in performance, especial when our application scales up. In the case of calculating factorial 100% accurately, there is no more efficient way of achieving that. But, if a very close approximation is sufficient, you can use something called [Stirling's Approximation](https://en.wikipedia.org/wiki/Stirling%27s_approximation), which you can read about on the linked Wikipedia page as supplemental information, if you're curious.

### Logarithmic time
Notice that, in the below example, the loop is constructed in such a way that we effectively divide the number of remaining elements to lookup **by 2 on each iteration** - this operation of cutting the number of elements to lookup in half at at each pass creates **logarithmic** behavior:
``` java
for (int i = 1; i < n; i = i * 2){
    System.out.println("looking up element at index " + i);
}
```
An example of a real-world algorithm which has O(log n) time complexity is [binary search](https://en.wikipedia.org/wiki/Binary_search_algorithm). This is a great improvement in time complexity over [linear search](https://en.wikipedia.org/wiki/Linear_search), which is O(n). Obviously, in applications where you find yourself having to lookup elements from a list very frequently, you can leverage a binary search implementation to improve performance.

### Quadratic time
An algorithm with quadratic time has a growth rate of n<sup>2</sup> - so, with input of size 3, it will perform 9 operations (`3 * 3`). If the input is of size 10, it will perform 100 operations (`10 * 10`), and so on. This is a relatively high growth rate, so it **should be avoided** where possible. Say you have an array of elements, and you want to check if there are any duplicates. A **naive solution** (obvious but not optimal) to this problem would be to loop through the *entire* array *for each element*, checking for a match each time:

```java
// a naive solution
public boolean hasDuplicates(int[] arr) {
    for(int i = 0; i < arr.length; i++) {
        for(int other = arr.length - 1; other >= 0; other--) {
            // skip if index is same
            if(i == other) continue;
            else if(arr[i] == arr[other]) {
                return true;
            }
        }
    }

    return false;
}
```

A good sign to look for when identifying quadratic time is **nested loops** which are **tied to** the **input** variable(s). If you examine the above example carefully, you'll see that we are indeed using nested loops, both of which **loop through all elements** of the input array. While you *might* be fortunate enough to find a match at the very first attempt, Big O is calculated off the **worst case scenario**. 

So, what is the worst case scenario above in terms of time complexity? If there are no matches at all in the array, you would loop through the entire construct without finding any matches. Therefore, this algorithm is in **quadratic time**, or O(n<sup>2</sup>).

### Can we **do better** than that?

Yes! The **same task** can be achieved in **linear time**, by using a little bit of **memory**. We will create an `ArrayList` called `set`, and add each new **unique** element to it as we loop through the array. If we try to add a new element, and realise that it already exists in the set, we have just found a duplicate, and can immediately return `true`:

``` java 
// a more optimal solution
public boolean hasDuplicates(Integer[] arr) {
    Set<Integer> set = new HashSet<>();
    for(int i = 0; i < arr.length; i++) {
        if(set.contains(arr[i])) return true;
        else set.add(arr[i]);
    }
    return false;
}
```

Notice that, by storing each unique value we encounter in the local `ArrayList` variable, we can easily detect a duplicate by looping through the array **only once**. We have achieved time complexity of **O(n)**, since that is the **worst case** scenario - that's a *huge* improvement!

Did you notice the **cost** of achieving this? We added an `ArrayList`, which **grows linearly**, in relation to the input - worst case scenario, this new ArrayList will grow to be the **same size** as the input array. Can you tell what space complexity that is? Remember, you always go with the *worst case*, so that means we have gone from our original space complexity of **O(1)**, which is *constant* space, to space complexity of **O(n)**, which is *linear* space. However, this is a relatively *small sacrifice* to make, considering the **massive** improvement we receive in time complexity as a result! 

By now, you can probably deduce that working with algorithms often involves making **trade-offs** between **time** *and* **space complexity** to achieve an *optimal* result for your particular needs. This is an important lesson, take a moment to absorb it...

## Finding the **Dominant** 'Term'
In some more complicated algorithms, you may identify **multiple Big O terms** within the body of the *same* algorithm. For example, how do you decide which Big O term applies to the following?

```java
public void multipleTerms(int[] arr) {
    // constant time calculation - O(1)
    // it is constant time, because it performs the same 
    // number of operations irrespective of input
    var constantResult = 4 * 3 + (3 - (9/2));

    // 1D (linear) loop - O(n)
    for(int i = 0; i < arr.length; i++) {
        System.out.println("linear loop at " + i);
    }

    // 2D (quadratic) loop - O(n2)
    for(int i = 0; i < arr.length; i++) {
        for(int other = arr.length - 1; other >= 0; other--) {
            System.out.println("quadratic loop at " + i + ", " + other);
        }
    }
}
```

As you can see, the above example contains code blocks with **different** Big O ratings, or terms - O(1), O(n) and O(n<sup>2</sup>), as part of the **same** algorithm. So, if we were being *exact*, then we would say that the Big O is **O(1) + O(n) + O(n<sup>2</sup>)**...

However, that is **not** what is done **in practice**. Instead, we choose the **dominant term**. Put simply, the dominant term is the one which is the **most operationally complex** (or, least efficient). Given that understanding, can you tell what the dominant term is in the body of this algorithm? 

Yes, it's O(n<sup>2</sup>). So we **disregard the rest**, and say that this algorithm is O(n<sup>2</sup>). It really is as simple as that!

## Supplemental Resources
This has been a general introduction to the subject of DSA, but it is by no means exhaustive. We would encourage you to get in the **daily habit** of spending a *few minutes* solving problems with algorithms - there are a number of popular online platforms for this, where you can practice in your language of choice. Java and JavaScript are among the supported languages for all of these. Here are some of the most useful and popular tools out there:

- [Codility Lessons](https://app.codility.com/programmers/lessons/1-iterations/): These lessons ar **excelent** - they're designed to teach you, from the ground up, how to solve complex algorithmic problems. They start simple, and get progressively more advanced. Read the **PDF guide** for each topic carefully, and apply what you learn. With practice, you'll get better at solving problems efficiently: [https://app.codility.com/programmers/lessons/1-iterations/](https://app.codility.com/programmers/lessons/1-iterations/)

These tools are a bit different from the codility lessons - they're challenges for you to solve, of varying complexity. They don't include the guidance you will find in the PDFs with codility, but they're still very useful for sharpening your DSA skills:

- [HackerRank](https://www.hackerrank.com/)
- [CodeSignal](https://codesignal.com/)
- [LeetCode](https://leetcode.com/)