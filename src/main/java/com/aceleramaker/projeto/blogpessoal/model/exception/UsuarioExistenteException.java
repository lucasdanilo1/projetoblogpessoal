package com.aceleramaker.projeto.blogpessoal.model.exception;

public class UsuarioExistenteException extends RuntimeException {
    public UsuarioExistenteException() {
        super("Nome de usuário em uso");
    }
}
