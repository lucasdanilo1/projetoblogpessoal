package com.aceleramaker.projeto.blogpessoal.controller;

import com.aceleramaker.projeto.blogpessoal.controller.schema.AtualizaPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.FiltrosPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.PostagemDTO;
import com.aceleramaker.projeto.blogpessoal.service.PostagemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/postagens")
public class PostagemController {

    private final PostagemService postagemService;

    public PostagemController(PostagemService postagemService) {
        this.postagemService = postagemService;
    }

    @PostMapping
    public ResponseEntity<PostagemDTO> criar(@RequestBody CriarPostagemDTO criarPostagemDTO) {
        return ResponseEntity.ok(postagemService.criar(criarPostagemDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostagemDTO> atualizar(@PathVariable Long id, @RequestBody AtualizaPostagemDTO dto) {
        return ResponseEntity.ok(postagemService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        postagemService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<PostagemDTO>> listarTodas(@RequestBody FiltrosPostagemDTO filtros, Pageable pageable) {
        return ResponseEntity.ok(postagemService.listarPostagem(filtros, pageable));
    }
}