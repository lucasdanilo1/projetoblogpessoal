package com.aceleramaker.projeto.blogpessoal.controller.schema;

import java.time.LocalDateTime;

public record FiltrosPostagemDTO(
    String titulo,
    LocalDateTime data,
    Long usuarioId,
    String usuarioNome,
    Long temaId
) {}