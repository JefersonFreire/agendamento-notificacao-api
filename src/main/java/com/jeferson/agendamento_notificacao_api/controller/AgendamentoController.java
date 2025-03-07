package com.jeferson.agendamento_notificacao_api.controller;

import com.jeferson.agendamento_notificacao_api.business.AgendamentoService;
import com.jeferson.agendamento_notificacao_api.controller.dto.in.AgendamentoRequestDTO;
import com.jeferson.agendamento_notificacao_api.controller.dto.out.AgendamentoResponseDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> gravarAgendamentos(@RequestBody AgendamentoRequestDTO agendamento) {
        return ResponseEntity.ok(agendamentoService.gravarAgendamentos(agendamento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> buscarAgendamentosPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(agendamentoService.buscarAgendamentosPorId(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarAgendamento(@PathVariable("id") Long id){
        agendamentoService.cancelarAgendamento(id);
        return ResponseEntity.accepted().build();
    }

}
