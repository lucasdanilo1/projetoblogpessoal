package com.aceleramaker.projeto.blogpessoal.controller;

import com.aceleramaker.projeto.blogpessoal.controller.schema.AtualizaUsuarioDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarUsuarioDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.UsuarioDTO;
import com.aceleramaker.projeto.blogpessoal.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody CriarUsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.cadastrar(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @RequestBody AtualizaUsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}