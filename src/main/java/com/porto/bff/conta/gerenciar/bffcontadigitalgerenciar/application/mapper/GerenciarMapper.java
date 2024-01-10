package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.mapper;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountFlagsResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario.SumarioResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.SPACE;

@Mapper(componentModel = "spring")
public interface GerenciarMapper {

    @Mapping(source = "data", target = "dados")
    DadosResponseDto<ContaResponseDto> paraDadosContaResponseDto(DataResponseIassPorto<AccountResponseIaasPorto> conta);

    String NOME_BANCO = "Porto Bank - Banco Itaucard S/A";
    String NUMERO_BANCO = "341";
    String TITULO_BLOQUEIO_BOLETO = "A sua conta está com bloqueio para pagar.";
    String TITULO_BLOQUEIO_PIX_CASHOUT = "A sua conta está bloqueada para enviar dinheiro.";
    String TITULO_BLOQUEIO_PIX_CASHIN = "A sua conta está bloqueada para receber dinheiro.";
    String TITULO_BLOQUEIO_PIX_CASHIN_AND_CASHOUT = "A sua conta está bloqueada para enviar e receber dinheiro.";
    String SUB_TITULO_BLOQUEIO = "Se tiver dúvidas, fale com a gente pelo chat.";

    @Mappings({
            @Mapping(source = "account.id", target = "id"),
            @Mapping(target = "contaBancaria.nomeBanco", constant = NOME_BANCO),
            @Mapping(target = "contaBancaria.codigoBanco", constant = NUMERO_BANCO),
            @Mapping(source = "account.bankAccount.branch", target = "contaBancaria.agencia"),
            @Mapping(source = "account.bankAccount.number", target = "contaBancaria.numeroConta"),
            @Mapping(source = "account.bankAccount.checkDigit", target = "contaBancaria.digitoConta"),
            @Mapping(source = "status", target = "statusConta"),
            @Mapping(source = "type", target = "tipo"),
            @Mapping(source = "createdAt", target = "criadoEm"),
            @Mapping(source = "updatedAt", target = "atualizadoEm")

    })
    ContaResponseDto paraContaResponseDto(AccountResponseIaasPorto account);

    @Mapping(source = "data", target = "dados")
    DadosResponseDto<SaldoResponseDto> paraDadosSaldoResponseDto(DataResponseIassPorto<BalanceResponseIaasPorto> balance);

    @Mappings({
            @Mapping(source = "accountId", target = "contaId"),
            @Mapping(source = "available", target = "disponivel", qualifiedByName = "mapSaldo"),
            @Mapping(source = "reserved", target = "reservada", qualifiedByName = "mapSaldo"),
            @Mapping(source = "blocked", target = "bloqueado", qualifiedByName = "mapSaldo")
    })
    SaldoResponseDto paraContaSaldoResponseDto(BalanceResponseIaasPorto balance);


    @Mapping(source = "data", target = "dados")
    DadosResponseDto<SumarioResponseDto> paraDadosSumarioResponseDto(DataResponseIassPorto<SumarioResponseIaasPorto> sumario);

    @Mappings({
            @Mapping(target = "nomeBanco", constant = NOME_BANCO),
            @Mapping(target = "codigoBanco", constant = NUMERO_BANCO),
            @Mapping(source = "sumario.account.data.bankAccount.branch", target = "agencia"),
            @Mapping(source = "sumario.account.data.bankAccount.number", target = "numeroConta"),
            @Mapping(source = "sumario.account.data.bankAccount.checkDigit", target = "digitoConta"),
            @Mapping(source = "sumario.account.data.status", target = "statusConta"),
            @Mapping(source = "sumario.account.data.flags", target = "bloqueios", qualifiedByName = "getBloqueiosConta"),
            @Mapping(source = "sumario.balance.data.available", target = "saldo", qualifiedByName = "mapSaldo"),
            @Mapping(source = "sumario.document", target = "documento"),
            @Mapping(source = "sumario.hasPortoCard", target = "possuiCartao"),
            @Mapping(source = "sumario.quantidadeChavePix", target = "quantidadeChavePix")
    })
    SumarioResponseDto paraContaSumarioResponseDto(SumarioResponseIaasPorto sumario);


    @Named("mapSaldo")
    default String mapSaldo(Double saldo) {
        var br = new Locale("pt", "BR");
        var formatoMoeda = NumberFormat.getCurrencyInstance(br);
        return formatoMoeda.format(saldo).replaceAll("\\p{Z}", SPACE);
    }

    @Named("getBloqueiosConta")
    default BloqueiosContaDto getBloqueiosConta(List<AccountFlagsResponseIaasPorto> flags) {
        if (flags == null || flags.isEmpty())
            return null;

        List<String> policies = flags.stream().map(AccountFlagsResponseIaasPorto::policyId).toList();
        boolean permiteCashIn = PoliticasBloqueioContaEnum.permiteCashIn(policies);
        boolean permiteCashOut = PoliticasBloqueioContaEnum.permiteCashOut(policies);

        return new BloqueiosContaDto(
                policies,
                permiteCashIn,
                permiteCashOut,
                !permiteCashOut ? TITULO_BLOQUEIO_BOLETO : "",
                !permiteCashOut && !permiteCashIn
                    ? TITULO_BLOQUEIO_PIX_CASHIN_AND_CASHOUT
                    : (!permiteCashIn ? TITULO_BLOQUEIO_PIX_CASHIN : TITULO_BLOQUEIO_PIX_CASHOUT),
                SUB_TITULO_BLOQUEIO);
    }
}
