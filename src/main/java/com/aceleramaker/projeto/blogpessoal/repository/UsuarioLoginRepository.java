package com.aceleramaker.projeto.blogpessoal.repository;

import com.aceleramaker.projeto.blogpessoal.model.UsuarioLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioLoginRepository extends JpaRepository<UsuarioLogin, Long> {
}
