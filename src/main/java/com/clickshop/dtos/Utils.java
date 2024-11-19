package com.clickshop.dtos;

import java.util.List;

import com.clickshop.entities.Coupon;
import com.clickshop.entities.Customer;
import com.clickshop.entities.Product;
import com.clickshop.entities.Sale;
import com.clickshop.entities.SaleDetails;


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

    public static ProductDto ProductModelToDto(Product product) {
        return new ProductDto(
            product.getId(),
            product.getDescription(),
            product.getPrice(),
            product.getQuantity()
        );
    }

    public static List<ProductDto> ProductModelListToDto(List<Product> productList) {
        return productList.stream().map((product) -> ProductModelToDto(product)).toList();
    }

    public static SaleDetailsDto saleDetailsModeltoDto(SaleDetails saleDetails) {
        return new SaleDetailsDto(
            saleDetails.getProduct().getId(),
            saleDetails.getProduct().getDescription(),
            saleDetails.getQuantity(),
            saleDetails.getProduct().getPrice()
        );
    }

    public static List<SaleDetailsDto> saleDetailsModelListToDto(List<SaleDetails> sailDetailsList) {
        return sailDetailsList.stream().map(item -> saleDetailsModeltoDto(item)).toList();
    }

    public static SaleDto saleModelToDto(Sale sale) {
        return new SaleDto(
            sale.getId(),
            sale.getPaymentMethod(),
            sale.getCashback(),
            CustomerModelToDto(sale.getCustomer()),
            sale.getAmountPaid(),
            couponModelToDto(sale.getCoupon()),
            saleDetailsModelListToDto(sale.getSaleDetails())
        );
    }

    public static CouponDto couponModelToDto(Coupon coupon) {
        return new CouponDto(
            coupon.getId(),
            coupon.getName(),
            coupon.getDiscount(),
            coupon.getStatus()
        );
    }

    public static List<CouponDto> couponModelListToDto(List<Coupon> couponList) {
        return couponList.stream().map(coupon -> couponModelToDto(coupon)).toList();
    }

}
