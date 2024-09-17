package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto;

public record PixKeySummaryResponseDto(
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
