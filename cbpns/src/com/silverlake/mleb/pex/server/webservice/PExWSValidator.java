package com.silverlake.mleb.pex.server.webservice;

import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.handler.RequestData;
import org.apache.ws.security.message.token.UsernameToken;
import org.apache.ws.security.validate.Credential;
import org.apache.ws.security.validate.Validator;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.pex.util.PropertiesManager;



@Service
public class PExWSValidator implements Validator 
{

	PropertiesManager property = new PropertiesManager();
	
	@Override
	public Credential validate(Credential arg0, RequestData arg1)
			throws WSSecurityException  {
		// TODO Auto-generated method stub
		
		
		
		Object userObj = property.getProperty("PExWSUser");
		if(null!=userObj)
		{
			String userData = userObj.toString();
			
			String userInfo[] = userData.split(",");
			
			
			for(int i=0;i<userInfo.length;i++)
			{
				
					if(validateUserInfo(arg0.getUsernametoken(),userInfo[i] ))
					{
						return arg0;
					}
					
			}
			
			String errMsg = "Invalid Username OR Password";
			throw new WSSecurityException(errMsg);
			
		}
		else
		{
			String errMsg = "Invalid Username OR Password";
			throw new WSSecurityException(errMsg);
		}
		
		
	}
	

	
	public boolean validateUserInfo(UsernameToken usernameToken, String userInfo)
	{
		String username = userInfo.split(":")[0];
		String password = userInfo.split(":")[1];
		
		if(usernameToken.getName().equalsIgnoreCase(username) && usernameToken.getPassword().equalsIgnoreCase(password))
		{
			return true;
		}
		
		
		return false;
	}
	
}
