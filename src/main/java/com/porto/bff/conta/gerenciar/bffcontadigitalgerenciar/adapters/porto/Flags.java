package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flags {
    List<AccountData> dados;

    public List<String> buscaPoliticasPorContaId(String contaId) {
        return dados.stream()
                .filter(dados1 -> dados1.getContaId().equals(contaId)).findFirst()
                .map(AccountData::getPoliticas)
                .orElse(List.of());
    }
}
