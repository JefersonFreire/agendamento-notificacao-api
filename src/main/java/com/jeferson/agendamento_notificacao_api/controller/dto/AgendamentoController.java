package com.jeferson.agendamento_notificacao_api.controller.dto;

import com.jeferson.agendamento_notificacao_api.business.AgendamentoService;
import com.jeferson.agendamento_notificacao_api.controller.dto.in.AgendamentoRequestDTO;
import com.jeferson.agendamento_notificacao_api.controller.dto.out.AgendamentoResponseDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
