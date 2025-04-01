package com.aceleramaker.projeto.blogpessoal.model.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }

    public EntidadeNaoEncontradaException(String msg, Throwable causa) {
        super(msg, causa);
    }
}
