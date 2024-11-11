package com.clickshop.dtos;

public record ProductCreationDto(
    String description,
    Double price,
    Integer quantity
) {
    
}
