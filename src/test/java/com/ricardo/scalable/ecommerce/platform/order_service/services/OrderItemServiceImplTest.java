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

import com.ricardo.scalable.ecommerce.platform.order_service.entities.OrderItem;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.DiscountRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.OrderItemRespository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.OrderRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.ProductSkuRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderItemDto;

import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.OrderItemServiceImplTestData.*;
import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.OrderServiceImplTestData.*;
import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils.DiscountTestData.*;
import static com.ricardo.scalable.ecommerce.platform.order_service.services.testData.utils.ProductSkuTestData.*;

@SpringBootTest
public class OrderItemServiceImplTest {

    @MockitoBean
	private OrderItemRespository orderItemRepository;

	@MockitoBean
	private OrderRepository orderRepository;

	@MockitoBean
	private ProductSkuRepository productSkuRepository;

	@MockitoBean
	private DiscountRepository discountRepository;

	@Autowired
	private OrderItemService orderItemService;

	@Test
	void testFindById() {
		when(orderItemRepository.findById(1L)).thenReturn(createOrderItem001());

		Optional<OrderItem> orderItem = orderItemService.findById(1L);

		assertAll(
			() -> assertTrue(orderItem.isPresent()),
			() -> assertEquals(1L, orderItem.orElseThrow().getId()),
			() -> assertEquals(1L, orderItem.orElseThrow().getOrder().getId()),
			() -> assertEquals(1L, orderItem.orElseThrow().getProductSku().getId()),
			() -> assertEquals(1L, orderItem.orElseThrow().getDiscount().getId()),
			() -> assertEquals(1, orderItem.orElseThrow().getQuantity()),
			() -> assertEquals(new BigDecimal(1000.00), orderItem.orElseThrow().getUnitPrice())
		);
	}

	@Test
	void testFindByOrderId() {
		when(orderItemRepository.findByOrderId(1L)).thenReturn(createListOfOrderItemByOrderId1());

		Optional<List<OrderItem>> orderItems = orderItemService.findByOrderId(1L);

		assertAll(
			() -> assertTrue(orderItems.isPresent()),
			() -> assertEquals(2, orderItems.orElseThrow().size()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(0).getId()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(0).getOrder().getId()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(0).getProductSku().getId()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(0).getDiscount().getId()),
			() -> assertEquals(1, orderItems.orElseThrow().get(0).getQuantity()),
			() -> assertEquals(new BigDecimal(1000.00), orderItems.orElseThrow().get(0).getUnitPrice()),
			() -> assertEquals(2L, orderItems.orElseThrow().get(1).getId()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(1).getOrder().getId()),
			() -> assertEquals(2L, orderItems.orElseThrow().get(1).getProductSku().getId()),
			() -> assertNull(orderItems.orElseThrow().get(1).getDiscount()),
			() -> assertEquals(1, orderItems.orElseThrow().get(1).getQuantity()),
			() -> assertEquals(new BigDecimal(899.99), orderItems.orElseThrow().get(1).getUnitPrice())
		);
	}

    @Test
	void testFindByProductSkuId() {
		when(orderItemRepository.findByProductSkuId(1L)).thenReturn(createListOfOrderItemByProductSkuId1());

		Optional<List<OrderItem>> orderItems = orderItemService.findByProductSkuId(1L);

		assertAll(
			() -> assertTrue(orderItems.isPresent()),
			() -> assertEquals(2, orderItems.orElseThrow().size()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(0).getId()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(0).getOrder().getId()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(0).getProductSku().getId()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(0).getDiscount().getId()),
			() -> assertEquals(1, orderItems.orElseThrow().get(0).getQuantity()),
			() -> assertEquals(new BigDecimal(1000.00), orderItems.orElseThrow().get(0).getUnitPrice()),
			() -> assertEquals(6L, orderItems.orElseThrow().get(1).getId()),
			() -> assertEquals(4L, orderItems.orElseThrow().get(1).getOrder().getId()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(1).getProductSku().getId()),
			() -> assertNull(orderItems.orElseThrow().get(1).getDiscount()),
			() -> assertEquals(1, orderItems.orElseThrow().get(1).getQuantity()),
			() -> assertEquals(new BigDecimal(1000.00), orderItems.orElseThrow().get(1).getUnitPrice())
		);
	}

