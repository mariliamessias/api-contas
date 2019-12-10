package com.banco.bancoapi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name="tb_conta")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int numero;
    private String descricao;
    private BigDecimal saldo;
}
