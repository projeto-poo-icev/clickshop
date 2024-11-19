package com.clickshop.entities;

import java.math.BigDecimal;
import java.util.List;

import com.clickshop.utils.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "sale")
public class Sale {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_method")
    private String paymentMethod;

    private String status;

    @Column(name = "total_amount")
    private Double totalAmount;


    private Double cashback;

    @Column(name = "amount_paid", precision = 10, scale = 2)
    private BigDecimal amountPaid;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @OneToMany(mappedBy = "sale")
    private List<SaleDetails> saleDetails;

    @ManyToOne()
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    public Sale() {}

    public Sale(String paymentMethod, Double cashback, Customer customer, Double totalAmount, Coupon coupon) {
        this.paymentMethod = paymentMethod;
        this.cashback = cashback;
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.coupon = coupon;
        this.status = OrderStatus.PENDENTE.toString();
        
        this.amountPaid = new BigDecimal(this.totalAmount * (1 - this.coupon.getDiscount()) - this.cashback);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus() {
        if (this.status == OrderStatus.PENDENTE.toString()) {
            this.status = OrderStatus.ENVIADO.toString();
        } else if (this.status == OrderStatus.ENVIADO.toString()) {
            this.status = OrderStatus.ENTREGUE.toString();
        }
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getCashback() {
        return cashback;
    }

    public void setCashback(Double cashback) {
        this.cashback = cashback;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<SaleDetails> getSaleDetails() {
        return saleDetails;
    }

    public void setSaleDetails(List<SaleDetails> saleDetails) {
        this.saleDetails = saleDetails;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    


    




    


}
