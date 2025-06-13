package com.silverlake.mleb.pex.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.codec.binary.Base64;








public class EncryptionServices
{
	static String key = "0123456789ABCDEF";
	Logger log = LogManager.getLogger(EncryptionServices.class);
	
	public String encrypt(String clearText)
	{
		String encryptedString = "";
		try
		{
			System.out.println("ClearText :: "+clearText);
			//byte[] rawKey = getRawKey(key.getBytes());
			byte[] rawKey = key.getBytes();
			System.out.println("RAW KEY :: "+rawKey);
			encryptedString =  this.encrypt(rawKey, clearText.getBytes());
			//encryptedString = this.decrypt(clearText);
			log.info("ENCRYPTED AND ENCODED STRING :: "+encryptedString);
			log.info("");
			
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN encrypt() :: "+e);
			e.printStackTrace();
		}
		 
		 return encryptedString;
	}
	
	public  String  encrypt(byte[] rawKey, byte[] clear) throws Exception
	{
		SecretKeySpec skeySpec = new SecretKeySpec(rawKey, "AES");
        //Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] ivByte = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        
        IvParameterSpec viSpec = new IvParameterSpec(ivByte);
        
        
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec,viSpec);
        
        byte[] encrypted = cipher.doFinal(clear);
        
        String cipherText = new String(Base64.encodeBase64(encrypted));
        //String cipherText = this.bytesToHex(encrypted);
        
        return cipherText;
	}
	
/*	private byte[] getRawKey(byte[] seed) throws Exception
	{
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        //System.out.println("256 Encryption");
        kgen.init(128, sr);	//using AES256 bits
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }
	*/
	
	
	
	public String decrypt(String encrypted)
	{
		String decryptedString = "";
		try
		{
			//byte[] rawKey = getRawKey(key.getBytes());
			byte[] rawKey = key.getBytes();
	        
			byte[] result = decrypt(rawKey, Base64.decodeBase64(encrypted.getBytes()));
			//byte[] result = decrypt(rawKey, this.hexStringToByteArray(encrypted));
	     
			return new String(result);
		}
		catch(Exception e)
		{
			System.out.println("EXCEPTION IN decrypt() :: "+e);
			e.printStackTrace();
		}
		return decryptedString;
	}
	
	public byte[] decrypt(byte[] rawKey, byte[] encrypted) throws Exception
	{
		SecretKeySpec skeySpec = new SecretKeySpec(rawKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] ivByte = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        
        IvParameterSpec viSpec = new IvParameterSpec(ivByte);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec,viSpec);
        
        byte[] decrypted = cipher.doFinal(encrypted);
        
        return decrypted;
	}
	
	

	
	public static void main(String args[]) throws Exception
	{
		EncryptionServices en = new EncryptionServices();
		char[] hexString = Hex.encodeHex("HLB@PEx@".getBytes());
		
		String encrypted = en.encrypt(String.valueOf(hexString).getBytes(),"564532".getBytes());
		
		//String encrytedMsg = "LU3gZD0EnluxRIkjj+7VSmr2BPwVmd1Sp4bsIEC3a2YK/92E+NMdP3kqJd6kQzHg5QjmutsyJMkEOEoisfUIwdhLo4bLZx2Z0Ixb1PQwuA7ca+SpJ01LryZojI8NkvL6ZTKZaDX2eODIXCEnBZGE7yZyuS/mak7UjkdlY08TO421joMj7whVI5qlfUDIP6akvxIsX1ravVdOTcVw41BaA0vYUpyXIgZlOD3rq7iWKgWj2yqbVwUeU4XYPxweaCTt";
		System.out.println("xxxxxxxxxx123 "+encrypted);
		

		System.out.println("xxxxxxxxxx "+new String(en.decrypt((String.valueOf(hexString).getBytes()),Base64.decodeBase64((encrypted.getBytes())))));
		
		
	}

}
