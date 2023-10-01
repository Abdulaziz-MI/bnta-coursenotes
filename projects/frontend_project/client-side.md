# Client-Side Project

In this project your team will be building a front-end application to consolidate the work you have done with JavaScript, React, HTML and CSS. You will have one week to research, plan and develop your application before you have the opportunity to present your work to your colleagues, instructors and potentially clients. You will be working in different groups from the last project.

For this project you will be building a React application to act as a front-end for one of the APIs built during the server-side projects. This will be a previously unseen API (apart from during project demos) and you will need to familiarise yourselves with it before you start developing your application. The design of your app is up to you, but it **must** make use of the routes exposed by the API.

## Groupings and General Format

The week will be structured like this:

- **Friday afternoon**:
	- Assigned to groups of 4-5
	- Familiarisation with assigned API
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
	- Submissions due at **4pm**

- **Friday**
	- Presentation practice
	- Self-reflection exercises
	- **2pm** presentations

These times are suggestions only, apart from the times in bold. A member of the training team **must** sign off on your plans before you start coding. 

## Tech Stack

This is a front-end project which should be built using:

- JavaScript + React
- HTML
- CSS
- The API asigned to your group

You are free to use any additional libraries as necessary (eg. for state management) but only do so if everyone in the group understands what they are for, how they work and how they fit in to the overall picture. Depending on the API your group is working with some extra libraries may be required.

Your plans **must** include:

- Wireframes
- Component diagrams
- A breakdown of your project into MVP and extension tasks

This project differs from a typical software engineering project in that the back-end has been built first. Usually the two parts would be designed in tandem to ensure that the back-end can provide the functionality needed by the front end. With the back-end already built there may be some limitations on what can be accomplished which you should investigate early in the design phase of the project.

That said, if there is some critical functionality missing you can refactor the back-end to make changes. **This doesn't mean you can change anything you like!** If, for example, a response is missing an important property such as an object's ID you can edit the DTO to include it. You shouldn't make fundamental changes to the API's architecture, or add lots of new routes. Before making any changes you should consider the routes already available to you and ensure you can't get what you need from there.

There is one change which you will definitely need to make to the API. When they were built they were tested using Postman and not designed to be consumed by a React application, meaning CORS won't be configured. One of your first tasks should be refactoring the API to set up CORS, otherwise you won't be able to make requests to it. Using the wildcards is acceptable here but as an extension you may want to refine the configuration as described [in the textbook](https://brightnetwork-technology-academy.github.io/curriculum/react/lessons/cors/cors/).

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

- **Description:** After submitting a new user their date of birth doesn't show up on the page
- **Severity:** Medium
- **Steps to reproduce:**
	1. Go to `localhost:3000/users/new`
	2. Complete the all details in the form and click "submit"
	3. Go to `localhost:3000/users` and confirm that the DOB is missing 

Your group should maintain a log of these bugs and if they were resolved or not. GitHub includes an issue tracking tool which you will be able to use from within your repository.

If you have any issues with the API your first point of contact should be the group who developed it during the server-side projects. Trainers will be available to help but won't be as familiar with the code as the developers. With that in mind, when planning your own projects bear in mind that you may need to spend some time supporting another group with API issues.


## Presentation

You should create a **public** GitHub repository **before you start writing any code** and send the link to your trainers. It is up to you how to manage your branch structure, but we expect to see contributions from **every** member of the group. Make sure that the most up-to-date working version of your project is merged to the `main` branch ahead of your presentation. 

The project will culminate in a presentation to the rest of the cohort along with some invited guests from industry. Each group will have **10 minutes** to present their work, plus some time for questions from the audience. Your presentation should include:

- A discussion of your planning
- A demo of your app
- Discussion of some of the code. 
	- What were you particularly proud of? 
	- Were there any bugs you found really difficult to squash?

Please note that **everyone** on the team is expected to speak during the presentation.

Do not go through the code line by line, you won't have time. Avoid showing the editor during the presentation as jumping between files can be very confusing for the viewer and time-consuming for the presenter. Instead use a tool such as [Carbon](https://carbon.now.sh/) or the [Polacode](https://marketplace.visualstudio.com/items?itemName=pnp.polacode) extension for VSCode to generate screenshots of code you want to talk about and add them to your slides.

We recommend that a single person shares their screen and handles both the slides and the demonstration of the API.

Following the presentation you will be sent a self reflection feedback form. As part of that self reflection we'll be looking for you to consider:

- How you worked as an individual within the team, what you achieved, and what could you have done to improve
- How you used the knowledge gained on the course to date to identify and solve the problem you've aimed to solve in your project
- How you would demonstrate that the team you worked in was professional, inclusive and fair

## Documentation

Every application needs good documentation to provide support for users. While some apps have their own distinct websites hosting their documentation for these projects it is sufficient to use the tools provided by GitHub.

There will be a file called `README.md` at the top level of your directory structure created for you by `create-react-app`. Delete the pre-made content and replace it with your own before pushing it to GitHub. GitHub will correctly format the markdown and display the content for us. Your README will provide enough detail about your project for anyone not involved with the team to be able to set it up and use it correctly. It should contain **at least**:

- A high-level description of the project's aims
- The names and versions of any libraries used
- Step-by-step setup instructions
- Links to any relevant diagrams such as wireframes or component diagrams
- Details of the project's MVP and any extensions covered, or a link to it 

This is not an exhaustive list; if there is something else you feel would be important for another developer to know about your application then include it here. There is no set format for a README but there are many examples such as [this article](https://www.freecodecamp.org/news/how-to-write-a-good-readme-file/) available online to give some guidance.
