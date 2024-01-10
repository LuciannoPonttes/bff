package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

import java.util.List;

public record BloqueiosContaDto(
        List<String> politica,
        boolean permiteCashIn,
        boolean permiteCashOut,
        String tituloBloqueioBoleto,
        String tituloBloqueioPix,
        String subTituloBloqueio
) {
    public BloqueiosContaDto() {
        this(List.of(), true, true, "", "", "");
    }
}

