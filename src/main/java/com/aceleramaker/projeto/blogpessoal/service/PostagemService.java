package com.aceleramaker.projeto.blogpessoal.service;

import com.aceleramaker.projeto.blogpessoal.controller.schema.AtualizaPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.FiltrosPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.PostagemDTO;
import com.aceleramaker.projeto.blogpessoal.model.Postagem;
import com.aceleramaker.projeto.blogpessoal.model.exception.EntidadeNaoEncontradaException;
import com.aceleramaker.projeto.blogpessoal.repository.PostagemRepository;
import com.aceleramaker.projeto.blogpessoal.repository.TemaRepository;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostagemService {

    private final PostagemRepository postagemRepository;
    private final UsuarioRepository usuarioRepository;
    private final TemaRepository temaRepository;

    public PostagemService(
            PostagemRepository postagemRepository,
            UsuarioRepository usuarioRepository,
            TemaRepository temaRepository) {
        this.postagemRepository = postagemRepository;
        this.usuarioRepository = usuarioRepository;
        this.temaRepository = temaRepository;
    }

    public PostagemDTO criar(CriarPostagemDTO dto) {
        var usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuario"));

        var tema = temaRepository.findById(dto.temaId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tema"));

        Postagem postagem = new Postagem(dto, usuario, tema);

        return new PostagemDTO(postagemRepository.save(postagem));
    }

    @Transactional
    public PostagemDTO atualizar(Long postagemId, AtualizaPostagemDTO dto) {
        var postagem = postagemRepository.findById(postagemId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Postagem"));

        if (dto.titulo() != null) postagem.setTitulo(postagem.getTitulo());

        if (dto.texto() != null) postagem.setTexto(postagem.getTexto());

        if (dto.temaId() != null) {
            var tema = temaRepository.findById(dto.temaId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Tema"));
            postagem.setTema(tema);
        }

        return new PostagemDTO(postagemRepository.save(postagem));
    }

    public Page<PostagemDTO> listarPostagem(FiltrosPostagemDTO filtros, Pageable pageable) {
        return postagemRepository.buscarComFiltros(filtros, pageable).map(PostagemDTO::new);
    }

    public void deletar(Long id) {
        postagemRepository.deleteById(id);
    }
}
