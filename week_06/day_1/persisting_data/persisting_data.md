# Persisting Data with Postgres

## Learning Objectives

- Understand what is meant by "data persistence"
- Be able to set up a connection between a Spring app to an SQL database within `application.properties`
- Understand the purpose of an Object Relational Mapper (ORM)
- Understand the purpose of a Repository and how it relates to our SQL database
* Be able to make use of CRUD functionality for POJOs using JPA in a Repository file
- Be able to tie a Controller, Service and Repository layer together to form a full Spring app with data persistence capabilities

## Introduction

So far, our app evolved to a stage where we have tons of functionality and features. One thing we are unable to do however, is to stop the server, fire it up again, and check the games that are either already solver or are in progress - every time we shut down our server, all data stored in the memory of the Java runtime is lost. Wouldn't it be amazing to be able to persist some data? 

## Setting up persistence

Spring gives us a plethora of different databases to use with it. We can easily set up Spring with database engines like MongoDB, H2 or different flavours of SQL - including Postgres!

In order for us to set up data persistence for our Spring app, we need to make sure the appropriate dependencies are added, the database is created, and the `application.properties` file is set up correctly.

> Either hand out start point with everything added, or ensure the following dependencies are added if continuing from previous lessons!

In your terminal, create your database:

```bash
createdb word_guesser
```

>You can use Postico as well

In your `pom.xml` add the following:

```xml
<!--as before-->
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
	<dependency>
		<groupId>org.postgresql</groupId>
		<artifactId>postgresql</artifactId>
		<scope>runtime</scope>
	</dependency>
	...
<!--as before-->
```
Once added, let's go to our `resources` folder, and in your `application.properties`, add the following:

```java
spring.datasource.url=jdbc:postgresql://localhost:5432/word_guesser
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=create-drop
```

* `spring.datasource.url` is the path towards your database. PSQL runs on localhost:5432, and under it, we can find our freshly created db
* `spring.datasource.username=` and `spring.datasource.password=` are optional, although in real life you absolutely must have both of these set up - for security purposes!
* `spring.datasource.driver-class-name=org.postgresql.Driver` tells Spring what should it use to integrate with our db. Since it's PSQL, we will ask it to use the postgresql driver
* `spring.jpa.hibernate.ddl-auto=create-drop` is telling Spring what to do when we start our app. `create-drop` does what it says on the tin: Creates our DB schema, then it will drop it on closing the app, and in case we have something set up for it, seeds it with seed data. This is commonly used in development for testing purposes, in production, you either leave it out or you set it to `none` - otherwise for each new deployment, valuable production data will be erased!

This would be the time to go get some elbow grease, and start creating database tables, setting up columns, writing SQL queries. Luckily we havce some tools to simplify this process for us - enter JPA!



## Storing `Game` objects with JPA ORM

JPA stands for `Jakarta Persistence API` (formerly known as Java Persistence API). It is an ORM (another acronym...), so first let's clarify what's an ORM!

ORM stands for *Object Relational Mapper*, and if dissected properly, it is a good description of what it does - ORMs help us map objects into databases. An ORM library is a completely ordinary library written in your language of choice that encapsulates the code needed to manipulate the data, so you don't use SQL anymore; you interact directly with an object in the same language you're using.

Since Java does not speak SQL natively, we need some help communicating our intentions with SQL if we were to use Java. JPA enables us to create tables, set up columns with datatypes, and execute SQL queries using Java - all the while simplifying our job immensely!

In order for us to start using JPA, we need 2 things: Our classes must be POJO classes, annotated with JPA annotations, and they also need to use JpaRepositories to give us SQL functionality.

Let's start by updating our POJO `Game` class. Every class that is to use JPA functionality must get the `@Entity` annotation.

```java
@Entity(name = "games") // UPDATED
public class Game {
   //...
}
```

The `name` argument provided will be given as the table name for out entity once Postgres is set up. If nothing is given, it will use the class' name as a table name.

Next, the `id` attribute must be updated - we want JPA to handle the setting and tracking of unique `id`s. We will annotate it with `@Id`, and with `@GeneratedValue(strategy = GenerationType.IDENTITY)` - this is what makes it a `PRIMARY KEY` with type `SERIAL`.

```java
    //private static int nextId = 1; // REMOVE

    @Id                              // UPDATED
    @GeneratedValue(strategy = GenerationType.IDENTITY) // UPDATED
    private int id;
```

