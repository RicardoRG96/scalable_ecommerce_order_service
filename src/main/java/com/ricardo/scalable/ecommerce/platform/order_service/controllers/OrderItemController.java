package com.ricardo.scalable.ecommerce.platform.order_service.controllers;

import static com.ricardo.scalable.ecommerce.platform.libs_common.validation.RequestBodyValidation.validation;

import java.math.BigDecimal;
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

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.OrderItem;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderItemDto;
import com.ricardo.scalable.ecommerce.platform.order_service.services.OrderItemService;
import com.ricardo.scalable.ecommerce.platform.order_service.services.StockService;

import jakarta.validation.Valid;

@RestController
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private StockService stockService;

    @GetMapping("/order-items/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        Optional<OrderItem> orderItemOptional = orderItemService.findById(id);
        
        if (orderItemOptional.isPresent()) {
            return ResponseEntity.ok(orderItemOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/order-items/order/{orderId}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        Optional<List<OrderItem>> orderItemsOptional = orderItemService.findByOrderId(orderId);
        boolean isEmpty = orderItemsOptional.orElseThrow().isEmpty();
        
        if (orderItemsOptional.isPresent() && !isEmpty) {
            return ResponseEntity.ok(orderItemsOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/order-items/product-sku/{productSkuId}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByProductSkuId(@PathVariable Long productSkuId) {
        Optional<List<OrderItem>> orderItemsOptional = orderItemService.findByProductSkuId(productSkuId);
        boolean isEmpty = orderItemsOptional.orElseThrow().isEmpty();
        
        if (orderItemsOptional.isPresent() && !isEmpty) {
            return ResponseEntity.ok(orderItemsOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/order-items/order/{orderId}/product-sku/{productSkuId}")
    public ResponseEntity<OrderItem> getOrderItemByOrderIdAndProductSkuId(
        @PathVariable Long orderId, 
        @PathVariable Long productSkuId
    ) {
        Optional<OrderItem> orderItemOptional = orderItemService.findByOrderIdAndProductSkuId(orderId, productSkuId);
        
        if (orderItemOptional.isPresent()) {
            return ResponseEntity.ok(orderItemOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/order-items/unit-price/{unitPrice}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByUnitPrice(@PathVariable BigDecimal unitPrice) {
        Optional<List<OrderItem>> orderItemsOptional = orderItemService.findByUnitPrice(unitPrice);
        boolean isEmpty = orderItemsOptional.orElseThrow().isEmpty();
        
        if (orderItemsOptional.isPresent() && !isEmpty) {
            return ResponseEntity.ok(orderItemsOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/order-items/discount/{discountId}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByDiscountId(@PathVariable Long discountId) {
        Optional<List<OrderItem>> orderItemsOptional = orderItemService.findByDiscountId(discountId);
        boolean isEmpty = orderItemsOptional.orElseThrow().isEmpty();
        
        if (orderItemsOptional.isPresent() && !isEmpty) {
            return ResponseEntity.ok(orderItemsOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/order-items")
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.findAll();
        return ResponseEntity.ok(orderItems);
    }

    @PostMapping("/order-items")
    public ResponseEntity<?> createOrderItem(
        @Valid @RequestBody OrderItemDto orderItem,
        BindingResult result
    ) {
        if (result.hasErrors()) {
            return validation(result);
        }
        stockService.verifyStock(orderItem);
        Optional<OrderItem> createdOrderItem = orderItemService.save(orderItem);
        
        if (createdOrderItem.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderItem.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/order-items/{id}")
    public ResponseEntity<?> updateOrderItem(
        @Valid @RequestBody OrderItemDto orderItem,
        BindingResult result,
        @PathVariable Long id
    ) {
        if (result.hasErrors()) {
            return validation(result);
        }
        stockService.verifyStock(orderItem);
        Optional<OrderItem> updatedOrderItem = orderItemService.update(id, orderItem);
        
        if (updatedOrderItem.isPresent()) {
            return ResponseEntity.ok(updatedOrderItem.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/order-items/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        Optional<OrderItem> orderItemOptional = orderItemService.findById(id);
        if (orderItemOptional.isPresent()) {
            orderItemService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
