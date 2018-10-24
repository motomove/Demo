package com.demo.jwt;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSADemo {

    public static void main(String[] args) {
        KeyPair key = RSAUtils.generateRSAKeyPair(2048);
        RSAUtils.printPrivateKeyInfo(key.getPrivate());
        RSAUtils.printPublicKeyInfo(key.getPublic());

        PublicKey publicKey = key.getPublic();
        PrivateKey privateKey = key.getPrivate();
        
        String test = "whaty";
        byte[] encry = RSAUtils.encryptData(test.getBytes(), publicKey);
        System.out.println("new String(encry) = " + new String(encry));

        byte[] decrypt = RSAUtils.decryptData(encry, privateKey);
        System.out.println("new String(decrypt) = " + new String(decrypt));
    }
}
