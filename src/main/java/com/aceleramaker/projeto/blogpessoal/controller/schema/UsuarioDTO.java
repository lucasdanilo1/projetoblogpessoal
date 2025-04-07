package com.aceleramaker.projeto.blogpessoal.controller.schema;

import com.aceleramaker.projeto.blogpessoal.model.Usuario;

public record UsuarioDTO(
    Long id,
    String nome,
    String usuario
) {
    public UsuarioDTO(Usuario usuario) {
        this(
            usuario.getId(),
            usuario.getNome(),
            usuario.getUsuario()
        );
    }
}