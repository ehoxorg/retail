package com.ehoxorg.retail.service;

import com.ehoxorg.retail.mapper.PriceMapper;
import com.ehoxorg.retail.model.PriceEntity;
import com.ehoxorg.retail.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    final PriceRepository priceRepository;

    @Override
    public Long createPrice(PriceDto priceDto) {
        PriceEntity newPriceEntity = PriceMapper.toEntity(priceDto);
        newPriceEntity = priceRepository.save(newPriceEntity);
        return newPriceEntity.getId();
    }

    @Override
    public PriceDto updatePrice(PriceDto priceDto) {
        var entity = PriceMapper.toEntity(priceDto);
        return PriceMapper.toDto(priceRepository.save(entity));
    }

    @Override
    public PriceDto getPrice(Long priceId) {
        try {
            PriceEntity priceEntity = priceRepository.getReferenceById(priceId);
            return PriceMapper.toDto(priceEntity);
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return null;
        }
    }

    @Override
    public List<PriceDto> getAllPrices() {
        return priceRepository.findAll().stream()
                .map(PriceMapper::toDto)
                .toList();
    }

    @Override
    public PriceDto getMaxPriorityPrice(long brandId, long productId, LocalDateTime date) {
        List<PriceEntity> prices = priceRepository.findPricesByBrandAndProduct(brandId, productId, date);
        if (prices.isEmpty()) {
            return null;
        }
        PriceEntity highestPriorityPrice = prices.stream()
                .max(Comparator.comparing(PriceEntity::getPriority)).get();
        return PriceMapper.toDto(highestPriorityPrice);
    }
}
