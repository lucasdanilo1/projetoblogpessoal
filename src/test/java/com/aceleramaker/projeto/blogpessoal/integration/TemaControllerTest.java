package com.aceleramaker.projeto.blogpessoal.integration;

import com.aceleramaker.projeto.blogpessoal.controller.TemaController;
import com.aceleramaker.projeto.blogpessoal.controller.schema.TemaDTO;
import com.aceleramaker.projeto.blogpessoal.infra.security.UserDetailsServiceImpl;
import com.aceleramaker.projeto.blogpessoal.model.Tema;
import com.aceleramaker.projeto.blogpessoal.service.TemaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TemaController.class)
@AutoConfigureMockMvc(addFilters = false)
class TemaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TemaService temaService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void deveCriarTemaComSucesso() throws Exception {
        when(temaService.criar(any())).thenReturn(new TemaDTO(new Tema()));

        mockMvc.perform(post("/api/temas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\":\"descricao\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void deveListarTemasComSucesso() throws Exception {
        when(temaService.listarTodos()).thenReturn(List.of(new TemaDTO(new Tema())));

        mockMvc.perform(get("/api/temas"))
                .andExpect(status().isOk());
    }
}