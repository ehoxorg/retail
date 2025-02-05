package com.ehoxorg.retail.rest;

import com.ehoxorg.retail.service.PriceDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@SpringBootTest
public class PriceControllerITest {
    @Autowired
    PricesController pricesController;

    @Test
    public void givenTestData_thenGetCorrectPriorityPricing() {
        ResponseEntity<PriceDto> result = pricesController.getPriorityPricing(35455L, 1L, LocalDateTime.of(2020, 6, 14, 10, 0));
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(1, result.getBody().getPriority());
        Assertions.assertEquals(35.5, result.getBody().getPrice());

        result = pricesController.getPriorityPricing(35455L, 1L, LocalDateTime.of(2020, 6, 14, 16, 0));
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(2, result.getBody().getPriority());
        Assertions.assertEquals(25.45, result.getBody().getPrice());

        result = pricesController.getPriorityPricing(35455L, 1L, LocalDateTime.of(2020, 6, 14, 21, 0));
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(1, result.getBody().getPriority());
        Assertions.assertEquals(35.5, result.getBody().getPrice());

        result = pricesController.getPriorityPricing(35455L, 1L, LocalDateTime.of(2020, 6, 15, 10, 0));
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(3, result.getBody().getPriority());
        Assertions.assertEquals(30.5, result.getBody().getPrice());

        result = pricesController.getPriorityPricing(35455L, 1L, LocalDateTime.of(2020, 6, 16, 21, 0));
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(4, result.getBody().getPriority());
        Assertions.assertEquals(38.95, result.getBody().getPrice());
    }

}
