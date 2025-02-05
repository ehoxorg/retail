package com.ehoxorg.retail.rest;

import com.ehoxorg.retail.service.PriceDto;
import com.ehoxorg.retail.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/prices")
@Validated
public class PricesController {

    @Autowired
    PriceService priceService;

    @Operation(summary = "Creates a new price entry using the provided DTO.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created a new price entry"),
            @ApiResponse(responseCode = "400", description = "Invalid price DTO provided")
    })
    @PostMapping
    public ResponseEntity<String> createPrice(@Valid @RequestBody PriceDto priceDto) {
        Long id = priceService.createPrice(priceDto);
        return ResponseEntity
                .created(URI.create("/api/v1/prices/"+id))
                .build();
    }

    @Operation(summary = "Retrieves a list of all available prices.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved a list of all prices"),
            @ApiResponse(responseCode = "500", description = "Error occurred while retrieving prices")
    })
    @GetMapping("/all")
    public ResponseEntity<List<PriceDto>> getPrices() {
        return ResponseEntity.ok(priceService.getAllPrices());
    }

    @Operation(summary = "Retrieves a single price based on its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved a single price"),
            @ApiResponse(responseCode = "400", description = "Invalid price ID provided"),
            @ApiResponse(responseCode = "404", description = "Price not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PriceDto> getPrice(@PathVariable Long priceId) {
        var result = priceService.getPrice(priceId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Updates an existing price entry using the provided DTO.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated a price entry"),
            @ApiResponse(responseCode = "400", description = "Invalid price DTO provided")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PriceDto> updatePrice(@Valid @RequestBody PriceDto priceDto) {
        return ResponseEntity.ok(priceService.updatePrice(priceDto));
    }

    @Operation(summary = "Retrieves the highest priority price for a specific product and brand during a given time.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the highest priority price")
    })
    @GetMapping("/priority-pricing/{brandId}/{productId}")
    public ResponseEntity<PriceDto> getPriorityPricing(@PathVariable Long productId, @PathVariable Long brandId, @RequestParam LocalDateTime dateTime) {
        return ResponseEntity.ok(priceService.getMaxPriorityPrice(brandId, productId, dateTime));
    }
}
