package com.aceleramaker.projeto.blogpessoal.service;

import com.aceleramaker.projeto.blogpessoal.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder,
                          JwtTokenUtil jwtTokenUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public Usuario cadastrar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        existente.setNome(usuario.getNome());
        existente.setFoto(usuario.getFoto());
        if (usuario.getSenha() != null) {
            existente.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        return usuarioRepository.save(existente);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public String login(Usuario usuario) {
        Usuario existente = usuarioRepository.findByUsuario(usuario.getUsuario())
                .orElseThrow(() -> new AuthenticationException("Credenciais inválidas"));

        if (!passwordEncoder.matches(usuario.getSenha(), existente.getSenha())) {
            throw new AuthenticationException("Credenciais inválidas");
        }

        return jwtTokenUtil.generateToken(existente);
    }
}
