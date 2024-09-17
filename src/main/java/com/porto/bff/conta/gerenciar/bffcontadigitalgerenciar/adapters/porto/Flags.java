package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto.Dados;
import lombok.*;

import java.util.List;

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
                .orElse(List.of());
    }
}
