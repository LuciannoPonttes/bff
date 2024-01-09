package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

import lombok.Setter;

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
