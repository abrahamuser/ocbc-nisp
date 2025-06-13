package com.silverlake.mleb.pex.util;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.commons.codec.binary.Hex;
import org.apache.ws.security.WSPasswordCallback;

import org.apache.commons.codec.binary.Base64;



public class ClientPasswordCallbackPExWS implements CallbackHandler {

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
			username = (pm.getProperty("PExWSUser").split(",")[0]).split(":")[0];
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
		
			
			
			String tempPass = (pm.getProperty("PExWSUser").split(",")[0]).split(":")[1];
			/*try {
				tempPass = new String(en.decrypt(new String(hexString).getBytes(), Base64.decodeBase64(tempPass.getBytes())));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			password = tempPass;
			System.out.println("::::::::::::::"+password);
		}
		
		
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
