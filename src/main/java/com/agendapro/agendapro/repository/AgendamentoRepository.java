package com.agendapro.agendapro.repository;

import com.agendapro.agendapro.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
