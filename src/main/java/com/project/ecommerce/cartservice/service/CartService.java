package com.project.ecommerce.cartservice.service;

import com.project.ecommerce.accountservice.entity.AddressEntity;
import com.project.ecommerce.accountservice.entity.CustomerEntity;
import com.project.ecommerce.accountservice.repository.AddressRepository;
import com.project.ecommerce.accountservice.repository.CustomerRepository;
import com.project.ecommerce.cartservice.entity.CartEntity;
import com.project.ecommerce.cartservice.entity.ItemsOrderedEntity;
import com.project.ecommerce.cartservice.model.CartModel;
import com.project.ecommerce.cartservice.repository.CartRepository;
import com.project.ecommerce.cartservice.repository.ItemsOrderedRepository;
import com.project.ecommerce.inventoryservice.entity.InventoryEntity;
import com.project.ecommerce.inventoryservice.repository.InventoryRepository;
import com.project.ecommerce.productservice.entity.ProductPriceEntity;
import com.project.ecommerce.productservice.repository.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ProductPriceRepository productPriceRepository;
    @Autowired
    private ItemsOrderedRepository itemsOrderedRepository;
    @Autowired
    private AddressRepository addressRepository;

    public String addToCart(CartModel cartModel, String email) {
        Optional<CustomerEntity> customerEntity = Optional.ofNullable(customerRepository.findByEmail(email));
        Integer quantityAvailable = inventoryRepository.findBySkuCode(cartModel.getSkuCode()).getQuantityAvailable();
        Integer quantityRequired = cartModel.getQuantity();
        if (customerEntity.isPresent()) {
            if (quantityAvailable - quantityRequired >= 0) {
                CartEntity cartEntity = new CartEntity();
                cartEntity.setProductCode(cartModel.getProductCode());
                cartEntity.setSkuCode(cartModel.getSkuCode());
                cartEntity.setQuantity(cartModel.getQuantity());
                cartRepository.save(cartEntity);
                return "Ok";
            } else {
                return "Limited Stock! Available: " + quantityAvailable;
            }
        } else {
            return "Email Not Valid";
        }
    }

    public String viewCart(String email) {

        List<CartEntity> cart = cartRepository.findAll();
        List<CustomerEntity> customer = customerRepository.findAll();

        AtomicReference<Double> grandTotal = new AtomicReference<>(0.0);
        AtomicReference<String> outPut = new AtomicReference<>("");
          if (!customer.isEmpty()) {
              cart.forEach(c -> {
                  String skuCode = c.getSkuCode();
                  Integer quantity = c.getQuantity();
                  Double price = productPriceRepository.findBySkuCode(c.getSkuCode()).getPrice();
                  Double subTotal = (quantity * price);

                  outPut.set(outPut + "\nSkuCode: " + skuCode + "\tPrice: " + price + "\tQuantity: " + quantity + "\tSubTotal: " + subTotal);
                  grandTotal.updateAndGet(s -> s + subTotal);
              });
              outPut.set(outPut + "\nGrandTotal: " + grandTotal);
          }
        return outPut.toString();
    }

    public CartModel getCart(CartEntity cartEntity) {
        CartModel cart = new CartModel();
        cart.setId(cartEntity.getId());
        cart.setProductCode(cartEntity.getProductCode());
        cart.setSkuCode(cartEntity.getSkuCode());
        cart.setQuantity(cartEntity.getQuantity());
        return cart;
    }

    public String placeOrder(Long addressId, String email) {
        Optional<CustomerEntity> customerEntity = Optional.ofNullable(customerRepository.findByEmail(email));
        Optional<AddressEntity> address = addressRepository.findById(addressId);
        if (!address.isPresent()) {
            return "Address Id Mismatched";
        }
        if (customerEntity.isPresent()) {
            List<Long> cartId = new ArrayList<>();
            List<CartEntity> cart = cartRepository.findAll();
            List<InventoryEntity> inventory = inventoryRepository.findAll();
            cart.forEach(x -> {
                inventory.forEach(i -> {
                  if (x.getSkuCode().equals(i.getSkuCode())) {
                      Integer quantityAvailable = i.getQuantityAvailable();
                      Integer quantityRequired = x.getQuantity();
                      if (quantityAvailable - quantityRequired >= 0) {
                          inventoryRepository.findBySkuCode(x.getSkuCode()).setQuantityAvailable(quantityAvailable - quantityRequired);
                          cartId.add(x.getId());
                      }
                  }
                });
            });

            List<ItemsOrderedEntity> itemsOrderedEntityList = new ArrayList<>();
            cartId.forEach(x->{
                ItemsOrderedEntity itemsOrderedEntity = new ItemsOrderedEntity();
                CartEntity cartEntity = cartRepository.findById(x).get();
                ProductPriceEntity productPriceEntity = productPriceRepository.findBySkuCode(cartEntity.getSkuCode());
                itemsOrderedEntity.setItemStatus("RECEIVED");
                itemsOrderedEntity.setProductCode(cartEntity.getProductCode());
                itemsOrderedEntity.setSkuCode(cartEntity.getSkuCode());
                itemsOrderedEntity.setQuantity(cartEntity.getQuantity());
                itemsOrderedEntity.setPrice(String.valueOf(productPriceEntity.getPrice()));
                itemsOrderedEntityList.add(itemsOrderedEntity);
                itemsOrderedRepository.save(itemsOrderedEntity);
            });
        }
        return "Order Placed";
    }

    public String itemsStatus(Long itemId){
        ItemsOrderedEntity itemsOrderedEntity= itemsOrderedRepository.findById(itemId).get();
        if(itemsOrderedEntity!=null)
            return "Item Status : "+itemsOrderedEntity.getItemStatus();
        else return "Invalid Item Id";
    }

}