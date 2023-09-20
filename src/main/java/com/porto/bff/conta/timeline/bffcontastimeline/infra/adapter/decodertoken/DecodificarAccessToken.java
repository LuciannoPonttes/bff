package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.decodertoken;

public interface DecodificarAccessToken {
    String getCpfPorToken(String accessToken);
}
