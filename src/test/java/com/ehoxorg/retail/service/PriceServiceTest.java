package com.ehoxorg.retail.service;

import com.ehoxorg.retail.model.PriceEntity;
import com.ehoxorg.retail.repository.PriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {
    @Mock
    private PriceRepository priceRepository;
    @InjectMocks
    private PriceServiceImpl priceService;


    @Test
    public void getPriceReturnsMaxPriorityTest() {
        double price1 = 2.2;
        double price2 = 4.4;
        double price3 = 6.6;
        List<PriceEntity> priceEntities = getPriceEntities(price1, price2, price3);

        when(priceRepository.findPricesByBrandAndProduct(anyLong(), anyLong(), any())).thenReturn(priceEntities);

        PriceDto result = priceService.getMaxPriorityPrice(0L, 0L, LocalDateTime.now());

        Assertions.assertEquals(price3, result.getPrice(), 0.0);
        Assertions.assertEquals(priceEntities.get(2).getPrice(), result.getPrice());
        Assertions.assertEquals(priceEntities.get(2).getPriority(), result.getPriority());
    }

    @Test
    public void getPriceReturnsNullIfEmptyListTest() {
        when(priceRepository.findPricesByBrandAndProduct(anyLong(), anyLong(), any())).thenReturn(Collections.emptyList());
        PriceDto result = priceService.getMaxPriorityPrice(0L, 0L, LocalDateTime.now());
        Assertions.assertNull(result);
    }

    private static List<PriceEntity> getPriceEntities(double price1, double price2, double price3) {
        PriceEntity entity1 = new PriceEntity();
        entity1.setPrice(price1);
        entity1.setProductId(1L);
        entity1.setPriority(2);

        PriceEntity entity2 = new PriceEntity();
        entity2.setPrice(price2);
        entity2.setProductId(1L);
        entity2.setPriority(1);

        PriceEntity entity3 = new PriceEntity();
        entity3.setPrice(price3);
        entity3.setProductId(1L);
        entity3.setPriority(3);

        return List.of(entity1, entity2, entity3);
    }

}
