package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v2.dto;

public record AccountBalanceDtoResponse(
        Double disponivel,
        Double reservada,
        Double bloqueado) {
}

