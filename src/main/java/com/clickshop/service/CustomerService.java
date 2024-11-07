package com.clickshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickshop.dtos.CustomerCreationDto;
import com.clickshop.dtos.CustomerDto;
import com.clickshop.dtos.Utils;
import com.clickshop.entities.Customer;
import com.clickshop.repositories.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    private CustomerDto createCustomer(CustomerCreationDto newCustomer) {
        Customer customer = new Customer(newCustomer.name());
        Customer createdCustomer = customerRepository.save(customer);
        return Utils.CustomerModelToDto(createdCustomer);
    }

    private List<CustomerDto> findAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        return Utils.CustomerModelListToDtoList(customerList);
    }

    
}
