package com.project.ecommerce.cartservice.controller;

import com.project.ecommerce.cartservice.model.CartModel;
import com.project.ecommerce.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addToCart(@Valid @RequestBody CartModel cartModel, @RequestHeader String email) {
        return cartService.addToCart(cartModel,email);
    }

    @GetMapping("/view")
    public String viewCart(@RequestHeader String email) {
        return cartService.viewCart(email);
    }

    @PostMapping("/place-order")
    public String placeOrder(@RequestParam Long addressId, @RequestHeader String email) {
        return cartService.placeOrder(addressId, email);
    }

    @GetMapping("/item-status/{itemId}")
    public String itemsStatus(@RequestParam Long itemId) {
        return cartService.itemsStatus(itemId);
    }

}
