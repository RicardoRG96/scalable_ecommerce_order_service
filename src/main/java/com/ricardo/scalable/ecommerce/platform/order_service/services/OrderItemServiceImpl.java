package com.ricardo.scalable.ecommerce.platform.order_service.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Discount;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Order;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.ProductSku;
import com.ricardo.scalable.ecommerce.platform.order_service.entities.OrderItem;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderItemDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.DiscountRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.OrderItemRespository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.OrderRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.ProductSkuRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRespository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductSkuRepository productSkuRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public Optional<OrderItem> findById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public Optional<List<OrderItem>> findByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    @Override
    public Optional<List<OrderItem>> findByProductSkuId(Long productSkuId) {
        return orderItemRepository.findByProductSkuId(productSkuId);
    }

    @Override
    public Optional<OrderItem> findByOrderIdAndProductSkuId(Long orderId, Long productSkuId) {
        return orderItemRepository.findByOrderIdAndProductSkuId(orderId, productSkuId);
    }

    @Override
    public Optional<List<OrderItem>> findByUnitPrice(BigDecimal unitPrice) {
        return orderItemRepository.findByUnitPrice(unitPrice);
    }

    @Override
    public Optional<List<OrderItem>> findByDiscountId(Long discountId) {
        return orderItemRepository.findByDiscountId(discountId);
    }

    @Override
    public List<OrderItem> findAll() {
        return (List<OrderItem>) orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> save(OrderItemDto orderItem) {
        Optional<Order> orderOptional = orderRepository.findById(orderItem.getOrderId());
        Optional<ProductSku> productSkuOptional = productSkuRepository.findById(orderItem.getProductSkuId());
        
        if (
            orderOptional.isPresent() && 
            productSkuOptional.isPresent()
        ) {
            Order order = orderOptional.orElseThrow();
            ProductSku productSku = productSkuOptional.orElseThrow();
        
            OrderItem createdOrderItem = new OrderItem();
            createdOrderItem.setOrder(order);
            createdOrderItem.setProductSku(productSku);
            createdOrderItem.setQuantity(orderItem.getQuantity());
            createdOrderItem.setUnitPrice(orderItem.getUnitPrice());
            if (orderItem.getDiscountId() == null) {
                createdOrderItem.setDiscount(null);
            } else {
                Optional<Discount> discountOptional = discountRepository.findById(orderItem.getDiscountId());
                if (discountOptional.isEmpty()) {
                    return Optional.empty();
                }
                Discount discount = discountOptional.orElseThrow();
                createdOrderItem.setDiscount(discount);
            }

            return Optional.of(orderItemRepository.save(createdOrderItem));
        }
        return Optional.empty();
    }

    @Override
    public Optional<OrderItem> update(Long id, OrderItemDto orderItem) {
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        Optional<Order> orderOptional = orderRepository.findById(orderItem.getOrderId());
        Optional<ProductSku> productSkuOptional = productSkuRepository.findById(orderItem.getProductSkuId());
        

        if (
            orderItemOptional.isPresent() && 
            orderOptional.isPresent() && 
            productSkuOptional.isPresent()
        ) {
            OrderItem orderItemToUpdate = orderItemOptional.orElseThrow();
            Order order = orderOptional.orElseThrow();
            ProductSku productSku = productSkuOptional.orElseThrow();

            orderItemToUpdate.setOrder(order);
            orderItemToUpdate.setProductSku(productSku);
            orderItemToUpdate.setQuantity(orderItem.getQuantity());
            orderItemToUpdate.setUnitPrice(orderItem.getUnitPrice());
            if (orderItem.getDiscountId() == null) {
                orderItemToUpdate.setDiscount(null);
            } else {
                Optional<Discount> discountOptional = discountRepository.findById(orderItem.getDiscountId());
                if (discountOptional.isEmpty()) {
                    return Optional.empty();
                }
                Discount discount = discountOptional.orElseThrow();
                orderItemToUpdate.setDiscount(discount);
            }

            return Optional.of(orderItemRepository.save(orderItemToUpdate));
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }

}
