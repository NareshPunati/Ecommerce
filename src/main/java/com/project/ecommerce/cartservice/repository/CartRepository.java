package com.project.ecommerce.cartservice.repository;

import com.project.ecommerce.cartservice.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

}
