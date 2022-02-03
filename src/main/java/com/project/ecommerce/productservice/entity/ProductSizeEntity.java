package com.project.ecommerce.productservice.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProductSize")
public class ProductSizeEntity {

    @Id
    private String skuCode;
    @Column
    private String productCode;
    @Column
    private String size;

}
