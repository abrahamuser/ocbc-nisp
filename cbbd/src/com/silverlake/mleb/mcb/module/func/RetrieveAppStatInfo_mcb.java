package com.silverlake.mleb.mcb.module.func;


import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAppStatRequest;
import com.silverlake.mleb.mcb.bean.ObAppStatResponse;
import com.silverlake.mleb.mcb.bean.ObGeneralCodeBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.MessagePropertiesI18n;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.MessagePropertiesDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.common.MiBServices;
import com.silverlake.mleb.mcb.util.MessageManager;
import com.silverlake.mleb.mcb.util.PropertiesManager;


@Service
public class RetrieveAppStatInfo_mcb extends FuncServices  
{

	private static Logger log = LogManager.getLogger(RetrieveAppStatInfo_mcb.class);
	//private MessageManager msgPro = new MessageManager();
	private PropertiesManager propMgr = new PropertiesManager();
	
	public static Hashtable<String, String> msgtable;
	public static Hashtable<String, String> msgtableIn;
	public static Hashtable<String, String> msgtableCn;
	
	@Autowired
	CustomerDAO dao;
	
	@Autowired
	MessagePropertiesDAO msgdao;
	
	@Autowired ApplicationContext appContext;
	
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObAppStatResponse appStatRespose = new ObAppStatResponse();
		appStatRespose.setObHeader(new ObHeaderResponse());
		
		try {

			log.info("MLEB UUID : "+arg0.getTranxID());
			//ObAuthenticationRequest req = (ObAuthenticationRequest) arg0.getBDObject();
			ObAppStatRequest req = (ObAppStatRequest) arg0.getBDObject();
			
			MiBServices mibServices = new MiBServices(dao);
			
			
			//pls add other connection here!
			
			//end check client connection
			
			
			//to check this is call from mleb or direct ccws
			appStatRespose.getObHeader().setId(null);
			if(arg0.getTranxID().length()!=36)
			{
				appStatRespose.getObHeader().setId(arg0.getTranxID());
			}
			

			boolean checkTelnet = true;
			//nonce to indicate reload properties
			//log.info("---------------------Reload Properties : "+req.getAction());
			if(null!=req.getAction() && req.getAction().equalsIgnoreCase("reloadProperties"))
			{

				propMgr.reloadProperties();
			}
			else if(null!=req.getAction() && req.getAction().equalsIgnoreCase("reloadMessageProperties"))
			{
				log.info("[[[ FORCE RELOAD CACHE MSG .... ]]]");
				 
				
				List<ObGeneralCodeBean> msgList = reloadMsgFromDB(msgdao);
			 
				
				appStatRespose.setMsgList(msgList);
			 
				
			}
			else if(null!=req.getAction() && req.getAction().equalsIgnoreCase("checkMessageProperties"))
			{
				/*
				 * This will crash with MIB messages reload, disable ccws from automatic update of messages.
				 * Only initial load of ccws able to update messages.
				 */
				/*MiBConf mibConf = mibServices.getMiBConf();

				if(mibServices.updateReloadMsgCheck(mibConf.getDescription(), mibConf.getUpdateDate(),msgdao))
				{
					log.info("[[[ REQUIRED RELOAD CACHE MSG .... ]]]");
					custProfileResponse.setIdType(getAllMsg());
				}*/

				checkTelnet = false;
			}
			
			
			VCOminiConnection fzcon = new VCOminiConnection();
			if(checkTelnet)
			{
				//check client connection
			
				Thread fzThread = new Thread(fzcon);
				fzThread.start();
			}
			
			
			
			appStatRespose.setSystemList(new ArrayList());
			appStatRespose.getSystemList().add(checkMCBMem(appStatRespose));
			
	 
            //appStatRespose.setModulus(checkSystemMem());
//			
//			
//			
//			
//			//check client connection result
            int limitLoop = 20;
			while(checkTelnet && fzcon.getResultTest()==null)
			{
				Thread.sleep(500);
				limitLoop --;
				if(limitLoop==0)
				{
					break;
				}
			}
//			
//			//add client connection result
			appStatRespose.setClientList(new ArrayList());
			if(null!=fzcon.getResultTest() && fzcon.getResultTest().trim().length()>0)
			{
			
				
				ObAppStatResponse.Services clientService = appStatRespose.new Services();
				clientService.setName(fzcon.getResultName());
				clientService.setStatus(fzcon.getResultTest());
				
				
				appStatRespose.getClientList().add(clientService);
			}
			//end client connection
			
			
		 
			
			appStatRespose.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//"ERROR.MIB.9999999"
			
			 e.printStackTrace();
			log.info(this.getClass().toString(), e);
			appStatRespose.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			appStatRespose.getObHeader().setStatusMessage(e.getMessage());
	
		}
	 
