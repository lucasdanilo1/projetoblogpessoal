package com.aceleramaker.projeto.blogpessoal.controller;

import com.aceleramaker.projeto.blogpessoal.controller.schema.DadosCadastroUsuario;
import com.aceleramaker.projeto.blogpessoal.controller.schema.LoginRequestDTO;
import com.aceleramaker.projeto.blogpessoal.infra.security.JwtService;
import com.aceleramaker.projeto.blogpessoal.model.Usuario;
import com.aceleramaker.projeto.blogpessoal.model.UsuarioLogin;
import com.aceleramaker.projeto.blogpessoal.model.exception.EntidadeNaoEncontradaException;
import com.aceleramaker.projeto.blogpessoal.model.exception.UsuarioJaCadastradoException;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioLoginRepository;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;

import static com.aceleramaker.projeto.blogpessoal.util.AuthUtils.generateTokenCookie;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager manager;
    private final JwtService jwtService;
    private final UsuarioLoginRepository usuarioLoginRepository;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("login")
    @ApiResponses(value = { @ApiResponse(responseCode = "403", description = "Usuário desativado"),
            @ApiResponse(responseCode = "400", description = "Usuário ou senha inválidos"),
            @ApiResponse(responseCode = "401", description = "Token expirado") })
    @Transactional
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginDto,
                                                      HttpServletResponse response) {
        var loginSenha = new UsernamePasswordAuthenticationToken(loginDto.usuario(), loginDto.senha());

        var auth = this.manager.authenticate(loginSenha);

        var usuario = (Usuario) auth.getPrincipal();

        var usuarioLogin = usuarioLoginRepository.save(UsuarioLogin.builder()
                .usuario(usuario)
                .dataExpiracao(Instant.ofEpochMilli(this.jwtService.getExpirationTime())).build());

        usuario.setUsuarioLogin(usuarioLogin);

        usuarioRepository.save(usuario);

        var jwtToken = this.jwtService.generateToken(usuario);

        response.addCookie(
                generateTokenCookie("acessToken", jwtToken, this.jwtService.getExpirationTime(), "/", true));

        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("registrar")
    public ResponseEntity<Void> registrar(@Valid @RequestBody DadosCadastroUsuario dados) {
        if(usuarioRepository.findByUsuario(dados.usuario()) != null)
            throw new UsuarioJaCadastradoException();

        String senhaEncriptada = new BCryptPasswordEncoder().encode(dados.senha());
        var usuario = Usuario.builder()
                .usuario(dados.usuario())
                .senha(senhaEncriptada)
                .nome(dados.nome())
                .build();

        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "foto/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> atualizarFoto(
            @PathVariable Long id,
            @RequestPart MultipartFile foto) throws IOException {

        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(Usuario.class));

        usuario.setFoto(foto.getBytes());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }
}