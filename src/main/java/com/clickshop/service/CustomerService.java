package com.clickshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickshop.dtos.CustomerCreationDto;
import com.clickshop.dtos.CustomerDto;
import com.clickshop.dtos.Utils;
import com.clickshop.entities.Customer;
import com.clickshop.repositories.CustomerRepository;
import com.clickshop.service.exception.CustomerNotFound;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDto createCustomer(CustomerCreationDto newCustomer) {
        Customer customer = new Customer(newCustomer.name());
        Customer createdCustomer = customerRepository.save(customer);
        return Utils.CustomerModelToDto(createdCustomer);
    }

    public List<CustomerDto> findAll() {
        List<Customer> customerList = customerRepository.findAll();
        return Utils.CustomerModelListToDtoList(customerList);
    }

    public CustomerDto findById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFound::new);
        return Utils.CustomerModelToDto(customer);
    }

    public List<CustomerDto> findAllByLevel(String level) {
        List<Customer> customers = customerRepository.findByCustomerLevel(level);
        return Utils.CustomerModelListToDtoList(customers);
    }


    // criar serviço com relatorio geral da tabela de customer como quantidade, cashback medio, quantidade por fidelidade.

}
