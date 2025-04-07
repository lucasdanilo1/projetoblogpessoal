package com.aceleramaker.projeto.blogpessoal.integration;

import com.aceleramaker.projeto.blogpessoal.controller.PostagemController;
import com.aceleramaker.projeto.blogpessoal.controller.schema.PostagemDTO;
import com.aceleramaker.projeto.blogpessoal.infra.security.JwtService;
import com.aceleramaker.projeto.blogpessoal.infra.security.SecurityConfiguration;
import com.aceleramaker.projeto.blogpessoal.infra.security.UserDetailsServiceImpl;
import com.aceleramaker.projeto.blogpessoal.model.Postagem;
import com.aceleramaker.projeto.blogpessoal.model.Tema;
import com.aceleramaker.projeto.blogpessoal.model.Usuario;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioLoginRepository;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioRepository;
import com.aceleramaker.projeto.blogpessoal.service.PostagemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostagemController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({SecurityConfiguration.class})
class PostagemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostagemService postagemService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UsuarioLoginRepository usuarioLoginRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Test User");

        Tema tema = new Tema();
        tema.setId(1L);

        Postagem postagem = new Postagem();
        postagem.setId(1L);
        postagem.setUsuario(usuario);
        postagem.setTema(tema);

        Page<PostagemDTO> page = new PageImpl<>(List.of(new PostagemDTO(postagem)));

        when(postagemService.listarPostagem(any(), any())).thenReturn(page);
    }

    @Test
    void deveCriarPostagemComSucesso() throws Exception {
        when(postagemService.criar(any())).thenReturn(new PostagemDTO(new Postagem()));

        mockMvc.perform(post("/api/postagens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"titulo\",\"texto\":\"texto\",\"usuarioId\":1,\"temaId\":1}"))
                .andExpect(status().isOk());
    }

    @Test
    void deveListarPostagensComSucesso() throws Exception {
        when(postagemService.listarPostagem(any(), any()))
                .thenReturn(new PageImpl<>(List.of(new PostagemDTO(new Postagem()))));

        mockMvc.perform(get("/api/postagens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }
}