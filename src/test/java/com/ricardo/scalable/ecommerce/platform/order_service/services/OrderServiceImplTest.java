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
import com.ricardo.scalable.ecommerce.platform.libs_common.enums.OrderStatus;
import com.ricardo.scalable.ecommerce.platform.libs_common.enums.PaymentStatus;
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

    @Test
	void testFindByPaymentStatus() {
		when(orderRepository.findByPaymentStatus("COMPLETED")).thenReturn(createListOfOrdersByPaymentStatus());
		
		Optional<List<Order>> orders = orderService.findByPaymentStatus("COMPLETED");

		assertAll(
			() -> assertTrue(orders.isPresent()),
			() -> assertEquals(4, orders.orElseThrow().size()),
			() -> assertEquals(2L, orders.orElseThrow().get(0).getId()),
			() -> assertEquals(2L, orders.orElseThrow().get(0).getUser().getId()),
			() -> assertEquals(2L, orders.orElseThrow().get(0).getShippingAddress().getId()),
			() -> assertEquals(2L, orders.orElseThrow().get(0).getBillingAddress().getId()),
			() -> assertEquals(new BigDecimal("49.99"), orders.orElseThrow().get(0).getTotalAmount()),
			() -> assertEquals(3L, orders.orElseThrow().get(1).getId()),
			() -> assertEquals(3L, orders.orElseThrow().get(1).getUser().getId()),
			() -> assertEquals(3L, orders.orElseThrow().get(1).getShippingAddress().getId()),
			() -> assertEquals(3L, orders.orElseThrow().get(1).getBillingAddress().getId()),
			() -> assertEquals(new BigDecimal("89.99"), orders.orElseThrow().get(1).getTotalAmount())
		);
	}

	@Test
	void testFindByShippingAddressId() {
		when(orderRepository.findByShippingAddressId(1L)).thenReturn(createListOfOrdersByShippingAddressId1());
		
		Optional<List<Order>> orders = orderService.findByShippingAddressId(1L);

		assertAll(
			() -> assertTrue(orders.isPresent()),
			() -> assertEquals(2, orders.orElseThrow().size()),
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
	void testFindByBillingAddressId() {
		when(orderRepository.findByBillingAddressId(1L)).thenReturn(createListOfOrdersByBillingAddressId1());
		
		Optional<List<Order>> orders = orderService.findByBillingAddressId(1L);

		assertAll(
			() -> assertTrue(orders.isPresent()),
			() -> assertEquals(2, orders.orElseThrow().size()),
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
	void testFindAll() {
		when(orderRepository.findAll()).thenReturn(createListOfOrders());
		
		List<Order> orders = orderService.findAll();

		assertAll(
			() -> assertEquals(5, orders.size()),
			() -> assertEquals(1L, orders.get(0).getId()),
			() -> assertEquals(1L, orders.get(0).getUser().getId()),
			() -> assertEquals(1L, orders.get(0).getShippingAddress().getId()),
			() -> assertEquals(1L, orders.get(0).getBillingAddress().getId()),
			() -> assertEquals(new BigDecimal("199.99"), orders.get(0).getTotalAmount()),
			() -> assertEquals(4L, orders.get(3).getId()),
			() -> assertEquals(1L, orders.get(3).getUser().getId()),
			() -> assertEquals(1L, orders.get(3).getShippingAddress().getId()),
			() -> assertEquals(1L, orders.get(3).getBillingAddress().getId()),
			() -> assertEquals(new BigDecimal("39.99"), orders.get(3).getTotalAmount())
		);
	}

    @Test
	void testSave() {
		when(userRepository.findById(3L)).thenReturn(createUser003());
		when(addressRepository.findById(3L)).thenReturn(createAddress003());
		when(orderRepository.save(any())).thenReturn(createOrderCreationResponse());

		OrderDto orderDto = createOrderDtoCreationRequest();
		Optional<Order> createdOrder = orderService.save(orderDto);

		assertAll(
			() -> assertTrue(createdOrder.isPresent()),
			() -> assertEquals(6L, createdOrder.orElseThrow().getId()),
			() -> assertEquals(3L, createdOrder.orElseThrow().getUser().getId()),
			() -> assertEquals(3L, createdOrder.orElseThrow().getShippingAddress().getId()),
			() -> assertEquals(3L, createdOrder.orElseThrow().getBillingAddress().getId()),
			() -> assertEquals(new BigDecimal("39.99"), createdOrder.orElseThrow().getTotalAmount()),
			() -> assertEquals(OrderStatus.valueOf("PENDING"), createdOrder.orElseThrow().getOrderStatus()),
			() -> assertEquals(PaymentStatus.valueOf("PENDING"), createdOrder.orElseThrow().getPaymentStatus())
		);
	}

	@Test
	void testUpdate() {
		when(orderRepository.findById(1L)).thenReturn(createOrder001());
		when(userRepository.findById(1L)).thenReturn(createUser001());
		when(addressRepository.findById(1L)).thenReturn(createAddress001());
		when(orderRepository.save(any())).thenReturn(createOrderUpdateResponse());

		OrderDto orderDto = createOrderDtoUpdateRequest();
		Optional<Order> updatedOrder = orderService.update(1L, orderDto);

		assertAll(
			() -> assertTrue(updatedOrder.isPresent()),
			() -> assertEquals(1L, updatedOrder.orElseThrow().getId()),
			() -> assertEquals(1L, updatedOrder.orElseThrow().getUser().getId()),
			() -> assertEquals(1L, updatedOrder.orElseThrow().getShippingAddress().getId()),
			() -> assertEquals(1L, updatedOrder.orElseThrow().getBillingAddress().getId()),
			() -> assertEquals(new BigDecimal("119.99"), updatedOrder.orElseThrow().getTotalAmount()),
			() -> assertEquals(OrderStatus.valueOf("PENDING"), updatedOrder.orElseThrow().getOrderStatus()),
			() -> assertEquals(PaymentStatus.valueOf("PENDING"), updatedOrder.orElseThrow().getPaymentStatus())
		);
	}

}
