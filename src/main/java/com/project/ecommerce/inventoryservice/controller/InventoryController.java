package com.project.ecommerce.inventoryservice.controller;

import com.project.ecommerce.inventoryservice.model.InventoryModel;
import com.project.ecommerce.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/add")
    public ResponseEntity<String> addInventory(@Valid @RequestBody InventoryModel inventoryModel, @RequestParam String skuCode){
        String message = inventoryService.addInventory(inventoryModel, skuCode);
        if(message.equals("INVENTORY ADDED"))
            return ResponseEntity.status(HttpStatus.CREATED).body("Inventory Added");
        else  return ResponseEntity.status(HttpStatus.CREATED).body("Inventory Added");
    }

    @PutMapping("/update")
    public String update( @RequestBody InventoryModel inventoryModel,@RequestParam String skuCode)
    {
        return inventoryService.updateStock(inventoryModel,skuCode);
    }
}
