package com.agendapro.agendapro.repository;

import com.agendapro.agendapro.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
