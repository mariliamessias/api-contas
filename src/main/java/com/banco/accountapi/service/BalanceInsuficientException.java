package com.banco.accountapi.service;

public class BalanceInsuficientException extends RuntimeException {
    public BalanceInsuficientException(String message) {
        super(message);
    }
}
