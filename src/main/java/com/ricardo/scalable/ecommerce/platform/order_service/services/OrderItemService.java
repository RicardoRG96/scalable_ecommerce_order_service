package com.ricardo.scalable.ecommerce.platform.order_service.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.OrderItem;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderItemDto;

public interface OrderItemService {

    Optional<OrderItem> findById(Long id);

    Optional<List<OrderItem>> findByOrderId(Long orderId);

    Optional<List<OrderItem>> findByProductSkuId(Long productSkuId);

    Optional<OrderItem> findByOrderIdAndProductSkuId(Long orderId, Long productSkuId);

    Optional<List<OrderItem>> findByUnitPrice(BigDecimal unitPrice);

    Optional<List<OrderItem>> findByDiscountId(Long discountId);

    List<OrderItem> findAll();

    Optional<OrderItem> save(OrderItemDto orderItem);

    Optional<OrderItem> update(Long id, OrderItemDto orderItem);

    void delete(Long id);

}
