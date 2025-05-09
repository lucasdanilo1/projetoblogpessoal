package com.aceleramaker.projeto.blogpessoal.controller;

import com.aceleramaker.projeto.blogpessoal.controller.schema.AtualizaTemaDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarTemaDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.TemaDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.TemaPostagemCountDTO;
import com.aceleramaker.projeto.blogpessoal.service.TemaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/temas")
@Tag(name = "Temas", description = "Endpoints para gerenciamento de temas")
public class TemaController {

    private final TemaServiceImpl temaServiceImpl;

    public TemaController(TemaServiceImpl temaServiceImpl) {
        this.temaServiceImpl = temaServiceImpl;
    }

    @Operation(summary = "Criar um novo tema")
    @ApiResponse(responseCode = "200", description = "Tema criado com sucesso")
    @PostMapping
    public ResponseEntity<TemaDTO> criar(@RequestBody CriarTemaDTO tema) {
        return ResponseEntity.ok(temaServiceImpl.criar(tema));
    }

    @Operation(summary = "Atualizar um tema existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tema atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tema não encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<TemaDTO> atualizar(@PathVariable Long id, @RequestBody AtualizaTemaDTO tema) {
        return ResponseEntity.ok(temaServiceImpl.atualizar(id, tema));
    }

    @Operation(summary = "Listar todos os temas")
    @ApiResponse(responseCode = "200", description = "Lista de temas retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<TemaDTO>> listarTodos() {
        return ResponseEntity.ok(temaServiceImpl.listarTodos());
    }

    @Operation(summary = "Deletar um tema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tema deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tema não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        temaServiceImpl.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Contar postagens por tema")
    @ApiResponse(responseCode = "200", description = "Contagem de postagens por tema retornada com sucesso")
    @GetMapping("/contagem-postagens")
    public ResponseEntity<List<TemaPostagemCountDTO>> contarPostagensPorTema() {
        return ResponseEntity.ok(temaServiceImpl.contarPostagensPorTema());
    }
}