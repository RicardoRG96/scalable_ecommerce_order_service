package com.ricardo.scalable.ecommerce.platform.order_service.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Cart;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.CartItem;
import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Order;
import com.ricardo.scalable.ecommerce.platform.libs_common.enums.OrderStatus;
import com.ricardo.scalable.ecommerce.platform.libs_common.exceptions.AddressNotFoundException;
import com.ricardo.scalable.ecommerce.platform.libs_common.exceptions.OrderNotFoundException;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.AddressRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.CartRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.DiscountRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.OrderRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.ProductSkuRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.UserRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdateOrderAddressesDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdateOrderStatusDto;

import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.OrderServiceImplTestData.*;
import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils.UserTestData.*;
import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils.AddressTestData.*;
import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils.CartTestData.*;
import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils.ProductSkuTestData.*;
import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils.DiscountTestData.*;

@SpringBootTest
public class OrderServiceImplTest {

	@MockitoBean
	private OrderRepository orderRepository;

	@MockitoBean
	private UserRepository userRepository;

	@MockitoBean
	private AddressRepository addressRepository;

	@MockitoBean
	private CartRepository cartRepository;

	@MockitoBean
	private ProductSkuRepository productSkuRepository;

	@MockitoBean
	private DiscountRepository discountRepository;

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
		when(orderRepository.findByOrderStatus(OrderStatus.PAID)).thenReturn(createListOfOrdersByOrderStatus());
		
		Optional<List<Order>> orders = orderService.findByOrderStatus(OrderStatus.PAID);

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
	void createOrderFromCart_whenUserAndAddressAndCartExist_thenCreateOrder() {
		when(userRepository.findById(3L)).thenReturn(createUser003());
		when(addressRepository.findById(3L)).thenReturn(createAddress003());
		when(cartRepository.findByUserId(3L)).thenReturn(createCart003());
		when(productSkuRepository.findById(5L)).thenReturn(createProductSku005());
		when(discountRepository.findActiveDiscountByProductSkuId(5L, LocalDateTime.now())).thenReturn(createDiscount003());
		doNothing().when(cartRepository).delete(createCart003().orElseThrow());
		when(orderRepository.save(any())).thenReturn(createOrderCreationResponse());

		OrderDto orderDto = createOrderDtoCreationRequest();
		Order createdOrder = orderService.createOrderFromCart(orderDto);

		assertAll(
			() -> assertNotNull(createdOrder),
			() -> assertEquals(6L, createdOrder.getId()),
			() -> assertEquals(3L, createdOrder.getUser().getId()),
			() -> assertEquals(3L, createdOrder.getShippingAddress().getId()),
			() -> assertEquals(3L, createdOrder.getBillingAddress().getId()),
			() -> assertEquals(new BigDecimal("80"), createdOrder.getTotalAmount()),
			() -> assertEquals(OrderStatus.valueOf("PENDING"), createdOrder.getOrderStatus())
		);
	}

	@Test
	void updateOrderAddress_whenOrderAndAddressExist_thenUpdateOrderAddress() {
		when(orderRepository.findById(1L)).thenReturn(createOrder001());
		when(addressRepository.findById(2L)).thenReturn(createAddress002());
		when(orderRepository.save(any())).thenReturn(createOrderAddressUpdateResponse());

		UpdateOrderAddressesDto updateOrderAddresses = createOrderAddressUpdateRequest();
		Order updatedOrder = orderService.updateOrderAddresses(updateOrderAddresses);

		assertAll(
			() -> assertNotNull(updatedOrder),
			() -> assertEquals(1L, updatedOrder.getId()),
			() -> assertEquals(1L, updatedOrder.getUser().getId()),
			() -> assertEquals(2L, updatedOrder.getShippingAddress().getId()),
			() -> assertEquals(2L, updatedOrder.getBillingAddress().getId()),
			() -> assertEquals(new BigDecimal("199.99"), updatedOrder.getTotalAmount())
		);
	}

	@Test
	void testUpdateOrderAddress_whenOrderDoesNotExist_thenThrowException() {
		when(orderRepository.findById(100L)).thenReturn(Optional.empty());

		UpdateOrderAddressesDto updateOrderAddresses = createOrderAddressUpdateRequest();
		updateOrderAddresses.setOrderId(100L);

		assertThrows(OrderNotFoundException.class, () -> {
			orderService.updateOrderAddresses(updateOrderAddresses);
		});
	}

	@Test
	void testUpdateOrderAddress_whenAddressDoesNotExist_thenThrowException() {
		when(orderRepository.findById(1L)).thenReturn(createOrder001());
		when(addressRepository.findById(200L)).thenReturn(Optional.empty());

		UpdateOrderAddressesDto updateOrderAddresses = createOrderAddressUpdateRequest();
		updateOrderAddresses.setOrderId(1L);
		updateOrderAddresses.setShippingAddressId(200L);
		updateOrderAddresses.setBillingAddressId(200L);

		assertThrows(AddressNotFoundException.class, () -> {
			orderService.updateOrderAddresses(updateOrderAddresses);
		});
	}

    @Test
	void testUpdateOrderStatus() {
		when(orderRepository.findById(1L)).thenReturn(createOrder001());
		when(orderRepository.save(any())).thenReturn(createOrderStatusUpdateResponse());

		UpdateOrderStatusDto updateOrderStatusDto = createOrderStatusUpdateRequest();
		Order updatedOrder = orderService.updateOrderStatus(updateOrderStatusDto);

		assertAll(
			() -> assertNotNull(updatedOrder),
			() -> assertEquals(1L, updatedOrder.getId()),
			() -> assertEquals(1L, updatedOrder.getUser().getId()),
			() -> assertEquals(1L, updatedOrder.getShippingAddress().getId()),
			() -> assertEquals(1L, updatedOrder.getBillingAddress().getId()),
			() -> assertEquals(new BigDecimal("199.99"), updatedOrder.getTotalAmount()),
			() -> assertEquals(OrderStatus.valueOf("PAID"), updatedOrder.getOrderStatus())
		);
	}

    @Test
	void testDelete() {
		doNothing().when(orderRepository).deleteById(5L);

		orderService.delete(5L);

		verify(orderRepository, times(1)).deleteById(5L);
	}

}
