create table accounts (
    id serial primary key,
    name varchar(50),
    age int
);

insert into accounts(name,age) values
('Bob', 34),
('Alice', 23),
('Ivan', 56);