package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta;


import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.client.ContaIaasPortoClient;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.decodertoken.DecodificarAccessToken;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.*;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ContaIassPortoAdapterImpl implements ContaIassPortoAdapter {

    private final ContaIaasPortoClient client;
    private final DecodificarAccessToken decodificador;

    @Value("${feign.client.config.porto.gerenciar.contas.saldo.endpoint}")
    private String contaSaldoFindIdPortoUrl;

    @Override
    public DadosResponseDto<ContaResponseDto> getConta(String xItauAuth, String contaId) {
        try {
            var responseIaas = this.client.findByIdContaIaas(this.getBearerInput(xItauAuth), "IAAS", contaId, contaId);
            return this.converteRespostaContaIaas(responseIaas);
        } catch (FeignException.FeignServerException | FeignException.FeignClientException exception) {
            if (exception.status() == HttpStatus.UNAUTHORIZED.value()) {
                throw new BusinessException(500, "IAAS_EXPIRATION_TOKEN", "AccessToken Inválido, gerar outro");
            }
            throw new BusinessException(exception.status(), "GET_CONTA_ERROR", exception.getLocalizedMessage());
        }
    }

    @Override
    public DadosResponseDto<ContaSaldoResponseDto> getContaSaldo(String xItauAuth, String contaId) {
        try {
            var responseIaas = this.client.findBySaldoContaIaas(getBearerInput(xItauAuth), "IAAS", contaId, contaId);
            return converteRespostaContaSaldoIaas(responseIaas);
        } catch (FeignException.FeignServerException | FeignException.FeignClientException exception) {
            if (exception.status() == HttpStatus.UNAUTHORIZED.value()) {
                throw new BusinessException(500, "IAAS_EXPIRATION_TOKEN", "AccessToken Inválido, gerar outro");
            }
            throw new BusinessException(exception.status(), "GET_SALDO_CONTA_ERROR", exception.getLocalizedMessage());
        }
    }

    @Override
    public DadosResponseDto<ContaSumarioResponseDto> sumarioConta(String tokenCognito, String xItauAuth, String contaId) {
        try {
            DataResponseIassPorto<AccountResponseIaasPorto> dadosConta =
                    this.client.findByIdContaIaas(this.getBearerInput(xItauAuth), "IAAS", contaId, contaId);
            DataResponseIassPorto<BalanceResponseIaasPorto> saldoConta =
                    this.client.findBySaldoContaIaas(getBearerInput(xItauAuth), "IAAS", contaId, contaId);
            return this.getSumarioContaConverte(tokenCognito, dadosConta, saldoConta);
        } catch (FeignException.FeignServerException | FeignException.FeignClientException exception) {
            if (exception.status() == HttpStatus.UNAUTHORIZED.value()) {
                throw new BusinessException(500, "IAAS_EXPIRATION_TOKEN", "AccessToken Inválido, gerar outro");
            }
            throw new BusinessException(exception.status(), "GET_SALDO_CONTA_ERROR", exception.getLocalizedMessage());
        }
    }

    private DadosResponseDto<ContaSumarioResponseDto> getSumarioContaConverte(String tokenCognito,
                                                                              DataResponseIassPorto<AccountResponseIaasPorto> conta,
                                                                              DataResponseIassPorto<BalanceResponseIaasPorto> saldo) {
        ContaSumarioResponseDto contaSumarioResponseDto = new ContaSumarioResponseDto(
                null, // TODO: 03/10/2023 Informação não encontrada
                conta.data().bankAccount().bank(),
                conta.data().bankAccount().branch(),
                conta.data().bankAccount().number(),
                conta.data().bankAccount().checkDigit(),
                String.valueOf(saldo.data().available()),
                conta.data().status(),
                this.decodificador.getCpfPorToken(tokenCognito)
        );
        return new DadosResponseDto<>(contaSumarioResponseDto);
    }

    private DadosResponseDto<ContaSaldoResponseDto> converteRespostaContaSaldoIaas(DataResponseIassPorto<BalanceResponseIaasPorto> balance) {
        var contaSaldoDto = new ContaSaldoResponseDto(
                balance.data().accountId(),
                balance.data().available(),
                balance.data().reserved(),
                balance.data().blocked());
        return new DadosResponseDto<>(contaSaldoDto);
    }

    private DadosResponseDto<ContaResponseDto> converteRespostaContaIaas(DataResponseIassPorto<AccountResponseIaasPorto> porto) {
        var contaBancariaDto = new ContaBancariaDto(
                porto.data().bankAccount().bank(),
                porto.data().bankAccount().branch(),
                porto.data().bankAccount().number(),
                porto.data().bankAccount().checkDigit()
        );
        var contaResponseDto = new ContaResponseDto(
                porto.data().id(),
                contaBancariaDto,
                porto.data().status(),
                porto.data().type(),
                porto.data().createdAt(),
                porto.data().updatedAt()
        );

        return new DadosResponseDto<>(contaResponseDto);
    }

    private String getBearerInput(String xItauAuth) {
        String prefixBearer = "Bearer ";
        if (xItauAuth.contains(prefixBearer)) {
            return xItauAuth;
        }
        return prefixBearer + xItauAuth;
    }

    private List<String> getMensagenError() {
        List<String> mensagen = new ArrayList<>();
        mensagen.add("Falha ao gerar accessToken");
        return mensagen;
    }

}
