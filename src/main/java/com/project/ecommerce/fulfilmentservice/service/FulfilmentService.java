package com.project.ecommerce.fulfilmentservice.service;

import com.project.ecommerce.cartservice.entity.ItemsOrderedEntity;
import com.project.ecommerce.cartservice.repository.ItemsOrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FulfilmentService {

    @Autowired
    private ItemsOrderedRepository itemsOrderedRepository;

    public String updateItemStatus(Long itemId, String status){
        Optional<ItemsOrderedEntity> item = itemsOrderedRepository.findById(itemId);
        if(item.isPresent())
        {
            item.get().setItemStatus(status);
            itemsOrderedRepository.save(item.get());
            return "Status Updated : "+item.get().getItemStatus();
        }
        else return "Invalid Id";
    }
}
