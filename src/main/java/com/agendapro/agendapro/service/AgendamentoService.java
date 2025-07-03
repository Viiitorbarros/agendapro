package com.agendapro.agendapro.service;

import com.agendapro.agendapro.model.Agendamento;
import com.agendapro.agendapro.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Transactional(readOnly = true)
    public List<Agendamento>findAll() {
        return agendamentoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Agendamento findById(Long id) {
        return agendamentoRepository.findById(id).get();
    }

    public Agendamento save(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }



}
