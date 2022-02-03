package com.project.ecommerce.accountservice.controller;

import com.project.ecommerce.accountservice.entity.AddressEntity;
import com.project.ecommerce.accountservice.model.AddressModel;
import com.project.ecommerce.accountservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/holiday")
public class AddressController {

    @Autowired
   private AddressService addressService;

    @PostMapping("/add/{customerId}")
    public AddressEntity addAddress( @Valid @RequestBody AddressModel address, @PathVariable Long customerId) {
        return addressService.addAddress(address, customerId);
    }
    @GetMapping("/get/{addressId}")
    public AddressModel getById(@PathVariable long addressId){
        return addressService.getById(addressId);
    }

    @GetMapping("/get/customer-details/{customerId}")
    public List getCustomerDetails(@PathVariable long customerId){
        return addressService.getCustomerDetails(customerId);
    }
}
