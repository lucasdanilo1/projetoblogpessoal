package com.aceleramaker.projeto.blogpessoal.service;

import com.aceleramaker.projeto.blogpessoal.model.Postagem;
import com.aceleramaker.projeto.blogpessoal.repository.PostagemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostagemService {
    private final PostagemRepository postagemRepository;

    public PostagemService(PostagemRepository postagemRepository) {
        this.postagemRepository = postagemRepository;
    }

    public Postagem criar(Postagem postagem) {
        postagem.setData(LocalDateTime.now());
        return postagemRepository.save(postagem);
    }

    public Postagem atualizar(Long id, Postagem postagem) {
        Postagem existente = postagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Postagem não encontrada"));

        existente.setTitulo(postagem.getTitulo());
        existente.setTexto(postagem.getTexto());
        existente.setTema(postagem.getTema());
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
