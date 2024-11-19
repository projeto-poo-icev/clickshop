package com.clickshop.controller.api;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clickshop.dtos.SaleCreationDto;
import com.clickshop.dtos.SaleDto;
import com.clickshop.dtos.SaleProductListDto;
import com.clickshop.service.SaleService;
import com.clickshop.utils.PaymentsMethods;

@RestController
@RequestMapping
public class SaleControllerApi {
    @Autowired
    public SaleService saleService;

    @PostMapping(value = "/createSale")
    public ResponseEntity<SaleDto> createSale(@RequestBody SaleCreationDto saleDto) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.createSale(saleDto));
    }

    @PostMapping(value = "/updateSale/{id}") 
    public ResponseEntity<SaleDto> updateSale(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(saleService.updateSaleStatus(id));
    }

    @GetMapping(value = "/paymentMethods")
    public ResponseEntity<List<PaymentsMethods>> getPaymentsMethods() {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.getPaymentsMethods());
    }
}
