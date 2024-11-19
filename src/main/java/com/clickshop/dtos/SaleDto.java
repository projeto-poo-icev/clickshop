package com.clickshop.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.clickshop.entities.SaleDetails;

public record SaleDto(
    Long saleId,
    String paymentMethod,
    Double cashback,
    CustomerDto customer,
    BigDecimal amountPaid,
    CouponDto coupon,
    List<SaleDetailsDto> productList
) {
    
}
