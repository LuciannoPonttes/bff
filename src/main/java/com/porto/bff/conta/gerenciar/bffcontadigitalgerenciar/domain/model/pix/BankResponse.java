package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.pix;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankResponse {

    private String type;
    private String branch;
    private String number;
    private String checkDigit;
    private String participant;
    private String participantName;
}
