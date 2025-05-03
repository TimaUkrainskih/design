create table products (
   id       serial primary key,
   name_product     varchar(50),
   producer varchar(50),
   count    integer default 0,
   price    integer
);

-- 1)  Триггер должен срабатывать после вставки данных,
-- для любого товара и просто насчитывать налог на товар
 -- (нужно прибавить налог к цене товара). Действовать он должен не на каждый ряд,
-- а на запрос (statement уровень)

create
or replace function add_tax()
   returns trigger as
$$
   BEGIN
       update products
       set price = price * 1.2
       where id in (select id from inserted);
       return new;
   END;
$$
LANGUAGE 'plpgsql';

create trigger products_insert_tax
   after insert
   on products
   referencing new table as
                   inserted
   for each statement
   execute procedure add_tax();

-- 2) Триггер должен срабатывать до вставки данных и насчитывать налог на товар (нужно прибавить налог к цене товара).
-- Здесь используем row уровень.

create or replace function before_insert_tax()
returns trigger as
$$
begin
    new.price := new.price * 1.2;
    return new;
end;
$$
language 'plpgsql';

create trigger products_before_insert_tax
before insert on products
for each row
execute function before_insert_tax();

-- 3)

create table history_of_price
(
   id    serial primary key,
   name  varchar(50),
   price integer,
   date  timestamp
);

create or replace function insert_history_of_price()
returns trigger as
$$
BEGIN
    INSERT INTO history_of_price (name, price, date)
    VALUES (NEW.name_product, NEW.price, now());
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

create trigger trigger_history_of_price
after insert
on products
for each row
execute procedure insert_history_of_price();


