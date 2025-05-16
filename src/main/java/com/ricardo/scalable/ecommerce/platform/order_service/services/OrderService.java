package com.ricardo.scalable.ecommerce.platform.order_service.services;

import java.util.List;
import java.util.Optional;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Order;
import com.ricardo.scalable.ecommerce.platform.libs_common.enums.OrderStatus;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdateOrderAddressesDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdateOrderStatusDto;

public interface OrderService {

    Optional<Order> findById(Long id);

    Optional<List<Order>> findByUserId(Long userId);

    Optional<Order> findByIdAndUserId(Long id, Long userId);

    Optional<List<Order>> findByOrderStatus(OrderStatus orderStatus);

    Optional<List<Order>> findByShippingAddressId(Long shippingAddressId);

    Optional<List<Order>> findByBillingAddressId(Long billingAddressId);

    List<Order> findAll();

    Order createOrderFromCart(OrderDto order);

    Order updateOrderAddresses(UpdateOrderAddressesDto orderAddresses);

    Order updateOrderStatus(UpdateOrderStatusDto orderStatus);

    void delete(Long id);

}
