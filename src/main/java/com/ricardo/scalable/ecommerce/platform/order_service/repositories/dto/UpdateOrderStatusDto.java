package com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto;

public class UpdateOrderStatusDto {

    private String orderStatus;

    public UpdateOrderStatusDto() {
    }

    public UpdateOrderStatusDto(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }  

}
