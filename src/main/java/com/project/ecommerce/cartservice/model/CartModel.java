package com.project.ecommerce.cartservice.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartModel {

    private Long id;
    private String productCode;
    private String skuCode;
    private int quantity;

}
