package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

public record SumarioChavePixResponseDto(
        String nomeBanco,
        String codigoBanco,
        String agencia,
        String numeroConta,
        String digitoConta,
        String saldo,
        String statusConta,
        String documento,
        Boolean possuiCartao,
        String msgChavePix
) {
}
