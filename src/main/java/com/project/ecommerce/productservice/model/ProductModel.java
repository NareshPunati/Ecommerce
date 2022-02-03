package com.project.ecommerce.productservice.model;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductModel {

    private String productCode;
    private String productName;
    private String description;
}
