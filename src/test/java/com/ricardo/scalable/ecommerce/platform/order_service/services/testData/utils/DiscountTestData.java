package com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Discount;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.ProductSku;
import com.ricardo.scalable.ecommerce.platform.libs_common.enums.DiscountType;

import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils.ProductSkuTestData.*;

public class DiscountTestData {

    public static List<Discount> createListOfDiscounts() {
        Discount discount1 = createDiscount001().orElseThrow();
        Discount discount2 = createDiscount002().orElseThrow();
        Discount discount3 = createDiscount003().orElseThrow();
        Discount discount4 = createDiscount004().orElseThrow();
        Discount discount5 = createDiscount005().orElseThrow();

        return List.of(discount1, discount2, discount3, discount4, discount5);
    }

    public static Optional<Discount> createDiscount001() {
        Discount discount = new Discount();
        List<ProductSku> productSkus = List.of(
            createProductSku001().orElseThrow(),
            createProductSku002().orElseThrow()
        );

        discount.setId(1L);
        discount.setDiscountType(DiscountType.PERCENTAGE);
        discount.setDiscountValue(10.0);
        discount.setStartDate(Timestamp.valueOf(LocalDateTime.of(2025, 3, 10, 0, 0, 0)));
        discount.setEndDate(Timestamp.valueOf(LocalDateTime.of(2025, 4, 10, 23, 59, 59)));
        discount.setIsActive(true);
        discount.setProductSkus(productSkus);
        discount.setCreatedAt(Timestamp.from(Instant.now()));
        discount.setUpdatedAt(Timestamp.from(Instant.now()));

        return Optional.of(discount);
    }

    public static Optional<Discount> createDiscount002() {
        Discount discount = new Discount();
        List<ProductSku> productSkus = List.of(
            createProductSku003().orElseThrow(),
            createProductSku004().orElseThrow()
        );

        discount.setId(2L);
        discount.setDiscountType(DiscountType.AMOUNT);
        discount.setDiscountValue(10.00);
        discount.setStartDate(Timestamp.valueOf(LocalDateTime.of(2025, 3, 1, 0, 0, 0)));
        discount.setEndDate(Timestamp.valueOf(LocalDateTime.of(2025, 3, 9, 23, 59, 59)));
        discount.setIsActive(true);
        discount.setProductSkus(productSkus);
        discount.setCreatedAt(Timestamp.from(Instant.now()));
        discount.setUpdatedAt(Timestamp.from(Instant.now()));

        return Optional.of(discount);
    }

    public static Optional<Discount> createDiscount003() {
        Discount discount = new Discount();
        List<ProductSku> productSkus = List.of(
            createProductSku005().orElseThrow(),
            createProductSku006().orElseThrow()
        );

        discount.setId(3L);
        discount.setDiscountType(DiscountType.AMOUNT);
        discount.setDiscountValue(5.00);
        discount.setStartDate(Timestamp.valueOf(LocalDateTime.of(2025, 3, 5, 0, 0, 0)));
        discount.setEndDate(Timestamp.valueOf(LocalDateTime.of(2025, 3, 20, 23, 59, 59)));
        discount.setIsActive(true);
        discount.setProductSkus(productSkus);
        discount.setCreatedAt(Timestamp.from(Instant.now()));
        discount.setUpdatedAt(Timestamp.from(Instant.now()));

        return Optional.of(discount);
    }

    public static Optional<Discount> createDiscount004() {
        Discount discount = new Discount();
        List<ProductSku> productSkus = List.of(
            createProductSku004().orElseThrow(),
            createProductSku007().orElseThrow()
        );

        discount.setId(4L);
        discount.setDiscountType(DiscountType.PERCENTAGE);
        discount.setDiscountValue(15.0);
        discount.setStartDate(Timestamp.valueOf(LocalDateTime.of(2025, 3, 1, 0, 0, 0)));
        discount.setEndDate(Timestamp.valueOf(LocalDateTime.of(2025, 3, 8, 23, 59, 59)));
        discount.setIsActive(true);
        discount.setProductSkus(productSkus);
        discount.setCreatedAt(Timestamp.from(Instant.now()));
        discount.setUpdatedAt(Timestamp.from(Instant.now()));

        return Optional.of(discount);
    }

    public static Optional<Discount> createDiscount005() {
        Discount discount = new Discount();
        List<ProductSku> productSkus = List.of(
            createProductSku003().orElseThrow(),
            createProductSku004().orElseThrow()
        );

        discount.setId(5L);
        discount.setDiscountType(DiscountType.PERCENTAGE);
        discount.setDiscountValue(20.0);
        discount.setStartDate(Timestamp.valueOf(LocalDateTime.of(2025, 3, 10, 0, 0, 0)));
        discount.setEndDate(Timestamp.valueOf(LocalDateTime.of(2025, 4, 10, 23, 59, 59)));
        discount.setIsActive(true);
        discount.setProductSkus(productSkus);
        discount.setCreatedAt(Timestamp.from(Instant.now()));
        discount.setUpdatedAt(Timestamp.from(Instant.now()));

        return Optional.of(discount);
    }


}
