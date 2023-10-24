package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListarCartoesResponseDomain {
  private String codigoProduto;
  private ClienteDomain cliente;
  private List<ListarCartoesResponseBodyDomain> lista;
}
