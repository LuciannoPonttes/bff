package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortoCardResponseData {
  private String codigoProduto;
  private CustomerResponse cliente;
  private List<ListCardsResponseBody> lista;
}
