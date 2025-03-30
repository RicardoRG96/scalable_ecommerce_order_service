package com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto;

public class UpdatePaymentStatusDto {

    private String paymentStatus;

    public UpdatePaymentStatusDto() {
    }

    public UpdatePaymentStatusDto(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}
