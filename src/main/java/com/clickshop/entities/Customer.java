package com.clickshop.entities;

import java.math.BigDecimal;
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
    
    @Column(name = "customer_level")
    private String customerLevel;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @OneToMany(mappedBy = "customer")
    private List<Sale> sales;


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

    public void setCashback(Double totalAmount) {
        this.cashback += totalAmount * 0.1;
    }

    public Double getAmountSpent() {
        return amountSpent;
    }
    
    public void setAmountSpent(BigDecimal saleValue, Double totalAmount) {
        this.amountSpent += saleValue.doubleValue();
        updateLevel();
        setCashback(totalAmount);
    }

    public String getCustomerLevel() {
        return customerLevel;
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

    public void addSale (Sale sale) {
        this.sales.add(sale);
        this.cashback -= sale.getCashback();
    }

    public List<Sale> getSales() {
        return this.sales;
    }

    public void updateLevel() {
        if (this.amountSpent > 1000 && this.amountSpent < 2000) {
            this.customerLevel = CustomerLevel.PRATA.toString();
        } else if (this.amountSpent >= 2000) {
            this.customerLevel = CustomerLevel.VIP.toString();
        }
    }

    public Double getDiscount() {
        if (this.customerLevel == CustomerLevel.BRONZE.toString()) {
            return 0.05;
        } else if (this.customerLevel == CustomerLevel.PRATA.toString()) {
            return 0.15;
        } 
        return 0.25;
        
    }


    
}
