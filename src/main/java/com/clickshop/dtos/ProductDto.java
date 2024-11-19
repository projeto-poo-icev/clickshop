package com.clickshop.dtos;

public record ProductDto(
    Long id,
    String description,
    Double price,
    Integer quantity
) {
    
}
