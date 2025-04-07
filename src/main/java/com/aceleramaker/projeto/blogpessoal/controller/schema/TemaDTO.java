package com.aceleramaker.projeto.blogpessoal.controller.schema;

import com.aceleramaker.projeto.blogpessoal.model.Tema;

public record TemaDTO(
        String descricao
) {
    public TemaDTO(Tema tema) {
        this(tema.getDescricao());
    }
}