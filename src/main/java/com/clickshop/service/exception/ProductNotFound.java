package com.clickshop.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFound extends NotFoundException{
    public ProductNotFound() {
        super("Produto não encontrado!");
    }
}
