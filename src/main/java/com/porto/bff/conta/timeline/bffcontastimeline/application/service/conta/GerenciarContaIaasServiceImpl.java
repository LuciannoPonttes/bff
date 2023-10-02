package com.porto.bff.conta.timeline.bffcontastimeline.application.service.conta;

import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.ContaIassPortoAdapter;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaSaldoResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaSumarioResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GerenciarContaIaasServiceImpl implements GerenciarContaIaasService {

    private final ContaIassPortoAdapter adapter;

    @Override
    public DadosResponseDto<ContaResponseDto> getConta(String xItauAuth, String contaId) {
        isValidHeader(xItauAuth, contaId);
        return adapter.getConta(xItauAuth, contaId);
    }

    @Override
    public DadosResponseDto<ContaSaldoResponseDto> getContaSaldo(String xItauAuth, String contaId) {
        isValidHeader(xItauAuth, contaId);
        return adapter.getContaSaldo(xItauAuth, contaId);
    }

    @Override
    public DadosResponseDto<ContaSumarioResponseDto> contaSumario(String tokenCognito, String xItauAuth, String contaId) {
        return adapter.sumarioConta(tokenCognito, xItauAuth, contaId);
    }

    private void isValidHeader(String xItauAuth, String contaId) {
        if (StringUtils.isEmpty(xItauAuth) || StringUtils.isEmpty(contaId)) {
            throw new BusinessException(500, "IAAS_EXPIRATION_TOKEN", "AccessToken Inválido, gerar outro.");
        }
    }
}
