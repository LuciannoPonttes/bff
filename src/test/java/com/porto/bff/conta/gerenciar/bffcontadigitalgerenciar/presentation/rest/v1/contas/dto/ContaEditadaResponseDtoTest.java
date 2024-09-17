package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.EditedAccountResponseDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaEditadaResponseDtoTest {

    @Test
    void testContaEditadaResponseDto() {
        EditedAccountResponseDto contaEditadaResponseDto = new EditedAccountResponseDto("ACTIVE");

        assertEquals("ACTIVE", contaEditadaResponseDto.status());

        EditedAccountResponseDto anotherContaEditadaResponseDto = new EditedAccountResponseDto("ACTIVE");

        assertEquals(contaEditadaResponseDto, anotherContaEditadaResponseDto);
    }
}