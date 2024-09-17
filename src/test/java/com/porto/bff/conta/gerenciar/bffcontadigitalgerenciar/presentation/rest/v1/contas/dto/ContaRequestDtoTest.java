package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto.AccountRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaRequestDtoTest {

    @Test
    void testContaRequestDto() {
        AccountRequestDto contaRequestDto = new AccountRequestDto("ACTIVE", "123456");

        assertEquals("ACTIVE", contaRequestDto.status());
        assertEquals("123456", contaRequestDto.requestId());

        AccountRequestDto anotherContaRequestDto = new AccountRequestDto("ACTIVE", "123456");

        assertEquals(contaRequestDto, anotherContaRequestDto);
    }
}