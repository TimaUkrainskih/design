create table devices(
   id serial primary key,
   name_devices varchar(255),
   price FLOAT
);

create table people(
   id serial primary key,
   name_people varchar(255)
);

create table devices_people(
   id serial primary key,
   device_id int references devices(id),
   people_id int references people(id)
);

INSERT INTO devices (name_devices, price) VALUES
('iPhone 14', 999.99),
('Samsung Galaxy S22', 849.50),
('MacBook Air M2', 1299.00),
('PlayStation 5', 499.99),
('iPad Pro', 1099.00),
('Xiaomi Mi Band 7', 49.99),
('Dell XPS 13', 1199.00),
('Kindle Paperwhite', 129.99),
('GoPro HERO11', 449.99),
('Nintendo Switch', 299.99);

INSERT INTO people (name_people) VALUES
('Алексей Смирнов'),
('Мария Иванова'),
('Игорь Кузнецов'),
('Елена Соколова'),
('Наталья Орлова'),
('Павел Морозов'),
('Ольга Кузьмина'),
('Владимир Соколов'),
('Анна Белова'),
('Дмитрий Фёдоров');

INSERT INTO devices_people (device_id, people_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(1, 2),
(3, 1),
(5, 4),
(6, 3),
(2, 5),
(4, 6),
(7, 8),
(9, 1),
(10, 3),
(8, 10);


select avg(price) from devices;

select
    avg(d.price) as "Средняя цена",
    p.name_people as "Имя Фамилия"
from
    devices d
join
    devices_people dp on d.id = dp.device_id
join
    people p on p.id = dp.people_id
group by p.name_people;

select
    avg(d.price) as "Средняя цена",
    p.name_people as "Имя Фамилия"
from
    devices d
join
    devices_people dp on d.id = dp.device_id
join
    people p on p.id = dp.people_id
group by p.name_people
having avg(d.price) > 5000;