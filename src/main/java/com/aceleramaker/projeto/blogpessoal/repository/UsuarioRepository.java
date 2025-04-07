package com.aceleramaker.projeto.blogpessoal.repository;

import com.aceleramaker.projeto.blogpessoal.controller.schema.FiltrosUsuarioDTO;
import com.aceleramaker.projeto.blogpessoal.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByUsuario(String usuario);
    @Query("""
        SELECT u FROM Usuario u
        WHERE (:#{#filtros.usuario()} IS NULL OR u.usuario LIKE %:#{#filtros.usuario()}%)
        AND (:#{#filtros.nome()} IS NULL OR u.nome LIKE %:#{#filtros.nome()}%)
    """)
    Page<Usuario> buscarComFiltros(@Param("filtros") FiltrosUsuarioDTO filtros, Pageable pageable);

    Optional<Usuario> findByUsuario(String usuario);
}
