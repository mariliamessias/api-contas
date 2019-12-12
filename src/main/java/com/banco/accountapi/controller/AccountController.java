package com.banco.accountapi.controller;

import com.banco.accountapi.model.Account;
import com.banco.accountapi.model.AccountTransferDTO;
import com.banco.accountapi.model.Extract;
import com.banco.accountapi.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins = "*")
@Api(value="API REST Contas")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    @ApiOperation(value="Return lists of Accounts")
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @PostMapping("/account")
    @ApiOperation(value="Add a new Account")
    public Account saveAccount(@RequestBody Account account){
        return accountService.save(account);
    }

    @PostMapping("/account/{numberAccount}/withdraw/{value}")
    @ApiOperation(value="Cash out an Account")
    public Account withdraw(@PathVariable("numberAccount") Long numberAccount, @PathVariable("value") BigDecimal value ){
        return accountService.withdraw(numberAccount, value);
    }

    @PostMapping("/account/{numberAccount}/deposit/{value}")
    @ApiOperation(value="Cash in an Account")
    public Account deposit(@PathVariable("numberAccount") Long numberAccount, @PathVariable("value") BigDecimal value ){
        return accountService.deposit(numberAccount, value);
    }

    @PostMapping("/account/transfer")
    @ApiOperation(value="Transfer value beetwen Accounts")
    public List <Account> transfer(@RequestBody AccountTransferDTO accountTransf){
        return accountService.transfer(accountTransf);
    }

    @GetMapping("/account/{numberAccount}/extract")
    @ApiOperation(value="Get extract from an account")
    public List <Extract> extract(
            @PathVariable(value="numberAccount") Long numberAccount,
            @RequestParam(value="category", required = false) Integer category
            ){
       return accountService.extract(numberAccount, category);
    }

}
