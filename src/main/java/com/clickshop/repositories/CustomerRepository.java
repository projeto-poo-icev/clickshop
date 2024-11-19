package com.clickshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clickshop.entities.Customer;
import java.util.List;
import java.util.Optional;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByCustomerLevel(String customerLevel);
    Optional<Customer> findByCpf(String cpf);
}
