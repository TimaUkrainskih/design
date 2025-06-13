CREATE TABLE customers
(
   id         serial primary key,
   first_name text,
   last_name  text,
   age        int,
   country    text
);

CREATE TABLE orders
(
   id          serial primary key,
   amount      int,
   customer_id int references customers (id)
);

INSERT INTO customers (first_name, last_name, age, country) VALUES
('John',     'Doe',       28, 'USA'),
('Maria',    'Gonzalez',  35, 'Mexico'),
('Li',       'Wei',       42, 'China'),
('Olga',     'Petrova',   31, 'Russia'),
('Ahmed',    'Khan',      29, 'Pakistan'),
('Sara',     'Müller',    33, 'Germany'),
('Yuki',     'Tanaka',    26, 'Japan'),
('Pierre',   'Dubois',    45, 'France'),
('Anna',     'Nowak',     38, 'Poland'),
('David',    'Smith',     52, 'Canada');

INSERT INTO orders (amount, customer_id) VALUES
(120, 1),
(200, 1),
(450, 2),
(300, 3),
(150, 3),
(500, 4),
(90,  5),
(700, 6),
(100, 6),
(250, 7),
(800, 8),
(350, 9),
(400, 10),
(180, 10);


select * from customers
where age = (select MIN(age) from customers);

select * from customers
where id not in (select customer_id from orders);