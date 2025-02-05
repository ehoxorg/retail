package com.ehoxorg.retail.mapper;

import com.ehoxorg.retail.model.PriceEntity;
import com.ehoxorg.retail.service.PriceDto;

public class PriceMapper {
    public static PriceDto toDto(PriceEntity priceEntity) {
        return PriceDto.builder()
                .id(priceEntity.getId())
                .price(priceEntity.getPrice())
                .startDate(priceEntity.getStartDate())
                .endDate(priceEntity.getEndDate())
                .priority(priceEntity.getPriority())
                .brandId(priceEntity.getBrandId())
                .currency(priceEntity.getCurrency())
                .productId(priceEntity.getProductId())
                .build();
    }

    public static PriceEntity toEntity(PriceDto priceDto) {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setPrice(priceDto.getPrice());
        priceEntity.setBrandId(priceDto.getBrandId());
        priceEntity.setCurrency(priceDto.getCurrency());
        priceEntity.setPriority(priceDto.getPriority());
        priceEntity.setStartDate(priceDto.getStartDate());
        priceEntity.setEndDate(priceDto.getEndDate());
        priceEntity.setProductId(priceDto.getProductId());
        return priceEntity;
    }
}
