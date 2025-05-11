package com.ricardo.scalable.ecommerce.platform.order_service.services.testData;

import java.math.BigDecimal;

import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdateOrderStatusDto;

public class OrderControllerTestData {
	
	public static OrderDto createOrderCreationDto() {
		OrderDto orderDto = new OrderDto();
		orderDto.setUserId(2L);
		orderDto.setTotalAmount(new BigDecimal("69.99"));
		orderDto.setOrderStatus("PENDING");
		orderDto.setShippingAddressId(2L);
		orderDto.setBillingAddressId(2L);

		return orderDto;
	}

	public static OrderDto createOrderUpdateDto() {
		OrderDto orderDto = new OrderDto();
		orderDto.setUserId(2L);
		orderDto.setTotalAmount(new BigDecimal("89.99"));
		orderDto.setOrderStatus("PENDING");
		orderDto.setShippingAddressId(2L);
		orderDto.setBillingAddressId(2L);

		return orderDto;
	}

	public static UpdateOrderStatusDto createUpdateOrderStatusDto() {
		UpdateOrderStatusDto updateOrderStatus = new UpdateOrderStatusDto();
		updateOrderStatus.setOrderId(2L);
		updateOrderStatus.setOrderStatus("SHIPPED");

		return updateOrderStatus;
	}

}