package com.aceleramaker.projeto.blogpessoal.service;

import com.aceleramaker.projeto.blogpessoal.controller.schema.AtualizaPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.FiltrosPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.PostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.PostagensPorDiaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostagemService {

    PostagemDTO criar(CriarPostagemDTO dto);

    PostagemDTO atualizar(Long postagemId, AtualizaPostagemDTO dto);

    Page<PostagemDTO> listarPostagem(FiltrosPostagemDTO filtros, Pageable pageable);

    List<PostagemDTO> listarUltimosPosts();

    PostagemDTO buscarPorId(Long id);

    void deletar(Long id);

    List<PostagensPorDiaDTO> contarPostagensPorDiaDaSemana();
}
