package com.agendapro.agendapro.repository;

import com.agendapro.agendapro.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    Optional<Agendamento> findByDataHora(LocalDateTime dataHora);

    //Optional<Agendamento> deleteAgendamentoById(Long id);
}
