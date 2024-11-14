package com.clickshop.service;

import java.util.List;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickshop.dtos.SaleCreationDto;
import com.clickshop.dtos.SaleProductListDto;
import com.clickshop.entities.Customer;
import com.clickshop.entities.Product;
import com.clickshop.entities.Sale;
import com.clickshop.entities.SaleDetails;
import com.clickshop.repositories.CustomerRepository;
import com.clickshop.repositories.ProductRepository;
import com.clickshop.repositories.SaleDetailsRepository;
import com.clickshop.repositories.SaleRepository;
import com.clickshop.service.exception.CustomerNotFound;
import com.clickshop.service.exception.ProductNotFound;

@Service
public class SaleService {
    
    @Autowired
    SaleRepository saleRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SaleDetailsRepository saleDetailsRepository;



    public void createSale(SaleCreationDto saleDto) throws BadRequestException {
        System.out.println(saleDto);
        
        if (saleDto.userId() == null) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
        }
    
        Customer customer = customerRepository.findById(saleDto.userId())
                                              .orElseThrow(CustomerNotFound::new);
    
        if (saleDto.cashback() > customer.getCashback()) {
            throw new BadRequestException("Saldo de cashback insuficiente");
        }
    
        
        List<SaleDetails> saleDetailsList = saleDto.productList().stream()
            .map(product -> {
                if (product.productId() == null) {
                    throw new IllegalArgumentException("O ID do produto não pode ser nulo.");
                }
                return new SaleDetails(
                    product.quantity(),
                    productRepository.findById(product.productId())
                                     .orElseThrow(() -> new ProductNotFound())
                );
            })
            .toList();
    
        
        Double totalAmount = saleDetailsList.stream()
                                            .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                                            .sum();
    
        
        Sale newSale = new Sale(saleDto.paymentMethod(), saleDto.cashback(), customer, totalAmount);
        Sale sale = saleRepository.save(newSale);
    
        
        saleDetailsList.forEach(item -> item.setSale(sale));
        saleDetailsRepository.saveAll(saleDetailsList);
    }
    
}

/*
 * Long userId,
    String paymentMethod,
    Double cashback,
    List<SaleProductListDto> productList
 */