package com.ricardo.scalable.ecommerce.platform.order_service.services;

import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderItemDto;

public interface StockService {

    void verifyStock(OrderItemDto orderItemDto);

}
