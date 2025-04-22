package com.aceleramaker.projeto.blogpessoal.controller.schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarPostagemDTO(
        @NotBlank String titulo,
        @NotBlank String texto,
        @NotNull Long temaId
) {
}
