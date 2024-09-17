package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto;

import java.util.List;

public record AccountBlockingDto(
        List<String> politica,
        boolean permiteCashIn,
        boolean permiteCashOut,
        String tituloBloqueioBoleto,
        String tituloBloqueioPix,
        String subTituloBloqueio
) {
    public AccountBlockingDto() {
        this(List.of(), true, true, "", "", "");
    }
}

