CREATE TABLE postagem
(
    id         BIGSERIAL PRIMARY KEY,
    titulo     VARCHAR(100) NOT NULL,
    texto      VARCHAR(500) NOT NULL,
    data       TIMESTAMP(6) NOT NULL,
    tema_id    BIGSERIAL,
    usuario_id BIGSERIAL,
    CONSTRAINT fk_tema FOREIGN KEY (tema_id) REFERENCES tema (id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);