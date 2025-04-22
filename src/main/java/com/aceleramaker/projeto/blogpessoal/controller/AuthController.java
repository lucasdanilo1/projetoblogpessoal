package com.aceleramaker.projeto.blogpessoal.controller;

import com.aceleramaker.projeto.blogpessoal.controller.schema.DadosCadastroUsuario;
import com.aceleramaker.projeto.blogpessoal.controller.schema.LoginRequestDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.LoginResponseDTO;
import com.aceleramaker.projeto.blogpessoal.infra.security.JwtService;
import com.aceleramaker.projeto.blogpessoal.model.Usuario;
import com.aceleramaker.projeto.blogpessoal.model.UsuarioLogin;
import com.aceleramaker.projeto.blogpessoal.model.exception.UsuarioJaCadastradoException;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioLoginRepository;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;
    private final UsuarioLoginRepository usuarioLoginRepository;
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido"),
            @ApiResponse(responseCode = "400", description = "Dados de login inválidos"),
            @ApiResponse(responseCode = "403", description = "Usuário desativado"),
            @ApiResponse(responseCode = "401", description = "Não autorizado")
    })
    @Transactional
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.usuario(), loginDto.senha());

        var auth = authenticationManager.authenticate(authenticationToken);

        UsuarioLogin usuario = (UsuarioLogin) auth.getPrincipal();

        String jwtToken = jwtService.geradorToken(usuario);

        System.out.println("Token: " + jwtToken);

        usuario.setToken(jwtToken);
        usuario.setDataExpiracao(jwtService.gerarDataDeExpiracao());

        var usuarioLogin = usuarioLoginRepository.save(usuario);

        return ResponseEntity.ok().body(new LoginResponseDTO(usuarioLogin.getUsername(), jwtToken));
    }

    @PostMapping("registrar")
    public ResponseEntity<Void> registrar(@Valid @RequestBody DadosCadastroUsuario dados) {
        if(usuarioRepository.findByUsuario(dados.usuario()).isPresent())
            throw new UsuarioJaCadastradoException();

        String senhaEncriptada = new BCryptPasswordEncoder().encode(dados.senha());
        var usuario = Usuario.builder()
                .usuario(dados.usuario())
                .senha(senhaEncriptada)
                .nome(dados.nome())
                .build();

        var usuarioLogin = UsuarioLogin.builder()
                .usuario(usuario)
                .build();

        usuario.setUsuarioLogin(usuarioLogin);
        usuarioLogin.setUsuario(usuario);
        usuarioRepository.save(usuario);
        usuarioLoginRepository.save(usuarioLogin);
        return ResponseEntity.ok().build();
    }
}