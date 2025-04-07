package com.aceleramaker.projeto.blogpessoal.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UsuarioLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Instant dataExpiracao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    public boolean isExpired() {
        return dataExpiracao.isBefore(Instant.now());
    }
}