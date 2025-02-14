create table fauna(
   id serial primary key,
   name text,
   avg_age int,
   discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values
('African Elephant', 29200, '1797-01-01'),
('Bald Eagle', 10950, '1766-06-01'),
('Blue Whale', 29200, '1692-09-15'),
('House Cat', 6570, '1758-02-01'),
('Komodo Dragon', 9125, '1912-07-01'),
('Tardigrade', 36500, '1773-03-10'),
('Green Sea Turtle', 73000, '1800-05-20'),
('Axolotl', 3650, '1863-11-15');

select * from fauna
where name like '%fish%';

select * from fauna
where avg_age between 10000 and 21000;

select * from fauna
where discovery_date is null;

select * from fauna
where discovery_date < '1950-01-01';