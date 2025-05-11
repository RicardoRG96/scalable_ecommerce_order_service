package com.ricardo.scalable.ecommerce.platform.order_service.services.testData;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Address;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Order;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.User;
import com.ricardo.scalable.ecommerce.platform.libs_common.enums.OrderStatus;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdateOrderStatusDto;

import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils.AddressTestData.*;
import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils.UserTestData.*; 

public class OrderServiceImplTestData {

    public static List<Order> createListOfOrders() {
		Order order1 = createOrder001().orElseThrow();
		Order order2 = createOrder002().orElseThrow();
		Order order3 = createOrder003().orElseThrow();
		Order order4 = createOrder004().orElseThrow();
		Order order5 = createOrder005().orElseThrow();

		return List.of(order1, order2, order3, order4, order5);
	}

	public static Optional<Order> createOrder001() {
		Order order = new Order();
		User user = createUser001().orElseThrow();
		Address shippingAddress = createAddress001().orElseThrow();
		Address billingAddress = createAddress001().orElseThrow();

		order.setId(1L);
		order.setUser(user);
        order.setTotalAmount(new BigDecimal("199.99"));
		order.setOrderStatus(OrderStatus.valueOf("PENDING"));
		order.setShippingAddress(shippingAddress);
		order.setBillingAddress(billingAddress);
		order.setCreatedAt(Timestamp.from(Instant.now()));
		order.setUpdatedAt(Timestamp.from(Instant.now()));

		return Optional.of(order);
	}

	public static Optional<Order> createOrder002() {
		Order order = new Order();
		User user = createUser002().orElseThrow();
		Address shippingAddress = createAddress002().orElseThrow();
		Address billingAddress = createAddress002().orElseThrow();

		order.setId(2L);
		order.setUser(user);
		order.setTotalAmount(new BigDecimal("49.99"));
		order.setOrderStatus(OrderStatus.valueOf("PAID"));
		order.setShippingAddress(shippingAddress);
		order.setBillingAddress(billingAddress);
		order.setCreatedAt(Timestamp.from(Instant.now()));
		order.setUpdatedAt(Timestamp.from(Instant.now()));

		return Optional.of(order);
	}

	public static Optional<Order> createOrder003() {
		Order order = new Order();
		User user = createUser003().orElseThrow();
		Address shippingAddress = createAddress003().orElseThrow();
		Address billingAddress = createAddress003().orElseThrow();

		order.setId(3L);
		order.setUser(user);
		order.setTotalAmount(new BigDecimal("89.99"));
		order.setOrderStatus(OrderStatus.valueOf("SHIPPED"));
		order.setShippingAddress(shippingAddress);
		order.setBillingAddress(billingAddress);
		order.setCreatedAt(Timestamp.from(Instant.now()));
		order.setUpdatedAt(Timestamp.from(Instant.now()));

		return Optional.of(order);
	}

	public static Optional<Order> createOrder004() {
		Order order = new Order();
		User user = createUser001().orElseThrow();
		Address shippingAddress = createAddress001().orElseThrow();
		Address billingAddress = createAddress001().orElseThrow();

		order.setId(4L);
		order.setUser(user);
		order.setTotalAmount(new BigDecimal("39.99"));
		order.setOrderStatus(OrderStatus.valueOf("PAID"));
		order.setShippingAddress(shippingAddress);
		order.setBillingAddress(billingAddress);
		order.setCreatedAt(Timestamp.from(Instant.now()));
		order.setUpdatedAt(Timestamp.from(Instant.now()));

		return Optional.of(order);
	}

	public static Optional<Order> createOrder005() {
		Order order = new Order();
		User user = createUser002().orElseThrow();
		Address shippingAddress = createAddress002().orElseThrow();
		Address billingAddress = createAddress002().orElseThrow();

		order.setId(5L);
		order.setUser(user);
		order.setTotalAmount(new BigDecimal("99.99"));
		order.setOrderStatus(OrderStatus.valueOf("SHIPPED"));
		order.setShippingAddress(shippingAddress);
		order.setBillingAddress(billingAddress);
		order.setCreatedAt(Timestamp.from(Instant.now()));
		order.setUpdatedAt(Timestamp.from(Instant.now()));

		return Optional.of(order);
	}

	public static Optional<List<Order>> createListOfOrdersByUserId1() {
		Order order1 = createOrder001().orElseThrow();
		Order order4 = createOrder004().orElseThrow();

		return Optional.of(List.of(order1, order4));
	}

	public static Optional<List<Order>> createListOfOrdersByUserId2() {
		Order order2 = createOrder002().orElseThrow();
		Order order5 = createOrder005().orElseThrow();

		return Optional.of(List.of(order2, order5));
	}

