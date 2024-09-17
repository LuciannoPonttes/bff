package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.core.domain.porto;

public record ContaResponseDto(
        String id,
        ContaBancariaDto contaBancaria,
        String statusConta,
        String tipo,
        String criadoEm,
        String atualizadoEm
) {
}

