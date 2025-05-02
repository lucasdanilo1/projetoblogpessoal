package com.aceleramaker.projeto.blogpessoal.service;

import com.aceleramaker.projeto.blogpessoal.controller.schema.AtualizaPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.ContagemDiaSemana;
import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.FiltrosPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.PostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.PostagensPorDiaDTO;
import com.aceleramaker.projeto.blogpessoal.model.Postagem;
import com.aceleramaker.projeto.blogpessoal.model.UsuarioLogin;
import com.aceleramaker.projeto.blogpessoal.model.exception.EntidadeNaoEncontradaException;
import com.aceleramaker.projeto.blogpessoal.model.exception.OperacaoNaoAutorizadaException;
import com.aceleramaker.projeto.blogpessoal.repository.PostagemRepository;
import com.aceleramaker.projeto.blogpessoal.repository.TemaRepository;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PostagemServiceImpl implements PostagemService {

    private final PostagemRepository postagemRepository;
    private final UsuarioRepository usuarioRepository;
    private final TemaRepository temaRepository;

    public PostagemServiceImpl(
            PostagemRepository postagemRepository,
            UsuarioRepository usuarioRepository,
            TemaRepository temaRepository) {
        this.postagemRepository = postagemRepository;
        this.usuarioRepository = usuarioRepository;
        this.temaRepository = temaRepository;
    }

    public PostagemDTO criar(CriarPostagemDTO dto) {

        var usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var usuarioLogin = (UsuarioLogin) usuarioLogado;

        var usuario = usuarioRepository.findById(usuarioLogin.getUsuario().getId())
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

        var usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var usuarioLogin = (UsuarioLogin) usuarioLogado;
        
        if (!postagem.getUsuario().getId().equals(usuarioLogin.getUsuario().getId())) {
            throw new OperacaoNaoAutorizadaException("Não é possível atualizar postagens de outros usuários");
        }

        if (dto.titulo() != null) postagem.setTitulo(dto.titulo());

        if (dto.texto() != null) postagem.setTexto(dto.texto());

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

    public Page<PostagemDTO> buscarPostagensPorUsuarioLogado(Pageable pageable) {
        var usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var usuarioLogin = (UsuarioLogin) usuarioLogado;

        return postagemRepository.buscarPostagensPorUsuario(usuarioLogin.getUsuario().getId(), pageable)
                .map(PostagemDTO::new);
    }

    public List<PostagemDTO> listarUltimosPosts() {
        return postagemRepository.listarUltimosPosts().stream()
                .map(PostagemDTO::new)
                .collect(Collectors.toList());
    }

    public PostagemDTO buscarPorId(Long id) {
        return postagemRepository.findById(id)
                .map(PostagemDTO::new)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Postagem"));
    }

    public void deletar(Long id) {
        var postagem = postagemRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Postagem"));

        var usuarioLogado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var usuarioLogin = (UsuarioLogin) usuarioLogado;
        
        if (!postagem.getUsuario().getId().equals(usuarioLogin.getUsuario().getId())) {
            throw new OperacaoNaoAutorizadaException("Não é possível deletar postagens de outros usuários");
        }
        
        postagemRepository.deleteById(id);
    }

    @Override
    public List<PostagensPorDiaDTO> contarPostagensPorDiaDaSemana() {
        List<ContagemDiaSemana> resultados = postagemRepository.contarPostagensPorDiaDaSemanaUltimaSemana();

        Map<Integer, String> mapDias = Map.of(
                1, "Segunda",
                2, "Terça",
                3, "Quarta",
                4, "Quinta",
                5, "Sexta",
                6, "Sábado",
                7, "Domingo"
        );

        return resultados.stream()
                .map(resultado -> {
                    Integer diaNumero = resultado.getDia_semana();
                    Long quantidade = resultado.getQuantidade();
                    String diaNome = mapDias.get(diaNumero);
                    return new PostagensPorDiaDTO(diaNome, quantidade);
                })
                .collect(Collectors.toList());
    }
}
