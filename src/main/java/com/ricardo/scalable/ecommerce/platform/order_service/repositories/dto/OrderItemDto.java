package com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto;

import java.math.BigDecimal;

public class OrderItemDto {

    private Long OrderId;

    private Long productSkuId;

    private int quantity;

    private BigDecimal unitPrice;

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
