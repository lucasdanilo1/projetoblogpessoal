package com.aceleramaker.projeto.blogpessoal.model.exception;

public class OperacaoNaoAutorizadaException extends RuntimeException {
    
    public OperacaoNaoAutorizadaException(String mensagem) {
        super(mensagem);
    }
} 