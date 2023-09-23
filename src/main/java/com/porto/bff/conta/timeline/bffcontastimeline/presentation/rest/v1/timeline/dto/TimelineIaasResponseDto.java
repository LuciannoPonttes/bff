package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.timeline.dto;

public record TimelineIaasResponseDto(
    String idIntegracao,
    String fornecedor,
    String status
) {
}
