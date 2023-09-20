package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.controller;

import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaEditadaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaRequestDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.DadosResponseDto;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception.ResponseErrorApi;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.timeline.dto.TimelineIaasResponseDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.porto.bff.conta.timeline.bffcontastimeline.common.utils.ApiDocsConstants.API_DOC_DESCRICAO;
import static com.porto.bff.conta.timeline.bffcontastimeline.common.utils.ApiDocsConstants.API_DOC_TITLE;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;



@RequestMapping("v3/conta-digital/gerenciamento")
@OpenAPIDefinition(
        info = @Info(
                title = API_DOC_TITLE,
                description = API_DOC_DESCRICAO
        )
)
public interface GerenciamentoContasControllerOperations {


    @Operation(
            summary = "Consulta de dados da conta",
            description = "para acessar tem que ter o escopo tipo iaas-accounts.read",
            tags = { "Gerenciar" }
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
    @GetMapping("/conta/{contaId}")
    ResponseEntity<DadosResponseDto<ContaResponseDto>> buscarConta(@RequestHeader(AUTHORIZATION) String tokenCognito,
                                                                   @RequestHeader("x-accountProvider") String xAccountProvider,
                                                                   @RequestHeader("x-itau-auth") String xItauAuth,
                                                                   @RequestHeader("x-account-id") String xAccountId,
                                                                   @PathVariable("accountId") String contaId,
                                                                   @RequestParam(required = false) String campos);


    @Operation(
            summary = "Atualização de status da conta",
            description = "para acessar tem que ter o escopo tipo iaas-accounts.write",
            tags = { "Gerenciar" }
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
    @PatchMapping("/conta/{contaId}")
    ResponseEntity<ContaEditadaResponseDto> ediatarStatusConta(@RequestHeader(AUTHORIZATION) String tokenCognito,
                                                               @RequestHeader("x-accountProvider") String xAccountProvider,
                                                               @RequestHeader("x-itau-auth") String xItauAuth,
                                                               @RequestHeader("x-account-id") String xAccountId,
                                                               @PathVariable("accountId") String contaId,
                                                               @RequestBody ContaRequestDto requestDto);


    @Operation(
            summary = "Encerramento da conta",
            description = "para acessar tem que ter o escopo tipo iaas-accounts.write",
            tags = { "Gerenciar" }
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
    @DeleteMapping("/conta/{contaId}")
    ResponseEntity<Void> apagarConta(@RequestHeader(AUTHORIZATION) String tokenCognito,
                                                        @RequestHeader("x-accountProvider") String xAccountProvider,
                                                        @RequestHeader("x-account-id") String xAccountId,
                                                        @RequestHeader("x-itau-auth") String xItauAuth,
                                                        @RequestHeader("x-external-id") String xExternalId,
                                                        @PathVariable("accountId") String contaId);


    @Operation(
            summary = "Consulta de saldo",
            description = "para acessar tem que ter o escopo tipo iaas-accounts.read",
            tags = { "Gerenciar" }
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
    @GetMapping("/conta/{accountId}/saldo")
    ResponseEntity<ContaEditadaResponseDto> consultarSaldoConta(@RequestHeader(AUTHORIZATION) String tokenCognito,
                                                                @RequestHeader("x-accountProvider") String xAccountProvider,
                                                                @RequestHeader("x-itau-auth") String xItauAuth,
                                                                @RequestHeader("x-account-id") String xAccountId,
                                                                @PathVariable("accountId") String contaId);
}
