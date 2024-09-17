package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.porto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dados {
    String contaId;
    List<String> politicas;
}
