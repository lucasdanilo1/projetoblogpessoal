package com.aceleramaker.projeto.blogpessoal.controller.schema;

import java.time.LocalDateTime;

public record FiltrosPostagemDTO(
    String termo,
    LocalDateTime dataInicio,
    LocalDateTime dataFim,
    Long temaId,
    Long usuarioId
) {}    