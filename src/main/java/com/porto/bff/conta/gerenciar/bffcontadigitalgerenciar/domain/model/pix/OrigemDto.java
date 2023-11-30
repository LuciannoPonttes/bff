package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.pix;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrigemDto {

    private String tipoDocumento;
    private String documento;
    private String documentoMascarado;
    private String ispb;
    private String tipoConta;
    private String instituicao;
    private String agencia;
    private String conta;
    private String digitoVerificador;
    private String id;
    private String nome;
}
