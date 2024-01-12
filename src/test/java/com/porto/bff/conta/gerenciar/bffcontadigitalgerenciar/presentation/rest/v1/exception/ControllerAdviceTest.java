package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ControllerAdviceTest {

    @Mock
    private PareamentoPortoException pareamentoPortoException;

    @Mock
    private TimelineIaasPortoException timelineIaasPortoException;

    @InjectMocks
    private ControllerAdvice controllerAdvice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handlePareamentoException() {
        when(pareamentoPortoException.getErros()).thenReturn(List.of(
                PareamentoPortoException.PareamentoPortoErroItem.builder()
                        .field("fieldName")
                        .message("errorMessage")
                        .build()
        ));

        ResponseEntity<ResponseErrorApi> responseEntity = controllerAdvice.handlePareamentoException(pareamentoPortoException);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(407));
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getErros()).hasSize(1);
    }

    @Test
    void handleTokenIaaSException() {
        when(timelineIaasPortoException.getErros()).thenReturn(List.of(
                TimelineIaasPortoException.TimelineIaasPortoErroItem.builder()
                        .campo("fieldName")
                        .mensagens(List.of("errorMessage"))
                        .build()
        ));

        ResponseEntity<ResponseErrorApi> responseEntity = controllerAdvice.handleTokenIaaSException(timelineIaasPortoException);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(500));
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getErros()).hasSize(1);
    }
}