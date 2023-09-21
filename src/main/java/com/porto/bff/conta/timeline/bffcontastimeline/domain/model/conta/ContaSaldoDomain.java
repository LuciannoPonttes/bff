package com.porto.bff.conta.timeline.bffcontastimeline.domain.model.conta;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaSaldoDomain {

    private String accountId;
    private int available;
    private int reserved;
    private int blocked;
}
