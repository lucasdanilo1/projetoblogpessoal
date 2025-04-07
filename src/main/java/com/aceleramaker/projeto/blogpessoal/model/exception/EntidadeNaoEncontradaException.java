package com.aceleramaker.projeto.blogpessoal.model.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(Object clazz) {
        super(clazz.toString() + " não encontrado");
    }
}
