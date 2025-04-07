package com.aceleramaker.projeto.blogpessoal.infra.security;

import com.aceleramaker.projeto.blogpessoal.repository.UsuarioLoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioLoginRepository usuarioLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        if (login == null || login.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não pode ser nulo ou vazio");
        }

        return usuarioLoginRepository.findByUsuario(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + login));
    }
}
