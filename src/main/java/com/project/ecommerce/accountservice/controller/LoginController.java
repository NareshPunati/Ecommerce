package com.project.ecommerce.accountservice.controller;

import com.project.ecommerce.accountservice.model.LoginModel;
import com.project.ecommerce.accountservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginModel loginModel)  {
        String email = loginModel.getEmail();
        String password = loginModel.getPassword();

        ResponseEntity<String> e =loginService.login(email, password);
        return e;
    }

}
