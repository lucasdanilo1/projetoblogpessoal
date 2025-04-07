package com.aceleramaker.projeto.blogpessoal.controller.schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CriarTemaDTO(@NotBlank @NotEmpty String descricao) {
}