	public static Optional<List<Order>> createListOfOrdersByUserId3() {
		Order order3 = createOrder003().orElseThrow();

		return Optional.of(List.of(order3));
	}

	public static Optional<List<Order>> createListOfOrdersByOrderStatus() {
		Order order2 = createOrder002().orElseThrow();
		Order order4 = createOrder004().orElseThrow();

		return Optional.of(List.of(order2, order4));	
	}

	public static Optional<List<Order>> createListOfOrdersByPaymentStatus() {
		Order order2 = createOrder002().orElseThrow();
		Order order3 = createOrder003().orElseThrow();
		Order order4 = createOrder004().orElseThrow();
		Order order5 = createOrder005().orElseThrow();

		return Optional.of(List.of(order2, order3, order4, order5));	
	}

	public static Optional<List<Order>> createListOfOrdersByShippingAddressId1() {
		Order order1 = createOrder001().orElseThrow();
		Order order4 = createOrder004().orElseThrow();

		return Optional.of(List.of(order1, order4));	
	}

	public static Optional<List<Order>> createListOfOrdersByBillingAddressId1() {
		Order order1 = createOrder001().orElseThrow();
		Order order4 = createOrder004().orElseThrow();

		return Optional.of(List.of(order1, order4));	
	}

	public static OrderDto createOrderDtoCreationRequest() {
		OrderDto orderDto = new OrderDto();
		orderDto.setUserId(3L);
		orderDto.setTotalAmount(new BigDecimal("39.99"));
		orderDto.setOrderStatus("PENDING");
		orderDto.setShippingAddressId(3L);
		orderDto.setBillingAddressId(3L);

		return orderDto;	
	}

	public static OrderDto createOrderDtoUpdateRequest() {
		// ACTUALIZA EL ORDER CON ID 1
		// CAMBIA EL TOTAL AMOUNT
		OrderDto orderDto = new OrderDto();
		orderDto.setUserId(1L);
		orderDto.setTotalAmount(new BigDecimal("119.99"));
		orderDto.setOrderStatus("PENDING");
		orderDto.setShippingAddressId(1L);
		orderDto.setBillingAddressId(1L);

		return orderDto;	
	}

	public static Order createOrderCreationResponse() {
		Order order = new Order();
		User user = createUser003().orElseThrow();
		Address shippingAddress = createAddress003().orElseThrow();
		Address billingAddress = createAddress003().orElseThrow();

		order.setId(6L);
		order.setUser(user);
		order.setTotalAmount(new BigDecimal("39.99"));
		order.setOrderStatus(OrderStatus.valueOf("PENDING"));
		order.setShippingAddress(shippingAddress);
		order.setBillingAddress(billingAddress);
		order.setCreatedAt(Timestamp.from(Instant.now()));
		order.setUpdatedAt(Timestamp.from(Instant.now()));

		return order;
	}

	public static Order createOrderUpdateResponse() {
		Order order = createOrder001().orElseThrow();
		order.setTotalAmount(new BigDecimal("119.99"));

		return order;
	}

	public static UpdateOrderStatusDto createOrderStatusUpdateRequest() {
		UpdateOrderStatusDto updateOrderStatus = new UpdateOrderStatusDto();
		updateOrderStatus.setOrderId(1L);
		updateOrderStatus.setOrderStatus("PAID");

		return updateOrderStatus;
	}

	public static Order createOrderStatusUpdateResponse() {
		Order order = new Order();
		User user = createUser001().orElseThrow();
		Address shippingAddress = createAddress001().orElseThrow();
		Address billingAddress = createAddress001().orElseThrow();

		order.setId(1L);
		order.setUser(user);
		order.setTotalAmount(new BigDecimal("199.99"));
		order.setOrderStatus(OrderStatus.valueOf("PAID"));
		order.setShippingAddress(shippingAddress);
		order.setBillingAddress(billingAddress);
		order.setCreatedAt(Timestamp.from(Instant.now()));
		order.setUpdatedAt(Timestamp.from(Instant.now()));

		return order;
	}

	public static Order createPaymentStatusUpdateResponse() {
		Order order = new Order();
		User user = createUser001().orElseThrow();
		Address shippingAddress = createAddress001().orElseThrow();
		Address billingAddress = createAddress001().orElseThrow();

		order.setId(1L);
		order.setUser(user);
		order.setTotalAmount(new BigDecimal("199.99"));
		order.setOrderStatus(OrderStatus.valueOf("PENDING"));
		order.setShippingAddress(shippingAddress);
		order.setBillingAddress(billingAddress);
		order.setCreatedAt(Timestamp.from(Instant.now()));
		order.setUpdatedAt(Timestamp.from(Instant.now()));

		return order;
	}

}
