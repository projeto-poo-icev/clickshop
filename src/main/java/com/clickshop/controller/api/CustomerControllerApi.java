package com.clickshop.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clickshop.dtos.CustomerCreationDto;
import com.clickshop.dtos.CustomerDto;
import com.clickshop.entities.Customer;
import com.clickshop.service.CustomerService;


@RestController
@RequestMapping
public class CustomerControllerApi {
    

    private final CustomerService customerService;


    public CustomerControllerApi(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/customer")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerCreationDto customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
    }

    
}
