CREATE TABLE customers (
    customer_id serial PRIMARY KEY,
    first_name varchar(50) NOT NULL CHECK (char_length(first_name) > 1),
    last_name varchar(50) NOT NULL CHECK (char_length(last_name) > 1),
    email varchar(255) NOT NULL UNIQUE
        CHECK (email ~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);

CREATE TABLE orders (
    order_id serial PRIMARY KEY,
    customer_id int NOT NULL REFERENCES customers(customer_id) ON DELETE CASCADE,
    order_date timestamp NOT NULL DEFAULT current_timestamp CHECK (order_date <= now())
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