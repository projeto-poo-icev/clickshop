package com.clickshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickshop.repositories.CouponRepository;
import com.clickshop.repositories.CustomerRepository;
import com.clickshop.repositories.ProductRepository;
import com.clickshop.repositories.SaleDetailsRepository;
import com.clickshop.repositories.SaleRepository;

@Service
public class ReportService {
    
    @Autowired
    SaleRepository saleRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SaleDetailsRepository saleDetailsRepository;

    @Autowired
    CouponRepository couponRepository;

    

}
