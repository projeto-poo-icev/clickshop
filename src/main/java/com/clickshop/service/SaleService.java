package com.clickshop.service;

import java.util.List;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickshop.dtos.SaleCreationDto;
import com.clickshop.dtos.SaleDto;
import com.clickshop.dtos.SaleProductListDto;
import com.clickshop.dtos.Utils;
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
import com.clickshop.service.exception.SaleNotFound;
import com.clickshop.utils.OrderStatus;

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



    public SaleDto createSale(SaleCreationDto saleDto) throws BadRequestException {
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
                Product productItem = productRepository.findById(product.productId())
                .orElseThrow(() -> new ProductNotFound());
                if (productItem.getQuantity() < product.quantity()) {
                    throw new IllegalArgumentException("Estoque insuficiente");
                }
                return new SaleDetails(
                    product.quantity(),
                    productItem
                );
            })
            .toList();
    
        
        Double totalAmount = saleDetailsList.stream()
                                            .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                                            .sum();
    
        
        Sale newSale = new Sale(saleDto.paymentMethod(), saleDto.cashback(), customer, totalAmount);
        Sale sale = saleRepository.save(newSale);
    
        
        saleDetailsList.forEach(item -> item.setSale(sale));
        saleDetailsList.forEach(item -> item.getProduct().sell(item.getQuantity()));
        saleDetailsList.forEach(item -> productRepository.save(item.getProduct()));

        customer.addSale(sale);
        customer.setAmountSpent(sale.getAmountPaid(), totalAmount);
        customerRepository.save(customer);
       
        List<SaleDetails> saleDetails = saleDetailsRepository.saveAll(saleDetailsList);
        sale.setSaleDetails(saleDetails);
        saleRepository.save(sale);
        

        return Utils.saleModelToDto(sale);
    }

    public SaleDto updateSaleStatus(Long id) {
        Sale sale = saleRepository.findById(id).orElseThrow(SaleNotFound::new);
        if(sale.getStatus() == OrderStatus.ENTREGUE.toString()) {
            throw new IllegalArgumentException("O produto ja foi entregue!");
        }
        sale.updateStatus();
        Sale updatedSale = saleRepository.save(sale);

        return Utils.saleModelToDto(updatedSale);
    }
    
}

/*
 * Long userId,
    String paymentMethod,
    Double cashback,
    List<SaleProductListDto> productList
 */