package com.jeferson.agendamento_notificacao_api.controller.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeferson.agendamento_notificacao_api.infrastructure.enums.StatusNotificacaoEnum;

import java.time.LocalDateTime;

public record AgendamentoRequestDTO(String emailDestinatario,
                                    String telefoneDestinatario,
                                    String mensagem,
                                    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy HH:mm:ss")
                                    LocalDateTime dataHoraEnvio) {
}
