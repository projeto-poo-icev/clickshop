package com.clickshop.entities;

import java.time.LocalDate;
import java.util.List;

import com.clickshop.utils.CustomerLevel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cpf;

    private Double cashback;

    private Double amountSpent;

    @OneToMany(mappedBy = "customer")
    private List<Sale> sales;
    
    @Column(name = "customer_level")
    private String customerLevel;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public Customer() {}
    
    public Customer(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
        this.cashback = 0d;
        this.amountSpent = 0d;
        this.customerLevel = CustomerLevel.BRONZE.toString();
        this.createdAt = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Double getCashback() {
        return cashback;
    }

    public void setCashback(Double saleValue) {
        this.cashback += saleValue * 0.1;
    }

    public Double getAmountSpent() {
        return amountSpent;
    }
    
    public void setAmountSpent(Double saleValue) {
        this.amountSpent += saleValue;
    }

    public String getCustomerLevel() {
        return customerLevel;
    }

    public void updateLevel() {
        if (this.amountSpent > 1000 && this.amountSpent < 2000) {
            this.customerLevel = CustomerLevel.PRATA.toString();
        } else if (this.amountSpent >= 2000) {
            this.customerLevel = CustomerLevel.OURO.toString();
        }
    }
    
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    
    


    
}
