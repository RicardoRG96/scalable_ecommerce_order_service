package com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdatePaymentStatusDto {

    @NotNull
    @Min(1)
    private Long orderId;

    @NotBlank
    private String paymentStatus;

    public UpdatePaymentStatusDto() {
    }

    public UpdatePaymentStatusDto(Long orderId, String paymentStatus) {
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}
