package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flags {
    List<Dados> dados;

    public List<String> buscaPoliticasPorContaId(String contaId) {
        return dados.stream()
                .filter(dados1 -> dados1.getContaId().equals(contaId)).findFirst()
                .map(Dados::getPoliticas)
                .orElse(null);
    }
}
