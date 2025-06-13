package com.silverlake.mleb.pex.server.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.pex.entity.ATMPExLog;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.server.socket.bean.OCMRequestHeader;
import com.silverlake.mleb.pex.server.socket.bean.OCMResponseHeader;
import com.silverlake.mleb.pex.server.socket.bean.OCM_PExCollectionResponse;
import com.silverlake.mleb.pex.server.socket.bean.OCM_UpdatePExStatusResponse;
import com.silverlake.mleb.pex.server.socket.handler.PerformPExATMCollection;
import com.silverlake.mleb.pex.server.socket.handler.UpdatePExStatus;
import com.silverlake.mleb.pex.util.DataBeanUtil;
import com.silverlake.mleb.pex.util.StringDataUtil;


@Service
public class ProcessOCMHandler extends TCPHandler
{
  
	private static Logger log = LogManager.getLogger(ProcessOCMHandler.class);

	public DataBeanUtil dataBeanUtil = new DataBeanUtil();
	
	@Autowired PerformPExATMCollection performATMPExCollection;
	@Autowired UpdatePExStatus updatePExStatus;
	@Autowired MLEBPExDAO dao;
	
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
					Date requestDateTime = new Date();
					log.debug("OCM MSG");
					Date dateReceive = new Date();
					if(new String(receiveByteData,MESSAGE_ENCODING).equalsIgnoreCase(CONNECTION_DISCONNECTED))
					{
						
					}
					else
					{
						
						
						OCMRequestHeader incommingRequest = new OCMRequestHeader();
						
						incommingRequest = (OCMRequestHeader) dataBeanUtil.setFieldNamesAndByte(incommingRequest, receiveByteData, MESSAGE_ENCODING);
						
						//validate & collect PEX
						if(incommingRequest.getTranxcode_4_f_0().equalsIgnoreCase("1001") && null!=incommingRequest.getRequestId_16_b_$())
						{
							
							
							OCM_PExCollectionResponse outGoingResponse = new OCM_PExCollectionResponse();
							outGoingResponse = performATMPExCollection.processTask(receiveByteData,MESSAGE_ENCODING);
							
							
							
							int outLenght = dataBeanUtil.getFullObjectLength(outGoingResponse);
							outGoingResponse.getResponseHeader().setMessageLength_9_f_0(String.valueOf(outLenght-9));
							outGoingResponse.setResponseHeader(insertRequestResponseTime(requestDateTime,incommingRequest,outGoingResponse.getResponseHeader(),outGoingResponse.getPexRefNo_20_b_$(),incommingRequest.getTranxcode_4_f_0().trim()));
							byte[] outMsgByte = dataBeanUtil.getFieldNamesAndByte(outGoingResponse, outLenght, MESSAGE_ENCODING);
							sendByte(outMsgByte,out);
						}
						else if(incommingRequest.getTranxcode_4_f_0().equalsIgnoreCase("1002") && null!=incommingRequest.getRequestId_16_b_$())
						{
							
							
							OCM_UpdatePExStatusResponse outGoingResponse = new OCM_UpdatePExStatusResponse();
							outGoingResponse = updatePExStatus.processTask(receiveByteData,MESSAGE_ENCODING);
							
							
							
							int outLenght = dataBeanUtil.getFullObjectLength(outGoingResponse);
							outGoingResponse.getResponseHeader().setMessageLength_9_f_0(String.valueOf(outLenght-9));
							outGoingResponse.setResponseHeader(insertRequestResponseTime(requestDateTime,incommingRequest,outGoingResponse.getResponseHeader(),outGoingResponse.getPexRefNo_20_b_$(),incommingRequest.getTranxcode_4_f_0().trim()));
							byte[] outMsgByte = dataBeanUtil.getFieldNamesAndByte(outGoingResponse, outLenght, MESSAGE_ENCODING);
							sendByte(outMsgByte,out);
						}
						else
						{
						
							
							OCM_PExCollectionResponse outGoingResponse = new OCM_PExCollectionResponse();
							outGoingResponse.getResponseHeader().setResponsecode_20_b_$("9999997");
							
							
							
							
							int outLenght = dataBeanUtil.getFullObjectLength(outGoingResponse);
							outGoingResponse.getResponseHeader().setMessageLength_9_f_0(String.valueOf(outLenght-9));
							
							outGoingResponse.setResponseHeader(insertRequestResponseTime(requestDateTime,incommingRequest,outGoingResponse.getResponseHeader(),"",""));
							
							byte[] outMsgByte = dataBeanUtil.getFieldNamesAndByte(outGoingResponse, outLenght, MESSAGE_ENCODING);
							sendByte(outMsgByte,out);
						}
						
						
					}
					
					
				}catch (Exception ex)
				{
					log.info("PEx TCPServer Process OCM Exception :::::::::::::: ",ex);
					OCM_PExCollectionResponse outGoingResponse = new OCM_PExCollectionResponse();
					outGoingResponse.getResponseHeader().setResponsecode_20_b_$("9999998");
					
					int outLenght = dataBeanUtil.getFullObjectLength(outGoingResponse);
					outGoingResponse.getResponseHeader().setMessageLength_9_f_0(String.valueOf(outLenght-9));
					byte[] outMsgByte = dataBeanUtil.getFieldNamesAndByte(outGoingResponse, outLenght, MESSAGE_ENCODING);
					sendByte(outMsgByte,out);
			
				}

			}
			
			
			closeConnection(in,out,connected);
				
		}catch (Exception e)
		{
			
			
			log.info("PEx TCPServer OCM Exception :::::::::::::: ",e);
			
		}
	}
	

   
   public static void main (String args[]) throws IllegalArgumentException, IllegalAccessException
   {
		
   }

   
   public OCMResponseHeader insertRequestResponseTime(Date requestDateTime,OCMRequestHeader ocmRequestHeader, OCMResponseHeader ocmResponseHeader, String ref_no, String serviceId)
   {
	  // String [] datetime = new String[2];
	  
	   SimpleDateFormat refDateTImeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	  // datetime[0] = refDateTImeFormat.format(requestDateTime);
	   //datetime[1] = refDateTImeFormat.format(responseDateTime);
	   
	   ocmResponseHeader.setRequestDate_23_b_$(refDateTImeFormat.format(requestDateTime));
	   
	   
	   
	   //insertLog
	   ATMPExLog atmLog = new ATMPExLog();
	   atmLog.setRequestId(ocmRequestHeader.getRequestId_16_b_$().trim());
	   atmLog.setStatusCode(ocmResponseHeader.getResponsecode_20_b_$());
	   atmLog.setRequestDateTime(requestDateTime);
	   atmLog.setRefNo(ref_no==null?null:ref_no.trim());
	   atmLog.setVersion(ocmRequestHeader.getVersion_4_f_0());
	   atmLog.setService(serviceId);
	   
	   
	   
	   Date responseDateTime = new Date();
	   atmLog.setProcessTime(Integer.parseInt((responseDateTime.getTime()-requestDateTime.getTime())+""));
	   ocmResponseHeader.setResponseDate_23_b_$(refDateTImeFormat.format(responseDateTime));
	   dao.insertEntity(atmLog);
	   return ocmResponseHeader;
   }
	
}
