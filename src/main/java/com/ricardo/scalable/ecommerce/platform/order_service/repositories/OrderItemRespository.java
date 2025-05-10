package com.ricardo.scalable.ecommerce.platform.order_service.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.OrderItem;

public interface OrderItemRespository extends CrudRepository<OrderItem, Long> {
    
    Optional<List<OrderItem>> findByOrderId(Long orderId);

    Optional<List<OrderItem>> findByProductSkuId(Long productSkuId);

    Optional<OrderItem> findByOrderIdAndProductSkuId(Long orderId, Long productSkuId);

    Optional<List<OrderItem>> findByUnitPrice(BigDecimal unitPrice);

    Optional<List<OrderItem>> findByDiscountId(Long discountId);

}
