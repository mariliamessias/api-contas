package com.banco.accountapi.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountTransferDTO {

    private long numberAccountOrigin;
    private long numberAccountDestiny;
    private BigDecimal valueTransf;

}
