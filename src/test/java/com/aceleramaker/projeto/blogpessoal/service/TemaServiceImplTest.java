package com.aceleramaker.projeto.blogpessoal.service;

import com.aceleramaker.projeto.blogpessoal.controller.schema.AtualizaTemaDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarTemaDTO;
import com.aceleramaker.projeto.blogpessoal.model.Tema;
import com.aceleramaker.projeto.blogpessoal.repository.TemaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TemaServiceImplTest {

    @Mock
    private TemaRepository temaRepository;

    private TemaServiceImpl temaServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        temaServiceImpl = new TemaServiceImpl(temaRepository);
    }

    @Test
    void deveCriarTemaComSucesso() {
        var dto = new CriarTemaDTO("Descrição teste");
        when(temaRepository.save(any())).thenReturn(new Tema());

        assertDoesNotThrow(() -> temaServiceImpl.criar(dto));
        verify(temaRepository).save(any());
    }

    @Test
    void deveListarTodosOsTemas() {
        when(temaRepository.findAll()).thenReturn(List.of(new Tema()));

        var temas = temaServiceImpl.listarTodos();

        assertNotNull(temas);
        assertFalse(temas.isEmpty());
        verify(temaRepository).findAll();
    }

    @Test
    void deveAtualizarTemaComSucesso() {
        var tema = new Tema();
        var dto = new AtualizaTemaDTO("Nova descrição");

        when(temaRepository.findById(1L)).thenReturn(Optional.of(tema));
        when(temaRepository.save(any())).thenReturn(tema);

        assertDoesNotThrow(() -> temaServiceImpl.atualizar(1L, dto));
        verify(temaRepository).save(any());
    }
}