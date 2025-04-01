CREATE EXTENSION IF NOT EXISTS "pgcrypto";

create table product (
    id UUID DEFAULT gen_random_uuid() primary key,
    name varchar(255) not null,
    price bigint not null
);