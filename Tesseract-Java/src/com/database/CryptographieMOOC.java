/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Objects;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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


public String get_SHA_512_SecurePassword(String passwordToHash, String   salt) throws UnsupportedEncodingException{
String generatedPassword = null;
    try {
         MessageDigest md = MessageDigest.getInstance("SHA-512");
         md.update(salt.getBytes("UTF-8"));
         byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
         StringBuilder sb = new StringBuilder();
         for(int i=0; i< bytes.length ;i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
         }
         generatedPassword = sb.toString();
        } 
       catch (NoSuchAlgorithmException e){
        e.printStackTrace();
       }
    return generatedPassword;
}
    public  String encrypt(String valueToEnc) throws Exception {
return valueToEnc;
//        Key key = generateKey();
//        Cipher c = Cipher.getInstance(ALGORITHM);
//        c.init(Cipher.ENCRYPT_MODE, key);
//
//        byte[] encValue = c.doFinal(valueToEnc.getBytes());
//
//        byte[] encryptedByteValue = new Base64().encode(encValue);
//        String encryptedValue = new String(encryptedByteValue);
//
//        return encryptedValue;
    }

    public  String decrypt(String encryptedValue) throws Exception {
        return encryptedValue;
//        Key key = generateKey();
//        Cipher c = Cipher.getInstance(ALGORITHM);
//        c.init(Cipher.DECRYPT_MODE, key);
//
//        byte[] decodedValue = new Base64().decode(encryptedValue.getBytes());
//        byte[] decryptedVal = c.doFinal(decodedValue);
//        return new String(decryptedVal);
    }

    private  Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }

}
