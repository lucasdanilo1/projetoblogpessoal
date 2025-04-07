package com.aceleramaker.projeto.blogpessoal.model;

import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarTemaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Postagem> postagens;

    public Tema(CriarTemaDTO dto){
        this.descricao = dto.descricao();
    }

}