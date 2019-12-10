package com.banco.bancoapi.repository;

import com.banco.bancoapi.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
