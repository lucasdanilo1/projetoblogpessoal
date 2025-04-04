package com.aceleramaker.projeto.blogpessoal.controller.schema;

public record AtualizaUsuarioDTO(
    String nome,
    String usuario,
    String senha,
    String foto
) {}