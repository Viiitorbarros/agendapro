package com.agendapro.agendapro.service;

import com.agendapro.agendapro.model.Usuario;
import com.agendapro.agendapro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired // Adicione a injeção
    private UsuarioRepository usuarioRepository;

    @Autowired // Injete o PasswordEncoder
    private PasswordEncoder passwordEncoder;

    public Usuario save(Usuario usuario) {
        // Codifica a senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }


}
