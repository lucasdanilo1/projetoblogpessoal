package com.aceleramaker.projeto.blogpessoal.controller.schema;

import com.aceleramaker.projeto.blogpessoal.model.Postagem;
import com.aceleramaker.projeto.blogpessoal.model.Usuario;

import java.time.LocalDateTime;

public record PostagemDTO(
        Long id,
        String titulo,
        String texto,
        LocalDateTime data,
        UsuarioDTO usuario,
        TemaDTO tema
) {
    public PostagemDTO(Postagem postagem) {
        this(
                postagem.getId(),
                postagem.getTitulo(),
                postagem.getTexto(),
                postagem.getData(),
                new UsuarioDTO(postagem.getUsuario()),
                new TemaDTO(postagem.getTema())
        );
    }

    private record UsuarioDTO(Long id, String nome, byte[] foto) {
        public UsuarioDTO(Usuario usuario) {
            this(usuario.getId(), usuario.getNome(), usuario.getFoto());
        }
    }
}

