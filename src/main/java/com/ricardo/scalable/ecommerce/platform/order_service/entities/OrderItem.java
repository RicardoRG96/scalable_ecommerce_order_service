package com.ricardo.scalable.ecommerce.platform.order_service.entities;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.ProductSku;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_sku_id")
    @NotNull
    private ProductSku productSku;

    @NotNull
    private int quantity;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    private Timestamp  updatedAt;

}
