package com.clickshop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickshop.dtos.ProductCreationDto;
import com.clickshop.dtos.ProductDto;
import com.clickshop.service.ProductService;

@RestController
@RequestMapping
public class ProductControllerApi {
    
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/prodcut")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductCreationDto product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    @GetMapping(value = "/product")
    public ResponseEntity<List<ProductDto>> findall() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }
}
