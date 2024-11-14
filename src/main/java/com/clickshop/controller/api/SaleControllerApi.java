package com.clickshop.controller.api;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickshop.dtos.SaleCreationDto;
import com.clickshop.dtos.SaleProductListDto;
import com.clickshop.service.SaleService;

@RestController
@RequestMapping
public class SaleControllerApi {
    @Autowired
    public SaleService saleService;

    @PostMapping(value = "/createSale")
    public ResponseEntity<String> createSale(@RequestBody SaleCreationDto saleDto) throws BadRequestException {
        saleService.createSale(saleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Venda criada");
    }
}
