package com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto;

import java.math.BigDecimal;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemDto {

    @NotNull
    @Min(1)
    private Long OrderId;

    @NotNull
    @Min(1)
    private Long productSkuId;

    @Min(1)
    private int quantity;

    @NotNull
    private BigDecimal unitPrice;

    @Nullable
    private Long discountId;

    public OrderItemDto() {
    }

    public OrderItemDto(Long orderId, Long productSkuId, int quantity, BigDecimal unitPrice, Long discountId) {
        OrderId = orderId;
        this.productSkuId = productSkuId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discountId = discountId;
    }

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public Long getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

}
