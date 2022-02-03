package com.project.ecommerce.accountservice.controller;

import com.project.ecommerce.accountservice.entity.CustomerEntity;
import com.project.ecommerce.accountservice.model.CustomerModel;
import com.project.ecommerce.accountservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public CustomerEntity addCustomer(@Valid @RequestBody CustomerModel customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping("/get/{id}")
    public CustomerModel getById(@PathVariable long id){
        return customerService.getById(id);
    }

    @GetMapping("get-all")
    public List<CustomerModel> getCustomers(){
        return customerService.getCustomers();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable long id){
        customerService.deleteById(id);
    }

}
