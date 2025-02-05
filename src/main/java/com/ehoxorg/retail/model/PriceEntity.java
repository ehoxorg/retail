package com.ehoxorg.retail.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "prices")
@Getter
@Setter
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    @NotNull
    private Long brandId;

    @Column(name = "product_id")
    @NotNull
    private Long productId;

    @Column(name = "start_date")
    @NotNull
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @NotNull
    private LocalDateTime endDate;

    @Min(value = 1, message = "Priority should be a positive number")
    private Integer priority;

    @Min(value = 0, message = "Price should be a positive number or 0")
    private Double price;

    @Size(min = 3, max = 3, message = "Currency must be exactly 3 characters long")
    @NotNull
    private String currency;
}
