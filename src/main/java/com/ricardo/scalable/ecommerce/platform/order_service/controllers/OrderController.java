package com.ricardo.scalable.ecommerce.platform.order_service.controllers;

import static com.ricardo.scalable.ecommerce.platform.libs_common.validation.RequestBodyValidation.validation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Order;
import com.ricardo.scalable.ecommerce.platform.libs_common.enums.OrderStatus;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdateOrderAddressesDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdateOrderStatusDto;
import com.ricardo.scalable.ecommerce.platform.order_service.services.OrderService;

import jakarta.validation.Valid;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> orderOptional = orderService.findById(id);
        
        if (orderOptional.isPresent()) {
            return ResponseEntity.ok(orderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable Long userId) {
        Optional<List<Order>> orderOptional = orderService.findByUserId(userId);
        boolean isEmpty = orderOptional.orElseThrow().isEmpty();
        
        if (orderOptional.isPresent() && !isEmpty) {
            return ResponseEntity.ok(orderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/user/{userId}")
    public ResponseEntity<Order> getOrderByIdAndUserId(@PathVariable Long id, @PathVariable Long userId) {
        Optional<Order> orderOptional = orderService.findByIdAndUserId(id, userId);
        
        if (orderOptional.isPresent()) {
            return ResponseEntity.ok(orderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/status/{orderStatus}")
    public ResponseEntity<List<Order>> getOrderByOrderStatus(@PathVariable String orderStatus) {
        try {
            OrderStatus orderStatusEnum = OrderStatus.valueOf(orderStatus.toUpperCase());
            Optional<List<Order>> orderOptional = orderService.findByOrderStatus(orderStatusEnum);
            boolean isEmpty = orderOptional.orElseThrow().isEmpty();
            
            if (orderOptional.isPresent() && !isEmpty) {
                return ResponseEntity.ok(orderOptional.orElseThrow());
            }
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/shipping-address/{shippingAddressId}")
    public ResponseEntity<List<Order>> getOrderByShippingAddressId(@PathVariable Long shippingAddressId) {
        Optional<List<Order>> orderOptional = orderService.findByShippingAddressId(shippingAddressId);
        boolean isEmpty = orderOptional.orElseThrow().isEmpty();
        
        if (orderOptional.isPresent() && !isEmpty) {
            return ResponseEntity.ok(orderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/billing-address/{billingAddressId}")
    public ResponseEntity<List<Order>> getOrderByBillingAddressId(@PathVariable Long billingAddressId) {
        Optional<List<Order>> orderOptional = orderService.findByBillingAddressId(billingAddressId);
        boolean isEmpty = orderOptional.orElseThrow().isEmpty();
        
        if (orderOptional.isPresent() && !isEmpty) {
            return ResponseEntity.ok(orderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/")
    public ResponseEntity<?> createOrder(
        @Valid @RequestBody OrderDto order,
        BindingResult result
    ) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Order createdOrder = orderService.createOrderFromCart(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderAddress(
        @Valid @RequestBody UpdateOrderAddressesDto orderAddressDto,
        @PathVariable Long id,
        BindingResult result
    ) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Order updatedOrderOptional = orderService.updateOrderAddresses(orderAddressDto);

        return ResponseEntity.ok(updatedOrderOptional);        
    }

    @PutMapping("/status")
    public ResponseEntity<?> updateOrderStatus(
        @Valid @RequestBody UpdateOrderStatusDto orderStatus,
        BindingResult result
    ) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Order updatedOrderOptional = orderService.updateOrderStatus(orderStatus);

        return ResponseEntity.ok(updatedOrderOptional);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        Optional<Order> orderOptional = orderService.findById(id);
        if (orderOptional.isPresent()) {
            orderService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
}