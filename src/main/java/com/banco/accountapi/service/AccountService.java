package com.banco.accountapi.service;

import com.banco.accountapi.model.Account;
import com.banco.accountapi.model.AccountTransferDTO;
import com.banco.accountapi.model.Extract;
import com.banco.accountapi.repository.AccountRepository;
import com.banco.accountapi.repository.ExtractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final ExtractRepository extractRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, ExtractRepository extractRepository){
        this.accountRepository = accountRepository;
        this.extractRepository = extractRepository;
    }

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Account deposit(Long numberAccount, BigDecimal value){
        Account account = accountRepository.findById(numberAccount).get();
        account.setBalance(account.getBalance().add(value));

        Extract extract = new Extract(
                numberAccount,
                "Simple Deposit",
                "Deposit",
                numberAccount,
                value,
                LocalDateTime.now(),
                1
        );

        extractRepository.save(extract);
        return accountRepository.save(account);
    }

    public Account withdraw(Long numberAccount, BigDecimal value) {
        Account account = accountRepository.findById(numberAccount).get();

        if(account.getBalance().compareTo(value) < 0){
           throw new BalanceInsuficientException("Saldo Insuficiente");
        }
        account.setBalance(account.getBalance().subtract(value));

        Extract extract = new Extract(
                numberAccount,
                "Simple withdraw",
                "withdraw",
                numberAccount,
                value,
                LocalDateTime.now(),
                2
        );

        extractRepository.save(extract);

        accountRepository.save(account);
        return account;
    }

    public List<Account> transfer(AccountTransferDTO accountTransf){

        Account accountOrigem = accountRepository.findById(accountTransf.getNumberAccountOrigin()).get();
        Account accountDestino = accountRepository.findById(accountTransf.getNumberAccountDestiny()).get();

        if(accountOrigem.getBalance().compareTo(accountTransf.getValueTransf()) < 0){
            throw new BalanceInsuficientException("Saldo Insuficiente");
        }

        accountDestino.setBalance(accountDestino.getBalance().add(accountTransf.getValueTransf()));
        accountOrigem.setBalance(accountOrigem.getBalance().subtract(accountTransf.getValueTransf()));

        saveExtract(accountTransf);

        accountRepository.save(accountDestino);
        accountRepository.save(accountOrigem);

        return Arrays.asList(accountDestino, accountOrigem);
    }

    public List<Extract> extract(Long numberAccount, Integer category){
        return extractRepository.findParameters(numberAccount, category);
    }

    private void saveExtract (AccountTransferDTO accountTransf){

        //category 1 entrada - 2 saida

        Extract extractDestiny = new Extract(
                accountTransf.getNumberAccountDestiny(),
                "Transfer between two accounts",
                "transfer",
                accountTransf.getNumberAccountOrigin(),
                accountTransf.getValueTransf(),
                LocalDateTime.now(),
                1
        );

        Extract extractOrigin = new Extract(
                accountTransf.getNumberAccountOrigin(),
                "Transfer between two accounts",
                "transfer",
                accountTransf.getNumberAccountDestiny(),
                accountTransf.getValueTransf(),
                LocalDateTime.now(),
                2
        );

        extractRepository.save(extractDestiny);
        extractRepository.save(extractOrigin);

    }
}
