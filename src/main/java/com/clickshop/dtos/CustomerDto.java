package com.clickshop.dtos;

import java.time.LocalDate;

public record CustomerDto(
    Long id,
    String name,
    Double cashback,
    Double amountSpent,
    String CustomerLevel,
    LocalDate createdAt
) {
    
}
