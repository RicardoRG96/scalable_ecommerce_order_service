package com.ricardo.scalable.ecommerce.platform.order_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Address;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Order;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.User;
import com.ricardo.scalable.ecommerce.platform.libs_common.enums.OrderStatus;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.AddressRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.OrderRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.UserRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdateOrderStatusDto;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<List<Order>> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Optional<Order> findByIdAndUserId(Long id, Long userId) {
        return orderRepository.findByIdAndUserId(id, userId);
    }

    @Override
    public Optional<List<Order>> findByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus);
    }

    @Override
    public Optional<List<Order>> findByShippingAddressId(Long shippingAddressId) {
        return orderRepository.findByShippingAddressId(shippingAddressId);
    }

    @Override
    public Optional<List<Order>> findByBillingAddressId(Long billingAddressId) {
        return orderRepository.findByBillingAddressId(billingAddressId);
    }

    @Override
    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Optional<Order> save(OrderDto order) {
        Optional<User> userOptional = userRepository.findById(order.getUserId());
        Optional<Address> shippingAddressOptional = addressRepository.findById(order.getShippingAddressId());
        Optional<Address> billingAddressOptional = addressRepository.findById(order.getBillingAddressId());

        if (
            userOptional.isPresent() && 
            shippingAddressOptional.isPresent() && 
            billingAddressOptional.isPresent()
        ) {
            Order createdOrder = new Order();
            createdOrder.setUser(userOptional.orElseThrow());
            createdOrder.setTotalAmount(order.getTotalAmount());
            createdOrder.setOrderStatus(OrderStatus.valueOf(order.getOrderStatus().toUpperCase()));
            createdOrder.setShippingAddress(shippingAddressOptional.orElseThrow());
            createdOrder.setBillingAddress(billingAddressOptional.orElseThrow());

            return Optional.of(orderRepository.save(createdOrder));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Order> update(Long id, OrderDto order) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        Optional<User> userOptional = userRepository.findById(order.getUserId());
        Optional<Address> shippingAddressOptional = addressRepository.findById(order.getShippingAddressId());
        Optional<Address> billingAddressOptional = addressRepository.findById(order.getBillingAddressId());

        if (
            orderOptional.isPresent() && 
            userOptional.isPresent() && 
            shippingAddressOptional.isPresent() && 
            billingAddressOptional.isPresent()
        ) {
            Order orderToUpdate = orderOptional.orElseThrow();
            orderToUpdate.setUser(userOptional.orElseThrow());
            orderToUpdate.setTotalAmount(order.getTotalAmount());
            orderToUpdate.setOrderStatus(OrderStatus.valueOf(order.getOrderStatus()));
            orderToUpdate.setShippingAddress(shippingAddressOptional.orElseThrow());
            orderToUpdate.setBillingAddress(billingAddressOptional.orElseThrow());

            return Optional.of(orderRepository.save(orderToUpdate));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Order> updateOrderStatus(UpdateOrderStatusDto orderStatus) {
        Optional<Order> orderOptional = orderRepository.findById(orderStatus.getOrderId());

        if (orderOptional.isPresent()) {
            Order orderToUpdate = orderOptional.orElseThrow();
            orderToUpdate.setOrderStatus(OrderStatus.valueOf(orderStatus.getOrderStatus()));
            return Optional.of(orderRepository.save(orderToUpdate));
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

}
