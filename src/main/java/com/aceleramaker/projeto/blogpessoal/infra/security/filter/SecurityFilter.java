package com.aceleramaker.projeto.blogpessoal.infra.security.filter;

import com.aceleramaker.projeto.blogpessoal.infra.security.UserDetailsServiceImpl;
import com.aceleramaker.projeto.blogpessoal.infra.security.JwtService;
import com.aceleramaker.projeto.blogpessoal.model.UsuarioLogin;
import com.aceleramaker.projeto.blogpessoal.model.exception.EntidadeNaoEncontradaException;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioLoginRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UsuarioLoginRepository usuarioLoginRepository;

    public SecurityFilter(JwtService jwtService, UserDetailsServiceImpl userDetailsService,
                          UsuarioLoginRepository usuarioLoginRepository) {
        this.jwtService = jwtService;
        this.usuarioLoginRepository = usuarioLoginRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recuperarToken(request);
        if(token != null){
            String login = jwtService.validarToken(token);

            UsuarioLogin usuario = usuarioLoginRepository.findByUsuario(login)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException(UsuarioLogin.class));

            UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;

        return authHeader.replace("Bearer ", "");
    }
}