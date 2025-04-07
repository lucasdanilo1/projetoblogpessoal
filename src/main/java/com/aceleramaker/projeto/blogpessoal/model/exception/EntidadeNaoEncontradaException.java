package com.aceleramaker.projeto.blogpessoal.model.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(String entidade) {
        super(entidade + " não encontrado");
    }
}
