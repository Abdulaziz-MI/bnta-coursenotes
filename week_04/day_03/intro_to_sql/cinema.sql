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

-- INSERT with missing info
INSERT INTO movies (title, rating) VALUES ('Braveheart', 'PG');

-- READ
SELECT title FROM movies;

SELECT * FROM movies WHERE rating = 'PG';

SELECT title FROM movies WHERE duration > 120;

-- UPDATE
UPDATE movies SET rating = '12A' WHERE id = 5;

UPDATE movies SET (duration, rating) = (178, '15') WHERE id = 5;

-- DELETE
DELETE FROM movies WHERE id = 5;

DELETE FROM movies;


SELECT * FROM movies;





