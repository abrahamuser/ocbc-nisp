package com.silverlake.mleb.mcb.listerner;


import java.util.Date;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;





public class MuleDispatcher {

	private static Logger logger = LogManager.getLogger(MuleDispatcher.class);
	//private static MuleContext muleContext;
	
	
	
	
	
	public void initialMuleContext()
	{
		/*try {
			SpringXmlConfigurationBuilder builder = new SpringXmlConfigurationBuilder("mule-config.xml");
			MuleContextFactory muleContextFactory = new DefaultMuleContextFactory();
			muleContext = muleContextFactory.createMuleContext(builder);
			muleContext.start();
			
			logger.info("Mule Context Started Successfully : "+new Date(muleContext.getStartDate()));
			
		
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			logger.error("ERROR", e);
		}*/
	}
	
	public void stopMuleContext()
	{
		/*try {
			
			muleContext.stop();
			muleContext.dispose();
			logger.info("Mule Context stoped : "+muleContext.isDisposed()+" : "+new Date());	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Failed Stop Mule", e);
		}*/
	}
	
	/*public static MuleContext getMuleContext() {
		return muleContext;
	}*/

	/*public static void setMuleContext(MuleContext muleContext) {
		MuleDispatcher.muleContext = muleContext;
	}*/

}
