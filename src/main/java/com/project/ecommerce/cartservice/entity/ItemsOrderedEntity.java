package com.project.ecommerce.cartservice.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Ordered")
public class ItemsOrderedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    @Column
    private String itemStatus;
    @Column
    private String productCode;
    @Column
    private String skuCode;
    @Column
    private int quantity;
    @Column
    private String price;
}
