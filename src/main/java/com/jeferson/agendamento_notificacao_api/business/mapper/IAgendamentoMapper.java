package com.jeferson.agendamento_notificacao_api.business.mapper;

import com.jeferson.agendamento_notificacao_api.controller.dto.in.AgendamentoRequestDTO;
import com.jeferson.agendamento_notificacao_api.controller.dto.out.AgendamentoResponseDTO;
import com.jeferson.agendamento_notificacao_api.infrastructure.entities.Agendamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IAgendamentoMapper {

    Agendamento paraEntity(AgendamentoRequestDTO agendamento);

    AgendamentoResponseDTO paraDTO(Agendamento agendamento);

    @Mapping(target = "dataHoraModificacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "statusNotificacao", expression = "java(StatusNotificacaoEnum.CANCELADO)")
    Agendamento paraEntityCancelamento(Agendamento agendamento);
}
