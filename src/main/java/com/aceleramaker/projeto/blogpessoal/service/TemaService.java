package com.aceleramaker.projeto.blogpessoal.service;

import com.aceleramaker.projeto.blogpessoal.controller.schema.*;

import java.util.List;

public interface TemaService {

    TemaDTO criar(CriarTemaDTO tema);

    TemaDTO atualizar(Long id, AtualizaTemaDTO dto);

    List<TemaDTO> listarTodos();

    void deletar(Long id);
}
