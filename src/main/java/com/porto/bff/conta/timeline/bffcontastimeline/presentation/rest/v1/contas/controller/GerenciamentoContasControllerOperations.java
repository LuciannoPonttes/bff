package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.controller;

import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.contas.dto.*;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.exception.ResponseErrorApi;
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
                                                                   @RequestHeader("x-itau-auth") String xItauAuth,
                                                                   @RequestHeader("x-account-id") String xAccountId,
                                                                   @PathVariable("contaId") String contaId,
                                                                   @RequestParam(required = false) String campos);

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
    @GetMapping("/conta/{contaId}/saldo")
    ResponseEntity<DadosResponseDto<ContaSaldoResponseDto>> consultarSaldoConta(@RequestHeader(AUTHORIZATION) String tokenCognito,
                                                                                @RequestHeader("x-itau-auth") String xItauAuth,
                                                                                @RequestHeader("x-account-id") String xAccountId,
                                                                                @PathVariable("contaId") String contaId);









    @Operation(
            summary = "Consulta do sumario",
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
    @GetMapping("/conta/{contaId}/sumario")
    ResponseEntity<DadosResponseDto<ContaSumarioResponseDto>> sumarioConta(@RequestHeader(AUTHORIZATION) String tokenCognito,
                                                                                @RequestHeader("x-itau-auth") String xItauAuth,
                                                                                @PathVariable("contaId") String contaId);





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
    ResponseEntity<Void> ediatarStatusConta(@RequestHeader(AUTHORIZATION) String tokenCognito,
                                                               @RequestHeader("x-itau-auth") String xItauAuth,
                                                               @RequestHeader("x-account-id") String xAccountId,
                                                               @RequestHeader("x-external-id") String xExternalId,
                                                               @PathVariable("contaId") String contaId,
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
                                     @RequestHeader("x-itau-auth") String xItauAuth,
                                     @RequestHeader("x-account-id") String xAccountId,
                                     @RequestHeader("x-external-id") String xExternalId,
                                     @PathVariable("contaId") String contaId);
}
