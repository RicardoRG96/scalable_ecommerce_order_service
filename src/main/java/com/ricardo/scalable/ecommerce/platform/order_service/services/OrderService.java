package com.ricardo.scalable.ecommerce.platform.order_service.services;

import java.util.List;
import java.util.Optional;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Order;
import com.ricardo.scalable.ecommerce.platform.libs_common.enums.OrderStatus;
import com.ricardo.scalable.ecommerce.platform.libs_common.enums.PaymentStatus;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdateOrderStatusDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdatePaymentStatusDto;

public interface OrderService {

    Optional<Order> findById(Long id);

    Optional<List<Order>> findByUserId(Long userId);

    Optional<Order> findByIdAndUserId(Long id, Long userId);

    Optional<List<Order>> findByOrderStatus(OrderStatus orderStatus);

    Optional<List<Order>> findByPaymentStatus(PaymentStatus paymentStatus);

    Optional<List<Order>> findByShippingAddressId(Long shippingAddressId);

    Optional<List<Order>> findByBillingAddressId(Long billingAddressId);

    List<Order> findAll();

    Optional<Order> save(OrderDto order);

    Optional<Order> update(Long id, OrderDto order);

    Optional<Order> updateOrderStatus(UpdateOrderStatusDto orderStatus);

    Optional<Order> updatePaymentStatus(UpdatePaymentStatusDto paymentStatus);

    void delete(Long id);

}
