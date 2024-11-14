package com.clickshop.dtos;

import java.util.List;

public record SaleCreationDto(
    Long userId,
    String paymentMethod,
    Double cashback,
    List<SaleProductListDto> productList
) {
    
}
