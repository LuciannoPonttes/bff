package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDomain {
  private String email;
  private String nome;
}

