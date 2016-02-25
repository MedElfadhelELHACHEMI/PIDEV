/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import java.security.Key;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class CryptographieMOOC {

    private static CryptographieMOOC cmooc = new CryptographieMOOC();
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = "ADBSJHJS12547896".getBytes();

    private CryptographieMOOC() {

    }
    public static synchronized CryptographieMOOC getCryptage(){
    if(Objects.isNull(cmooc)) return new CryptographieMOOC();
    
    
    return cmooc ;
    }

    public  String encrypt(String valueToEnc) throws Exception {

        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);

        byte[] encValue = c.doFinal(valueToEnc.getBytes());

        byte[] encryptedByteValue = new Base64().encode(encValue);
        String encryptedValue = new String(encryptedByteValue);

        return encryptedValue;
    }

    public  String decrypt(String encryptedValue) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
//
//        byte[] enctVal = c.doFinal(encryptedValue.getBytes());
//        System.out.println("enctVal length " + enctVal.length);
//
//        byte[] decordedValue = new Base64().decode(enctVal);
        byte[] decodedValue = new Base64().decode(encryptedValue.getBytes());
        byte[] decryptedVal = c.doFinal(decodedValue);
        return new String(decryptedVal);// return decordedValue.toString();
    }

    private  Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }

}
