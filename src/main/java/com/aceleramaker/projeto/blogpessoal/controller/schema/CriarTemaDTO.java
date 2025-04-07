package com.aceleramaker.projeto.blogpessoal.controller.schema;

import jakarta.validation.constraints.NotEmpty;

public record CriarTemaDTO(@NotEmpty String descricao) {
}
