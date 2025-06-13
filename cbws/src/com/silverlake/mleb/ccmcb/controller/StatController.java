package com.silverlake.mleb.ccmcb.controller;


import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.silverlake.mleb.ccmcb.listerner.WebFilterListener;
import com.silverlake.mleb.ccmcb.module.RetrieveAppStatInfo;
import com.silverlake.mleb.ccmcb.util.CacheMessageManager;
import com.silverlake.mleb.ccmcb.util.EhCacheMsgManager;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAppStatRequest;
import com.silverlake.mleb.mcb.bean.ObAppStatResponse;
import com.silverlake.mleb.mcb.bean.ObAppStatResponse.Services;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObGeneralCodeBean;
import com.silverlake.mleb.mcb.constant.MiBConstant;

 
@Controller
public class StatController {
 
	final static Logger log = LogManager.getLogger(StatController.class);
 
	private PropertiesManager pro = new PropertiesManager();
	private static String mibSrv = "MIB ";
	private static String mlebSrv = "MLEB CORE ";
	private static String pexSrv = "PEX ";
	public static final String runningFlag = "<span style=\"color:green;font-weight: bold;\" >Running</span>";
	public static final String stopFlag =  "<span style=\"color:red;font-weight: bold;\" >Stopped</span>";
	public static final String unknowFlag =  "<span style=\"color:grey;font-weight: bold;\" >Unknown</span>";
	public static final String onFlag = "<span style=\"color:green;font-weight: bold;\" >On</span>";
	
	@Autowired ApplicationContext appContext;
	
	
	
