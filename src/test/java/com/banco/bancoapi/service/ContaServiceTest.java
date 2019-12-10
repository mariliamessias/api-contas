package com.banco.bancoapi.service;

import com.banco.bancoapi.model.Conta;
import com.banco.bancoapi.repository.ContaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

public class ContaServiceTest {


    private ContaService contaService;

    private ContaRepository contaRepositoryMock;

    @Before
    public void setup() {
        contaRepositoryMock = Mockito.mock(ContaRepository.class);
        contaService = new ContaService(contaRepositoryMock);

    }

    @Test
    public void sacaValorContaTest() {
        BigDecimal valor = new BigDecimal("35.00");
        BigDecimal saldo = new BigDecimal("200.00");
        Long contaId = 1L;

        Conta contaMock = Conta.builder().id(contaId).numero(12345).saldo(saldo).build();

        Mockito.when(contaRepositoryMock.findById(contaId)).thenReturn(Optional.of(contaMock));
        Mockito.when(contaRepositoryMock.save(Mockito.any())).thenReturn(contaMock);

        Conta conta = contaService.saca(contaId, valor);
        Assertions.assertEquals(saldo.subtract(valor), conta.getSaldo());
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void sacaValorContaSemSaldoTest() {
        BigDecimal valor = new BigDecimal("35.00");
        Long contaId = 1L;

        Conta contaMock = Conta.builder().id(contaId).numero(12345).saldo(new BigDecimal("20.00")).build();

        Mockito.when(contaRepositoryMock.findById(contaId)).thenReturn(Optional.of(contaMock));

        contaService.saca(contaId, valor);
    }

}