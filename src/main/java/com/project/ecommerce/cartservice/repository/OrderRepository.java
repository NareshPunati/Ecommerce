package com.project.ecommerce.cartservice.repository;

import com.project.ecommerce.cartservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
