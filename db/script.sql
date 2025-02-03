create table students (
    id serial primary key,
    first_name varchar(20),
    last_name varchar(20),
    birth_date date,
    group_number varchar(10),
    enrollment_date date,
    gender char(1) check (gender in ('F', 'M'))
);

insert into students (first_name, last_name, birth_date, group_namber, enrollment_date, gender)
values
    ('Иван', 'Иванов', '2000-05-10', 'IU8-74', '2019-09-01', 'M'),
    ('Мария', 'Петрова', '2001-02-18', 'IU3-44', '2020-09-01', 'F'),
    ('София', 'Петрова', '1999-01-01', 'IU1-61', '2018-09-01', 'F');

update students
set group_namber = 'IU8-84'
where first_name = 'Иван';

delete from students
where group_namber = 'IU3-44';

select * from students;