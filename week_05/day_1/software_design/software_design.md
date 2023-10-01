# Software Design Methodologies

## Introduction

There is no single correct way to approach a software engineering problem. Designing a product which satisfies a customer's needs while leveraging a team's skillset (and delivering on time and on budget) isn't something with a one-size-fits-all solution. There is, however, usually one option which is a better fit for a given situation than others. In this exercise we will find out about some of the different ways in which we could go about designing a system.

## Design Strategies

There are three principle design strategies used in software engineering:

- Structured design
- Function oriented design
- Object oriented design

None of these are compulsory when designing a product but following one will greatly aid the process. Each has its benefits and each has its drawbacks. Unlike many other aspects of software engineering they generally exist in isolation as the principles of each sit in direct opposition to each other in some scenarios.

There is no scenario where one of these strategies _can't_ be used, but we may be making our lives harder by choosing one over another. Likewise the majority of programming languages can be used with any strategy, but some are better fits for one strategy than another. For example Java is an excellent choice for an object oriented design while Scala would be a better choice for a functional design. Some languages such as Python can be used effectively with either, but have to sacrifice some features to achieve that flexibility.

Within each methodology there are various **design patterns** which provide further structure for us. In general though these are much more easily transferred across methodologies, although the precise implementation will vary by language. In practice the language used for a project will likely be the last thing to be chosen, determined by a combination of methodology and design pattern.

## Task

In this exercise you will be required to research the design strategies listed above and answer some questions about them. Your answers should be in a markdown (`.md`) file and uploaded to GitHub, then the link submitted using the lab submission form. There is no MVP/extension split for this task - you should attempt to answer every question. You can collaborate with others in the cohort on the research part of the task but everyone should submit their own answers.


1. What do we mean by **coupling** and **cohesion** when discussing structured design?
2. What is the difference between **top-down** and **bottom-up** design? Which best describes a function oriented design?
3. In which design methodology would a **class diagram** be most useful?
4. What are the **four pillars of object oriented programming**? Give a single-sentence description of each.
5. What is the **strategy pattern**? How would its implementation differ between a functional and object oriented system?
6. Imagine your is creating a new online payment system. In order to gain maximum market share it can't be tied to a particular sector - it needs to work just as well when ordering a takeaway as when buying a new coat. Which design methodology would you suggest following? Give some justification for your decision.