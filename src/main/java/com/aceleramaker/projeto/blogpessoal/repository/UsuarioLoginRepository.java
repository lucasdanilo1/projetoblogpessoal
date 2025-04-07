package com.aceleramaker.projeto.blogpessoal.repository;

import com.aceleramaker.projeto.blogpessoal.model.UsuarioLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioLoginRepository extends JpaRepository<UsuarioLogin, Long> {
        @Query("SELECT ul FROM UsuarioLogin ul JOIN FETCH ul.usuario u WHERE u.usuario = :login")
        Optional<UsuarioLogin> findByUsuario(@Param("login") String login);
}
