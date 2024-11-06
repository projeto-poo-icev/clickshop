package com.clickshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clickshop.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
