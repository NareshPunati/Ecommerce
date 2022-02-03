package com.project.ecommerce.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LoginModel {

    @Email(message = "enter a valid email")
    private String email;
    private String password;
}
