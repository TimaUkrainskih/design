create table projects(
    id serial primary key,
    project_name varchar(255)
);

create table participants(
    id serial primary key,
    full_name varchar(100)
);

create table project_participants(
    project_id int references projects(id),
    participants_id int references participants(id),
    role varchar(50)
    primary key (project_id, participants_id)
);