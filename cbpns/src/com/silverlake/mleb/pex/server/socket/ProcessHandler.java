package com.silverlake.mleb.pex.server.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.pex.server.socket.bean.DSPHeader;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPRequest;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPResponse;
import com.silverlake.mleb.pex.util.DataBeanUtil;
import com.silverlake.mleb.pex.util.StringDataUtil;


@Service
public class ProcessHandler 
{
  
	private static Logger log = LogManager.getLogger(ProcessHandler.class);
	
	public static final int maxMsgSize = 10000;
	public static final String CONNECTION_DISCONNECTED = "@DISCONNECTED@";
	public static final String MESSAGE_ENCODING = "Cp1047";
	public static final int SERVER_RECEIVE_SEND_TIMEOUT = 30000;

	
	
	@Async
	public void processTask(Socket connected)
	{
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		StringDataUtil strDataUtil = new StringDataUtil();
		try
		{
			connected.setKeepAlive(true);
			connected.setSoTimeout(SERVER_RECEIVE_SEND_TIMEOUT);
			
			in = new BufferedInputStream(connected.getInputStream());
			out = new BufferedOutputStream(connected.getOutputStream());
			String rData = null;
			while(null!=connected && connected.isConnected()   )
			{
				try
				{
					rData = receive(in,connected);
				
					Date dateReceive = new Date();
					if(rData.equalsIgnoreCase(CONNECTION_DISCONNECTED))
					{
						break;
					}
					
					if(rData.length()>9)
					{
					
						
						boolean chkFormat = checkLengthFormat(rData);
						
						String soutMsg = null;
						if(!chkFormat)
						{
							log.info("RECEIVED INVALID MSG LENGTH : "+rData);
						}
						else
						{
							//proccess Logic
							
							DataBeanUtil dataBeanUtil = new DataBeanUtil();
							
							DSPHeader dspHeader = new DSPHeader();
							int dspHeader_length = dataBeanUtil.getFieldNamesAndValues(dspHeader).length();
							String dspHeader_msg = rData.substring(0, dspHeader_length);
							
							dspHeader = (DSPHeader) dataBeanUtil.setFieldNamesAndValues(dspHeader, dspHeader_msg);
							
							
							
							if(dspHeader.getTransactionCode_4_b_$().equalsIgnoreCase("9987"))
							{
								RBS_DSPRequest earmark = new RBS_DSPRequest();
								earmark = (RBS_DSPRequest) dataBeanUtil.setFieldNamesAndValues(earmark, rData);
								
								log.info("SOCKET SERVER PROCCESS DSP Transaction Code : "+earmark.getDspHeader().getTransactionCode_4_b_$());
								
								RBS_DSPResponse earMarkresponse = new RBS_DSPResponse();
								int rspLength = dataBeanUtil.getFieldNamesAndValues(earMarkresponse).length();
								
							
								earMarkresponse.getDspHeader().setMessageLength_4_n_0(String.valueOf((rspLength-9)));
								earMarkresponse.getDspHeader().setTransactionCode_4_b_$("9987");
								//earMarkresponse.getMbaseMsg().setMessageType_4_b_$("*OUT");
								//earMarkresponse.getMbaseMsg().setMessageCode_4_f_0("0200");
								
								soutMsg = dataBeanUtil.getFieldNamesAndValues(earMarkresponse);
								
								
								
							}
							
							
							
							log.info("SOCKET SERVER RESPONSE : ["+soutMsg+"]");
							send(soutMsg,out,dateReceive);
						}
						
						
					}
					else
					{
						if(null!=rData && rData.length()>0)
						log.info("RECEIVED INVALID MSG : "+rData);
					}
					
					
					
				}catch (Exception ex)
				{
					log.info("PEx TCPServer Process Exception :::::::::::::: ",ex);
				}

			}
			
			
			closeConnection(in,out,connected);
				
		}catch (Exception e)
		{
			
			
			log.info("PEx TCPServer Exception :::::::::::::: ",e);
			
		}
	}
	
	public void closeConnection(BufferedInputStream in, BufferedOutputStream out, Socket connected)
	{
		try
		{
			if(null!=in)
				in.close();
			if(null!=out)
				out.close();
			if(null!=connected && !connected.isClosed())
				connected.close();
			log.info("Pex TCPServer-Client "+connected.getInetAddress() +":"+connected.getPort()+" IS DISCONNECTED ");
			
		}
		catch(Exception conx)
		{
		
		}
	}

	

	public String  receive(BufferedInputStream in,Socket socket){
		byte[] buffer = new byte[maxMsgSize];

			int msgResponseLength = 0;
			int bite = 0;
			int n = 0;
			int tryReceive = 0;
			String result = "";
			try
			{
			
				while(bite>=0 )
				{
	
					
					try {
						bite = in.read();
							if(bite==-1)
							{		
								
								return CONNECTION_DISCONNECTED;
							}
						
						} catch(SocketException ce)
						{
							log.info("SocketException : "+ce.getMessage());
							
							return CONNECTION_DISCONNECTED;
							
						}			
						catch (IOException e) {
							result = new String(buffer,MESSAGE_ENCODING);
							return result.trim();
						}
				
					buffer[n++] = (byte) bite;
					
					
					if(n==9)
					{
					
						String lengtMsg = new String (buffer,MESSAGE_ENCODING);
						msgResponseLength = Integer.parseInt(lengtMsg.substring(0, 9));
					}
					else if(n>9)
					{
						msgResponseLength--;
						
						if(msgResponseLength == 0)
						{
							break;
						}
					}
					
					
				}
				result = new String(buffer,MESSAGE_ENCODING);
			}
			catch (Exception ex) {
				log.info("PEx receive msg format exception", ex);
				return CONNECTION_DISCONNECTED;
			}

	
	    return result.trim();
	}
   
   
   public void  send(String reqMessage, BufferedOutputStream out, Date dateReceive) throws Exception{
	   	Date dateSend = new Date();
		if((dateSend.getTime()- dateReceive.getTime())+ 3000 < SERVER_RECEIVE_SEND_TIMEOUT)
		{
			out.write(reqMessage.getBytes(MESSAGE_ENCODING));      
		       out.flush();
		}
       
   }
   
   
   public boolean checkLengthFormat(String receiveMsgData)
   {
	   int lengthData = 0;
	   try
	   {
		   lengthData = Integer.parseInt(receiveMsgData.substring(0, 9));
	   }catch (Exception e)
	   {
		   
	   }
	   
	   
	  
	   
	   if(lengthData == receiveMsgData.substring(9).length())
	   {
		   return true;
	   }
	   
	   
	   
	   return false;
   }
	
   public static final byte[] intTo4ByteArray(int value) {
	    return new byte[] {
	            (byte)(value >>> 24),
	            (byte)(value >>> 16),
	            (byte)(value >>> 8),
	            (byte)value};
	}
	
}
