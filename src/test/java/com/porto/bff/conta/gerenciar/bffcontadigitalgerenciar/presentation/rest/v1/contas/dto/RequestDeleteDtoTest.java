package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.RequestDeleteDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestDeleteDtoTest {

    @Test
    void testRequestDeleteDto() {
        RequestDeleteDto requestDeleteDto = new RequestDeleteDto("externalId123");

        assertEquals("externalId123", requestDeleteDto.externalId());

        RequestDeleteDto anotherRequestDeleteDto = new RequestDeleteDto("externalId123");

        assertEquals(requestDeleteDto, anotherRequestDeleteDto);
    }
}