package com.aceleramaker.projeto.blogpessoal.repository;

import com.aceleramaker.projeto.blogpessoal.controller.schema.FiltrosPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.model.Postagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    @Query("""
        SELECT p FROM Postagem p
        WHERE (:#{#filtros.titulo()} IS NULL OR p.titulo LIKE %:#{#filtros.titulo()}%)
        AND (:#{#filtros.data()} IS NULL OR p.data = :#{#filtros.data()})
        AND (:#{#filtros.usuarioId()} IS NULL OR p.usuario.id = :#{#filtros.usuarioId()})
        AND (:#{#filtros.usuarioNome()} IS NULL OR p.usuario.nome LIKE %:#{#filtros.usuarioNome()}%)
        AND (:#{#filtros.temaId()} IS NULL OR p.tema.id = :#{#filtros.temaId()})
    """)
    Page<Postagem> buscarComFiltros(@Param("filtros") FiltrosPostagemDTO filtros, Pageable pageable);
}
