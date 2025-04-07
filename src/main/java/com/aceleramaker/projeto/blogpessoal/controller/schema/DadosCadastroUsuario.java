package com.aceleramaker.projeto.blogpessoal.controller.schema;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record DadosCadastroUsuario(
        @NotBlank String usuario,
        @NotBlank String senha,
        @NotBlank String nome,
        MultipartFile foto
) {}
