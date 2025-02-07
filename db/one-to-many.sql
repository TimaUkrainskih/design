create table departments(
    id serial primary key,
    department_name varchar(255)
);

create table employees(
    id serial primary key,
    full_name varchar(100),
    department_id int references departments(id)
);