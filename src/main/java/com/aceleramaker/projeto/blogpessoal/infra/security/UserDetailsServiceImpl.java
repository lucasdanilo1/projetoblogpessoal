package com.aceleramaker.projeto.blogpessoal.infra.security;

import com.aceleramaker.projeto.blogpessoal.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        if (login == null || login.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return usuarioRepository.findByUsuario(login);
    }

}
