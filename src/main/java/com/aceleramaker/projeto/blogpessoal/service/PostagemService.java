package com.aceleramaker.projeto.blogpessoal.service;

import com.aceleramaker.projeto.blogpessoal.controller.schema.AtualizaPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.FiltrosPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.PostagemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostagemService {

    PostagemDTO criar(CriarPostagemDTO dto);

    PostagemDTO atualizar(Long postagemId, AtualizaPostagemDTO dto);

    Page<PostagemDTO> listarPostagem(FiltrosPostagemDTO filtros, Pageable pageable);

    void deletar(Long id);
}
