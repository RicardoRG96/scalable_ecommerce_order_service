package com.ricardo.scalable.ecommerce.platform.order_service.repositories.dto;

import java.math.BigDecimal;

public class OrderDto {

    private Long userId;

    private BigDecimal totalAmount;

    private String orderStatus;

    private String paymentStatus;

    private Long shippingAddressId;

    private Long billingAddressId;

    public OrderDto() {
    }

    public OrderDto(Long userId, BigDecimal totalAmount, String orderStatus, String paymentStatus,
            Long shippingAddressId, Long billingAddressId) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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
