--liquibase formatted sql
--changeset arngrame:create_result_table

CREATE TABLE results (
    id SERIAL PRIMARY KEY,
    first_arg DECIMAL,
    second_arg DECIMAL,
    result DECIMAL,
    operation TEXT,
    create_date TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);

--rollback DROP TABLE results;
