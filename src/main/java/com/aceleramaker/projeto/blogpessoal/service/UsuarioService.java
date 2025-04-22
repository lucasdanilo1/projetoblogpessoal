package com.aceleramaker.projeto.blogpessoal.service;

import com.aceleramaker.projeto.blogpessoal.controller.schema.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UsuarioService {

    void atualizarFoto(Long id, MultipartFile foto) throws IOException;

    UsuarioDTO buscarPorId(Long id);

    UsuarioDTO atualizar(Long id, AtualizaUsuarioDTO dto);

    Page<UsuarioDTO> listarUsuarios(FiltrosUsuarioDTO filtros, Pageable pageable);

    void deletar(Long id);

    String getUsuarioLogado();
}
