package com.banco.bancoapi.service;

import com.banco.bancoapi.model.Conta;
import com.banco.bancoapi.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    @Autowired
    public ContaService(ContaRepository contaRepository){
        this.contaRepository = contaRepository;
    }

    public List<Conta> findAll(){
        return contaRepository.findAll();
    }

    public Conta salvar(Conta conta) {
        return contaRepository.save(conta);
    }
    public Conta deposita(Conta conta){
        return contaRepository.save(conta);
    }

    public Conta saca(Long id, BigDecimal valor) {
        Conta conta = contaRepository.findById(id).get();

        if(conta.getSaldo().compareTo(valor) < 0){
           throw new SaldoInsuficienteException("Saldo Insuficiente");
        }
        conta.setSaldo(conta.getSaldo().subtract(valor));
        contaRepository.save(conta);
        return conta;
    }
}
