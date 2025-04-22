package com.aceleramaker.projeto.blogpessoal.controller;

import com.aceleramaker.projeto.blogpessoal.controller.schema.AtualizaPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.FiltrosPostagemDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.PostagemDTO;
import com.aceleramaker.projeto.blogpessoal.service.PostagemServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postagens")
@Tag(name = "Postagens", description = "Endpoints para gerenciamento de postagens")
public class PostagemController {

    private final PostagemServiceImpl postagemServiceImpl;

    public PostagemController(PostagemServiceImpl postagemServiceImpl) {
        this.postagemServiceImpl = postagemServiceImpl;
    }

    @Operation(summary = "Criar uma nova postagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Postagem criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<PostagemDTO> criar(@RequestBody CriarPostagemDTO criarPostagemDTO) {
        return ResponseEntity.ok(postagemServiceImpl.criar(criarPostagemDTO));
    }

    @Operation(summary = "Atualizar uma postagem existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Postagem atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Postagem não encontrada")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<PostagemDTO> atualizar(@PathVariable Long id, @RequestBody AtualizaPostagemDTO dto) {
        return ResponseEntity.ok(postagemServiceImpl.atualizar(id, dto));
    }

    @Operation(summary = "Deletar uma postagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Postagem deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Postagem não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        postagemServiceImpl.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar postagens com filtros")
    @ApiResponse(responseCode = "200", description = "Lista de postagens retornada com sucesso")
    @PostMapping("listagem")
    public ResponseEntity<Page<PostagemDTO>> listarTodas(@RequestBody(required = false) FiltrosPostagemDTO filtros, Pageable pageable) {
        return ResponseEntity.ok(postagemServiceImpl.listarPostagem(filtros, pageable));
    }

    @Operation(summary = "Listar postagens do usuário logado")
    @ApiResponse(responseCode = "200", description = "Lista de postagens do usuário retornada com sucesso")
    @GetMapping("/listagem/usuario-logado")
    public ResponseEntity<Page<PostagemDTO>> listarPostagensUsuarioLogado(Pageable pageable) {
        return ResponseEntity.ok(postagemServiceImpl.buscarPostagensPorUsuarioLogado(pageable));
    }

    @Operation(summary = "Buscar as ultimas postagens mais recentes")
    @ApiResponse(responseCode = "200", description = "Lista das ultimas postagens mais recentes retornada com sucesso")
    @GetMapping("/listagem/recentes")
    public ResponseEntity<List<PostagemDTO>> listarUltimosPosts() {
        return ResponseEntity.ok(postagemServiceImpl.listarUltimosPosts());
    }

    @Operation(summary = "Buscar uma postagem pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Postagem encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Postagem não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PostagemDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(postagemServiceImpl.buscarPorId(id));
    }
}