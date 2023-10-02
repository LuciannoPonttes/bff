package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.decodertoken;

import com.porto.experiencia.cliente.cartoes.commons.domain.BusinessException;
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
                new BusinessException("CPF.getStatus()", "CPF.getCampo()", "CPF.getMensagem()"));
    }
}
