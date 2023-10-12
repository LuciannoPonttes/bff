package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.infra.adapter.decodertoken;

public interface DecodificarAccessToken {
    String getCpfPorToken(String accessToken);
}
