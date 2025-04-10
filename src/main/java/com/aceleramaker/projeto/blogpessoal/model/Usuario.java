package com.aceleramaker.projeto.blogpessoal.model;

import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarUsuarioDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String usuario;

    private String senha;

    private byte[] foto;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Postagem> postagens;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private UsuarioLogin usuarioLogin;

    public Usuario(CriarUsuarioDTO dto) {
        this.nome = dto.nome();
        this.usuario = dto.usuario();
    }

    public void setSenha(String senha) {
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }
}