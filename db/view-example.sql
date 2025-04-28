create table students
(
   id   serial primary key,
   full_name varchar(50)
);

insert into students (full_name)
values ('Иван Иванов');
insert into students (name)
values ('Петр Петров');

create table authors
(
   id   serial primary key,
   full_name varchar(50)
);

insert into authors (full_name)
values ('Александр Пушкин');
insert into authors (full_name)
values ('Николай Гоголь');

create table books
(
   id serial primary key,
   full_name varchar(200),
   author_id integer references authors (id)
);

insert into books (full_name, author_id)
values ('Евгений Онегин', 1);
insert into books (full_name, author_id)
values ('Капитанская дочка', 1);
insert into books (full_name, author_id)
values ('Дубровский', 1);
insert into books (full_name, author_id)
values ('Мертвые души', 2);
insert into books (full_name, author_id)
values ('Вий', 2);

create table orders
(
   id serial primary key,
   active boolean default true,
   book_id integer references books (id),
   student_id integer references students (id)
);

insert into orders (book_id, student_id)
values (1, 1);
insert into orders (book_id, student_id)
values (3, 1);
insert into orders (book_id, student_id)
values (5, 2);
insert into orders (book_id, student_id)
values (4, 1);
insert into orders (book_id, student_id)
values (2, 2);


-- исходный запрос без представления
select
    o.id as order_id,
    s.full_name as student_name,
    b.full_name as book_name,
    a.full_name as author_name,
    case
        when o.active then 'Активный заказ'
        else 'Завершенный заказ'
    end as order_status
from
    orders o
inner join
    students s on o.student_id = s.id
inner join
    books b on o.book_id = b.id
inner join
    authors a on b.author_id = a.id
where
    s.full_name like '%ов'
    or a.full_name like 'Николай%'
order by
    s.full_name, a.full_name, b.full_name;

-- представление
create view student_book_orders as
select
    o.id as order_id,
    s.full_name as student_name,
    b.full_name as book_name,
    a.full_name as author_name,
    case
        when o.active then 'Активный заказ'
        else 'Завершенный заказ'
    end as order_status
from
    orders o
inner join
    students s on o.student_id = s.id
inner join
    books b on o.book_id = b.id
inner join
    authors a on b.author_id = a.id;


-- запрос, упрощенный представлением
select *
from student_book_orders
where
    student_name like '%ов'
    or author_name like 'Николай%'
order by
    student_name, author_name, book_name;
