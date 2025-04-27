create table teens (
    id serial primary key,
    first_name varchar(100) not null,
    gender varchar(10) not null check (gender in ('male', 'female'))
);

insert into teens (first_name, gender) values
('Алексей', 'male'),
('Мария', 'female'),
('Иван', 'male'),
('Анна', 'female'),
('Дмитрий', 'male'),
('Екатерина', 'female'),
('Сергей', 'male'),
('Ольга', 'female');

select
    t1.first_name as Имя1,
    t2.first_name as Имя2,
    t1.gender as Пол1,
    t2.gender as Пол2
from teens t1
cross join teens t2
where t1.gender != t2.gender and t1.id < t2.id;;