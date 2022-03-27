create table task
(
    id          serial primary key,
    description text,
    created     timestamp without time zone,
    done        boolean,
    user_id     int not null references users (id)
);

create table users
(
    id   serial primary key,
    name varchar(2000)
);

/*******************************************************/

create table task
(
    id          serial primary key,
    description text,
    created     timestamp without time zone,
    done        boolean,
    author      text not null references users (email)
);

create table users
(
    email text serial primary key,
    password text
);
