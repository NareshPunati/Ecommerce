package com.project.ecommerce.inventoryservice.repository;

import com.project.ecommerce.inventoryservice.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    InventoryEntity findBySkuCode(String skuCode);

    List<InventoryEntity> findByskuCode(String skuCode);
}
