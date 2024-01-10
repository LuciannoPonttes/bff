package com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.application.mapper;

import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.DataResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountFlagsResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.AccountResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.conta.BankAccountResponseIassPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.saldo.BalanceResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.domain.model.sumario.SumarioResponseIaasPorto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto.ContaResponseDto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto.DadosResponseDto;
import com.porto.bff.conta.gerenciar.bffcontadigitalgerenciar.presentation.rest.v1.contas.dto.SaldoResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GerenciarMapperTest {


    private GerenciarMapper mapper;
    private DataResponseIassPorto<AccountResponseIaasPorto> conta;
    private DataResponseIassPorto<BalanceResponseIaasPorto> balance;
    private DataResponseIassPorto<SumarioResponseIaasPorto> sumario;
    private DadosResponseDto<ContaResponseDto> contaDto;
    private DadosResponseDto<SaldoResponseDto> saldoDto;
    private List<AccountFlagsResponseIaasPorto> accountFlagsResponseIaasPorto;
    String NUMERO_BANCO = "341";

    @BeforeEach
    void setUp() {
        mapper = new GerenciarMapperImpl();
        accountFlagsResponseIaasPorto = List.of(
                new AccountFlagsResponseIaasPorto(
                        "123",
                        "c76e0255-17af45be-a5b6-6c57d58abf49",
                        "dataHora")
        );
        conta = new DataResponseIassPorto<>(new AccountResponseIaasPorto(
                "123",
                new BankAccountResponseIassPorto(
                        "banco",
                        "agencia",
                        "numeroConta",
                        "digitoConta"
                ),
                "status",
                "type",
                accountFlagsResponseIaasPorto,
                "criado",
                "atualizado"
        ));
        balance = new DataResponseIassPorto<>(
                new BalanceResponseIaasPorto(
                        "accountid",
                        10.5,
                        15.65,
                        134535460.78
                )
        );
        sumario = new DataResponseIassPorto<>(
                new SumarioResponseIaasPorto(
                        "0000000000",
                        this.conta,
                        this.balance,
                        false,
                        "Cadastre suas Chaves Pix"
                )
        );
        contaDto = this.mapper.paraDadosContaResponseDto(conta);
        saldoDto = this.mapper.paraDadosSaldoResponseDto(this.balance);
    }

    @Test
    void mapSaldo() {
        assertEquals("R$ 10,55", mapper.mapSaldo(10.55));
        assertEquals("R$ 10,50", mapper.mapSaldo(10.5));
        assertEquals("R$ 10,79", mapper.mapSaldo(10.79));
        assertEquals("R$ 10,55", mapper.mapSaldo(10.555));
        assertEquals("R$ 134.535.469,78", mapper.mapSaldo(134535469.78));
    }

    @Test
    void paraDadosContaResponseDto() {
        assertEquals(contaDto.dados().id(), conta.data().id());
        assertEquals(contaDto.dados().contaBancaria().numeroConta(), conta.data().bankAccount().number());
        assertEquals(contaDto.dados().contaBancaria().agencia(), conta.data().bankAccount().branch());
        assertEquals(contaDto.dados().contaBancaria().digitoConta(), conta.data().bankAccount().checkDigit());
        assertEquals(contaDto.dados().contaBancaria().codigoBanco(), NUMERO_BANCO);
        assertEquals(contaDto.dados().statusConta(), conta.data().status());
        assertEquals(contaDto.dados().tipo(), conta.data().type());
        assertEquals(contaDto.dados().criadoEm(), conta.data().createdAt());
        assertEquals(contaDto.dados().atualizadoEm(), conta.data().updatedAt());
    }

    @Test
    void paraDadosSaldoResponseDto() {
        assertEquals(saldoDto.dados().contaId(), balance.data().accountId());
        assertEquals(saldoDto.dados().disponivel(), "R$ 10,50");
        assertEquals(saldoDto.dados().bloqueado(), "R$ 134.535.460,78");
        assertEquals(saldoDto.dados().reservada(), "R$ 15,65");
    }

    @Test
    void paraDadosSumarioResponseDto() {
        var dto = this.mapper.paraDadosSumarioResponseDto(sumario);
        assertEquals(contaDto.dados().contaBancaria().agencia(), dto.dados().agencia());
        assertEquals(contaDto.dados().contaBancaria().numeroConta(), dto.dados().numeroConta());
        assertEquals(contaDto.dados().contaBancaria().codigoBanco(), dto.dados().codigoBanco());
        assertEquals(contaDto.dados().contaBancaria().digitoConta(), dto.dados().digitoConta());
        assertEquals(contaDto.dados().statusConta(), dto.dados().statusConta());
        Assertions.assertEquals(saldoDto.dados().disponivel(), dto.dados().saldo());
        Assertions.assertEquals(GerenciarMapper.NOME_BANCO, dto.dados().nomeBanco());
        assertEquals(sumario.data().document(), dto.dados().documento());
    }

    @Test
    void getBloqueiosConta() {
        var dto = mapper.getBloqueiosConta(accountFlagsResponseIaasPorto.stream().toList());
        assertNotNull(dto.politica());
        assertEquals(dto.politica().size(), accountFlagsResponseIaasPorto.size());
        assertFalse(dto.permiteCashOut());
        assertTrue(dto.permiteCashIn());
    }

    @ParameterizedTest()
    @NullSource
    @EmptySource
    void getBloqueiosContaSemFlagBloqueio(List<AccountFlagsResponseIaasPorto> politicas) {
        assertNull(mapper.getBloqueiosConta(politicas));
    }
}