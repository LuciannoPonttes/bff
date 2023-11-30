package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.pix;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KeyPixSearchWithClaimDto {
    private OrigemDto origem;
    private String doador;
    private String statusProcessoPix;
    private String icone;
    private String statusOperacao;
    private String idOperacao;
}
