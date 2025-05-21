package com.ricardo.scalable.ecommerce.platform.order_service.services.testData;

import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdateOrderAddressesDto;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.UpdateOrderStatusDto;

public class OrderControllerTestData {
	
	public static OrderDto createOrderCreationDto() {
		OrderDto orderDto = new OrderDto();
		orderDto.setUserId(2L);
		orderDto.setShippingAddressId(2L);
		orderDto.setBillingAddressId(2L);

		return orderDto;
	}

	public static UpdateOrderAddressesDto createOrderUpdateAddressesDto() {
		UpdateOrderAddressesDto updateOrderAddressesDto = new UpdateOrderAddressesDto();
		updateOrderAddressesDto.setOrderId(1L);
		updateOrderAddressesDto.setShippingAddressId(3L);
		updateOrderAddressesDto.setBillingAddressId(3L);

		return updateOrderAddressesDto;
	}

	public static UpdateOrderStatusDto createUpdateOrderStatusDto() {
		UpdateOrderStatusDto updateOrderStatus = new UpdateOrderStatusDto();
		updateOrderStatus.setOrderId(2L);
		updateOrderStatus.setOrderStatus("SHIPPED");

		return updateOrderStatus;
	}

}