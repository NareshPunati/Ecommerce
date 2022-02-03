package com.project.ecommerce.accountservice.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressModel {

    private long addressId;
    private long customerId;
    private String line1;
    private String line2;
    private String postalCode;
    private String city;
    private String state;
    private boolean shippingAddress;
    private boolean billingAddress;
}
