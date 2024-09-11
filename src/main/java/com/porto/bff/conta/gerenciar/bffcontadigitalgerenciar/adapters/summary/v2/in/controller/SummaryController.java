package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.in.controller;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.in.controller.mapper.SummaryMapper;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.summary.v2.in.controller.response.dto.SummaryDtoResponse;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SummaryController implements SummaryOperations{

    private final SummaryMapper mapper;

    @Override
    public ResponseEntity<ApiResponseData<SummaryDtoResponse>> getSummaryAccount(String cognitoToken, String xItauAuth, String accountId) {
        return null;
        //return ResponseEntity.ok(this.mapper.toSummaryDto(this.service.getSummaryAccount(cognitoToken, xItauAuth, accountId)));
    }
}
