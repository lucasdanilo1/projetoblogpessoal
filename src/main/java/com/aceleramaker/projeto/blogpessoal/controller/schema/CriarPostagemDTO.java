package com.aceleramaker.projeto.blogpessoal.controller.schema;

public record CriarPostagemDTO(
        String titulo,
        String texto,
        Long temaId,
        Long usuarioId
) {
}
