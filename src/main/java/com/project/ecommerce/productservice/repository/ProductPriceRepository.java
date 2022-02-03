package com.project.ecommerce.productservice.repository;

import com.project.ecommerce.productservice.entity.ProductPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, String> {

    ProductPriceEntity findBySkuCode(String skuCode);
}
