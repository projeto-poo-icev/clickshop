package com.clickshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickshop.dtos.ProductCreationDto;
import com.clickshop.dtos.ProductDto;
import com.clickshop.dtos.Utils;
import com.clickshop.entities.Product;
import com.clickshop.repositories.ProductRepository;
import com.clickshop.service.exception.ProductNotFound;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public ProductDto createProduct(ProductCreationDto product) {
        Product newProduct = new Product(product.description(), product.price(), product.quantity());

        Product createdProduct = productRepository.save(newProduct);

        return Utils.ProductModelToDto(createdProduct);
    }

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFound::new);
        return Utils.ProductModelToDto(product);
    }

    public List<ProductDto> findAll() {
        List<Product> productList = productRepository.findAll();
        return Utils.ProductModelListToDto(productList);
    }
}