Next, for every attribute we want to store, we will annotate it with `@Column`, with the appropriate column names. If we don't add anything for it's `name` property, the variable name will be used. Don't worry about datatypes - JPA will auto-detect it through the Java datatypes, and set them up accordingly - `INT` for `int`, `TEXT` for `String`, etc.

```java

@Column(name = "word")           // UPDATED
private String word;
@Column(name = "guesses")        // UPDATED
private int guesses;
@Column(name = "complete")       // UPDATED
private boolean complete;
```
Finally, we will update the constructor - from now on we will trust JPA to handle the ID generation, so we won't even set it in the constructor.

```java   
public Game(String word) {
    this.id = nextId;            // REMOVE
    nextId += 1;                 // REMOVE
    this.guesses = 0;
    this.complete = false;
    this.word = word;
}
```

For everything else, it just needs to follow the POJO rules - which it already does!

> Run `WordGuesserApplication.java`, and confirm with Postico/through the terminal that the database schema is created.

But how can we interact with this table?



## GameRepository

If you've dreaded the prospect of writing a ton of SQL statements to be able to execute `CRUD` operations, I have great news for you - we will delegate this task to JPA. The repositories that are extending the JpaRepository interface will give us full `CRUD` functionality out of the box!

First, create the `GameRepository` interface inside the `repositories` package.

```java
package com.demos.bnta.word_guesser.repositories;

public interface GameRepository{
}
```

Next, we will extend (yes, extend!) the `JpaRepository` interface. This will take in 2 types in angle brackets - first, the class type for which we create the repository, second, the datatype of the ID used by the class.

```java
package com.demos.bnta.word_guesser.repositories;

import com.demos.bnta.word_guesser.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
```

And that's it! Now we have full CRUD functionality (and more!) for our `Game` class! In order to prove it, let's modify our `GameService` to use the `GameRepository` instead of the `GameList`!

In `GameList`, let's change the `@Autowired` `GameList` to an `@Autowired` `GameRepository` - don't worry, `JpaRepository` also implements `Repository`, so even without annotations, this bean can be autowired!

```java

public class GameService {


    @Autowired
    GameRepository gameRepository; // UPDATED
    
    
//...
}
```

Now we can change the logic to find a game based on its id in `processGuess()`, as well as the updating of the game from incomplete to complete:

```java
public Reply processGuess(Guess guess, int id){

    // Find the correct game
    Game game = gameRepository.findById(id).get(); // UPDATED
        
    //...

    setCurrentWord(runningResult);

    // Check for win
    if (checkWinCondition(game)){
        game.setComplete(true);
        gameRepository.save(game);  // ADDED
        return new Reply(true, this.currentWord, "You win!");
    } else {
    
    //...

}
```
Also, we can update the method `incrementGuesses()` to use the repository:

```java
private void incrementGuesses(Game game){
    game.setGuesses(game.getGuesses() + 1);
    gameRepository.save(game);  // ADDED
}
```

> Play around with creating a new game via Postman, guessing, and checking the updates in either the psql terminal or Postico!

Since we already have a lot of CRUD functionality at our fingertips, might as well use them - let's create and endpoint to serve up a single game based on its id, and an endpoint to give us all games in the db (completed or not).

First, we create the methods in the `GameService` - don't forget, the controller should not care how the data is being accessed, that is the job of our Service!

```java
@Service
public class GameService {

   // as before

	public List<Game> getAllGames(){
    	return gameRepository.findAll();
	}

	public Optional<Game> getGameById(int id){
    	return gameRepository.findById(id);
	}

}
```

`Optional` is a great new datatype we can use. `findById(id)` returns us an `Optional` type, with the implied type of `Game`. We can call the `isPresent()` method on an `Optional` to check if it has value, and if it does, we can access said value with the `.get()` method. Let's use this in our `GameController`!

```java
@RestController
@RequestMapping(value = "/games")
public class GameController {

	@GetMapping
    public ResponseEntity<List<Game>> getAllGames(){
        List<Game> games = gameService.getAllGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id){
        Optional<Game> game = gameService.getGameById(id);
        if (game.isPresent()){
            return new ResponseEntity<>(game.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    //...
}
```

> Check in Postman that we can get back one game, all games, and we get a 404 in case of a non-existent game id.



