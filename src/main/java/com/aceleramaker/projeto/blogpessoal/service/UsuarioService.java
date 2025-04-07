package com.aceleramaker.projeto.blogpessoal.service;

import com.aceleramaker.projeto.blogpessoal.controller.schema.AtualizaUsuarioDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.CriarUsuarioDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.FiltrosUsuarioDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.UsuarioDTO;
import com.aceleramaker.projeto.blogpessoal.model.Usuario;
import com.aceleramaker.projeto.blogpessoal.model.exception.EntidadeNaoEncontradaException;
import com.aceleramaker.projeto.blogpessoal.model.exception.UsuarioExistenteException;
import com.aceleramaker.projeto.blogpessoal.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO cadastrar(CriarUsuarioDTO dto) {
        if (usuarioRepository.existsByUsuario(dto.usuario()))
            throw new UsuarioExistenteException();

        return new UsuarioDTO(usuarioRepository.save(new Usuario(dto)));
    }

    public void atualizarFoto(Long id, MultipartFile foto) throws IOException {
        String contentType = foto.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Arquivo deve ser uma imagem");
        }

        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(Usuario.class));

        usuario.setFoto(foto.getBytes());
        usuarioRepository.save(usuario);
    }

    public UsuarioDTO atualizar(Long id, AtualizaUsuarioDTO dto) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        if (usuarioRepository.existsByUsuario(dto.usuario()))
            throw new UsuarioExistenteException();

        if (dto.nome() != null) usuario.setNome(dto.nome());

        if (dto.usuario() != null) usuario.setUsuario(dto.usuario());

        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    public Page<UsuarioDTO> listarUsuarios(FiltrosUsuarioDTO filtros, Pageable pageable) {
        return usuarioRepository.buscarComFiltros(filtros, pageable).map(UsuarioDTO::new);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
