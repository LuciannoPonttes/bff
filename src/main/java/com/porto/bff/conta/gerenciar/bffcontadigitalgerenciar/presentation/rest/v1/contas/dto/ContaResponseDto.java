package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

public record ContaResponseDto(
        String id,
        ContaBancariaDto contaBancaria,
        String statusConta,
        String tipo,
        BloqueiosContaDto bloqueios,
        String criadoEm,
        String atualizadoEm
) {
}

