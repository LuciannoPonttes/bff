package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaRequestDtoTest {

    @Test
    void testContaRequestDto() {
        ContaRequestDto contaRequestDto = new ContaRequestDto("ACTIVE", "123456");

        assertEquals("ACTIVE", contaRequestDto.status());
        assertEquals("123456", contaRequestDto.requestId());

        ContaRequestDto anotherContaRequestDto = new ContaRequestDto("ACTIVE", "123456");

        assertEquals(contaRequestDto, anotherContaRequestDto);
    }
}