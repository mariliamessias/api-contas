package com.banco.bancoapi.controller;

import com.banco.bancoapi.model.Conta;
import com.banco.bancoapi.service.ContaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins = "*")
@Api(value="API REST Contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping("/contas")
    @ApiOperation(value="Retorna uma lista de contas")
    public List<Conta> findAll(){
        return contaService.findAll();
    }

    @PostMapping("/conta")
    @ApiOperation(value="Adiciona uma conta")
    public Conta salvarConta(@RequestBody Conta conta){
        return contaService.salvar(conta);
    }

    @PostMapping("/conta/{id}/saca/{valor}")
    @ApiOperation(value="Realiza um deposito em uma conta")
    public Conta saca(@PathVariable("id") Long id, @PathVariable("valor") BigDecimal valor ){
        return contaService.saca(id, valor);
    }

}
