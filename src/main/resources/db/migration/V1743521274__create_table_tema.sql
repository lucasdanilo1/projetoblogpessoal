CREATE TABLE postagem (
    id BIGINT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    texto VARCHAR(500) NOT NULL,
    data DATETIME(6) NOT NULL,
    tema_id BIGINT,
    usuario_id BIGINT,
    FOREIGN KEY (tema_id) REFERENCES tema(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);