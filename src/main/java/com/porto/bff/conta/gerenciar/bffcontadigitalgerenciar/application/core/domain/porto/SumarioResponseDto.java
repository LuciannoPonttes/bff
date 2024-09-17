package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto;

public record SumarioResponseDto(
        String nomeBanco,
        String codigoBanco,
        String agencia,
        String numeroConta,
        String digitoConta,
        String saldo,
        String statusConta,
        BloqueiosContaDto bloqueios,
        String documento,
        Boolean possuiCartao,
        String quantidadeChavePix

) {
}
