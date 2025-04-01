package com.aceleramaker.projeto.blogpessoal.service;

import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.model.Postagem;
import com.aceleramaker.projeto.blogpessoal.model.exception.EntidadeNaoEncontradaException;
import com.aceleramaker.projeto.blogpessoal.repository.PostagemRepository;
import com.aceleramaker.projeto.blogpessoal.repository.TemaRepository;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostagemService {
    private final PostagemRepository postagemRepository;
    private final UsuarioRepository usuarioRepository;
    private final TemaRepository temaRepository;

    public PostagemService(PostagemRepository postagemRepository, UsuarioRepository usuarioRepository, TemaRepository temaRepository) {
        this.postagemRepository = postagemRepository;
        this.usuarioRepository = usuarioRepository;
        this.temaRepository = temaRepository;
    }

    public Postagem criar(CriarPostagemDTO dto) {
        var usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        var tema = temaRepository.findById(dto.temaId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tema não encontrado"));

        return postagemRepository.save(new Postagem(dto, usuario, tema));
    }

    public Postagem atualizar(Long id, Postagem postagem) {
        var postagem = postagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Postagem não encontrada"));

        postagem = Postagem.builder()
                .id(existente.getId())
                .titulo(postagem.getTitulo())
                .texto(postagem.getTexto())
                .tema(postagem.getTema())
                .data(existente.getData())
                .build();

        return postagemRepository.save(existente);
    }

    public void deletar(Long id) {
        postagemRepository.deleteById(id);
    }

    public List<Postagem> listarTodas() {
        return postagemRepository.findAll();
    }

    public List<Postagem> filtrar(Long autorId, Long temaId) {
        if (autorId != null && temaId != null) {
            return postagemRepository.findByUsuarioIdAndTemaId(autorId, temaId);
        } else if (autorId != null) {
            return postagemRepository.findByUsuarioId(autorId);
        } else if (temaId != null) {
            return postagemRepository.findByTemaId(temaId);
        }
        return listarTodas();
    }
}
