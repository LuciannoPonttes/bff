package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.in.controller;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.adapters.account.v2.out.client.mapper.AccountDataDtoResponseMapper;
import com.porto.experiencia.cliente.conta.digital.commons.web.model.ApiResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountDataController implements AccountDataOperations {

    @Override
    public ResponseEntity<ApiResponseData<AccountDataDtoResponseMapper>> getAccountData(String cognitoToken, String xItauAuth, String accountId) {
        String teste = "teste";
        return null;
        //return ResponseEntity.ok(this.mapper.toAccountDataDto(this.service.getAccountData(xItauAuth, accountId)));
    }
}
