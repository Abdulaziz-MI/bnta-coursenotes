## Data API Example Brief

This type of project is the broadest and the most generic of all: it’s a data API. This sort of API is very common on the web and exist as access points for a large amount of related information. It’s not as flashy at first glance as some of the other project briefs but it does bring a major strength in that it is agnostic—how your information is being used is felt largely to the end-user.

A Data API may contain a selection of pre-loaded data (*e.g.* Workout Generator), or may instead look to accept information from users which others can then make use of (*e.g.* a Reviews database).

### MVP

The MVP of a Data API would be first a solid database structure and set of associated models. Think about what relationships exist between your data, and hence what defines the “service” you are providing to an end-user. This would likely go further as to introduce novel routes within many of your controllers for better selecting what data is returned from your database.

This API structure finds its strength in the varied usage it could have in the front-end. First imagine that you have a plethora of information available to yourself on your chosen topic. How would this be defined? What do the models look like? And hence, what can you filter the output by?

Please note that we do not count hard-coding of the data input as a principal part of these projects so please only define a data-set which enables you to show off your functionality and little more. We will assume that the MVP functionality is defined from the point of an admin user of the back-end project.

Getting familiar with common object notation, and looking at how you can best provide JSON responses to the client, is a must for this project.

### Key Functionality

Your MVP should be able to:

* Add information to your database
* Update information in your database
* Grab all information from your database
* Grab specific information from your database by specifying some filtering property
* Delete information from your database

### Extensions

You will notice that the key functionality of your app is rooted in the CRUD design pattern. Your project primarily exists as an access point for differing end users with differing visions. You can take some of this creativity on yourselves, however, through the creation of bespoke routes with differing functionality. 

Let’s take an example for this. You could create a Workout Generator project. At base, this would be a Data API, providing an access point to a database of individual exercises. The exercises can be organised by perceived ability rating, body part trained and strength vs cardio. However, if you meet your MVP early, you can use the remainder of the time on the creation of logic which presents a means to define a whole workout based on user requirements. 
A natural extension for a project like this is to look at implementing a new model for a Workout. This itself is defined by a series of properties specified by the user on request, such as number of exercises wanted and user fitness level (correlated with “ability level”). Associated methods would then initialise this Workout object, ready to be returned to the user on Postman, by finding exercises that satisfied their requirements.

### Flavours

Let your mind run wild. This API is limited in topic only by the information you can feasibly gather on said topic. That said, please do keep it work-appropriate and ideally on something multiple members of your group are familiar with or passionate about.

- Workout generator
   - At base, a database of individual exercises

- A Dungeons & Dragons Spellbook database
   - Bespoke functions included filtering spells by Player Class and Level

- An outfit generator
   - At base, a database of clothing items organised into multiple categories such as appropriate Weather and Colour
   - This is a very visual example and will likely rely on the storage of images in some manner. Reach out to the trainers about this before settling on the idea!

- Inventory management
   - Either in relation to e-commerce or general inventory management of *e.g.* a bookcase
   - This has significant overlap with the To-Do List example

- A review aggregate platform
   - Seen for movies, books, snacks and board games previously

- A music recommendation site
   - At base, a database of individual Songs and Albums
   - Recommendation platform akin to the generators above, with the logic defined largely through the use of filters and a shuffled output