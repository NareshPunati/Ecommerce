package com.project.ecommerce.accountservice.service;

import com.project.ecommerce.accountservice.entity.CustomerEntity;
import com.project.ecommerce.accountservice.model.CustomerModel;
import com.project.ecommerce.accountservice.repository.CustomerRepository;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerEntity addCustomer(CustomerModel customer) {

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setFirstName(customer.getFirstName());
        customerEntity.setLastName(customer.getLastName());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setMobileNumber(customer.getMobileNumber());
//        customerEntity.setPassword(customer.getPassword());
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("customer_password");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");

        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        String encryptedData = encryptor.encrypt(customer.getPassword());
        customerEntity.setPassword(encryptedData);

        return customerRepository.save(customerEntity);
    }
    public CustomerModel getCustomerModel(CustomerEntity customerEntity){

        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerId(customerEntity.getCustomerId());
        customerModel.setFirstName(customerEntity.getFirstName());
        customerModel.setLastName(customerEntity.getLastName());
        customerModel.setEmail(customerEntity.getEmail());
        customerModel.setMobileNumber(customerEntity.getMobileNumber());
 //       customerModel.setPassword(customerEntity.getPassword());
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("customer_password");
        config.setPoolSize("1");
        encryptor.setConfig(config);
        String password = encryptor.decrypt(customerEntity.getPassword());
        customerModel.setPassword(password);

        return customerModel;
    }
    public CustomerModel getById(long id){
        Optional<CustomerEntity> customer = customerRepository.findById(id);
        if (customer.isPresent()){
            return getCustomerModel(customer.get());
        }
        return null;
    }

    public List<CustomerModel> getCustomers(){
        List<CustomerEntity> customers = customerRepository.findAll();
        return customers.stream().map(c -> getCustomerModel(c)).collect(Collectors.toList());
    }

    public void deleteById(long id){
        Optional<CustomerEntity> first = customerRepository.findById(id).stream().filter(c ->
                c.getCustomerId() == id).findFirst();
        first.ifPresent(f -> customerRepository.deleteById(id));
    }

}
