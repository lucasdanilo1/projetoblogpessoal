package com.aceleramaker.projeto.blogpessoal.service;

import com.aceleramaker.projeto.blogpessoal.controller.schema.LoginRequestDTO;
import com.aceleramaker.projeto.blogpessoal.model.UsuarioLogin;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioLoginRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;

public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private UsuarioLoginRepository usuarioLoginRepository;

    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public Optional<UsuarioLogin> authenticate(LoginRequestDTO loginDto){
            this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDto.usuario(), loginDto.senha()));
        return extrairUsuario(loginDto.usuario());
    }

    public Optional<UsuarioLogin> extrairUsuario(String username) {
        return this.usuarioLoginRepository.findByUsuario(username);
    }
}