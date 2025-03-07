package com.jeferson.agendamento_notificacao_api.business;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jeferson.agendamento_notificacao_api.business.mapper.IAgendamentoMapper;
import com.jeferson.agendamento_notificacao_api.controller.dto.in.AgendamentoRequestDTO;
import com.jeferson.agendamento_notificacao_api.controller.dto.out.AgendamentoResponseDTO;
import com.jeferson.agendamento_notificacao_api.infrastructure.entities.Agendamento;
import com.jeferson.agendamento_notificacao_api.infrastructure.enums.StatusNotificacaoEnum;
import com.jeferson.agendamento_notificacao_api.infrastructure.repositories.AgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private IAgendamentoMapper agendamentoMapper;

    private AgendamentoRequestDTO agendamentoRequestDTO;
    private AgendamentoResponseDTO agendamentoResponseDTO;
    private Agendamento agendamentoEntity;

    @BeforeEach
    void setup(){

        agendamentoEntity = new Agendamento(1L,"email@email.com","99985698",
                LocalDateTime.of(2025,3,7,13,1,30),
                LocalDateTime.now(),null,"Bem vindo ao portal",
                StatusNotificacaoEnum.AGENDADO);

        agendamentoRequestDTO = new AgendamentoRequestDTO("email@email.com","99985698",
                "Bem vindo ao portal", LocalDateTime.of(2025,3,7,13,1,30));

        agendamentoResponseDTO = new AgendamentoResponseDTO(1L,"email@email.com","99985698",
                "Bem vindo ao portal", LocalDateTime.of(2025,3,7,13,1,30),
                StatusNotificacaoEnum.AGENDADO);
    }

    @Test
    void deveGravarAgendamentoComSucesso(){
        when(agendamentoMapper.paraEntity(agendamentoRequestDTO))
                .thenReturn(agendamentoEntity);
        when(agendamentoRepository.save(agendamentoEntity))
                .thenReturn(agendamentoEntity);
        when(agendamentoMapper.paraDTO(agendamentoEntity))
                .thenReturn(agendamentoResponseDTO);

        AgendamentoResponseDTO outDto = agendamentoService.gravarAgendamentos(agendamentoRequestDTO);

        verify(agendamentoMapper, times(1)).paraEntity(agendamentoRequestDTO);
        verify(agendamentoRepository, times(1)).save(agendamentoEntity);
        verify(agendamentoMapper, times(1)).paraDTO(agendamentoEntity);
        assertThat(outDto).usingRecursiveComparison().isEqualTo(agendamentoResponseDTO);

    }
}
