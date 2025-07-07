package com.agendapro.agendapro.service;

import com.agendapro.agendapro.model.Agendamento;
import com.agendapro.agendapro.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Transactional(readOnly = true)
    public List<Agendamento> findAll() {
        return agendamentoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Agendamento findById(Long id) {
        return agendamentoRepository.findById(id).get();
    }

    public Agendamento save(Agendamento agendamento) {
        Optional<Agendamento> agendamentoexistente = agendamentoRepository.findByDataHora(agendamento.getDataHora());
        if (agendamentoexistente.isPresent()) {
            throw new RuntimeException("Já existe um agendamento para esta data e hora.");  // ou lance uma exceção, ou retorne uma resposta de erro
        }
        if (agendamento.getNome() == null || agendamento.getNome().isEmpty()) {
            throw new RuntimeException("O nome do aluno é obrigatório.");
        }

        return agendamentoRepository.save(agendamento);
    }
}