package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.cartoes;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListarCartoesResponseBodyDomain {
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

