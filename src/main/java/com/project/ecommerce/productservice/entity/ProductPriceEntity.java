package com.project.ecommerce.productservice.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProductPrice")
public class ProductPriceEntity {

    @Id
    private String skuCode;
    @Column
    private Double price;
    @Column
    private String currency;

}
