package com.project.ecommerce.returnservice.service;

import com.project.ecommerce.accountservice.entity.CustomerEntity;
import com.project.ecommerce.accountservice.repository.CustomerRepository;
import com.project.ecommerce.cartservice.entity.ItemsOrderedEntity;
import com.project.ecommerce.cartservice.repository.ItemsOrderedRepository;
import com.project.ecommerce.inventoryservice.entity.InventoryEntity;
import com.project.ecommerce.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReturnService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ItemsOrderedRepository itemsOrderedRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    public String returnItem(Long itemId, String email){
        Optional<CustomerEntity> customerEntity = Optional.ofNullable(customerRepository.findByEmail(email));
        ItemsOrderedEntity itemsOrderedEntity= itemsOrderedRepository.findById(itemId).get();

        if(customerEntity.isPresent() && !itemsOrderedEntity.getItemStatus().equals("Return")){
            Optional<ItemsOrderedEntity> item =itemsOrderedRepository.findById(itemId);
            if(item.isPresent()){
                InventoryEntity inventoryEntity = inventoryRepository.findBySkuCode(item.get().getSkuCode());
                inventoryEntity.setQuantityAvailable(inventoryEntity.getQuantityAvailable()+item.get().getQuantity());
                item.get().setItemStatus("Return");
                inventoryRepository.save(inventoryEntity);
                return "Return Accepted\n Item Id: "+ item.get().getItemId() + "\tRefund Amount: "+
                        (Double.parseDouble(item.get().getPrice()) * item.get().getQuantity());
            }else return "Check Your Item Id";
        }   else return "Already Returned";
    }
}
