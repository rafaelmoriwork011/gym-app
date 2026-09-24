package com.rvm.gym.service;

import com.rvm.gym.entity.Usuario;
import com.rvm.gym.exception.BusinessException;
import com.rvm.gym.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario findById(UUID id) {

        Usuario usuario = this.usuarioRepository.findById(id)
                                                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));
        return usuario;
    }

}
