create table car_bodies (
    id serial primary key,
    full_name varchar(100) not null
);

create table car_engines (
    id serial primary key,
    full_name varchar(100) not null
);

create table car_transmissions (
    id serial primary key,
    full_name varchar(100) not null
);

create table cars (
    id serial primary key,
    full_name varchar(100) not null,
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies (full_name) values
('Седан'),
('Хэтчбек'),
('Универсал'),
('Кроссовер'),
('Купе'),
('Пикап'),
('Кабриолет');

insert into car_engines (full_name) values
('Бензиновый 1.6'),
('Бензиновый 2.0'),
('Дизельный 2.2'),
('Электрический'),
('Гибридный');

insert into car_transmissions (full_name) values
('Механическая'),
('Автоматическая'),
('Роботизированная'),
('Вариатор');

insert into cars (full_name, body_id, engine_id, transmission_id) values
('Toyota Corolla', 1, 1, 2),
('Honda Civic', 2, 1, 1),
('Volkswagen Passat', 3, 2, 2),
('BMW X5', 4, 2, 2),
('Audi TT', 5, 2, 1),
('Mazda MX-5', 6, 1, 1),
('Tesla Model 3', 1, 4, 2),
('Toyota Prius', 2, 5, 3);

insert into cars (full_name) values
('Example');

select
    c.id,
    c.full_name as car_name,
    b.full_name as body_name,
    e.full_name as engine_name,
    t.full_name as transmission_name
from
    cars c
left join car_bodies b on c.body_id = b.id
left join car_engines e on c.engine_id = e.id
left join car_transmissions t on c.transmission_id = t.id;

select
    b.full_name as body_name
from
    car_bodies b
left join cars c on b.id = c.body_id
where c.body_id is null;

select
    e.full_name as engine_name
from
    car_engines e
left join cars c on e.id = c.engine_id
where c.engine_id  is null;

select
    t.full_name as transmission_name
from
    car_transmissions t
left join cars c on t.id = c.transmission_id
where c.transmission_id is null;