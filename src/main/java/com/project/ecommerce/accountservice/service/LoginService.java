package com.project.ecommerce.accountservice.service;

import com.project.ecommerce.accountservice.entity.CustomerEntity;
import com.project.ecommerce.accountservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AuthenticationService authenticationService;

    public ResponseEntity<String> login(String email, String password){
        CustomerEntity customerEntity = customerRepository.findByEmail(email);
        if(customerEntity!=null && new JasyptService().decryptPassword(customerEntity.getPassword()).equals(password)){
            return authenticationService.GenerateToken(email);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Email Or Password");  // }

        }
    }
}
