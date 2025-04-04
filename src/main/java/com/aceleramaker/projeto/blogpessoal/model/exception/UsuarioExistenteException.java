package com.aceleramaker.projeto.blogpessoal.model.exception;

public class UsuarioExistenteException extends RuntimeException {
    public UsuarioExistenteException() {
        super("Usuário já existe");
    }
}
