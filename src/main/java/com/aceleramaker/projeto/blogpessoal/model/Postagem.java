package com.aceleramaker.projeto.blogpessoal.model;

import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarPostagemDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String texto;

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "tema_id")
    private Tema tema;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Postagem(CriarPostagemDTO dto, Usuario usuario, Tema tema) {
        this.titulo = dto.titulo();
        this.texto = dto.texto();
        this.tema = tema;
        this.usuario = usuario;
        this.data = LocalDateTime.now();
    }
}