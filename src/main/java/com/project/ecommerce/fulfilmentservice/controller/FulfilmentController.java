package com.project.ecommerce.fulfilmentservice.controller;

import com.project.ecommerce.fulfilmentservice.service.FulfilmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fulfilment")
public class FulfilmentController {

    @Autowired
    private FulfilmentService fulfilmentService;

    @PostMapping("update-item-status")
    public ResponseEntity<String> updateItemStatus(@RequestParam Long itemId, @RequestParam String status){
        System.out.println(itemId+"  status: "+ status);
        String message = fulfilmentService.updateItemStatus(itemId, status);
        if(message.contains("Status Updated"))
            return ResponseEntity.status(HttpStatus.OK).body(message);
        if(message.equals("Invalid Id"))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Unknown");
    }
}
