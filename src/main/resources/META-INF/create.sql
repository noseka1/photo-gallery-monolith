CREATE TABLE IF NOT EXISTS PhotoItem (
    id int8 not null,
    category varchar(255),
    name varchar(255),
    primary key (id)
);

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 1;

CREATE TABLE IF NOT EXISTS LikesItem (
    id int8 not null,
    likes int8,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS QueryItem (
    id int8 not null,
    category varchar(255),
    name varchar(255),
    likes int8,
    primary key (id)
);