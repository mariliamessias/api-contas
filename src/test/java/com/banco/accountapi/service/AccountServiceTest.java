package com.banco.accountapi.service;

import com.banco.accountapi.model.Account;
import com.banco.accountapi.repository.AccountRepository;
import com.banco.accountapi.repository.ExtractRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

public class AccountServiceTest {


    private AccountService contaService;

    private AccountRepository accountRepositoryMock;
    private ExtractRepository extractRepositoryMock;

    @Before
    public void setup() {
        accountRepositoryMock = Mockito.mock(AccountRepository.class);
        contaService = new AccountService(accountRepositoryMock, extractRepositoryMock);

    }

    @Test
    public void sacaValorContaTest() {
        BigDecimal valor = new BigDecimal("35.00");
        BigDecimal saldo = new BigDecimal("200.00");
        Long contaId = 1L;

        Account accountMock = Account.builder().numberAccount(contaId).balance(saldo).build();

        Mockito.when(accountRepositoryMock.findById(contaId)).thenReturn(Optional.of(accountMock));
        Mockito.when(accountRepositoryMock.save(Mockito.any())).thenReturn(accountMock);

        Account account = contaService.withdraw(contaId, valor);
        Assertions.assertEquals(saldo.subtract(valor), account.getBalance());
    }

    @Test(expected = BalanceInsuficientException.class)
    public void sacaValorContaSemSaldoTest() {
        BigDecimal valor = new BigDecimal("35.00");
        Long contaId = 1L;

        Account accountMock = Account.builder().numberAccount(contaId).balance(new BigDecimal("20.00")).build();

        Mockito.when(accountRepositoryMock.findById(contaId)).thenReturn(Optional.of(accountMock));

        contaService.withdraw(contaId, valor);
    }

}