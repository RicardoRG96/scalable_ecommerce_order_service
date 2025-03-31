package com.ricardo.scalable.ecommerce.platform.order_service.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.Discount;

public interface DiscountRepository extends CrudRepository<Discount, Long> {
}
