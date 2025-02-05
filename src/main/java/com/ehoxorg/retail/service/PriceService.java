package com.ehoxorg.retail.service;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {
        Long createPrice(PriceDto priceDto);
        PriceDto updatePrice(PriceDto priceDto);
        PriceDto getPrice(Long priceId);
        List<PriceDto> getAllPrices();
        PriceDto getMaxPriorityPrice(long brandId, long productId, LocalDateTime date);
}
