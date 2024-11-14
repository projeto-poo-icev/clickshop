package com.clickshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickshop.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{
    
}
