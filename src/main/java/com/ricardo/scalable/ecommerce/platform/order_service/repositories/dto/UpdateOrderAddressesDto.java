package com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UpdateOrderAddressesDto {

    @NotNull
    @Min(1)
    private Long orderId;

    @NotNull
    @Min(1)
    private Long shippingAddressId;

    @NotNull
    @Min(1)
    private Long billingAddressId;

    public UpdateOrderAddressesDto() {
    }

    public UpdateOrderAddressesDto(Long orderId, Long shippingAddressId, Long billingAddressId) {
        this.orderId = orderId;
        this.shippingAddressId = shippingAddressId;
        this.billingAddressId = billingAddressId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    @Override
    public String toString() {
        return "UpdateOrderAddressesDto [orderId=" + orderId + ", shippingAddressId=" + shippingAddressId
                + ", billingAddressId=" + billingAddressId + "]";
    }

}
