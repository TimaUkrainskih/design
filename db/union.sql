CREATE TABLE movie (
   id       SERIAL PRIMARY KEY,
   movie_name     text,
   director text
);

CREATE TABLE book
(
   id     SERIAL PRIMARY KEY,
   title  text,
   author text
);

INSERT INTO movie (movie_name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
      ('Матрица', 'Братья Вачовски'),
      ('Властелин колец', 'Питер Джексон'),
      ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
      ('Железный человек', 'Джон Фавро');

INSERT INTO book (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
      ('Властелин колец', 'Джон Толкин'),
      ('1984', 'Джордж Оруэлл'),
      ('Марсианин', 'Энди Уир'),
      ('Божественная комедия', 'Данте Алигьери');

select movie_name as title from movie
intersect
select title from book;

select movie_name as title from movie
except
select title from book;

select movie_name as title from movie
except
select title from book

union

select title from book
except
select movie_name from movie;