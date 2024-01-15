package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaEditadaResponseDtoTest {

    @Test
    void testContaEditadaResponseDto() {
        ContaEditadaResponseDto contaEditadaResponseDto = new ContaEditadaResponseDto("ACTIVE");

        assertEquals("ACTIVE", contaEditadaResponseDto.status());

        ContaEditadaResponseDto anotherContaEditadaResponseDto = new ContaEditadaResponseDto("ACTIVE");

        assertEquals(contaEditadaResponseDto, anotherContaEditadaResponseDto);
    }
}