	@RequestMapping(value = "stat", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("block");
		
		ObAppStatResponse appStatResp = new ObAppStatResponse();
		
		String stat_ip = pro.getProperty("stat-ip");
		stat_ip = null==stat_ip?"":stat_ip;
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		   if (ipAddress == null) {  
			   ipAddress = request.getRemoteAddr();  
		   }
		
		log.info("stat restrict ip : "+stat_ip);
		
		log.info("stat client ip : "+ipAddress);
		
		if(ipAddress.equalsIgnoreCase("0:0:0:0:0:0:0:1") || ipAddress.equalsIgnoreCase("127.0.0.1")  || stat_ip.indexOf("["+ipAddress+"]")>=0)
		{
			UserLoginSession userSession =  new UserLoginSession();
			int totalRecord = userSession.getTotalRecord();
			model.addObject("totalRecords", totalRecord);
			model.addObject("totalRequest", WebFilterListener.cchashMap.size());
			
			appStatResp.setRequestList(new ArrayList());
			
			ObAppStatResponse.Services wsLoginCount =  appStatResp.new Services();
			wsLoginCount.setName("Login Session:");
			wsLoginCount.setStatus(String.valueOf(totalRecord));
			
			ObAppStatResponse.Services wsRequestCount =  appStatResp.new Services();
			wsRequestCount.setName("Concurrent Request:");
			wsRequestCount.setStatus(String.valueOf(WebFilterListener.cchashMap.size()));
		 
			appStatResp.getRequestList().add(wsLoginCount);
			appStatResp.getRequestList().add(wsRequestCount);
			
			
			
			
			
			List<String[]> mlebSrvs = new ArrayList();
			List<String[]> clientSrvs = new ArrayList();
			
			
			
			log.info("[POST Session]"+request.getSession().getAttribute("reloadProperties"));
			
			if(null!=request.getSession() && null!=request.getSession().getAttribute("reloadProperties"))
			{
				try {
					if(request.getSession().getAttribute("reloadProperties").toString().equalsIgnoreCase("reloadProperties"))
					{
						log.info("-----reload properties file ----");
						PropertiesManager proMgm = new PropertiesManager();
					
						proMgm.reloadProperties();

					}
					
				} catch (ConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			appStatResp = requestMib(request,appStatResp);
			
			
			//model.addObject("mlebSrvs", mlebSrvs);
			//model.addObject("clientSrvs", clientSrvs);
			
		
			MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
			
			/*long heapFreeSize = Runtime.getRuntime().freeMemory(); 
			
			heapFreeSize = heapFreeSize/1024/1024;*/

			
			
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
			String servMem = (String) request.getSession().getAttribute("mibMem");
			log.info(servMem);
			log.info("["+heapUsedx+"]["+heapCommitx+"]["+heapMaxx+"]["+nonHeapSize+"]["+nonHeapMaxSize+"]");
			model.addObject("heapMaxSize", heapMaxx);
			model.addObject("heapSize", heapUsedx);
			model.addObject("heapCommitedSize", heapCommitx);
			model.addObject("nonHeapSize", nonHeapSize);
			model.addObject("nonHeapMaxSize", nonHeapMaxSize);
			
		
			
			
			
			String hostname="";
			try {
				hostname = InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			ObAppStatResponse.SystemInfo ccmem = appStatResp.new SystemInfo();
			ccmem.setHostName(hostname);
			ccmem.setCurrentHeap(String.valueOf(heapUsedx));
			ccmem.setCommitedHeap(String.valueOf(heapCommitx));
			ccmem.setMaxHeap(String.valueOf(heapMaxx));
			ccmem.setMaxNonHeap(String.valueOf(nonHeapMaxSize));
			ccmem.setNonHeap(String.valueOf(nonHeapSize));
			
			if(appStatResp.getSystemList()==null )
			{
				appStatResp.setSystemList(new ArrayList());
			}
			appStatResp.getSystemList().add(ccmem);
			
			
			if(appStatResp.getSystemList()!=null && appStatResp.getSystemList().size()>1)
			{
				//String[] mibHostMem = servMem.split(",");
				//model.addObject("xSizeTab", "40%");
				if(!appStatResp.getSystemList().get(0).getHostName().equalsIgnoreCase(appStatResp.getSystemList().get(1).getHostName()) || (null!=pro.getProperty("stat.web") && pro.getProperty("stat.web").equalsIgnoreCase("true")))
				{
					 
				}
				else
				{
					appStatResp.getSystemList().remove(0);
				}
			}
			
			
			
			Date today = new Date();
			SimpleDateFormat dt = new SimpleDateFormat("EEE, dd MMM yyyy 'at' HH:mm:ss aaa");
		
			model.addObject("infoDateTime",dt.format(today));
			model.setViewName("dashboard");
			
			model.addObject("appStatResp",appStatResp);
		
			if(null!=request.getSession() && null!=request.getSession().getAttribute("reloadProperties"))
			{
				if(request.getSession().getAttribute("reloadProperties").toString().equalsIgnoreCase("reloadProperties"))
				{
					model.addObject("rsReload", "success");
					model.addObject("rsReloadMsg", "Success Reload Properties");
				}
				else if(request.getSession().getAttribute("reloadProperties").toString().equalsIgnoreCase("reloadMessageProperties"))
				{
					/*MessageManager msg = new MessageManager();
					msg.reloadMsg();*/
					
					
					EhCacheMsgManager cacheManager = new EhCacheMsgManager();
					cacheManager.reloadMsg(appStatResp.getMsgList());
					
					
					model.addObject("rsRespMsgReload", "success");
					model.addObject("rsRespMsgReloadMsg", "Success Reload Response Message");
				}
				
				request.getSession().invalidate();
			}

		}
		
		
		return model;
	}
	
	
	@RequestMapping(value = "stat", method = RequestMethod.POST)
	public ModelAndView  indexPost(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		log.info("[POST REQUEST RELOAD PROPERTIES]");
		try
		{
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}


		request.getSession().setAttribute("reloadProperties", "reloadProperties");
		//redirectAttributes.addAttribute("reloadProperties", "reloadProperties");
		
		return index(request);
		
		
		//return "redirect:stat";
	}
	
	
	@RequestMapping(value = "statmsg", method = RequestMethod.POST)
	public ModelAndView  indexPostMsg(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception {
		log.info("[POST REQUEST RELOAD MESSAGE PORPERTIES]");
		try
		{
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		                                                        
		request.getSession().setAttribute("reloadProperties", "reloadMessageProperties");
		//redirectAttributes.addAttribute("reloadProperties", "reloadProperties");
		
		return index(request);
		
		
		//return "redirect:stat";
	}
	
	
	
	
	
	public ObAppStatResponse requestMib(HttpServletRequest request, ObAppStatResponse respx)
	{
		List<String[]>[] fullList = new ArrayList[2];
		List<String[]> rsMlebList = new ArrayList();
		List<String[]> rsClientList = new ArrayList();
		String[] mlebsrv = new String[2];
		String[] mibsrv = new String[2];
		mlebsrv[0] = mlebSrv;
		mibsrv[0] = mibSrv;
		ObAppStatRequest req = new ObAppStatRequest();
		
		 
		
		
		RetrieveAppStatInfo appstat = new RetrieveAppStatInfo(null);
		if(null == appstat.getObResponse())
		{
			appstat.processData(request.getSession(),null);
		}
		Object obj=  appstat.processSend(ObAppStatResponse.class);
		
		if(obj instanceof ObAppStatResponse)
		{
			ObAppStatResponse resp = (ObAppStatResponse) obj;
			
			
			
			log.info(resp.getObHeader().getStatusCode()+"-"+resp.getObHeader().getStatusMessage());
			if(resp.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
			{
				//update cache msg
				//CacheMessageManager cacheMgr = new CacheMessageManager();
				//cacheMgr.processUpdateCache(resp.getMsgList());
				respx.setMsgList(resp.getMsgList());
				
				//update memmib
				//request.getSession().setAttribute("mibMem", resp.getSystemList());
				respx.setSystemList(resp.getSystemList());
				
				
				
				//mib connection
				//mibsrv[1] = runningFlag;
				respx.setMlebList(new ArrayList());
				ObAppStatResponse.Services mcbApp = resp.new Services();
				mcbApp.setName("MCB");
				mcbApp.setStatus("active");
				mcbApp.setDesc(runningFlag);
				respx.getMlebList().add(mcbApp);
				
				//client connection
				for(Services temp:resp.getClientList())
				{
					String statusC = temp.getStatus();
					String titleC = temp.getName();
					if(statusC.equalsIgnoreCase("success"))
					{
						//statusC = onFlag;
						temp.setDesc(onFlag);
					}
					else if(statusC.equalsIgnoreCase("failed"))
					{
						//statusC = stopFlag;
						temp.setDesc(stopFlag);
					}
					else
					{
						//statusC = unknowFlag;
						temp.setDesc(unknowFlag);
					}
					
					//String[] con = {titleC,statusC};
					//rsClientList.add(con);
				}
				
				
				respx.setClientList(resp.getClientList());
			}
			else
			{
				//mibsrv[1] = stopFlag;	
				if(respx.getMlebList()==null)
				{
					respx.setMlebList(new ArrayList());
				}
				ObAppStatResponse.Services mcbApp = resp.new Services();
				mcbApp.setName("MCB");
				mcbApp.setStatus("active");
				mcbApp.setDesc(stopFlag);
				respx.getMlebList().add(mcbApp);
			}
			
			
			
			if(null!=resp.getObHeader().getId() && resp.getObHeader().getId().trim().length()>0)
			{
				
				//via mleb
				//mlebsrv[1] = runningFlag;	
				ObAppStatResponse.Services mlebApp = resp.new Services();
				mlebApp.setName("MLEB");
				mlebApp.setStatus("active");
				mlebApp.setDesc(runningFlag);
				if(respx.getMlebList()==null)
				{
					respx.setMlebList(new ArrayList());
				}
				respx.getMlebList().add(mlebApp);
			}
			else
			{
				//mlebsrv[1] = unknowFlag;	
				ObAppStatResponse.Services mlebApp = resp.new Services();
				mlebApp.setName("MLEB");
				mlebApp.setStatus("active");
				mlebApp.setDesc(unknowFlag);
				if(respx.getMlebList()==null)
				{
					respx.setMlebList(new ArrayList());
				}
				respx.getMlebList().add(mlebApp);
			}
			
			
		}
		else
		{
			log.info("Failed to call Mib BD");
			//failed to call bd
			//mibsrv[1] = stopFlag;
			//mlebsrv[1] = unknowFlag;
			
			
			respx.setMlebList(new ArrayList());
			
			ObAppStatResponse.Services mcbApp = respx.new Services();
			mcbApp.setName("MCB");
			mcbApp.setStatus("active");
			mcbApp.setDesc(stopFlag);
			respx.getMlebList().add(mcbApp);
			
			
			ObAppStatResponse.Services mlebApp = respx.new Services();
			mlebApp.setName("MLEB");
			mlebApp.setStatus("active");
			mlebApp.setDesc(unknowFlag);
			respx.getMlebList().add(mlebApp);
		}
		
		//rsMlebList.add(mlebsrv);
		//rsMlebList.add(mibsrv);
		
		//fullList[0] = rsMlebList;
		//fullList[1] = rsClientList;
		return respx;
		

	}
	
	
	
	/*public String[] requestPEx(HttpServletRequest request)
	{
		String[] mlebsrv = new String[2];
		ObAuthenticationRequest req = new ObAuthenticationRequest();
		mlebsrv[0]=pexSrv;
		
		RetrieveAppStatInfo_pex appstat = new RetrieveAppStatInfo_pex(null);
		if(null == appstat.getObResponse())
		{
			appstat.processData(request.getSession());
		}
		Object obj=  appstat.processSend();
		
		if(obj instanceof ObAuthenticationResponse)
		{
			ObAuthenticationResponse regRs = (ObAuthenticationResponse) obj;
			log.info(regRs.getObHeader().getStatusCode());
			
			if(regRs.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
			{
				mlebsrv[1] = runningFlag;
			}
			else
			{
				mlebsrv[1] = stopFlag;
			}
			
		}
		else
		{
			mlebsrv[1] = stopFlag;
	
		}
		return mlebsrv;
	}*/
 
}