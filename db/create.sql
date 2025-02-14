create table roles(
    id serial primary key,
    role_name varchar(50)
);

create table rules(
    id serial primary key,
    permission varchar(100)
);

create table role_rules(
    role_id int references roles(id),
    rule_id int references rules(id)
);

create table users(
    id serial primary key,
    role_id int references roles(id),
    full_name varchar(255),
    login varchar(255),
    password text,
    created_at timestamp
);

create table states(
    id serial primary key,
    state varchar(10)
);

create table categories(
    id serial primary key,
    category varchar(50)
);

create table items(
    id serial primary key,
    user_id int references users(id),
    category_id int references categories(id),
    state_id int references states(id),
    title varchar(255),
    description text,
    created_at timestamp,
    last_update timestamp
);

create table comments(
    id serial primary key,
    item_id int references items(id),
    author_id int references users(id),
    comment text,
    created_at timestamp
);

create table attachs(
    id serial primary key,
    item_id int references items(id),
    author_id int references users(id),
    file_path text,
    upload_at timestamp
);
