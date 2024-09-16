package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.in.controller.response.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BankAccountDto(
        @JsonProperty("nomeBanco")
        String bankName,
        @JsonProperty("codigoBanco")
        String bank,
        @JsonProperty("agencia")
        String branch,
        @JsonProperty("numeroConta")
        String number,
        @JsonProperty("digitoConta")
        String checkDigit
) {
}
