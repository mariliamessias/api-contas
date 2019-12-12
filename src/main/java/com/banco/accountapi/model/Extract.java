package com.banco.accountapi.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Table(name="tb_extrato")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long numberAccount;
    private String desctiption;
    private String typeTransaction;
    private long origin;
    private int category;
    private BigDecimal value;
    private LocalDateTime dateTransaction;

    public Extract(long numberAccount, String description, String typeTransaction, long origin, BigDecimal value, LocalDateTime dataTransaction, Integer category ){
        this.numberAccount = numberAccount;
        this.desctiption = description;
        this.typeTransaction = typeTransaction;
        this.value = value;
        this.origin = origin;
        this.dateTransaction = dataTransaction;
        this.category = category;
    }
}
