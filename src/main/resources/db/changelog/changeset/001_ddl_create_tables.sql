--liquibase formatted sql
--changeset arngrame:create_users_table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(2000)
);

