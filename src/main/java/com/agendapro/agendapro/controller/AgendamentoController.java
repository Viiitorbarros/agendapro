package com.agendapro.agendapro.controller;



import com.agendapro.agendapro.model.Agendamento;
import com.agendapro.agendapro.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendapro/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public List<Agendamento> findAll() {
        return agendamentoService.findAll();
    }

    @GetMapping("/{id}")
    public Agendamento findById(@PathVariable Long id) {
      return  agendamentoService.findById(id);
    }

    @PostMapping
    public Agendamento createAgendamento(@RequestBody Agendamento agendamento){
        return agendamentoService.save(agendamento);
    }


}
