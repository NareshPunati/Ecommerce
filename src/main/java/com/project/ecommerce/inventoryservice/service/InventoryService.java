package com.project.ecommerce.inventoryservice.service;

import com.project.ecommerce.inventoryservice.entity.InventoryEntity;
import com.project.ecommerce.inventoryservice.model.InventoryModel;
import com.project.ecommerce.inventoryservice.repository.InventoryRepository;
import com.project.ecommerce.productservice.entity.ProductSizeEntity;
import com.project.ecommerce.productservice.repository.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;

    public String addInventory(InventoryModel inventoryModel, String skuCode){
        Optional<ProductSizeEntity> productSizeEntity = Optional.ofNullable(productSizeRepository.findBySkuCode(skuCode));
        Optional<InventoryEntity> inventory = Optional.ofNullable(inventoryRepository.findBySkuCode(skuCode));
        if(inventory.isPresent()){
            return "Already Sku Available";
        }
        else if(productSizeEntity.isPresent()){
            InventoryEntity inventoryEntity = new InventoryEntity();
            inventoryEntity.setQuantityAvailable(inventoryModel.getQuantityAvailable());
            inventoryEntity.setSkuCode(skuCode);

            inventoryRepository.save(inventoryEntity);
            return "Inventory Added";
        }
        else return "Skucode Invalid";
    }

    public String updateStock(InventoryModel inventoryModel, String skuCode)
    {
        if(!inventoryRepository.findByskuCode(skuCode).isEmpty()) {
            List<InventoryEntity> inventory = inventoryRepository.findByskuCode(skuCode);
            inventory.forEach(i ->
            {
                i.setQuantityAvailable(inventoryModel.getQuantityAvailable() + i.getQuantityAvailable());
                inventoryRepository.save(i);
            });
            return "Product Updated";
        }
        return "Product Not Found";
    }
}
