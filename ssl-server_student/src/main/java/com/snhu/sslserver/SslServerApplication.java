/*
 * Class: CS-305-T4502 Software Security
 * Instructor: Dr. Vivian Lyon
 * Assignment: 7-Project 2
 * Student: Santiago Ramirez
 * Date: 15 APR 2022
 */
package com.snhu.sslserver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
@RestController
class ServerController{  
    @RequestMapping("/hash")
    public String myHash() throws NoSuchAlgorithmException{
    	String data = "Hello World Check Sum!";
    	//creating the messagDigest object with the sha-256 hash function
    	MessageDigest md = MessageDigest.getInstance("SHA-256");
    	
    	//we pass the data into the messageDigest object
    	md.update(data.getBytes());
    	
    	//we digest the message
    	byte[] digest = md.digest();
    	
    	//Converting the original byte array into a hexadecimal value 
        StringBuffer hexString = new StringBuffer();
        
        for (int i = 0;i<digest.length;i++) {
           hexString.append(Integer.toHexString(0xFF & digest[i]));
        }
    	//return the value 
        return "<p>data: "+data + "<p></p>My name is Santiago Ramirez and Name of cipher algorithm used: SHA-256, checksum value:" + hexString.toString(); 
    }
}