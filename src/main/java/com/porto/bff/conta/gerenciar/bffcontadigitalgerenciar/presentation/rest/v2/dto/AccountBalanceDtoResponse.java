package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountBalanceDtoResponse(
        @JsonProperty("disponivel")
        String available,
        @JsonProperty("reservada")
        String reserved,
        @JsonProperty("bloqueado")
        String blocked) {
}

