package com.aceleramaker.projeto.blogpessoal.integration;

import com.aceleramaker.projeto.blogpessoal.controller.UsuarioController;
import com.aceleramaker.projeto.blogpessoal.controller.schema.UsuarioDTO;
import com.aceleramaker.projeto.blogpessoal.model.Usuario;
import com.aceleramaker.projeto.blogpessoal.service.UsuarioServiceImpl;
import com.aceleramaker.projeto.blogpessoal.infra.security.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioServiceImpl usuarioServiceImpl;

    @MockBean
    private JwtService jwtService;

    @Test
    void deveAtualizarUsuarioComSucesso() throws Exception {
        when(usuarioServiceImpl.atualizar(anyLong(), any())).thenReturn(new UsuarioDTO(new Usuario()));

        mockMvc.perform(patch("/api/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"nome\",\"usuario\":\"usuario\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void deveListarUsuariosComSucesso() throws Exception {
        when(usuarioServiceImpl.listarUsuarios(any(), any()))
                .thenReturn(new PageImpl<>(List.of(new UsuarioDTO(new Usuario()))));

        mockMvc.perform(get("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void deveAtualizarFotoComSucesso() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "foto",
                "foto.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "conteudo".getBytes()
        );

        mockMvc.perform(multipart("/api/usuarios/foto/1")
                        .file(file))
                .andExpect(status().isOk());
    }
}