package com.project.ecommerce.productservice.repository;

import com.project.ecommerce.productservice.entity.ProductSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, String> {

    ProductSizeEntity findBySkuCode(String skuCode);
}
