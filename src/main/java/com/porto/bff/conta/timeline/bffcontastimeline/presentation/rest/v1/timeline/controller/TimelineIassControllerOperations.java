package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.timeline.controller;

import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception.ResponseErrorApi;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.timeline.dto.TimelineIaasResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequestMapping("v3/conta-digital/timeline")
public interface TimelineIassControllerOperations {

    @Operation(
            summary = "Confira como usar a API para ter acesso a toda a movimentação da conta para exibição ao cliente.",
            description = "Consulta transações do cliente/Conta.",
            tags = { "Timeline" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros obrigatórios não enviados", content =
                    {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = ResponseErrorApi.class))
                    }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    {
                            @Content(mediaType = "application/json", schema =
                            @Schema(implementation = ResponseErrorApi.class))
                    })
    })
    @GetMapping("/contas/{accountId}")
    ResponseEntity<TimelineIaasResponseDto> geraTimelineIaaS(@RequestHeader(AUTHORIZATION) String tokenCognito,
                                                             @RequestHeader("x-accountProvider") String xAccountId,
                                                             @RequestHeader("x-itau-auth") String xItauAuth,
                                                             @PathVariable("accountId") String accountId,
                                                             @RequestParam(required = false) Integer perPage,
                                                             @RequestParam(required = false) String afterId,
                                                             @RequestParam(required = false) String beginDate,
                                                             @RequestParam(required = false) String endDate);
}
