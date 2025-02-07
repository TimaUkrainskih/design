create table users(
    id serial primary key,
    username varchar(100)
);

create table user_settings(
    id serial primary key,
    user_id int references users(id) unique,
    theme varchar(50),
    notifications_enabled boolean
);

