package com.ricardo.scalable.ecommerce.platform.order_service.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {

    List<CartItem> findByUserId(Long userId);

    List<CartItem> findByCartId(Long cartId);

}
