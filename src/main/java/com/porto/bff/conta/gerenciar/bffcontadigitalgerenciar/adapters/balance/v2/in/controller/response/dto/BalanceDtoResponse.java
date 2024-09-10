package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.balance.v2.in.controller.response.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BalanceDtoResponse(
        @JsonProperty("disponivel")
        String available,
        @JsonProperty("reservada")
        String reserved,
        @JsonProperty("bloqueado")
        String blocked) {
}
