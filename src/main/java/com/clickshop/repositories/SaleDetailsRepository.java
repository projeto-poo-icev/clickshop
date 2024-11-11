package com.clickshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickshop.entities.SaleDetails;

public interface SaleDetailsRepository extends JpaRepository<SaleDetails, Long> {
    
}
