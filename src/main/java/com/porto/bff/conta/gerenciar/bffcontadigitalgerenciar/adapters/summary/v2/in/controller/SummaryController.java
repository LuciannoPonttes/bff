package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.in.controller;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.in.controller.mapper.SummaryMapper;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.in.controller.response.dto.SummaryDtoResponse;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.ports.account.in.AccountDataInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SummaryController implements SummaryOperations{

    private final SummaryMapper mapper;
    private final AccountDataInputPort balanceInputPort;

    @Override
    public ResponseEntity<SummaryDtoResponse> getSummaryAccount(String cognitoToken, String xItauAuth, String accountId) {
        return ResponseEntity.ok(this.mapper.toSummaryDto(this.balanceInputPort.getSummaryAccount(cognitoToken, xItauAuth, accountId)));
    }
}
