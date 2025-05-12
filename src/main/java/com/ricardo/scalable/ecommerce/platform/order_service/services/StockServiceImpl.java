package com.ricardo.scalable.ecommerce.platform.order_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardo.scalable.ecommerce.platform.libs_common.exceptions.InsufficientStockException;
import com.ricardo.scalable.ecommerce.platform.libs_common.exceptions.ResourceNotFoundException;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.ProductSkuRepository;
import com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto.OrderItemDto;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private ProductSkuRepository productSkuRepository;

    @Override
    public void verifyStock(OrderItemDto orderItemDto) {
        Long productSkuId = orderItemDto.getProductSkuId();
        int quantity = orderItemDto.getQuantity();

        productSkuRepository.findById(productSkuId)
            .ifPresentOrElse(productSku -> {
                if (productSku.getStock() < quantity) {
                    throw new InsufficientStockException("Not enough stock for product SKU: " + productSkuId);
                }
            }, () -> {
                throw new ResourceNotFoundException("Product SKU not found: " + productSkuId);
            });
    }

}
