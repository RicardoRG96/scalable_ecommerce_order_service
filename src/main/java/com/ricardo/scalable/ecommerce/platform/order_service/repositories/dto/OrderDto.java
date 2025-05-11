package com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderDto {

    @NotNull
    @Min(1)
    private Long userId;

    @NotNull
    private BigDecimal totalAmount;

    @NotBlank
    private String orderStatus;

    @NotNull
    @Min(1)
    private Long shippingAddressId;

    @NotNull
    @Min(1)
    private Long billingAddressId;

    public OrderDto() {
    }

    public OrderDto(Long userId, BigDecimal totalAmount, String orderStatus,
            Long shippingAddressId, Long billingAddressId) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.shippingAddressId = shippingAddressId;
        this.billingAddressId = billingAddressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public Long getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(Long billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

}
