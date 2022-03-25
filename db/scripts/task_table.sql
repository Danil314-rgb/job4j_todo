create table task
(
    id serial primary key,
    description text,
    created timestamp without time zone,
    done boolean
);