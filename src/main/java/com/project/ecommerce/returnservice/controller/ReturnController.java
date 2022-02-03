package com.project.ecommerce.returnservice.controller;

import com.project.ecommerce.accountservice.service.AuthenticationService;
import com.project.ecommerce.returnservice.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/return")
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    @PostMapping("item/{itemId}")
    public ResponseEntity<String> returnItem(@PathVariable Long itemId, @RequestHeader String encryptedToken){
        AuthenticationService authenticationService = new AuthenticationService();
        if(authenticationService.validToken(encryptedToken)) {
            String message = returnService.returnItem(itemId, authenticationService.getTokenDetails(encryptedToken));
            if(message.contains("RETURN ACCEPTED"))
                return ResponseEntity.status(HttpStatus.OK).body(message);
            if(message.equals("INVALID ITEM ID"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
            if(message.equals("EMAIL NOT FOUND"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
            if(message.equals("ALREADY RETURNED"))
                return ResponseEntity.status(HttpStatus.OK).body(message);
            else
                return ResponseEntity.status(HttpStatus.OK).body("Return Process Will Start Soon");

        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token ");
    }
}
