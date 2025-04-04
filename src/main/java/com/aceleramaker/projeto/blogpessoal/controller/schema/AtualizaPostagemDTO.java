package com.aceleramaker.projeto.blogpessoal.controller.schema;

public record AtualizaPostagemDTO(
        String titulo,
        String texto,
        Long temaId,
        Long usuarioId
) {
}
