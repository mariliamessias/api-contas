package com.banco.bancoapi.config;

import com.banco.bancoapi.service.SaldoInsuficienteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResultError saldoInsuficiente(SaldoInsuficienteException e) {
        return ResultError.builder().message(e.getMessage()).build();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResultError saldoInsuficiente(Exception e) {
        return ResultError.builder().message("Erro interno do Sistema").build();
    }

}
