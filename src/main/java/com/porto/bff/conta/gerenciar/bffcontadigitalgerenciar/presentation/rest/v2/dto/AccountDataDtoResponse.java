package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountDataDtoResponse(
        @JsonProperty("id")
        String id,
        @JsonProperty("contaBancaria")
        BankAccountDto bankAccount,
        @JsonProperty("statusConta")
        String status,
        @JsonProperty("tipo")
        String type,
        @JsonProperty("criadoEm")
        @JsonFormat(pattern = "dd/MM/yyyy")
        String createdAt,
        @JsonProperty("atualizadoEm")
        @JsonFormat(pattern = "dd/MM/yyyy")
        String updatedAt
) {
}