	@Test
	void testFindByOrderIdAndProductSkuId() {
		when(orderItemRepository.findByOrderIdAndProductSkuId(2L, 3L)).thenReturn(createOrderItem003());

		Optional<OrderItem> orderItems = orderItemService.findByOrderIdAndProductSkuId(2L, 3L);

		assertAll(
			() -> assertTrue(orderItems.isPresent()),
			() -> assertEquals(3L, orderItems.orElseThrow().getId()),
			() -> assertEquals(2L, orderItems.orElseThrow().getOrder().getId()),
			() -> assertEquals(3L, orderItems.orElseThrow().getProductSku().getId()),
			() -> assertEquals(2L, orderItems.orElseThrow().getDiscount().getId()),
			() -> assertEquals(1, orderItems.orElseThrow().getQuantity()),
			() -> assertEquals(new BigDecimal(1299.99), orderItems.orElseThrow().getUnitPrice())
		);
	}

    @Test
	void testFindByUnitPrice() {
		when(orderItemRepository.findByUnitPrice(new BigDecimal(1000.00))).thenReturn(createListOfOrderItemByUnitPrice());

		Optional<List<OrderItem>> orderItems = orderItemService.findByUnitPrice(new BigDecimal(1000.00));

		assertAll(
			() -> assertTrue(orderItems.isPresent()),
			() -> assertEquals(2, orderItems.orElseThrow().size()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(0).getId()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(0).getOrder().getId()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(0).getProductSku().getId()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(0).getDiscount().getId()),
			() -> assertEquals(1, orderItems.orElseThrow().get(0).getQuantity()),
			() -> assertEquals(new BigDecimal(1000.00), orderItems.orElseThrow().get(0).getUnitPrice()),
			() -> assertEquals(6L, orderItems.orElseThrow().get(1).getId()),
			() -> assertEquals(4L, orderItems.orElseThrow().get(1).getOrder().getId()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(1).getProductSku().getId()),
			() -> assertNull(orderItems.orElseThrow().get(1).getDiscount()),
			() -> assertEquals(1, orderItems.orElseThrow().get(1).getQuantity()),
			() -> assertEquals(new BigDecimal(1000.00), orderItems.orElseThrow().get(1).getUnitPrice())
		);
	}

	@Test
	void testFindByDiscountId() {
		when(orderItemRepository.findByDiscountId(1L)).thenReturn(createListOfOrderItemByDiscountId1());

		Optional<List<OrderItem>> orderItems = orderItemService.findByDiscountId(1L);

		assertAll(
			() -> assertTrue(orderItems.isPresent()),
			() -> assertEquals(2, orderItems.orElseThrow().size()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(0).getId()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(0).getOrder().getId()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(0).getProductSku().getId()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(0).getDiscount().getId()),
			() -> assertEquals(1, orderItems.orElseThrow().get(0).getQuantity()),
			() -> assertEquals(new BigDecimal(1000.00), orderItems.orElseThrow().get(0).getUnitPrice()),
			() -> assertEquals(7L, orderItems.orElseThrow().get(1).getId()),
			() -> assertEquals(5L, orderItems.orElseThrow().get(1).getOrder().getId()),
			() -> assertEquals(6L, orderItems.orElseThrow().get(1).getProductSku().getId()),
			() -> assertEquals(1L, orderItems.orElseThrow().get(1).getDiscount().getId()),
			() -> assertEquals(1, orderItems.orElseThrow().get(1).getQuantity()),
			() -> assertEquals(new BigDecimal(50.00), orderItems.orElseThrow().get(1).getUnitPrice())
		);
	}

    @Test
	void testFindAll() {
		when(orderItemRepository.findAll()).thenReturn(createListOfOrderItems());

		List<OrderItem> orderItems = orderItemService.findAll();

		assertAll(
			() -> assertEquals(7, orderItems.size()),
			() -> assertEquals(1L, orderItems.get(0).getId()),
			() -> assertEquals(1L, orderItems.get(0).getOrder().getId()),
			() -> assertEquals(new BigDecimal(1000.00), orderItems.get(0).getUnitPrice()),
			() -> assertEquals(7L, orderItems.get(6).getId()),
			() -> assertEquals(5L, orderItems.get(6).getOrder().getId()),
			() -> assertEquals(new BigDecimal(50.00), orderItems.get(6).getUnitPrice())
		);
	}

}
