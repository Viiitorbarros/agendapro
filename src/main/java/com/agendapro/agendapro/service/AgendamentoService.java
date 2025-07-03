package com.agendapro.agendapro.service;

import com.agendapro.agendapro.model.Agendamento;
import com.agendapro.agendapro.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Agendamento>findAll() {
        return agendamentoRepository.findAll();
    }



}
