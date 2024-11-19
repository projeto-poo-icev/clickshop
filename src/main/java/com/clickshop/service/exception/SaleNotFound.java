package com.clickshop.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SaleNotFound extends NotFoundException {
    public SaleNotFound() {
        super("Venda não encontrada!");
    }
}
