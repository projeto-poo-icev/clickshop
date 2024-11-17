package com.clickshop.dtos;

public record CouponDto(
    Long id,
    String name,
    Double discount,
    Boolean status
) {
    
}
