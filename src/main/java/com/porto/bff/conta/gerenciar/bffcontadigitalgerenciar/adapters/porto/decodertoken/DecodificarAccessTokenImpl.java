package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.decodertoken;

import com.porto.experiencia.cliente.conta.digital.commons.domain.exception.BusinessException;
import com.porto.experiencia.cliente.conta.digital.commons.web.tokendecoder.AccessTokenDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


//import static com.porto.experiencia.cliente.cartoes.contestacao.common.enums.ErrosEnum.CPF;

@Component
@RequiredArgsConstructor
public class DecodificarAccessTokenImpl implements DecodificarAccessToken {

    private final AccessTokenDecoder decoder;

    @Override
    public String getCpfPorToken(String accessToken) {
//        return "11122233344";
        return decoder.getCpf(accessToken).orElseThrow(() ->
                new BusinessException(400, "ERRO_DECODIFICAR_DOCUMENTO", "Não foi possível decodificar o documento"));
    }
}
