package com.aceleramaker.projeto.blogpessoal.repository;

import com.aceleramaker.projeto.blogpessoal.controller.schema.TemaPostagemCountDTO;
import com.aceleramaker.projeto.blogpessoal.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TemaRepository extends JpaRepository<Tema, Long> {
    
    @Query("SELECT new com.aceleramaker.projeto.blogpessoal.controller.schema.TemaPostagemCountDTO(t.id, t.descricao, COUNT(p)) FROM Tema t LEFT JOIN t.postagens p GROUP BY t.id, t.descricao")
    List<TemaPostagemCountDTO> countPostagensPorTema();
}
