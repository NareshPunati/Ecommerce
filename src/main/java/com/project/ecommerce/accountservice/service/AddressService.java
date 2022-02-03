package com.project.ecommerce.accountservice.service;

import com.project.ecommerce.accountservice.entity.AddressEntity;
import com.project.ecommerce.accountservice.entity.CustomerEntity;
import com.project.ecommerce.accountservice.model.AddressModel;
import com.project.ecommerce.accountservice.repository.AddressRepository;
import com.project.ecommerce.accountservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    public AddressEntity addAddress(AddressModel addressModel, Long customerId) {

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCustomerId(customerId);
        addressEntity.setLine1(addressModel.getLine1());
        addressEntity.setLine2(addressModel.getLine2());
        addressEntity.setPostalCode(addressModel.getPostalCode());
        addressEntity.setCity(addressModel.getCity());
        addressEntity.setState(addressModel.getState());
        addressEntity.setShippingAddress(addressModel.isShippingAddress());
        addressEntity.setBillingAddress(addressModel.isBillingAddress());

        return addressRepository.save(addressEntity);
    }
    public AddressModel getAddressModel(AddressEntity addressEntity){

        AddressModel addressModel = new AddressModel();
        addressModel.setAddressId(addressEntity.getAddressId());
        addressModel.setCustomerId(addressEntity.getCustomerId());
        addressModel.setLine1(addressEntity.getLine1());
        addressModel.setLine2(addressEntity.getLine2());
        addressModel.setPostalCode(addressEntity.getPostalCode());
        addressModel.setCity(addressEntity.getCity());
        addressModel.setState(addressEntity.getState());
        addressModel.setShippingAddress(addressEntity.isShippingAddress());
        addressModel.setShippingAddress(addressEntity.isBillingAddress());
        return addressModel;
    }

    public AddressModel getById(long id){
        Optional<AddressEntity> address = addressRepository.findById(id);
        if (address.isPresent()){
            return getAddressModel(address.get());
        }
        return null;
    }

   public List getCustomerDetails (long customerId){
        List customerDetails = new ArrayList();
        Optional<CustomerEntity> customer = customerRepository.findById(customerId);
        customerDetails.add(customer.stream().map(c -> customerService.getCustomerModel(c)).collect(Collectors.toList()));
        Optional <AddressEntity> address = addressRepository.findById(customerId);
        customerDetails.add(address.stream().map(a -> getAddressModel(a)).collect(Collectors.toList()));

        return customerDetails;
    }
}
