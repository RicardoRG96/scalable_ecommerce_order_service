package com.ricardo.scalable.ecommerce.platform.order_service.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

    Optional<List<Order>> findByUserId(Long userId);

    Optional<Order> findByIdAndUserId(Long id, Long userId);

    Optional<List<Order>> findByOrderStatus(String orderStatus);

    Optional<List<Order>> findByPaymentStatus(String paymentStatus);

    Optional<List<Order>> findByShippingAddressId(Long shippingAddressId);

    Optional<List<Order>> findByBillingAddressId(Long billingAddressId);

}
