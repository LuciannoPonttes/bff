package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta;

import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaSaldoResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaSumarioResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;

public interface ContaIassPortoAdapter {

    DadosResponseDto<ContaResponseDto> getConta(String xItauAuth, String contaId);

    DadosResponseDto<ContaSaldoResponseDto> getContaSaldo(String xItauAuth, String contaId);

    DadosResponseDto<ContaSumarioResponseDto> sumarioConta(String tokenCognito, String xItauAuth, String contaId);
}
