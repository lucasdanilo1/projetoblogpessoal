package com.aceleramaker.projeto.blogpessoal.controller.schema;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank String usuario,
        @NotBlank String senha,
        @NotBlank String nome
) {}