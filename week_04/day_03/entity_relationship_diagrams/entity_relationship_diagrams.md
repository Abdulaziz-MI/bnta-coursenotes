# Entity Relationship Diagrams

Diagrams are an important tool in any aspect of software engineering. When working with databases we will be drawing **Entity Relationship Diagrams** (**ERD**s) to describe our schemas and show the relationships between tables.

At the top of each table is its name. Names must begin with an alphabetic character (a-z) or an underscore, no numbers or special characters. Capitalisation and pluralisation will depend on both the database system in use and your organisations coding standards. Here we will use lower-case and plurals.

Each row in the ERD table represents a field in the database table. There are three columns for each row:

- The first indicates any constraints on the row
- The second gives the name assigned to the column in the database
- The third gives the datatype

Typically we use abbreviations in the first column. The most common include:

| Abbreviation | Meaning |
|---|---|
| **PK** | **P**rimary **K**ey |
| **FK** | **F**oreign **K**ey |
| **NN** | **N**ot **N**ull |
| **U** | **U**nique |

The diagram below shows an example of how the entity relationship diagram would be structured for the `movies` table in our example:

![the movies table](../../../assets/sql/entity_relationship_diagrams/movies_table.png)

Once we start adding additional tables to our database we need to consider how we can illustrate the relationships between them. If a relationship exists we draw a line between the two tables in the diagram and annotate each end appropriately to show the nature of the relationship. There are two aspects to consider:

- **Ordinality**: The *minimum* number of times an entry in one table can be associated with something in the other
- **Cardinality**: The *maximum* number of associations for an entry

Consider the relationship between the `movies` and `directors` table. They have a **one-to-many** relationship: one director can direct many movies. The `directors` end of the relationship has both an ordinality and cardinality of **one**. This implies that a movie must have exactly one director - no more, no less. The `movies` end of the relationship has no ordinality specified but a cardinality of **many**. A director can direct none of the movies listed, one of them or any other number.

These values can be shown on the diagram in various ways, including numerically and by adding different arrow heads to the lines. Here we have used the "crow's foot" notation to modify the lines and give a graphical representation.

| Annotation | Ordinality | Cardinality |
|:---:|:---:|:---:|
|![](../../../assets/sql/entity_relationship_diagrams/one.png)| undefined | one | 
|![](../../../assets/sql/entity_relationship_diagrams/many.png)| undefined | many |
|![](../../../assets/sql/entity_relationship_diagrams/exactly_one.png)| one | one | 
|![](../../../assets/sql/entity_relationship_diagrams/zero_one.png)| zero | one | 
|![](../../../assets/sql/entity_relationship_diagrams/one_many.png)| one | many | 
|![](../../../assets/sql/entity_relationship_diagrams/zero_many.png)| zero | many | 

The one-to-many relationship between `movies` and `directors` is shown below.

![relationship between the movies and directors table](../../../assets/sql/entity_relationship_diagrams/movies_directors.png)

Adding more tables is relatively straightforward, so long as care is taken when connecting the lines. It can occasionally be challenging, but lines shouldn't overlap where possible. The ends of each line should connect with the table at the appropriate row, eg. the `director_id` row for `movies` connects to the `id` row in `directors`. 

The final tables are shown in the diagram below. Note that although we describe the relationship between `movies` and `actors` as many-to-many we show it on the ERD as two one-to-many relationships, one from each table to the `castings` join table.

![relationship between the movies, directors, actors and castings tables](../../../assets/sql/entity_relationship_diagrams/movies_directors_actors.png)

