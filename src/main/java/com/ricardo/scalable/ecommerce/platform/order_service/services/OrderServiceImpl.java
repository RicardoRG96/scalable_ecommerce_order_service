package com.ricardo.scalable.ecommerce.platform.order_service.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Address;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Cart;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.CartItem;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Discount;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Order;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.OrderItem;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.ProductSku;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.User;
import com.ricardo.scalable.ecommerce.platform.libs_common.enums.OrderStatus;
import com.ricardo.scalable.ecommerce.platform.libs_common.exceptions.AddressNotFoundException;
import com.ricardo.scalable.ecommerce.platform.libs_common.exceptions.CartNotFoundException;
import com.ricardo.scalable.ecommerce.platform.libs_common.exceptions.InsufficientStockException;
import com.ricardo.scalable.ecommerce.platform.libs_common.exceptions.OrderNotFoundException;
import com.ricardo.scalable.ecommerce.platform.libs_common.exceptions.UserNotFoundException;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.AddressRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.CartRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.DiscountRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.OrderRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.ProductSkuRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.UserRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdateOrderAddressesDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdateOrderStatusDto;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductSkuRepository productSkuRepository;

    @Autowired
    private DiscountRepository discountRepository;

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
    @Transactional
    public Order createOrderFromCart(OrderDto orderDto) {
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Address shippingAddress = addressRepository.findById(orderDto.getShippingAddressId())
                .orElseThrow(() -> new AddressNotFoundException("Shipping address not found"));
                
        Address billingAddress = addressRepository.findById(orderDto.getBillingAddressId())
                .orElseThrow(() -> new AddressNotFoundException("Billing address not found"));

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user"));

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(calculateTotalAmount(cart));
        order.setOrderStatus(OrderStatus.PENDING);
        order.setShippingAddress(shippingAddress);
        order.setBillingAddress(billingAddress);

        order = orderRepository.save(order);

        setCartItemsToOrderItems(cart, order);
        clearCart(cart);

        return orderRepository.save(order);
    }

    private BigDecimal calculateTotalAmount(Cart cart) {
        double totalAmount = cart.getItems()
                .stream()
                .mapToDouble(
                    item -> productSkuRepository.findById(item.getProductSku().getId())
                            .map(productSku -> {
                                BigDecimal priceToUse = BigDecimal.valueOf(productSku.getPrice());
                                Optional<Discount> discount = findActiveDiscountByProductSkuId(productSku.getId());
                                if (discount.isPresent()) {
                                    Discount activeDiscount = discount.orElseThrow();
                                    priceToUse = applyDiscount(priceToUse, activeDiscount);
                                }
                                return priceToUse.multiply(BigDecimal.valueOf(item.getQuantity()))
                                        .setScale(0, RoundingMode.HALF_UP)
                                        .doubleValue();
                            })
                            .orElse(0.0)
                )
                .sum();
        return BigDecimal.valueOf(totalAmount);
    }

    private Optional<Discount> findActiveDiscountByProductSkuId(Long productSkuId) {
        return discountRepository.findActiveDiscountByProductSkuId(productSkuId, LocalDateTime.now());
    }

    private BigDecimal applyDiscount(BigDecimal price, Discount discount) {
        return discount.getDiscountType().apply(price, BigDecimal.valueOf(discount.getDiscountValue()));
    }

    private void setCartItemsToOrderItems(Cart cart, Order order) {
        for (CartItem item : cart.getItems()) {
            ProductSku productSku = item.getProductSku();
            if (productSku.getStock() < item.getQuantity()) {
                throw new InsufficientStockException("Not enough stock for product: " + productSku.getId());
            }

            OrderItem orderItem = new OrderItem();
            Discount discount = findActiveDiscountByProductSkuId(productSku.getId()).orElse(null);
            BigDecimal unitPriceToUse = BigDecimal.valueOf(productSku.getPrice());
            if (discount != null) {
                unitPriceToUse = applyDiscount(unitPriceToUse, discount);
            }
            orderItem.setOrder(order);
            orderItem.setProductSku(productSku);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setUnitPrice(unitPriceToUse);
            orderItem.setDiscount(discount);
            order.getItems().add(orderItem);
        }
    }

    private void clearCart(Cart cart) {
        cartRepository.delete(cart);
    }

    @Override
    @Transactional
    public Order updateOrderAddresses(UpdateOrderAddressesDto orderAddresses) {
        Order orderToUpdate = orderRepository.findById(orderAddresses.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        Address shippingAddress = addressRepository.findById(orderAddresses.getShippingAddressId())
                .orElseThrow(() -> new AddressNotFoundException("Shipping address not found"));

        Address billingAddress = addressRepository.findById(orderAddresses.getBillingAddressId())
                .orElseThrow(() -> new AddressNotFoundException("Billing address not found"));

        orderToUpdate.setShippingAddress(shippingAddress);
        orderToUpdate.setBillingAddress(billingAddress);
        return orderRepository.save(orderToUpdate);
    }

    @Override
    @Transactional
    public Order updateOrderStatus(UpdateOrderStatusDto orderStatus) {
        Order orderToUpdate = orderRepository.findById(orderStatus.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        orderToUpdate.setOrderStatus(OrderStatus.valueOf(orderStatus.getOrderStatus()));
        return orderRepository.save(orderToUpdate);

    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

}
