package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.pix;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class OwnerResponse {

    private String id;
    private String name;
    private DocumentResponse document;
    private AccountResponse account;
}
