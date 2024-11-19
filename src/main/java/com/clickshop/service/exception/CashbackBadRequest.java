package com.clickshop.service.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CashbackBadRequest extends BadRequestException{
    public CashbackBadRequest() {
        super("Saldo de cashback insuficiente");
    }
}
