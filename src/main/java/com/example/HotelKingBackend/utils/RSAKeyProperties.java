//package com.example.HotelKingBackend.utils;
//
//import org.springframework.stereotype.Component;
//
//import java.security.KeyPair;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//
//@Component
//public class RSAKeyProperties {
//
//    private RSAPrivateKey rsaPrivateKey;
//    private RSAPublicKey rsaPublicKey;
//
//    public RSAKeyProperties() {
//        KeyPair key = KeyGeneratorUtility.generateRsaKey();
//        this.rsaPrivateKey = (RSAPrivateKey) key.getPrivate();
//        this.rsaPublicKey = (RSAPublicKey) key.getPublic();
//    }
//
//    public RSAPrivateKey getRsaPrivateKey() {
//        return rsaPrivateKey;
//    }
//
//    public void setRsaPrivateKey(RSAPrivateKey rsaPrivateKey) {
//        this.rsaPrivateKey = rsaPrivateKey;
//    }
//
//    public RSAPublicKey getRsaPublicKey() {
//        return rsaPublicKey;
//    }
//
//    public void setRsaPublicKey(RSAPublicKey rsaPublicKey) {
//        this.rsaPublicKey = rsaPublicKey;
//    }
//}
