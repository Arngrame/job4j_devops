--liquibase formatted sql
--changeset arngrame:create_calc_event_table
CREATE TABLE calc_event (
    id SERIAL PRIMARY KEY NOT NULL,
    user_id INTEGER REFERENCES users(id) NOT NULL,
    first_arg INTEGER,
    second_arg INTEGER,
    result INTEGER,
    type VARCHAR,
    create_date TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL
);