package com.jeferson.agendamento_notificacao_api.business;

import com.jeferson.agendamento_notificacao_api.business.mapper.IAgendamentoMapper;
import com.jeferson.agendamento_notificacao_api.controller.dto.in.AgendamentoRequestDTO;
import com.jeferson.agendamento_notificacao_api.controller.dto.out.AgendamentoResponseDTO;
import com.jeferson.agendamento_notificacao_api.infrastructure.exception.NotFoundException;
import com.jeferson.agendamento_notificacao_api.infrastructure.repositories.AgendamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final IAgendamentoMapper agendamentoMapper;

    public AgendamentoService(AgendamentoRepository repository, IAgendamentoMapper agendamentoMapper) {
        this.repository = repository;
        this.agendamentoMapper = agendamentoMapper;
    }

    public AgendamentoResponseDTO gravarAgendamentos(AgendamentoRequestDTO agendamento){
       return agendamentoMapper.paraDTO(
                repository.save(
                        agendamentoMapper.paraEntity(agendamento)));
    }

    public AgendamentoResponseDTO buscarAgendamentosPorId(Long id){
        return agendamentoMapper.paraDTO(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrado")));
    }


}
