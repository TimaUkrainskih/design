create table accounts (
    id serial primary key,
    name varchar(50),
    age int
);

INSERT INTO accounts(name, age) VALUES
('Bob', 34),
('Alice', 23),
('Ivan', 56),
('John', 45),
('Kate', 29),
('Emily', 37),
('Alex', 41),
('Mira', 25),
('Tim', 60);


begin;

update accounts set age = 35 where name = 'Bob';
savepoint sp1;

update accounts set age = 24 where name = 'Alice';
savepoint sp2;

update accounts set age = 57 where name = 'Ivan';

select * from accounts;

rollback to savepoint sp2;
select * from accounts;

rollback to savepoint sp1;
select * from accounts;

commit;