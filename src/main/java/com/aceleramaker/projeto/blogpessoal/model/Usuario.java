package com.aceleramaker.projeto.blogpessoal.model;

import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarUsuarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String usuario;

    private String senha;

    private String foto;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Postagem> postagens;

    public Usuario(CriarUsuarioDTO dto) {
        this.nome = dto.nome();
        this.usuario = dto.usuario();
        this.foto = dto.foto();
    }

}