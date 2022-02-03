package com.project.ecommerce.productservice.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class ProductEntity {

    @Id
    private String productCode;
    @Column
    private String productName;
    @Column
    private String description;

}
