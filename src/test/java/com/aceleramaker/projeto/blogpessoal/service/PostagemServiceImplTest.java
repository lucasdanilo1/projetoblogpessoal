package com.aceleramaker.projeto.blogpessoal.service;

import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.FiltrosPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.model.Postagem;
import com.aceleramaker.projeto.blogpessoal.model.Tema;
import com.aceleramaker.projeto.blogpessoal.model.Usuario;
import com.aceleramaker.projeto.blogpessoal.repository.PostagemRepository;
import com.aceleramaker.projeto.blogpessoal.repository.TemaRepository;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PostagemServiceImplTest {

    @Mock
    private PostagemRepository postagemRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private TemaRepository temaRepository;

    private PostagemServiceImpl postagemServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postagemServiceImpl = new PostagemServiceImpl(postagemRepository, usuarioRepository, temaRepository);
    }

    @Test
    void devecriarPostagemComSucesso() {
        var usuario = new Usuario();
        usuario.setId(1l);
        var tema = new Tema();
        var dto = new CriarPostagemDTO("Título", "Texto", 1L);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(temaRepository.findById(1L)).thenReturn(Optional.of(tema));
        when(postagemRepository.save(any())).thenReturn(new Postagem());

        assertDoesNotThrow(() -> postagemServiceImpl.criar(dto));
        verify(postagemRepository).save(any());
    }

    @Test
    void deveListarPostagensComFiltros() {
        var pageable = mock(Pageable.class);
        var filtros = new FiltrosPostagemDTO("asd", null, null, null, null);
        var page = new PageImpl<>(List.of(new Postagem()));

        when(postagemRepository.buscarComFiltros(any(), any())).thenReturn(page);

        Page<?> resultado = postagemServiceImpl.listarPostagem(filtros, pageable);

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        verify(postagemRepository).buscarComFiltros(filtros, pageable);
    }
}