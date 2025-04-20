CREATE TABLE customers(
    customer_id serial primary key,
    first_name varchar(50),
    last_name varchar(50),
    email varchar(255)
);

CREATE TABLE orders(
    order_id serial primary key,
    customer_id int references customers(customer_id),
    order_date timestamp
);


INSERT INTO customers (first_name, last_name, email) VALUES
('Иван', 'Иванов', 'ivan@example.com'),
('Мария', 'Петрова', 'maria@example.com'),
('Алексей', 'Сидоров', 'alexey@example.com');


INSERT INTO orders(customer_id, order_date) VALUES
(1, '2025-04-01 10:00:00'),
(1, '2025-04-03 14:30:00'),
(2, '2025-04-02 12:15:00'),
(3, '2025-04-05 09:45:00'),
(1, '2025-04-10 16:00:00');


select
    first_name as "Имя",
    last_name as "Фамилия",
    o.order_id
from
    customers c
join
    orders o on c.customer_id = o.customer_id;

select
    o.order_id as "Номер заказа"
from
    customers c
join
    orders o on c.customer_id = o.customer_id
where c.first_name = 'Иван';

select
    o.order_id as "Заказ №",
    CONCAT(c.first_name, ' ', c.last_name) as "Клиент",
    c.email as "Email",
    to_char(o.order_date, 'YYYY-MM-DD HH24:MI') AS "Дата заказа"
FROM
    orders o
JOIN
    customers c ON o.customer_id = c.customer_id
ORDER BY
    o.order_date;