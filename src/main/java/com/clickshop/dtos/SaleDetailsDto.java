package com.clickshop.dtos;

public record SaleDetailsDto(
    Long productId,
    String productDescription,
    Integer quantity,
    Double price
) {
}