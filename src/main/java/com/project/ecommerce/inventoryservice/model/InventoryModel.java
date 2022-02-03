package com.project.ecommerce.inventoryservice.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryModel {

    private Long id;
    private String skuCode;
    private int quantityAvailable;
}
