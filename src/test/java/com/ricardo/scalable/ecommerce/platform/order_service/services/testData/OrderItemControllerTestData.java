package com.ricardo.scalable.ecommerce.platform.order_service.services.testData;

import java.math.BigDecimal;

import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderItemDto;

public class OrderItemControllerTestData {

    public static OrderItemDto createOrderItemCreationDto() {
		OrderItemDto orderItemDto = new OrderItemDto();
		orderItemDto.setOrderId(2L);
		orderItemDto.setProductSkuId(3L);
		orderItemDto.setQuantity(2);
		orderItemDto.setUnitPrice(new BigDecimal("29.99"));
		orderItemDto.setDiscountId(2L);

		return orderItemDto;
	}
	

	public static OrderItemDto createOrderItemUpdateDto() {
		OrderItemDto orderItemDto = new OrderItemDto();
		orderItemDto.setOrderId(2L);
		orderItemDto.setProductSkuId(3L);
		orderItemDto.setQuantity(4);
		orderItemDto.setUnitPrice(new BigDecimal("29.99"));
		orderItemDto.setDiscountId(3L);

		return orderItemDto;
	}

}
