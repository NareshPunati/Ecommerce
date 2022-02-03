package com.project.ecommerce.productservice.model;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceModel {

    private String skuCode;
    private Double price;
    private String currency;
}
