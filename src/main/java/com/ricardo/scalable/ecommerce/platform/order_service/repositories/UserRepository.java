package com.ricardo.scalable.ecommerce.platform.order_service.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ricardo.scalable.ecommerce.platform.libs_common.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
