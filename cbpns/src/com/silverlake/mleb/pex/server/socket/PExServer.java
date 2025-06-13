package com.silverlake.mleb.pex.server.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.pex.util.PropertiesManager;

@Service
public class PExServer 
{
  
	private static Logger log = LogManager.getLogger(PExServer.class);
	private PropertiesManager pmgr = new PropertiesManager();
	
	@Autowired ProcessOCMHandler pexTask;
	@Autowired ProcessRBSHandler pexRBSTask;
	
	ServerSocket server;
	ServerSocket rbsServer;
	ServerSocket rbsServer2;
	//Socket connected ;
	
	public PExServer()
	{
		
	}
	
	@Scheduled(fixedDelay=2000)
	public void startServer()
	{
		try
		{
			if(null == server || server.isClosed())
			{
			
		        server = new ServerSocket (Integer.parseInt(pmgr.getProperty("PExServer.OCM.PORT")));
		        log.info("Server Started");
		        log.info("TCPServer Waiting for client on port "+server.getLocalPort());
		       
		        while(true) 
		        {
		        	Socket connected = server.accept();

		        	pexTask.processTask(connected);
		        	log.info("PEx TCPServer-Client"+" "+connected.getInetAddress() +":"+connected.getPort()+" IS CONNECTED ");
		        }
			}
	        
		}catch(Exception e)
		{
			e.printStackTrace();
			log.error("TCPServer Exception", e);
		}
	}
	
	
	
	//@Scheduled(fixedDelay=500)
	public void startRBSServer()
	{
		try
		{
			if(null == rbsServer || rbsServer.isClosed())
			{
			
				rbsServer = new ServerSocket (9988);
		        log.info("RBS Server Started");
		        log.info("TCPServer Waiting for client on port "+rbsServer.getLocalPort());
		       
		        while(true) 
		        {
		        	Socket connected = rbsServer.accept();

		        	pexRBSTask.processTask(connected);
		        	log.info("PEx RBS Server["+rbsServer.getLocalPort()+"]-Client"+" "+connected.getInetAddress() +":"+connected.getPort()+" IS CONNECTED ");
		        }
			}
	        
		}catch(Exception e)
		{
			e.printStackTrace();
			log.error("TCPServer Exception", e);
		}
	}
	
	
	
	
	//@Scheduled(fixedDelay=500)
	public void startRBS2Server()
	{
		try
		{
			if(null == rbsServer2 || rbsServer2.isClosed())
			{
			
				rbsServer2 = new ServerSocket (54323);
		        log.info("rbsServer Started");
		        log.info("TCPServer Waiting for client on port 54323");
		       
		        while(true) 
		        {
		        	Socket connected = rbsServer2.accept();

		        	pexRBSTask.processTask(connected);
		        	log.info("PEx RBS Server["+rbsServer2.getLocalPort()+"]-Client"+" "+connected.getInetAddress() +":"+connected.getPort()+" IS CONNECTED ");
		        }
			}
	        
		}catch(Exception e)
		{
			e.printStackTrace();
			log.error("TCPServer Exception", e);
		}
	}
	
	
	@PreDestroy
	public void stopServer()
	{
		log.info("STOP SERVER");
		try {server.close();} catch (IOException e) {}
		try {rbsServer.close();} catch (IOException e) {}
		try {rbsServer2.close();} catch (IOException e) {}
		//rbsServer.close();
		//rbsServer2.close();
	}
	
}
