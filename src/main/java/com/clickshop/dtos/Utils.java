package com.clickshop.dtos;

import com.clickshop.entities.Customer;

public class Utils {
    public static CustomerDto CustomerModelToDto(Customer customer) {
        return new CustomerDto(
            customer.getId(),
            customer.getName(),
            customer.getCashback(),
            customer.getAmountSpent(),
            customer.getCustomerLevel(),
            customer.getCreatedAt()
        );
    }
}
