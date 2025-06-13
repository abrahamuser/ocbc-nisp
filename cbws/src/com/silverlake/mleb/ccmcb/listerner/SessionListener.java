package com.silverlake.mleb.ccmcb.listerner;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.module.PerformLogout;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObResponse;



public class SessionListener implements HttpSessionListener, ServletRequestListener {

	private static Logger log = LogManager.getLogger(SessionListener.class);
	private UserLoginSession userLoginSession = new UserLoginSession();
	private PropertiesManager rtPro = new PropertiesManager();
		
	public void sessionCreated(HttpSessionEvent arg0){
		HttpSession session = arg0.getSession();
		log.debug("Session Created : "+session.getId());
		
		String ssTime = rtPro.getProperty("http.session.time");
		ssTime = null==ssTime?"180":ssTime;
		arg0.getSession().setMaxInactiveInterval(Integer.parseInt(ssTime)); 
			  
	}
	
	

	
	public void sessionDestroyed(HttpSessionEvent arg0) {
		
		
		try
		{
			
			//log.info("Session Destroyed DestroyedDestroyedDestroyedDestroyedDestroyedDestroyedDestroyed : "+arg0.getSession().getId());
			
			if(userLoginSession.isSessionUserCache(arg0.getSession()))
			{
				PerformLogout logout = new PerformLogout(null);
				logout.setHttpSession(arg0.getSession());
				logout.processData(true);
				logout.processSend(ObResponse.class);
			}
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
			log.info("Session Listener Error", ex);
		}
	}

	public void requestDestroyed(ServletRequestEvent arg0) {
		
	}

	public void requestInitialized(ServletRequestEvent arg0) {
		
	}


	

}
