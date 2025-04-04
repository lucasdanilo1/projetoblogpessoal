package com.aceleramaker.projeto.blogpessoal.repository;

import com.aceleramaker.projeto.blogpessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByUsuario(String usuario);
}
