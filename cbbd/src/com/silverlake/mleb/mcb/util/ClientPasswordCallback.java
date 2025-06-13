package com.silverlake.mleb.mcb.util;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.commons.codec.binary.Hex;
import org.apache.ws.security.WSPasswordCallback;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.codec.binary.Base64;

import com.silverlake.micb.core.util.EncryptionServices;


public class ClientPasswordCallback implements CallbackHandler {

	PropertiesManager  pm = new PropertiesManager();

	public static String username;
	public static String password;
	
    public void handle(Callback[] callbacks) throws IOException, 
            UnsupportedCallbackException {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        System.out.println("WS USERNAME : "+getUsername());
        if (getUsername().equals(pc.getIdentifier())) {
            pc.setPassword(getPassword());
        } // else {...} - can add more users, access DB, etc.
    }

	public String getUsername() {
		
		
		if(null==username)
		{
			username = pm.getProperty("fuzion.ws.username");
		}
		
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
	
	public String getPassword()
	{
		
		
		if(null==password)
		{
			EncryptionServices en = new EncryptionServices();
		
			char[] hexString = Hex.encodeHex(("hlbfz.ws").getBytes());
			
			
			String tempPass = pm.getProperty("fuzion.ws.password");
			try {
				tempPass = new String(en.decrypt(new String(hexString).getBytes(), Base64.decodeBase64(tempPass.getBytes())));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			password = tempPass;
		}
		
		
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static void main(String args[])
	{
		System.out.println(getPassword("i7LhKuJTU6INGYw0z0AczPM3IOY+GUXBnEGYf2qSA8PeThYCsIbkVx0c7iIhECoTZeFrIe3afP9dZLX8DHHFOxG6AhYzvAIF+oz6w32URN0="));
	}
	
	public static String getPassword(String tempPass)
	{
		
		
		if(null==password)
		{
			EncryptionServices en = new EncryptionServices();
		
			char[] hexString = Hex.encodeHex(("hlbfz.ws").getBytes());
			
			
			
			try {
				tempPass = new String(en.decrypt(new String(hexString).getBytes(), Base64.decodeBase64(tempPass.getBytes())));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			password = tempPass;
		}
		
		
		return password;
	}
    
}
