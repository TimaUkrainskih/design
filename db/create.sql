create table roles(
    id serial primary key,
    role_name varchar(50) unique not null
);

create table rules(
    id serial primary key,
    permission varchar(100) unique not null
);

create table role_rules(
    role_id int not null references roles(id) on delete cascade,
    rule_id int not null references rules(id) on delete cascade,
    primary key (role_id, rule_id)
);

create table users(
    id serial primary key,
    role_id int not null references roles(id) on delete set null,
    full_name varchar(255) not null,
    login varchar(255) unique not null,
    password text not null,
    created_at timestamp default now()
);

create table states(
    id serial primary key,
    state varchar(10) unique not null
);

create table categories(
    id serial primary key,
    category varchar(50) unique not null
);

create table items(
    id serial primary key,
    user_id int not null references users(id) on delete set null,
    category_id int not null references categories(id) on delete set null,
    state_id int references states(id) on delete set null,
    title varchar(255) not null,
    description text not null,
    created_at timestamp default now(),
    last_update timestamp default now()
);

create table comments(
    id serial primary key,
    item_id int not null references items(id) on delete cascade,
    author_id int not null references users(id) on delete set null,
    comment text not null,
    created_at timestamp default now()
);

create table attachs(
    id serial primary key,
    item_id int not null references items(id) on delete cascade,
    author_id int not null references users(id) on delete set null,
    file_path text not null,
    upload_at timestamp default now()
);
