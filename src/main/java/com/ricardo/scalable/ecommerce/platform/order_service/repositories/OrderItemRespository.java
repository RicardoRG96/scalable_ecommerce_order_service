package com.ricardo.scalable.ecommerce.platform.order_service.repositories;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ricardo.scalable.ecommerce.platform.order_service.entities.OrderItem;

public interface OrderItemRespository extends CrudRepository<OrderItem, Long> {
    
    Optional<OrderItem> findByOrderId(Long orderId);

    Optional<OrderItem> findByProductSkuId(Long productSkuId);

    Optional<OrderItem> findByOrderIdAndProductSkuId(Long orderId, Long productSkuId);

    Optional<OrderItem> findByUnitePrice(BigDecimal unitPrice);

    Optional<OrderItem> findByDiscountId(Long discountId);

}
