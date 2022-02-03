package com.project.ecommerce.cartservice.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ItemsOrderedModel {

    private Long itemId;
    private String itemStatus;
    private String productCode;
    private String skuCode;
    private String quantity;
    private String price;
}
