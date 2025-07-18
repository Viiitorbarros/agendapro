package com.agendapro.agendapro.service;

import com.agendapro.agendapro.model.Usuario;
import com.agendapro.agendapro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUserName(userName).
                orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado"));

        return User.builder()
                .username(usuario.getUserName())
                .password(usuario.getSenha())
                .roles(usuario.getRole()) // Spring adicionará o prefixo "ROLE_" automaticamente
                .build();

    }
}
