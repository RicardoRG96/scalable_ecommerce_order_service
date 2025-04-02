package com.ricardo.scalable.ecommerce.platform.order_service.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Order;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.AddressRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.OrderRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.UserRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdateOrderStatusDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdatePaymentStatusDto;

import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.OrderServiceImplTestData.*;
import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils.UserTestData.*;
import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils.AddressTestData.*;

@SpringBootTest
public class OrderServiceImplTest {

	@MockitoBean
	private OrderRepository orderRepository;

	@MockitoBean
	private UserRepository userRepository;

	@MockitoBean
	private AddressRepository addressRepository;

	@Autowired
	private OrderService orderService;	

	@Test
	void testFindById() {
		when(orderRepository.findById(1L)).thenReturn(createOrder001());
		
		Optional<Order> order = orderService.findById(1L);

		assertAll(
			() -> assertTrue(order.isPresent()),
			() -> assertEquals(1L, order.orElseThrow().getId()),
			() -> assertEquals(1L, order.orElseThrow().getUser().getId()),
			() -> assertEquals(1L, order.orElseThrow().getShippingAddress().getId()),
			() -> assertEquals(1L, order.orElseThrow().getBillingAddress().getId()),
			() -> assertEquals(new BigDecimal("199.99"), order.orElseThrow().getTotalAmount())
		);
	}

	@Test
	void testFindByUserId() {
		when(orderRepository.findByUserId(1L)).thenReturn(createListOfOrdersByUserId1());
		
		Optional<List<Order>> orders = orderService.findByUserId(1L);

		assertAll(
			() -> assertTrue(orders.isPresent()),
			() -> assertEquals(1L, orders.orElseThrow().get(0).getId()),
			() -> assertEquals(1L, orders.orElseThrow().get(0).getUser().getId()),
			() -> assertEquals(1L, orders.orElseThrow().get(0).getShippingAddress().getId()),
			() -> assertEquals(1L, orders.orElseThrow().get(0).getBillingAddress().getId()),
			() -> assertEquals(new BigDecimal("199.99"), orders.orElseThrow().get(0).getTotalAmount()),
			() -> assertEquals(4L, orders.orElseThrow().get(1).getId()),
			() -> assertEquals(1L, orders.orElseThrow().get(1).getUser().getId()),
			() -> assertEquals(1L, orders.orElseThrow().get(1).getShippingAddress().getId()),
			() -> assertEquals(1L, orders.orElseThrow().get(1).getBillingAddress().getId()),
			() -> assertEquals(new BigDecimal("39.99"), orders.orElseThrow().get(1).getTotalAmount())
		);
	}

    @Test
	void testFindByIdAndUserId() {
		when(orderRepository.findByIdAndUserId(2L, 2L)).thenReturn(createOrder002());
		
		Optional<Order> order = orderService.findByIdAndUserId(2L, 2L);

		assertAll(
			() -> assertTrue(order.isPresent()),
			() -> assertEquals(2L, order.orElseThrow().getId()),
			() -> assertEquals(2L, order.orElseThrow().getUser().getId()),
			() -> assertEquals(2L, order.orElseThrow().getShippingAddress().getId()),
			() -> assertEquals(2L, order.orElseThrow().getBillingAddress().getId()),
			() -> assertEquals(new BigDecimal("49.99"), order.orElseThrow().getTotalAmount())
		);
	}

	@Test
	void testFindByOrderStatus() {
		when(orderRepository.findByOrderStatus("PAID")).thenReturn(createListOfOrdersByOrderStatus());
		
		Optional<List<Order>> orders = orderService.findByOrderStatus("PAID");

		assertAll(
			() -> assertTrue(orders.isPresent()),
			() -> assertEquals(2L, orders.orElseThrow().get(0).getId()),
			() -> assertEquals(2L, orders.orElseThrow().get(0).getUser().getId()),
			() -> assertEquals(2L, orders.orElseThrow().get(0).getShippingAddress().getId()),
			() -> assertEquals(2L, orders.orElseThrow().get(0).getBillingAddress().getId()),
			() -> assertEquals(new BigDecimal("49.99"), orders.orElseThrow().get(0).getTotalAmount()),
			() -> assertEquals(4L, orders.orElseThrow().get(1).getId()),
			() -> assertEquals(1L, orders.orElseThrow().get(1).getUser().getId()),
			() -> assertEquals(1L, orders.orElseThrow().get(1).getShippingAddress().getId()),
			() -> assertEquals(1L, orders.orElseThrow().get(1).getBillingAddress().getId()),
			() -> assertEquals(new BigDecimal("39.99"), orders.orElseThrow().get(1).getTotalAmount())
		);
	}

}
