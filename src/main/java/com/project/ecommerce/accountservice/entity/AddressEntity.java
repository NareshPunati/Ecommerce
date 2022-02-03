package com.project.ecommerce.accountservice.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "address")
@Entity
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
    @Column
    private long customerId;
    @Column
    private String line1;
    @Column
    private String line2;
    @Column
    private String postalCode;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private boolean shippingAddress;
    @Column
    private boolean billingAddress;

}
