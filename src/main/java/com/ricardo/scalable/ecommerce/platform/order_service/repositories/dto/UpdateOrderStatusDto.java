package com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateOrderStatusDto {

    @NotNull
    @Min(1)
    private Long orderId;

    @NotBlank
    private String orderStatus;

    public UpdateOrderStatusDto() {
    }

    public UpdateOrderStatusDto(Long orderId, String orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }  

}
