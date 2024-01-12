package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumarioChavePixResponseDtoTest {

    @Test
    void testSumarioChavePixResponseDto() {
        SumarioChavePixResponseDto sumarioChavePixResponseDto = new SumarioChavePixResponseDto(
                "BancoX",
                "123",
                "7890",
                "12345678",
                "9",
                "1000.00",
                "Active",
                "123456789",
                true,
                "Pix is available"
        );

        assertEquals("BancoX", sumarioChavePixResponseDto.nomeBanco());
        assertEquals("123", sumarioChavePixResponseDto.codigoBanco());
        assertEquals("7890", sumarioChavePixResponseDto.agencia());
        assertEquals("12345678", sumarioChavePixResponseDto.numeroConta());
        assertEquals("9", sumarioChavePixResponseDto.digitoConta());
        assertEquals("1000.00", sumarioChavePixResponseDto.saldo());
        assertEquals("Active", sumarioChavePixResponseDto.statusConta());
        assertEquals("123456789", sumarioChavePixResponseDto.documento());
        assertEquals(true, sumarioChavePixResponseDto.possuiCartao());
        assertEquals("Pix is available", sumarioChavePixResponseDto.msgChavePix());

        SumarioChavePixResponseDto anotherSumarioChavePixResponseDto = new SumarioChavePixResponseDto(
                "BancoX",
                "123",
                "7890",
                "12345678",
                "9",
                "1000.00",
                "Active",
                "123456789",
                true,
                "Pix is available"
        );

        assertEquals(sumarioChavePixResponseDto, anotherSumarioChavePixResponseDto);
    }
}