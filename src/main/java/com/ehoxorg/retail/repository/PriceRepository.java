package com.ehoxorg.retail.repository;

import com.ehoxorg.retail.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM prices p WHERE p.brandId = :brandId AND p.productId = :productId AND p.startDate <= :dateTime AND p.endDate >= :dateTime")
    List<PriceEntity> findPricesByBrandAndProduct(@Param("brandId") long brandId,
                                                  @Param("productId") long productId,
                                                  @Param("dateTime") LocalDateTime dateTime);

}
