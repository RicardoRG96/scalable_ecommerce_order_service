package com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderDto {

    @NotNull
    @Min(1)
    private Long userId;

    @NotNull
    @Min(1)
    private Long shippingAddressId;

    @NotNull
    @Min(1)
    private Long billingAddressId;

    public OrderDto() {
    }

    public OrderDto(Long userId,
            Long shippingAddressId, Long billingAddressId) {
        this.userId = userId;
        this.shippingAddressId = shippingAddressId;
        this.billingAddressId = billingAddressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
