package com.project.ecommerce.productservice.service;

import com.project.ecommerce.productservice.entity.ProductEntity;
import com.project.ecommerce.productservice.entity.ProductPriceEntity;
import com.project.ecommerce.productservice.entity.ProductSizeEntity;
import com.project.ecommerce.productservice.model.ProductModel;
import com.project.ecommerce.productservice.model.ProductPriceModel;
import com.project.ecommerce.productservice.model.ProductSizeModel;
import com.project.ecommerce.productservice.repository.ProductPriceRepository;
import com.project.ecommerce.productservice.repository.ProductRepository;
import com.project.ecommerce.productservice.repository.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductPriceRepository productPriceRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;

    public ProductEntity addProduct(ProductModel productModel) {

            ProductEntity products = new ProductEntity();
            products.setProductCode(productModel.getProductCode());
            products.setProductName(productModel.getProductName());
            products.setDescription(productModel.getDescription());

             return productRepository.save(products);
    }

    public ProductModel getProductModel(ProductEntity product){
        ProductModel productModel = new ProductModel();
        productModel.setProductCode(product.getProductCode());
        productModel.setProductName(product.getProductName());
        productModel.setDescription(product.getDescription());
        return productModel;
    }

    public ProductSizeEntity addProductSize(ProductSizeModel productSize, String productCode){
        ProductSizeEntity productSizeEntity = new ProductSizeEntity();
        productSizeEntity.setSkuCode(productSize.getSkuCode());
        productSizeEntity.setSize(productSize.getSize());
        productSizeEntity.setProductCode(productCode);
         return productSizeRepository.save(productSizeEntity);
    }

    public ProductSizeModel getProductSizeModel(ProductSizeEntity productSizeEntity){
        ProductSizeModel productSizeModel = new ProductSizeModel();
        productSizeModel.setSkuCode(productSizeEntity.getSkuCode());
        productSizeModel.setSize(productSizeEntity.getSize());
        productSizeModel.setProductCode(productSizeEntity.getProductCode());
         return productSizeModel;
    }

    public ProductPriceEntity addProductPrice(ProductPriceModel productPrice, String skuCode){
        ProductPriceEntity productPriceEntity = new ProductPriceEntity();
        productPriceEntity.setSkuCode(skuCode);
        productPriceEntity.setPrice(productPrice.getPrice());
        productPriceEntity.setCurrency(productPrice.getCurrency());
        return productPriceRepository.save(productPriceEntity);
    }

    public ProductPriceModel getProductPriceModel(ProductPriceEntity productPriceEntity){
        ProductPriceModel productPriceModel = new ProductPriceModel();
        productPriceModel.setSkuCode(productPriceEntity.getSkuCode());
        productPriceModel.setPrice(productPriceEntity.getPrice());
        productPriceModel.setCurrency(productPriceEntity.getCurrency());
        return productPriceModel;
    }

    public List<ProductModel> getProducts(){
        List<ProductEntity> products = productRepository.findAll();
        return products.stream().map(p -> getProductModel(p)).collect(Collectors.toList());
    }

    public List<ProductSizeModel> getSize(){
        List<ProductSizeEntity> size = productSizeRepository.findAll();
        return size.stream().map(s -> getProductSizeModel(s)).collect(Collectors.toList());
    }

    public List<ProductPriceModel> getPrice(){
        List<ProductPriceEntity> price = productPriceRepository.findAll();
        return price.stream().map(p -> getProductPriceModel(p)).collect(Collectors.toList());
    }
}
