# Server-Side Project

In this project your team will be building a back-end application to consolidate the topics covered so far in the course. You will have one week to research, plan, and develop your application before presenting your work to your colleagues, instructors and potentially clients.

You will be building an API which the user will make requests to using Postman. The structure of the data and the functionality required will depend on the topic you choose. We will provide some example briefs which can be re-themed as you like but you can also suggest a topic not covered there. Your APIs can provide access to data or expose functionality required for some other process such as a game.

The decision about how to theme your project should be made by the whole team. The Training Team will be available to answer any questions about project scope and/or practicality during the planning stage.



## Groupings and General Format

The week will be structured like this:

- **Friday afternoon**:
	- Assigned to groups of 4-5
	- Reading example briefs
	- Initial planning

- **Monday**
	- Trainers sign off plans
	- Start coding

- **Tuesday**
	- Class stand-up
	- Coding

- **Wednesday**
	- Coding

- **Thursday**
	- Submissions due at **3pm**

- **Friday**
	- Presentation practice
	- Self-reflection exercises
	- **2pm** presentations

These times are suggestions only, apart from the times in bold. A member of the training team **must** sign off on your plans before you start coding. 


## Tech Stack

This is a back-end project, which means there is **no need to build a front-end** or use any front-end technologies/frameworks. You **must** use:

- Java
- Spring Boot
- A PostgreSQL database

You are free to use additional libraries as necessary but only do so if everyone in the group understands what they are for, how they work and how they fit in to the overall picture. **The aim of this project is consolidation and understanding**, not adding all the extras you can.

Your plans **must** include:

- Entity relationship diagrams
- Class diagrams
- A list of routes your API will need to provide
- A breakdown of your project into MVP and extension tasks in the same format as the example briefs provided

## Project Management

### Structure

We **strongly recommend** following an Agile structure while working on these projects. That means organising your time into sprints which can be as long or short as necessary. For a project of this length we suggest making making your sprints either a half-day or full day. Each sprint should begin with a discussion of what the team hopes to achive in that sprint and the allocation of work then end with a review of what was achieved during the sprint. The team should have regular stand-ups to check in with each other.

