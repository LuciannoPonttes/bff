package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta;


import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.domain.model.sumario.SumarioResponseIaasPorto;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.conta.client.ContaIaasPortoClient;
import com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.decodertoken.DecodificarAccessToken;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContaIassPortoAdapterImpl implements ContaIassPortoAdapter {

    private final ContaIaasPortoClient client;
    private final DecodificarAccessToken decodificador;

    @Override
    public DataResponseIassPorto<AccountResponseIaasPorto> getConta(String xItauAuth, String contaId) {
        try {
            return this.client.findByIdContaIaas(this.getBearerInput(xItauAuth), "IAAS", contaId, contaId);
        } catch (FeignException.FeignServerException | FeignException.FeignClientException exception) {
            if (exception.status() == HttpStatus.UNAUTHORIZED.value()) {
                throw new BusinessException(500, "IAAS_EXPIRATION_TOKEN", "AccessToken Inválido, gerar outro");
            }
            throw new BusinessException(exception.status(), "GET_CONTA_ERROR", exception.getLocalizedMessage());
        }
    }

    @Override
    public DataResponseIassPorto<BalanceResponseIaasPorto> getContaSaldo(String xItauAuth, String contaId) {
        try {
            return this.client.findBySaldoContaIaas(getBearerInput(xItauAuth), "IAAS", contaId, contaId);
        } catch (FeignException.FeignServerException | FeignException.FeignClientException exception) {
            if (exception.status() == HttpStatus.UNAUTHORIZED.value()) {
                throw new BusinessException(500, "IAAS_EXPIRATION_TOKEN", "AccessToken Inválido, gerar outro");
            }
            throw new BusinessException(exception.status(), "GET_SALDO_CONTA_ERROR", exception.getLocalizedMessage());
        }
    }

    @Override
    public DataResponseIassPorto<SumarioResponseIaasPorto> sumarioConta(String tokenCognito, String xItauAuth, String contaId) {
        try {
            DataResponseIassPorto<AccountResponseIaasPorto> dadosConta =
                    this.client.findByIdContaIaas(this.getBearerInput(xItauAuth), "IAAS", contaId, contaId);
            DataResponseIassPorto<BalanceResponseIaasPorto> saldoConta =
                    this.client.findBySaldoContaIaas(getBearerInput(xItauAuth), "IAAS", contaId, contaId);
            return new DataResponseIassPorto<>(new SumarioResponseIaasPorto(this.decodificador.getCpfPorToken(tokenCognito), dadosConta, saldoConta));
        } catch (FeignException.FeignServerException | FeignException.FeignClientException exception) {
            if (exception.status() == HttpStatus.UNAUTHORIZED.value()) {
                throw new BusinessException(500, "IAAS_EXPIRATION_TOKEN", "AccessToken Inválido, gerar outro");
            }
            throw new BusinessException(exception.status(), "GET_SALDO_CONTA_ERROR", exception.getLocalizedMessage());
        }
    }

    private String getBearerInput(String xItauAuth) {
        String prefixBearer = "Bearer ";
        if (xItauAuth.contains(prefixBearer)) {
            return xItauAuth;
        }
        return prefixBearer + xItauAuth;
    }

}
