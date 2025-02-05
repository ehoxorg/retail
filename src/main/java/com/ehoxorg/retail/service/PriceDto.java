package com.ehoxorg.retail.service;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PriceDto {
    private Long id;
    @NotNull
    private Long brandId;
    @NotNull
    private Long productId;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;
    @Min(value = 1, message = "Priority should be a positive number")
    private Integer priority;
    @Min(value = 0, message = "Price should be a positive number or 0")
    private Double price;
    @NotNull
    @Size(min = 3, max = 3, message = "Currency must be exactly 3 characters long")
    private String currency;
}
