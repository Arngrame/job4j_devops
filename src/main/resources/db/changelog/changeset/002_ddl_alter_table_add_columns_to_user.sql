--liquibase formatted sql
--changeset arngrame:alter_table_add_columns_to_user
ALTER TABLE users ADD COLUMN first_arg FLOAT;
ALTER TABLE users ADD COLUMN second_arg FLOAT;
ALTER TABLE users ADD COLUMN result FLOAT;
