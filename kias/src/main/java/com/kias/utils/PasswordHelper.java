package com.kias.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.kias.model.Account;

public class PasswordHelper {
    private String algorithmName = "md5";
    private final int hashIterations = 2;
    public void encryptPassword(Account account) {
    	//account.setSalt(randomNumberGenerator.nextBytes().toHex());
    	account.setSalt(account.getAccountname()+account.getPassword());
        String newPassword = new SimpleHash(
                algorithmName,
                account.getPassword(),
                ByteSource.Util.bytes(account.getSalt()),
                hashIterations).toHex();
        account.setPassword(newPassword);
    }
    public static void main(String[] args) {
    	 String algorithmName = "md5";  
    	    String username = "1234";  
    	    String password = "1";  
    	    String salt1 = username;  
    	    String salt2 =password;  
    	    int hashIterations = 2;  
    	    SimpleHash hash = new SimpleHash(algorithmName, password,  
    	            salt1 + salt2, hashIterations);  
    	    String encodedPassword = hash.toHex();  
    	    System.out.println(encodedPassword);  
    	    System.out.println(salt2);  
	}
}
