package com.aceleramaker.projeto.blogpessoal.service;

import com.aceleramaker.projeto.blogpessoal.controller.schema.FiltrosUsuarioDTO;
import com.aceleramaker.projeto.blogpessoal.model.Usuario;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    private UsuarioServiceImpl usuarioServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioServiceImpl = new UsuarioServiceImpl(usuarioRepository);
    }

    @Test
    void deveAtualizarFotoComSucesso() {
        var mockFile = new MockMultipartFile(
                "foto", "foto.jpg", "image/jpeg", "test".getBytes()
        );

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new Usuario()));

        assertDoesNotThrow(() -> usuarioServiceImpl.atualizarFoto(1L, mockFile));
        verify(usuarioRepository).save(any());
    }

    @Test
    void deveListarUsuariosComFiltros() {
        var pageable = mock(Pageable.class);
        var filtros = new FiltrosUsuarioDTO("asdfas", "ifad");
        var page = new PageImpl<>(List.of(new Usuario()));

        when(usuarioRepository.buscarComFiltros(any(), any())).thenReturn(page);

        var resultado = usuarioServiceImpl.listarUsuarios(filtros, pageable);

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        verify(usuarioRepository).buscarComFiltros(filtros, pageable);
    }
}