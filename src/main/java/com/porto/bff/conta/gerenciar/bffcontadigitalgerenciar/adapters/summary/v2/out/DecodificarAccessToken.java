package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.decodertoken.out.DecodificarAccessTokenOutPutPort;
import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import com.porto.experiencia.cliente.conta.digital.commons.web.tokendecoder.AccessTokenDecoder;
import org.springframework.stereotype.Component;


//import static com.porto.experiencia.cliente.cartoes.contestacao.common.enums.ErrosEnum.CPF;

@Component
public class DecodificarAccessToken implements DecodificarAccessTokenOutPutPort {

    public DecodificarAccessToken(AccessTokenDecoder decoder) {
        this.decoder = decoder;
    }

    private final AccessTokenDecoder decoder;

    @Override
    public String getCpfPorToken(String accessToken) {
//        return "11122233344";
        return decoder.getCpf(accessToken).orElseThrow(() ->
                new BusinessException(400, "ERRO_DECODIFICAR_DOCUMENTO", "Não foi possível decodificar o documento"));
    }
}
