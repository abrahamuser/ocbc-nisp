package com.silverlake.mleb.pex.server.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.pex.server.socket.bean.RBSRequest;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPECResponse;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPERRResponse;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPResponse;
import com.silverlake.mleb.pex.util.DataBeanUtil;
import com.silverlake.mleb.pex.util.StringDataUtil;


@Service
public class ProcessRBSHandler 
{
  
	private static Logger log = LogManager.getLogger(ProcessRBSHandler.class);
	
	public static final int maxMsgSize = 10000;
	public static final String CONNECTION_DISCONNECTED = "@DISCONNECTED@";
	public static final String MESSAGE_ENCODING = "CP037";
	public static final int SERVER_RECEIVE_SEND_TIMEOUT = 30000;
	public DataBeanUtil dataBeanUtil = new DataBeanUtil();
	
	
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
			if(null!=connected && connected.isConnected()   )
			{
				try
				{
					//rData = receive(in,connected);
					
					byte[] receiveByteData  = receiveByte(in);
					
				
					Date dateReceive = new Date();
					if(new String(receiveByteData).equalsIgnoreCase(CONNECTION_DISCONNECTED))
					{
						
					}
					else
					{
						
						
						RBSRequest incommingRequest = new RBSRequest();
						
						incommingRequest = (RBSRequest) dataBeanUtil.setFieldNamesAndByte(incommingRequest, receiveByteData, MESSAGE_ENCODING);
						
						//earmark
						if(incommingRequest.getHostMsg().getTransactionCode_10_b_$().trim().equalsIgnoreCase("9128") || incommingRequest.getHostMsg().getTransactionCode_10_b_$().trim().equalsIgnoreCase("9129"))
						{
							Thread.sleep(35000);
							RBS_DSPResponse outGoingResponse = new RBS_DSPResponse();
							outGoingResponse.getResponseHeader().setRspCOD_2_b_$("F1");
							int outLenght = dataBeanUtil.getFullObjectLength(outGoingResponse);
							
							outGoingResponse.getDspHeader().setMessageLength_4_n_0(String.valueOf(outLenght-4));
							byte[] outMsgByte = dataBeanUtil.getFieldNamesAndByte(outGoingResponse, outLenght, MESSAGE_ENCODING);
							sendByte(outMsgByte,out);
						}
						else
						{
							
							RBS_DSPERRResponse outGoingResponse = new RBS_DSPERRResponse();
							outGoingResponse.getResponse41().getRspHeader().setRspCOD_2_b_$("F2");
							outGoingResponse.getResponse42().setErrorCode_2_p_0("456");
							int outLenght = dataBeanUtil.getFullObjectLength(outGoingResponse);
							outGoingResponse.getDspHeader().setMessageLength_4_n_0(String.valueOf(outLenght-4));
							byte[] outMsgByte = dataBeanUtil.getFieldNamesAndByte(outGoingResponse, outLenght, MESSAGE_ENCODING);
							sendByte(outMsgByte,out);
						}
						
						
					}
					
					/*if(rData.length()>9)
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
							
							
							RBS_DSPRequest earmark = new RBS_DSPRequest();
							earmark = (RBS_DSPRequest) dataBeanUtil.setFieldNamesAndValues(earmark, rData);
							
							log.info("SOCKET SERVER ["+connected.getPort()+"] RBS PROCCESS DSP Transaction Code : "+earmark.getMbaseHd1Msg().getTransactionCode_10_b_$());
							
							
							if(earmark.getMbaseHd1Msg().getTransactionCode_10_b_$().trim().equalsIgnoreCase("00009"))
							{
								//tac - EC
								RBS_DSPECResponse inquiryProfileResponse = new RBS_DSPECResponse();
								int rspLength = dataBeanUtil.getFieldNamesAndValues(inquiryProfileResponse).length();
								inquiryProfileResponse.getDspHeader().setMessageLength_4_n_0(String.valueOf((rspLength-9)));
								inquiryProfileResponse.getResponseEC().getRspHeader().setRspCOD_2_b_$("EC");
								inquiryProfileResponse.getResponseEC().setCustContactNumTAC_30_b_$("0122512231");
								soutMsg = dataBeanUtil.getFieldNamesAndValues(inquiryProfileResponse);
								
								
							}
							else if(earmark.getMbaseHd1Msg().getTransactionCode_10_b_$().trim().equalsIgnoreCase("000010"))
							{
								//acc - DA
								RBS_DSPDAResponse inquiryAccResponse = new RBS_DSPDAResponse();
								int rspLength = dataBeanUtil.getFieldNamesAndValues(inquiryAccResponse).length();
								inquiryAccResponse.getDspHeader().setMessageLength_4_n_0(String.valueOf((rspLength-9)));
								inquiryAccResponse.getResponseDA().getRspHeader().setRspCOD_2_b_$("DA");
								inquiryAccResponse.getResponseDA().setAccountName_40_b_$("James Bond");
								soutMsg = dataBeanUtil.getFieldNamesAndValues(inquiryAccResponse);
							}
							else
							{
							
								RBS_DSPResponse earMarkresponse = new RBS_DSPResponse();
								int rspLength = dataBeanUtil.getFieldNamesAndValues(earMarkresponse).length();
								
							
								earMarkresponse.getDspHeader().setMessageLength_4_n_0(String.valueOf((rspLength-9)));
								earMarkresponse.getResponseHeader().setRspCOD_2_b_$("F1");
								
								
								soutMsg = dataBeanUtil.getFieldNamesAndValues(earMarkresponse);
							}
							
							
							log.info("SOCKET SERVER RBS RESPONSE : ["+soutMsg+"]");
							send(soutMsg,out,dateReceive);
						}
						
						
					}
					else
					{
						if(null!=rData && rData.length()>0)
						log.info("RECEIVED INVALID MSG : "+rData);
					}*/
					
					
					
				}catch (Exception ex)
				{
					log.info("PEx TCPServer Process Exception :::::::::::::: ",ex);
					RBS_DSPERRResponse outGoingResponse = new RBS_DSPERRResponse();
					
					outGoingResponse.getResponse41().getRspHeader().setRspCOD_2_b_$("F2");
					outGoingResponse.getResponse42().setErrorCode_2_p_0("123");
					int outLenght = dataBeanUtil.getFullObjectLength(outGoingResponse);
					outGoingResponse.getDspHeader().setMessageLength_4_n_0(String.valueOf(outLenght-4));
					byte[] outMsgByte = dataBeanUtil.getFieldNamesAndByte(outGoingResponse, outLenght, MESSAGE_ENCODING);
					sendByte(outMsgByte,out);
				}

			}
			
			
			closeConnection(in,out,connected);
				
		}catch (Exception e)
		{
			
			
			log.info("PEx TCPServer Exception :::::::::::::: ",e);
			
		}
	}
	
	
	public static final byte[] intTo4ByteArray(int value) {
	    return new byte[] {
	            (byte)(value >>> 24),
	            (byte)(value >>> 16),
	            (byte)(value >>> 8),
	            (byte)value};
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

	

	
	
	 public byte[] receiveByte(BufferedInputStream in)throws IOException {
			
			byte[] buffer = new byte[maxMsgSize];
				int msgResponseLength = 0;
				int bite = 0;
				int n = 0;
				int tryReceive = 0;
				while(bite>=0 )
				{
					
					try {
						bite = in.read();
						if(bite==-1)
						{		
							return "CONNECTION_DISCONNECTED".getBytes();
						}
					} 
					catch (SocketException ce) {
						ce.printStackTrace();
				
						return "CONNECTION_DISCONNECTED".getBytes();
					}
					catch (IOException e) {
						
						break;
					}
					
					buffer[n++] = (byte) bite;
					
					
					if(n==4)
					{
						byte[] msgL = {buffer[0],buffer[1],buffer[2],buffer[3]};
						msgResponseLength = ByteBuffer.wrap(msgL).getInt();
						
						
					}
					else if(n>4)
					{
						msgResponseLength--;
						
						if(msgResponseLength == 0)
						{
							break;
						}
					}
					
					
				}

		    return buffer;
		} 
	   
	
	
	
	public String  receive(BufferedInputStream in,Socket socket){
		byte[] buffer = new byte[maxMsgSize];
		byte[] bufferx  = null;
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
						
						bufferx = new byte[9+msgResponseLength];
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
				
				System.arraycopy(buffer, 0, bufferx, 0, bufferx.length);
				result = new String(bufferx,MESSAGE_ENCODING);
			}
			catch (Exception ex) {
				log.info("PEx receive msg format exception", ex);
				return CONNECTION_DISCONNECTED;
			}

	
			
	    return result;
	}
   
   
   public void  send(String reqMessage, BufferedOutputStream out, Date dateReceive) throws Exception{
	   	Date dateSend = new Date();
		if((dateSend.getTime()- dateReceive.getTime())+ 3000 < SERVER_RECEIVE_SEND_TIMEOUT)
		{
			out.write(reqMessage.getBytes(MESSAGE_ENCODING));      
		       out.flush();
		}
       
   }
   
   
	  public void sendByte(byte[] msg,BufferedOutputStream out) throws Exception{
		   
	       out.write(msg);
	       
	       out.flush();
		  
	       
	      /* DataOutputStream outToServer = new DataOutputStream(sock.getOutputStream());
	   outToServer.writeBytes(reqMessage);*/
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
	   
	   
	  
	   System.out.println("Length Check : "+lengthData + " ["+receiveMsgData.substring(9).length()+"]");
	   if(lengthData == receiveMsgData.substring(9).length())
	   {
		   return true;
	   }
	   
	   
	   
	   return false;
   }
	
   
   
   public static void main (String args[]) throws IllegalArgumentException, IllegalAccessException
   {
		DataBeanUtil dataBeanUtil = new DataBeanUtil();
		RBS_DSPECResponse inquiryProfileResponse = new RBS_DSPECResponse();
		int rspLength = dataBeanUtil.getFieldNamesAndValues(inquiryProfileResponse).length();
		inquiryProfileResponse.getDspHeader().setMessageLength_4_n_0(String.valueOf((rspLength-9)));
		inquiryProfileResponse.getResponseEC().getRspHeader().setRspCOD_2_b_$("EC");
		inquiryProfileResponse.getResponseEC().setCustContactNumTAC_30_b_$("0122512231");
		String soutMsg = dataBeanUtil.getFieldNamesAndValues(inquiryProfileResponse);
		System.out.println(soutMsg);
   }
	
	
}
