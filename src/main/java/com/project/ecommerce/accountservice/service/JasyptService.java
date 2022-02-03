package com.project.ecommerce.accountservice.service;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class JasyptService {

    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
    public void initConfig(){
        config.setPassword("customer_password");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");

        encryptor.setConfig(config);
    }

    public String encryptPassword(String password) {
        initConfig();
        return encryptor.encrypt(password);
    }

    public String decryptPassword(String password){
        initConfig();
        return encryptor.decrypt(password);
    }
}