We suggest using something like a [Kanban board](https://kanbanize.com/kanban-resources/getting-started/what-is-kanban-board) to help you manage your time. You can use a free service such as Trello or the built-in tools on GitHub. Even something like a Google Drive with live documents can be great to facilitate collaboration.

How tasks are assigned is up to each group. If you are working individually or in pairs **remember that communication is the most important tool you have** at your disposal and that many problems will be averted if the whole team has regular check-ins. 

You can work on the project outside of normal working hours, but there is no expectation of this. It will be up to each group to decide on how to structure the week to ensure everyone is able to contribute effectively. Please don't be tempted to rush off and do extra work without keeping your colleagues in the loop, and also make sure you stick to any commitments you make. If unexpected issues start to arise, this is okay! Simply talk to your team and reorganise yourselves.

### Communication

It is important to maintain good communication with all stakeholders in a project, although not all will require the same frequency or depth of update. In this project your stakeholders are:

- Your team members
- The training team
- Clients attending the presentations

All need to be kept up to date, but not in the same way. For example, if a bug is discovered in the code the team definitely need to know but the trainers only need to know if their input is required to fix it. The clients definitely don't need to know. Tools such as [Mendelow's Matrix](https://www.professionalacademy.com/blogs/mendelows-matrix-marketing-theories/) will help you assess what level of communication is needed for each stakeholder.

### Handling problems

It is inevitable that _something_ will happen during the project which will affect your progress in an unforseen way. That something won't necessarily be bad (maybe someone has a breakthrough and finishes a task much quicker than anticipated?) but it will influence your timelines and need to be accounted for. You can do a lot to mitigate the effects though by considering any likely blockers before starting the project. For example:

- Does anyone in the group have another appointment they need to miss time for?
- Is anyone's internet connection likely to fail?
- Is the group attempting something complex which is likely to need external support?

These are examples of factors which represent a risk to your project and need to be considered. Your group will have its own which likely don't appear here.

Once the coding starts it's also inevitable that there will be bugs. Some of these will be relatively trivial and fixable immediately but others will be more involved and span code which multiple people have worked on. In these situations it is important that your group has some system for **bug tracking**. This could be as simple as a shared document listing problems but it is important to describe what the bug is, an estimate of how serious it is and the steps to reproduce it. For example:

- **Description:** Updating a user's details deletes the user from the database
- **Severity:** High
- **Steps to reproduce:**
	1. Make a `PATCH` request to `localhost:8080/users/:id`
	2. Make a `GET` request to the same url and receive a 500 error 

Your group should maintain a log of these bugs and if they were resolved or not. GitHub includes an issue tracking tool which you will be able to use from within your repository.

The training team will be available to help during working hours — please post questions in the cohort class channel. Please ensure that your issues are as descriptive as possible. Bear in mind that the trainers won't be familiar with your code so be prepared to explain what you're trying to do, what's going wrong and what you've tried so far to fix the problem. This is especially important if you're introducing something we haven't seen in the course so far.

## Submission & Presentation

You should create a **public** GitHub repository **before you start writing any code** and send the link to your trainers. It is up to you how to manage your branch structure, but we expect to see contributions from **every** member of the group. Make sure that the most up-to-date working version of your project is merged to the `main` branch ahead of your presentation.  

The project will culminate in a presentation to the rest of the cohort along with representatives from some clients. Each group will have **10 minutes** to present their work plus some time for questions from the audience. Your presentation should include:

- An introduction to your theme and the motivation behind it
- A discussion of your planning (take screenshots as you go along!)
- A demo of your app
- Discussion of some of the code
 - What were you particularly proud of? 
 - Were there any bugs you found really difficult to squash?

Please note that **everyone** on the team is expected to speak during the presentation.

Do not go through the code line by line, you won't have time (and it's dull!). Additionally, do not show the IDE during the presentation as jumping between files can be very confusing for the viewer and time-consuming for the presenter. Instead make use a tools such as [Carbon](https://carbon.now.sh/) to generate screenshots of code you want to talk about and add them to your slides.

We recommend that a single person shares their screen and handles both the slides and the demonstration of the API.

There will be time following your presentations for a short Q&A session with the audience. Spend some time ahead of your presentation premeditating what questions may be asked and organise how you would like to answer these questions as a team. Please be aware of your other team members during this portion of the presentation and afford each other the chance to speak.

Following the presentation you will be sent a self reflection feedback form. As part of that self reflection we'll be looking for you to consider:

- How you worked as an individual within the team, what you achieved, and what could you have done to improve
- How you used the knowledge gained on the course to date to identify and solve the problem you've aimed to solve in your project
- How you would demonstrate that the team you worked in was professional, inclusive and fair

## Documentation

Every application needs good documentation to provide support for users. While some apps have their own distinct websites hosting their documentation for these projects it is sufficient to use the tools provided by GitHub.

Create a file called `README.md` at the top level of your directory structure and ensure that it is pushed to GitHub. GitHub will correctly format the markdown and display the content for us. Your README will provide enough detail about your project for anyone not involved with the team to be able to set it up and use it correctly. It should contain **at least**:

- A high-level description of the project's aims
- The names and versions of any libraries used
- Step-by-step setup instructions
- Links to any relevant diagrams such as ERDs or class diagrams
- A full list of example routes (or a link to one) including:
   - Full urls including placeholders for variables (`localhost:8080/users/:id`)
   - Permitted requests for each route (`GET`, `POST`, etc)
   - Examples of permitted requests where appropriate
   - Examples of responses 
   - Routes should be organised by route and not by request method
- Details of the project's MVP and any extensions covered, or a link to it 

This is not an exhaustive list; if there is something else you feel would be important for another developer to know about your application then include it here. There is no set format for a README but there are many examples such as [this article](https://www.freecodecamp.org/news/how-to-write-a-good-readme-file/) available online to give some guidance.
















