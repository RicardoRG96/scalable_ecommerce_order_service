package com.ricardo.scalable.ecommerce.platform.order_service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

    Optional<List<Order>> findByUserId(Long userId);

    Optional<Order> findByIdAndUserId(Long id, Long userId);

    Optional<Order> findByOrderStatus(String orderStatus);

    Optional<Order> findByPaymentStatus(String paymentStatus);

    Optional<Order> findByShippingAddressId(Long shippingAddressId);

    Optional<Order> findByBillingAddressId(Long billingAddressId);

}
