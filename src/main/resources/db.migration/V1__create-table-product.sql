CREATE EXTENSION IF NOT EXISTS "pgcrypto";

create table product (
    id serial primary key,
    name varchar(255) not null,
    price bigint not null
);