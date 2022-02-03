package com.project.ecommerce.productservice.controller;

import com.project.ecommerce.productservice.entity.ProductEntity;
import com.project.ecommerce.productservice.entity.ProductPriceEntity;
import com.project.ecommerce.productservice.entity.ProductSizeEntity;
import com.project.ecommerce.productservice.model.ProductModel;
import com.project.ecommerce.productservice.model.ProductPriceModel;
import com.project.ecommerce.productservice.model.ProductSizeModel;
import com.project.ecommerce.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ProductEntity addProduct(@Valid @RequestBody ProductModel product) {
        return productService.addProduct(product);
    }

    @PostMapping("/size/add/{productCode}")
    public ProductSizeEntity addProductSize(@Valid @RequestBody ProductSizeModel productSize, @PathVariable String productCode) {
        return productService.addProductSize(productSize, productCode);
    }

    @PostMapping("/price/add/{skuCode}")
    public ProductPriceEntity addProductPrice(@Valid @RequestBody ProductPriceModel productPrice, @PathVariable String skuCode) {
        return productService.addProductPrice(productPrice, skuCode);
    }

    @GetMapping("get-products")
    public List<ProductModel> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("get-sizes")
    public List<ProductSizeModel> getSize(){
        return productService.getSize();
    }

    @GetMapping("get-prices")
    public List<ProductPriceModel> getPrice(){
        return productService.getPrice();
    }

}
