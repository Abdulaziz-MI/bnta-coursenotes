## To-Do List Example Brief

The To-Do List is a classic basic app to build during the beginning of your software engineering career. The beauty of this example comes not only from its relative simplicity when envisioning the basic functionality, but also how the RESTful standards can act as a base for so many other applications

### MVP

At its base, the to-do list has two models, one for the piece of information being stored, whether this be a simple string or a more involved object, and a container class for said information. You can think of this as your To-Dos and your List. The bulk of the logic sits in between these models and where the information is persisted, generally within a Controller. You’ll want to include RESTful functionality here

Beyond these models, you could look to create bespoke functionality - such as methods which return specific items dependant on some criteria, for instance, via “tags” placed on each To-Do for Priority or Topic

### Key Functionality

Your MVP should be able to:

* Add an item to your to-do list
* Display all items in your to-do list
* Update an item on your to-do list (*e.g.* to mark as “done”)
* Delete an item on your to-do list
* Filter items on your to-do list by some property

### Extensions

The classic to-do list above is pretty lightweight. A common example of how to extend this project would be to include some sort of User class which ties to each To-Do. Having this functionality already creates another dimension and hence purpose for bespoke functionality such as filtering.

Tying some form of dynamic behaviour to different GET routes is the most obvious.

Additionally, fleshing out your app to best utilise testing of each route and some form of input validation would be great means to solidify your app.

### Flavours

As mentioned above, the To-Do list example is fantastic because it’s so easy to put your own spin on it. What we have here is simply an app with basic functionality which directly ties to the REST framework. 

Each “flavour” as we’re calling them makes use of the same key functionality as detailed above however the extensions may vary

- Online Diary or Gratitude Journal
   - Add in notes detailing things that made you smile across the day

- General Notes keeping
   - A place to keep small notes such as shopping lists or names of movie recommendations

- Houseplant Tracker (extension to provide reminders of when to water)
   - The strength of this app will be in the filtering of the plants that soon need watered. Keeping track of date last watered would be handy here

- Study Tool (Flashcards)
   - Key functionality would extend to include a means to grab a random “card”. Associated tables of questions and answers may be handy here if you want to provide this information separately. Care to ensure matching IDs

- An Online Bookshelf
   - For keeping track of what books you own, have read, and associated ratings you have given them