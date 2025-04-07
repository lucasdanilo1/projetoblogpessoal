package com.aceleramaker.projeto.blogpessoal.model.exception;

public class UsuarioJaCadastradoException extends RuntimeException {
    public UsuarioJaCadastradoException() {
        super("Usuário já cadastrado");
    }
}
