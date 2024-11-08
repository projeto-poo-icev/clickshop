package com.clickshop.dtos;

import java.util.List;

import com.clickshop.entities.Customer;

public class Utils {
    public static CustomerDto CustomerModelToDto(Customer customer) {
        return new CustomerDto(
            customer.getId(),
            customer.getName(),
            customer.getCpf(),
            customer.getCashback(),
            customer.getAmountSpent(),
            customer.getCustomerLevel(),
            customer.getCreatedAt()
        );
    }

    public static List<CustomerDto> CustomerModelListToDtoList(List<Customer> customerList) {
        return customerList.stream().map((customer) -> CustomerModelToDto(customer)).toList();
    }
}
