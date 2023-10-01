## Game Example Brief

Playing video games is a common hobby and is quite possibly part of what piqued your interest in Software Engineering. Modern video games are immense logical constructs with hundreds, if not thousands of moving parts. It's worth noting that many "Triple A" games not only have massive development teams, but also lean heavily on Game Engines which help bolster the efficiency of the game-making process by defining base systems such as *e.g.* core movement functionality. These teams also certainly have more time than a week to build their games.

To put our point succinctly: Scope is the greatest adversity for this kind of project, and you will need to recognise early that what you will be able to build within the time afforded may seem minimal

### MVP

The base requirements of this kind of project are difficult to define but they do all contain two key components at minimum:

1.  A set of interconnected objects, as we have seen with previous projects
2. A series of bespoke methods which are affected by, or directly affect, a Game-State

On this second point, for your game to exist, it must be tied to some timeline of events. The most basic game can be defined as a system of logic that is in some manner reactive to the previous, and current, state of some objects / properties. This combined is the "Game-State". Let's take  Tic-Tac-Toe as an example: the game only has meaning when we have a board whose **state** is updated each turn. The players, jointly, "create" a unique game-state that they update together until some end condition is met.

This series of events, or timeline, can be difficult to envision for any more grand ideas, so we encourage that you **start small and build upwards**. Your app will still likely require the basics of some POJOs and their associated methods / routes so start there.

If you decide on this project, we encourage you to open a dialogue with the training team as early as possible.

There are two common ways that you can build up your game-state across the progression of a game instance:

#### Planning & Progression

The first involves alternating "planning" and "progression" events. Here, you would have a set of bespoke functions that update a set of properties as needed within a "planning" stage, which are then locked in and acted upon by a separate set of methods when an *e.g.* "Advance Turn" route is called.

To tie this to an example, take a simple farming game where the player can plant crops which grow over the course of "turns" representing days. In the planning phase, the player could plant new crops within their array of available field positions, and then in the "progression" phase, the game would move on to the next day – updating a suggested "percentageGrown" property of each crop.

The progression of your game is tied **only** to the "Advance Turn" route. Some data may be persisted, such as the planned changes to the players field above, however the movement towards the end-condition only occurs when calling this route. Take care when planning how your game shall be played to ensure that unintended behaviour doesn't slip through, such as caused by a player updating some property multiple times between turns.

#### All-in-One

Perhaps a little simpler than the example above, you could build your API in such a way that any "progression" functionality is updated on **each and every action** from the user. Remember here that this progression is towards the end condition of your game. An example of where this may be the case is again within the Tic-Tac-Toe example where a "checkIfPlayerHasWon" method would be called when each new piece is placed on the board

### Key Functionality

Your MVP should be able to:

* Create a new instance of your "game"
* Provide means by which your game-state is updated and hence progresses toward the "end condition" of the game
* End the game upon reaching the "end condition"

### Extensions

There is similar difficulty in defining proposed extensions for this type of project as they can vary so significantly and thus this advice will remain fairly general.

With this type of project, it is incredibly important to take it piece by piece. The underlying object structures and their associated methods take a backseat compared to the system of bespoke functionality present when you show off your game but they're just as important as within other projects.

We hence reiterate that it is important to define the scope, and hence MVP, of your project carefully. It may be that your MVP is far less exciting than your original vision, however please bear in mind that time is very limited for these projects and having something that you can pass over to a different team in a few weeks is our chief requirement.

Generally, we would expect your extensions to further your logical system, adding some other dimension to your game. Alternatively, you could look to include a User class for saving certain information such as number of games won which may not exist within your initial plan.

### Flavours

There are tons of different ideas you could look to implement here. Examples we've seen previously are:

- A farming game where the user plants crops which grow and are sold over a sequence of turns. The aim of the game was to surpasss a defined amount of earnings
- Tic-Tac-Toe, the classic tabletop game
- Tamagotchi clones: take care of a virtual pet!
- A game similar to Top-Trumps where properties of a "card" are compared
- A text-adventure which includes a simple battle mechanic (D&D inspired)