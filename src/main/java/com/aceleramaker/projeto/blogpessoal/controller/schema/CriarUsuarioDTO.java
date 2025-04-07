package com.aceleramaker.projeto.blogpessoal.controller.schema;

import org.springframework.web.multipart.MultipartFile;

public record CriarUsuarioDTO(
        String nome,
        String usuario,
        MultipartFile foto
) {
}
