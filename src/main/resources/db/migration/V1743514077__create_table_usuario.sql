CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE usuario
(
    id      BIGSERIAL PRIMARY KEY,
    nome    VARCHAR(255) NOT NULL,
    usuario VARCHAR(255) NOT NULL,
    senha   VARCHAR(255) NOT NULL,
    foto    BYTEA
);

CREATE TABLE usuario_login
(
    id             BIGSERIAL PRIMARY KEY,
    token          VARCHAR(255),
    data_expiracao TIMESTAMP,
    usuario_id     BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);