package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.out.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListCardsResponseBody {
  private String estado;
  private String bandeira;
  private String descricaoFormatada;
  private String finalCartao;
  private String flagTitular;
  private String melhorDataCompra;
  private String cartaoTipo;
  private String cartaoLogoCode;
  private String corPlastico;
  private String statusBloqueio;
}

