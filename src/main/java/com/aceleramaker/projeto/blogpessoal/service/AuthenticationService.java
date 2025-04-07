package com.aceleramaker.projeto.blogpessoal.service;

import com.aceleramaker.projeto.blogpessoal.controller.schema.LoginRequestDTO;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private UsuarioRepository usuarioRepository;

    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public UserDetails authenticate(LoginRequestDTO loginDto){
            this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDto.usuario(), loginDto.senha()));
        return extrairUsuario(loginDto.usuario());
    }

    public UserDetails extrairUsuario(String username) {
        return this.usuarioRepository.findByUsuario(username);
    }
}