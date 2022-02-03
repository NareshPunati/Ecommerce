package com.project.ecommerce.accountservice.repository;

import com.project.ecommerce.accountservice.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity,Long> {

}
