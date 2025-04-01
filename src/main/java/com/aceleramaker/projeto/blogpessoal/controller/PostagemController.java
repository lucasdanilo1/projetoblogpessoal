// PostagemController.java
package com.aceleramaker.projeto.blogpessoal.controller;

import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.model.Postagem;
import com.aceleramaker.projeto.blogpessoal.service.PostagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postagens")
public class PostagemController {

    private final PostagemService postagemService;

    public PostagemController(PostagemService postagemService) {
        this.postagemService = postagemService;
    }

    @PostMapping
    public ResponseEntity<Postagem> criar(@RequestBody CriarPostagemDTO criarPostagemDTO) {
        return ResponseEntity.ok(postagemService.criar(criarPostagemDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postagem> atualizar(@PathVariable Long id, @RequestBody Postagem postagem) {
        return ResponseEntity.ok(postagemService.atualizar(id, postagem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        postagemService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Postagem>> listarTodas() {
        return ResponseEntity.ok(postagemService.listarTodas());
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<Postagem>> filtrar(
            @RequestParam(required = false) Long autor,
            @RequestParam(required = false) Long tema) {
        return ResponseEntity.ok(postagemService.filtrar(autor, tema));
    }
}