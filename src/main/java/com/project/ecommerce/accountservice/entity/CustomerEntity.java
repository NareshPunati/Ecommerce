package com.project.ecommerce.accountservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "customer")
@Entity
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;

    @Column
    @NotNull(message = "Name Should not be empty")
    private String firstName;

    @Column
    private String lastName;

    @Column
    @Email(message = "Please enter Valid email")
    private String email;

    @Column
    private long mobileNumber;

    @Column
    @NotNull(message = "Please Enter Strong Password")
    private String password;

}
