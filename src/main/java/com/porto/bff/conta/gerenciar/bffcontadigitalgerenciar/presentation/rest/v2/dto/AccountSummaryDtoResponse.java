package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountSummaryDtoResponse(
        @JsonProperty("nomeBanco")
        String bankName,
        @JsonProperty("codigoBanco")
        String bank,
        @JsonProperty("agencia")
        String branch,
        @JsonProperty("numeroConta")
        String number,
        @JsonProperty("digitoConta")
        String checkDigit,
        @JsonProperty("saldo")
        String balance,
        @JsonProperty("statusConta")
        String status,
        @JsonProperty("documento")
        String document,
        @JsonProperty("possuiCartao")
        Boolean hasPortoCard,
        @JsonProperty("quantidadeChavePix")
        String pixKeyCount

) {
}
