package com.ricardo.scalable.ecommerce.platform.order_service.services;

import java.util.List;
import java.util.Optional;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Order;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderDto;

public interface OrderService {

    Optional<Order> findById(Long id);

    Optional<List<Order>> findByUserId(Long userId);

    Optional<Order> findByIdAndUserId(Long id, Long userId);

    Optional<List<Order>> findByOrderStatus(String orderStatus);

    Optional<List<Order>> findByPaymentStatus(String paymentStatus);

    Optional<List<Order>> findByShippingAddressId(Long shippingAddressId);

    Optional<List<Order>> findByBillingAddressId(Long billingAddressId);

    List<Order> findAll();

    Optional<Order> save(OrderDto order);

    Optional<Order> update(Long id, OrderDto order);

    void delete(Long id);

}
