package com.jeferson.agendamento_notificacao_api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jeferson.agendamento_notificacao_api.business.AgendamentoService;
import com.jeferson.agendamento_notificacao_api.controller.dto.in.AgendamentoRequestDTO;
import com.jeferson.agendamento_notificacao_api.controller.dto.out.AgendamentoResponseDTO;
import com.jeferson.agendamento_notificacao_api.infrastructure.enums.StatusNotificacaoEnum;
import jakarta.persistence.Id;
import lombok.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@TestConfiguration
public class AgendamentoControllerTest {

    @InjectMocks
    AgendamentoController agendamentoController;

    @Mock
    AgendamentoService agendamentoService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private AgendamentoRequestDTO agendamentoRequestDTO;
    private AgendamentoResponseDTO agendamentoResponseDTO;

    @BeforeEach
    void setup(){

        mockMvc = MockMvcBuilders.standaloneSetup(agendamentoController).build();

        objectMapper.registerModule(new JavaTimeModule());

        agendamentoRequestDTO = new AgendamentoRequestDTO("email@email.com","99985698",
                "Bem vindo ao portal", LocalDateTime.of(2025,3,7,13,1,30));

        agendamentoResponseDTO = new AgendamentoResponseDTO(1L,"email@email.com","99985698",
                "Bem vindo ao portal", LocalDateTime.of(2025,3,7,13,1,30),
                StatusNotificacaoEnum.AGENDADO);
    }

    @Test
    void deveCriarAgendamentoComSucesso() throws Exception {
        when(agendamentoService.gravarAgendamentos(agendamentoRequestDTO))
                .thenReturn(agendamentoResponseDTO);
        mockMvc.perform(post("/agendamento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(agendamentoRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.emailDestinatario").value("email@email.com"))
                .andExpect(jsonPath("$.telefoneDestinatario").value("99985698"))
                .andExpect(jsonPath("$.mensagem").value(agendamentoResponseDTO.mensagem()))
                .andExpect(jsonPath("$.dataHoraEnvio").value("07-03-2025 13:01:30"))
                .andExpect(jsonPath("$.statusNotificacao").value("AGENDADO"));
        verify(agendamentoService, times(1)).gravarAgendamentos(agendamentoRequestDTO);
    }
}
