package com.aceleramaker.projeto.blogpessoal.repository;

import com.aceleramaker.projeto.blogpessoal.controller.schema.FiltrosPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.ContagemDiaSemana;
import com.aceleramaker.projeto.blogpessoal.model.Postagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    @Query("""
        SELECT p FROM Postagem p
        WHERE (:#{#filtros.termo()} IS NULL OR 
               p.titulo LIKE %:#{#filtros.termo()}% OR 
               p.texto LIKE %:#{#filtros.termo()}% OR 
               p.usuario.nome LIKE %:#{#filtros.termo()}%)
        AND (:#{#filtros.dataInicio()} IS NULL OR p.data >= :#{#filtros.dataInicio()})
        AND (:#{#filtros.dataFim()} IS NULL OR p.data <= :#{#filtros.dataFim()})
        AND (:#{#filtros.temaId()} IS NULL OR p.tema.id = :#{#filtros.temaId()})
        AND (:#{#filtros.usuarioId()} IS NULL OR p.usuario.id = :#{#filtros.usuarioId()})
    """)
    Page<Postagem> buscarComFiltros(@Param("filtros") FiltrosPostagemDTO filtros, Pageable pageable);

    @Query("SELECT p FROM Postagem p WHERE p.usuario.id = :usuarioId")
    Page<Postagem> buscarPostagensPorUsuario(@Param("usuarioId") Long usuarioId, Pageable pageable);

    @Query(value = "SELECT * FROM postagem ORDER BY data DESC LIMIT 6 ", nativeQuery = true)
    List<Postagem> listarUltimosPosts();

    @Query(value = """
        SELECT 
            CAST(EXTRACT(ISODOW FROM data) AS INTEGER) as dia_semana, 
            COUNT(*) as quantidade
        FROM 
            postagem
        WHERE 
            data >= DATEADD('DAY', -7, CURRENT_DATE) 
            AND data < CAST(CURRENT_DATE AS TIMESTAMP)
        GROUP BY 
            dia_semana
        ORDER BY
            dia_semana
    """, nativeQuery = true)
    List<ContagemDiaSemana> contarPostagensPorDiaDaSemanaUltimaSemana();
}
