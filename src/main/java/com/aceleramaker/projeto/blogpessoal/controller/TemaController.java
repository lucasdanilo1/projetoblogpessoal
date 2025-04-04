package com.aceleramaker.projeto.blogpessoal.controller;

import com.aceleramaker.projeto.blogpessoal.controller.schema.AtualizaTemaDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.TemaDTO;
import com.aceleramaker.projeto.blogpessoal.model.Tema;
import com.aceleramaker.projeto.blogpessoal.service.TemaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/temas")
public class TemaController {

    private final TemaService temaService;

    public TemaController(TemaService temaService) {
        this.temaService = temaService;
    }

    @PostMapping
    public ResponseEntity<TemaDTO> criar(@RequestBody Tema tema) {
        return ResponseEntity.ok(temaService.criar(tema));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TemaDTO> atualizar(@PathVariable Long id, @RequestBody AtualizaTemaDTO tema) {
        return ResponseEntity.ok(temaService.atualizar(id, tema));
    }

    @GetMapping
    public ResponseEntity<List<TemaDTO>> listarTodos() {
        return ResponseEntity.ok(temaService.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        temaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}