DROP TABLE movies;

CREATE TABLE movies (
    id SERIAL,
    title VARCHAR(255),
    duration INT,
    rating VARCHAR(255)
);

-- CREATE
INSERT INTO movies (title, duration, rating) VALUES ('Alien', 117, '18');
INSERT INTO movies (title, duration, rating) VALUES ('The Imitation Game', 114, '12A');
INSERT INTO movies (title, duration, rating) VALUES ('Iron Man', 126, '12A');
INSERT INTO movies (title, duration, rating) VALUES ('The Martian', 144, '12A');
INSERT INTO movies (title, duration, rating) VALUES ('Guardians of the Galaxy', 121, '12A');
INSERT INTO movies (title, duration, rating) VALUES ('You Only Live Twice', 117, 'PG');
INSERT INTO movies (title, duration, rating) VALUES ('I Know What You Did Last Summer', 101, '18');

-- LIKE with exatct match

SELECT * FROM movies WHERE title LIKE 'Alien';

-- LIKE with fuzzy match at one end of query

SELECT * FROM movies WHERE title LIKE 'You%';

-- LIKE with fuzzy match at both ends

SELECT * FROM movies WHERE title LIKE '%You%';

-- Case-sensitive matching

SELECT * FROM movies WHERE LOWER(title) LIKE LOWER('%The%');

-- Ordering results

SELECT * FROM movies ORDER BY duration;

-- Ordering with filtered rows

SELECT title FROM movies WHERE rating = '12A' ORDER BY duration DESC;

-- Counting

SELECT COUNT(*) FROM movies;

-- Finding unique values in a column

SELECT DISTINCT rating FROM movies;

-- Grouping by values in a column

SELECT rating FROM movies GROUP BY rating;

-- Summarising groups

SELECT rating, AVG(duration) FROM movies GROUP BY rating;
