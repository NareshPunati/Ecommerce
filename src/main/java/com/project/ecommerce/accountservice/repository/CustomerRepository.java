package com.project.ecommerce.accountservice.repository;

import com.project.ecommerce.accountservice.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByEmail(String email);

    boolean existsByEmail(String email);
}
