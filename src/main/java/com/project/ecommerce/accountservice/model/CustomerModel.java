package com.project.ecommerce.accountservice.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CustomerModel {

    private long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private long mobileNumber;
    private String password;
}
