## Quiz Example Brief

Folk love quizzes. Whether it be tied to studying or how your star sign can tell you what you should have for lunch, quizzes at their base are a set of questions which reduce down to a single answer. This “answer” can be numerical, taking the former example of a study tool, or could be something a little more quirky; think returning “Pasta Dragon Apocalypse” after you’ve asked someone who their favourite actor is from a set of movies.

### MVP

At its core, a quiz API has a few distinct parts. The first is a bank of questions. Now, this can be organised in a few different ways so we’ll leave it open but we would recommend that you think about how you associate questions to each other and hence enable the shipping of a complete Quiz. You would likely have Quiz and Question models here where the former houses a collection of the latter.

The second piece of the puzzle houses your bespoke functionality which somehow parses the input from your user and then returns a result. As mentioned about, this can be logical or more quirky. Either way, the logic housed within this layer will vary between apps.

We would recommend that you spend a significant amount of time early on to ensure you are comfortable with the format your data is being sent by and received by your application. Have a look at APIs on the web to see what kind of format their responses are in. JSON formatting your questions and answers will ensure the front-end team can make easy use of your API.

### Key Functionality

Your MVP should be able to:

* Add quiz questions
* Display all quiz questions
* Return a list of quiz questions with a shared property (provide “a quiz”)
* Implement this firstly as a hard-coded route and then in relation to a Quiz object
* Update a quiz question
* Delete a quiz question
* Provide a result/score based on the answers

### Extensions 

Extensions for this project could take you in a few different directions. You could look to create an additional User model which would be tied to a quiz Submission for the sake of keeping a record of said submissions. The User could also store previous responses, in case you find that useful. You could also start to look at what additional information you would like to store for your Quizzes. For instance, you could either create a “timesCompleted” property or alternatively create a novel method for returning this same information.

You could also look to shuffle the questions on your quiz to provide a different experience to different users. 

Note that for ease of marking, you could use strict numerical answers or multiple-choice type questions where the answer is one of A, B, C or D, to avoid tricky string matching. 

You could also gamify this type of application, incentivising the completion of surveys. This would touch on the addition of some data to a User model. Note that we do stress that you should aim to utilise dummy data over spending a significant amount of time hard-coding a large bank of questions.

The strength of this application will come in how you design and hence display the question bank at the core of the functionality. Aim for flexibility over fragility by keeping each model discrete.

### Flavours

The different flavours of this application are primarily introduced through the topic of the quiz itself and hence content of the list of questions. We’ve seen many different examples over the past cohorts and so will note some of these down now:


- Study Tool
- A selection of questions, with user answers marked to provide a numerical total
- A personality quiz which provides you a boxing training regime based off your affinity to the natural elements
- Takeaway recommendations based off of your tastes
- Questions around your skin type and habits to select a skincare routine
- A selection of working preference-based questions to highlight your future career paths
- A uncommon board game suggester based off of your preferences and previously-played games
- A Pokémon/Astrology themed quiz where the result is your partner Pokémon

What we’re aiming to get across here is that you are free to cover any topic you would like for your quiz – providing it is work appropriate.