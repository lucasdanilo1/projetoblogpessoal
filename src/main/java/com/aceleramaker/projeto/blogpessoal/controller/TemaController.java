package com.aceleramaker.projeto.blogpessoal.controller;

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
    public ResponseEntity<Tema> criar(@RequestBody Tema tema) {
        return ResponseEntity.ok(temaService.criar(tema));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tema> atualizar(@PathVariable Long id, @RequestBody Tema tema) {
        return ResponseEntity.ok(temaService.atualizar(id, tema));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        temaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Tema>> listarTodos() {
        return ResponseEntity.ok(temaService.listarTodos());
    }
}