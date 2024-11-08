package com.clickshop.dtos;

import java.time.LocalDate;

public record CustomerDto(
    Long id,
    String name,
    String cpf,
    Double cashback,
    Double amountSpent,
    String CustomerLevel,
    LocalDate createdAt
) {
    
}