		response.setBDObject(appStatRespose);
		 
		return response;
	}
	
	
	
	
	public ObAppStatResponse.SystemInfo checkMCBMem(ObAppStatResponse appResp) throws UnknownHostException
	{
		ObAppStatResponse.SystemInfo sysInfo = appResp.new SystemInfo();
		MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
		String rs = "";
		long nonHeapSize = mem.getNonHeapMemoryUsage().getUsed();
		nonHeapSize = nonHeapSize/1024/1024;
		
		long nonHeapMaxSize = mem.getNonHeapMemoryUsage().getMax();
		nonHeapMaxSize = nonHeapMaxSize/1024/1024;
		long heapCommitx = mem.getHeapMemoryUsage().getCommitted();
		long heapMaxx = mem.getHeapMemoryUsage().getMax();
		long heapUsedx = mem.getHeapMemoryUsage().getUsed();
		heapMaxx = heapMaxx/1024/1024;
		heapUsedx = heapUsedx/1024/1024;
		heapCommitx = heapCommitx/1024/1024;
		
		String hostname = InetAddress.getLocalHost().getHostName();
		log.info("["+hostname+"]+["+heapUsedx+"]["+heapCommitx+"]["+heapMaxx+"]["+nonHeapSize+"]["+nonHeapMaxSize+"]");
		rs = hostname+","+heapUsedx+","+heapCommitx+","+heapMaxx+","+nonHeapSize+","+nonHeapMaxSize;
		
		
		sysInfo.setHostName(hostname);
		sysInfo.setCurrentHeap(String.valueOf(heapUsedx));
		sysInfo.setCommitedHeap(String.valueOf(heapCommitx));
		sysInfo.setMaxHeap(String.valueOf(heapMaxx));
		sysInfo.setNonHeap(String.valueOf(nonHeapSize));
		sysInfo.setMaxNonHeap(String.valueOf(nonHeapMaxSize));
		
	 
		return sysInfo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String checkSystemMem() throws UnknownHostException
	{
		MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
		String rs = "";
		long nonHeapSize = mem.getNonHeapMemoryUsage().getUsed();
		nonHeapSize = nonHeapSize/1024/1024;
		
		long nonHeapMaxSize = mem.getNonHeapMemoryUsage().getMax();
		nonHeapMaxSize = nonHeapMaxSize/1024/1024;
		long heapCommitx = mem.getHeapMemoryUsage().getCommitted();
		long heapMaxx = mem.getHeapMemoryUsage().getMax();
		long heapUsedx = mem.getHeapMemoryUsage().getUsed();
		heapMaxx = heapMaxx/1024/1024;
		heapUsedx = heapUsedx/1024/1024;
		heapCommitx = heapCommitx/1024/1024;
		
		String hostname = InetAddress.getLocalHost().getHostName();
		log.info("["+hostname+"]+["+heapUsedx+"]["+heapCommitx+"]["+heapMaxx+"]["+nonHeapSize+"]["+nonHeapMaxSize+"]");
		rs = hostname+","+heapUsedx+","+heapCommitx+","+heapMaxx+","+nonHeapSize+","+nonHeapMaxSize;
		
		return rs;
	}
	
	public Hashtable getExtraProMsg(List<MessagePropertiesI18n> db, Properties pro)
	{
		Hashtable hash = new Hashtable();
		
		
		Iterator<Entry<Object, Object>> ite = pro.entrySet().iterator();
		while(ite.hasNext())
		{
			Entry ent = ite.next();
			boolean checkExist = false;
			for(MessagePropertiesI18n msg:db)
			{
				if(ent.getKey().toString().equalsIgnoreCase(msg.getErrMessageCode()))
				{
					checkExist = true;
				}
			}
			
			if(!checkExist)
			{
				hash.put(ent.getKey().toString(), ent.getValue());
				//log.info("WARNING !!! Duplicate Msg key Found in properties ["+ent.getKey()+"] ");
				
				
			}
		}
		
		
		
		return hash;
	}
	
	
	
	
	
	
	public List<ObGeneralCodeBean> reloadMsgFromDB(MessagePropertiesDAO mdao)
	{
		if( null == msgtable )
		{
			msgtable = new Hashtable<String, String>();
		}
		if( null == msgtableIn )
		{
			msgtableIn = new Hashtable<String, String>();
		}

		if(null == msgtableCn)
		{
			msgtableCn = new Hashtable<String, String>();
		}

		List<MessagePropertiesI18n> en_db = mdao.retrieveAllMessageByLocale(MiBConstant.LOCALE_EN);
		List<MessagePropertiesI18n> in_db = mdao.retrieveAllMessageByLocale(MiBConstant.LOCALE_IN);
		List<MessagePropertiesI18n> cn_db = mdao.retrieveAllMessageByLocale(MiBConstant.LOCALE_CN);
 	
		List<ObGeneralCodeBean> listMsgbean = new ArrayList();
	 
		
		for(MessagePropertiesI18n msg:en_db)
		{
			
			ObGeneralCodeBean temp = new ObGeneralCodeBean();
			temp.setGnCode(msg.getErrMessageCode());
			temp.setGnCodeDescription(msg.getErrMessage());
			temp.setGnCodeType(msg.getLanguageCode());
			msgtable.put(msg.getErrMessageCode(), msg.getErrMessage());
			listMsgbean.add(temp);
		}
 
		
		for(MessagePropertiesI18n msg:in_db)
		{
			ObGeneralCodeBean temp = new ObGeneralCodeBean();
			temp.setGnCode(msg.getErrMessageCode());
			temp.setGnCodeDescription(msg.getErrMessage());
			temp.setGnCodeType(msg.getLanguageCode());
			msgtableIn.put(msg.getErrMessageCode(), msg.getErrMessage());
			listMsgbean.add(temp);
		}
		
		for(MessagePropertiesI18n msg:cn_db)
		{
			ObGeneralCodeBean temp = new ObGeneralCodeBean();
			temp.setGnCode(msg.getErrMessageCode());
			temp.setGnCodeDescription(msg.getErrMessage());
			temp.setGnCodeType(msg.getLanguageCode());
			msgtableCn.put(msg.getErrMessageCode(), msg.getErrMessage());
			listMsgbean.add(temp);
		}
		
		
 
		return listMsgbean;
		
		 
	}
	
	public static Hashtable<String, String> getMessageTable(String locale){
		if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)){
			return msgtable;
		}
		else if(locale.equalsIgnoreCase(MiBConstant.LANG_CN)){
			return msgtableCn;
		}else{
			return msgtableIn;
		}
	}
}



class VCOminiConnection implements Runnable {
	
	private static Logger log = LogManager.getLogger(RetrieveAppStatInfo_mcb.class);
	private PropertiesManager propMgr = new PropertiesManager();
	private String resultName  = "OCBC Velocity Omni Restful API";
	private String resultTest  = null;
	
    @Override
    public void run() {
        
    	String fz = propMgr.getProperty("vc.rest.url");
    	
    	if(null!=fz)
    	{
    		
    	    
	    	try
	    	{
	    		URL url = new URL(fz);
	    		URLConnection conn = url.openConnection();
	    	    conn.connect();
	    		resultTest = "success";
	    		
	    	}catch (Exception e)
	    	{
	    		e.printStackTrace();
	    		log.info("",e);
	    		//System.out.println("FAILLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLED");
	    		resultTest = "failed";
	    		//log.info(resultTest);
	    	}
    	
    	}
    	else
    	{
    		resultTest = "";
    	}
    	
    }
    
    
    

	public String getResultTest() {
		return resultTest;
	}

	public void setResultTest(String resultTest) {
		this.resultTest = resultTest;
	}



	public String getResultName() {
		return resultName;
	}



	public void setResultName(String resultName) {
		this.resultName = resultName;
	}
	
	
	
	
    
    
}