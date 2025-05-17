package com.ricardo.scalable.ecommerce.platform.order_service.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Discount;

public interface DiscountRepository extends CrudRepository<Discount, Long> {

    @Query(value = "SELECT d.id, d.discount_type, d.discount_value, d.start_date, d.end_date, d.is_active " +
                   "FROM discounts d " +
                   "JOIN discount_product_sku dps ON d.id = dps.discount_id " +
                   "WHERE dps.product_sku_id = :productSkuId " +
                   "AND d.is_active = TRUE " +
                   "AND (d.start_date <= :now AND d.end_date >= :now)",
                   nativeQuery = true)
    Optional<Discount> findActiveDiscountByProductSkuId(
        @Param("productSkuId") Long productSkuId, 
        @Param("now") LocalDateTime now
    );

}
