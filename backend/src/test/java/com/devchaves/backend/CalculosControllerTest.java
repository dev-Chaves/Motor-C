package com.devchaves.backend;

import com.devchaves.backend.controller.CalculosController;
import com.devchaves.backend.dto.CalculoRequest;
import com.devchaves.backend.exception.RestExceptionHandler;
import com.devchaves.backend.service.CalculoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculosController.class)
@Import(RestExceptionHandler.class)
public class CalculosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CalculoService calculoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRetornarBadRequestQuandoRpaForVazio() throws Exception {

        CalculoRequest dtoInvalido = new CalculoRequest(
                "27.252.423/0001-55",
                "", //
                "350000.00",
                3L
        );

        String jsonRequest = objectMapper.writeValueAsString(dtoInvalido);

        mockMvc.perform(post("/calculos/das")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("RPA não pode ser vazio"));
    }

}
