package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BloqueiosContaDtoTest {


    @Test
    void testBloqueiosContaDto() {
        BloqueiosContaDto bloqueiosContaDto = new BloqueiosContaDto(
                List.of("Politica1", "Politica2"),
                true,
                true,
                "BloqueioBoleto",
                "BloqueioPix",
                "SubTituloBloqueio"
        );

        assertEquals(List.of("Politica1", "Politica2"), bloqueiosContaDto.politica());
        assertEquals(true, bloqueiosContaDto.permiteCashIn());
        assertEquals(true, bloqueiosContaDto.permiteCashOut());
        assertEquals("BloqueioBoleto", bloqueiosContaDto.tituloBloqueioBoleto());
        assertEquals("BloqueioPix", bloqueiosContaDto.tituloBloqueioPix());
        assertEquals("SubTituloBloqueio", bloqueiosContaDto.subTituloBloqueio());

        // Create another instance for comparison
        BloqueiosContaDto anotherBloqueiosContaDto = new BloqueiosContaDto(
                List.of("Politica1", "Politica2"),
                true,
                true,
                "BloqueioBoleto",
                "BloqueioPix",
                "SubTituloBloqueio"
        );

        assertEquals(bloqueiosContaDto, anotherBloqueiosContaDto);
    }

    @Test
    void testBloqueiosContaDtoDefaultConstructor() {
        BloqueiosContaDto defaultBloqueiosContaDto = new BloqueiosContaDto();

        assertEquals(List.of(), defaultBloqueiosContaDto.politica());
        assertEquals(true, defaultBloqueiosContaDto.permiteCashIn());
        assertEquals(true, defaultBloqueiosContaDto.permiteCashOut());
        assertEquals("", defaultBloqueiosContaDto.tituloBloqueioBoleto());
        assertEquals("", defaultBloqueiosContaDto.tituloBloqueioPix());
        assertEquals("", defaultBloqueiosContaDto.subTituloBloqueio());
    }
}