package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.decodertoken.out;

public interface DecodificarAccessTokenOutPutPort {
    String getCpfPorToken(String accessToken);
}
