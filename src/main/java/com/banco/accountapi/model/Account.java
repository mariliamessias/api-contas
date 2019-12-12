package com.banco.accountapi.model;

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
public class Account implements Serializable {

    @Id
    private long numberAccount;
    private String description;
    private String type;
    private BigDecimal balance;
}
