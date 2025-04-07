package com.aceleramaker.projeto.blogpessoal.integration;

import com.aceleramaker.projeto.blogpessoal.controller.AuthController;
import com.aceleramaker.projeto.blogpessoal.infra.security.JwtService;
import com.aceleramaker.projeto.blogpessoal.infra.security.UserDetailsServiceImpl;
import com.aceleramaker.projeto.blogpessoal.model.UsuarioLogin;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioLoginRepository;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import java.time.Instant;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UsuarioLoginRepository usuarioLoginRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void deveRealizarLoginComSucesso() throws Exception {
        var usuario = new UsuarioLogin();
        var auth = new UsernamePasswordAuthenticationToken(usuario, null);

        when(authenticationManager.authenticate(any())).thenReturn(auth);
        when(jwtService.geradorToken(any())).thenReturn("token");
        when(jwtService.gerarDataDeExpiracao()).thenReturn(Instant.now());

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"usuario\":\"usuario\",\"senha\":\"senha\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("token"));
    }

    @Test
    void deveRegistrarUsuarioComSucesso() throws Exception {
        when(usuarioRepository.findByUsuario(any())).thenReturn(Optional.empty());

        mockMvc.perform(post("/auth/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"usuario\":\"usuario\",\"senha\":\"senha\",\"nome\":\"nome\"}"))
                .andExpect(status().isOk());
    }
}