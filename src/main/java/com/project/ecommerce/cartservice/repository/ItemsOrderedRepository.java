package com.project.ecommerce.cartservice.repository;

import com.project.ecommerce.cartservice.entity.ItemsOrderedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsOrderedRepository extends JpaRepository<ItemsOrderedEntity, Long> {

}
