CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE usuario
(
    id      BIGINT PRIMARY KEY,
    nome    VARCHAR(255) NOT NULL,
    usuario VARCHAR(255) NOT NULL UNIQUE,
    senha   VARCHAR(255) NOT NULL,
    foto    BYTEA
);