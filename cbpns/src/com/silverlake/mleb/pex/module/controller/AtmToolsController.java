package com.silverlake.mleb.pex.module.controller;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.caucho.hessian.client.HessianProxyFactory;
import com.silverlake.mleb.pex.server.socket.bean.OCMRequestHeader;
import com.silverlake.mleb.pex.server.socket.bean.OCM_PExCollectionRequest;
import com.silverlake.mleb.pex.server.socket.bean.OCM_PExCollectionResponse;
import com.silverlake.mleb.pex.server.socket.bean.OCM_UpdatePExStatusRequest;
import com.silverlake.mleb.pex.server.socket.bean.OCM_UpdatePExStatusResponse;
import com.silverlake.mleb.pex.util.DataBeanUtil;
import com.silverlake.mleb.pex.util.PropertiesManager;
import com.silverlake.mleb.pex.util.TCPClientUtil;

 
@Controller
public class AtmToolsController {
 
	final static Logger log = LogManager.getLogger(AtmToolsController.class);
 
	
	
	@RequestMapping(value = "atmsimtools", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.setViewName("atmTools");
		log.info("--------------  Get ATM Collection ------------------");
		return model;
	}
	
	
	@RequestMapping(value = "atmsimtools", method = RequestMethod.POST)
	public ModelAndView postatm(HttpServletRequest request,
	        HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		model.setViewName("atmTools");
		
		DataBeanUtil dataBeanUtil = new DataBeanUtil();
		PropertiesManager pm = new PropertiesManager();
		String pdata = pm.getProperty("atmsimtool");
	
		if(null!=pdata && pdata.equalsIgnoreCase("true"))
		{
		
			try
			{
			
				String pServerPort = pm.getProperty("PExServer.OCM.PORT");
				
				
				
				String pin = request.getParameter("pin");
				String amount = request.getParameter("amount");
				String ccode = request.getParameter("ccode");
				String mnumber = request.getParameter("mnumber");
				
				pin = ""==pin?null:pin;
				amount = ""==amount?null:(null==amount?null:amount+"00");
				ccode = ""==ccode?null:ccode;
				mnumber = ""==mnumber?null:mnumber;
				
				log.info("["+pin+"] : ["+amount+"] : ["+ccode+"] : ["+mnumber+"]");
				
				if(null!=pin && null!=amount && null!=ccode && null!=mnumber)
				{
					
					
					OCM_PExCollectionRequest requestBean = new OCM_PExCollectionRequest();
					requestBean.setRequestHeader(new OCMRequestHeader());
					OCMRequestHeader incommingRequest = new OCMRequestHeader();
					incommingRequest.setTranxcode_4_f_0("1001");
					incommingRequest.setRequestId_16_b_$(UUID.randomUUID().toString().substring(0, 16));
					incommingRequest.setChannelid_4_b_$("*EBS");
					incommingRequest.setVersion_4_f_0("1001");
					
					
					requestBean.setAmount_19_f_0(amount);
					requestBean.setCollectioncode_40_b_$(ccode);
					requestBean.setMobile_20_b_$(mnumber);
					requestBean.setPin_40_b_$(pin);
					requestBean.setCurrency_3_b_$("KHD");
					
					MessageDigest md = MessageDigest.getInstance( "SHA1" );
					md.update(requestBean.getCollectioncode_40_b_$().getBytes());
					String encryptedCollectionCode =  new BigInteger( 1, md.digest() ).toString(16);
					requestBean.setCollectioncode_40_b_$(encryptedCollectionCode);
					
				
					md.update(requestBean.getPin_40_b_$().getBytes());
					String encryptedPin =  new BigInteger( 1, md.digest() ).toString(16);
					requestBean.setPin_40_b_$(encryptedPin);
					
					
					TCPClientUtil tcpClient = null;
					
					
					int outLenght = dataBeanUtil.getFullObjectLength(requestBean);
					incommingRequest.setMessageLength_9_f_0(String.valueOf(outLenght-9));
					requestBean.setRequestHeader(incommingRequest);
					byte[] outMsgByte = dataBeanUtil.getFieldNamesAndByte(requestBean, outLenght, "CP037");
					
					byte[] tcpResponse;
					int[] rbsPort = { Integer.parseInt(pServerPort) };
					try
					{
						tcpClient = new TCPClientUtil("localhost", rbsPort, 10*1000 , "CP037", 1024 * 8);
					}catch(Exception e)
					{
						model.addObject("rsx", "Failed to connect TCP Server : [localhost:"+rbsPort+"] ");
						throw new Exception(e);
					}
					tcpClient.send(outMsgByte);
					tcpResponse = tcpClient.receiveByte();
					tcpClient.closeSocketx();
					
					
					OCM_PExCollectionResponse pexResponse = new OCM_PExCollectionResponse();
					pexResponse = (OCM_PExCollectionResponse) dataBeanUtil.setFieldNamesAndByte(pexResponse, tcpResponse, "CP037");
					String resultRsp = pexResponse.getResponseHeader().getResponsecode_20_b_$();
					
					String result1 = "Result Validate Input :: "+resultRsp;
					model.addObject("rs1", result1);
					
					
					if(resultRsp.trim().equalsIgnoreCase("0000000"))
					{
						
						OCM_UpdatePExStatusRequest updateRequest = new OCM_UpdatePExStatusRequest();
						requestBean.setRequestHeader(new OCMRequestHeader());
						OCMRequestHeader headerRequest = new OCMRequestHeader();
						headerRequest.setTranxcode_4_f_0("1002");
						headerRequest.setRequestId_16_b_$(UUID.randomUUID().toString().substring(0, 16));
						headerRequest.setChannelid_4_b_$("*EBS");
						headerRequest.setVersion_4_f_0("1001");
						
						updateRequest.setEarmarkRefId_40_b_$(pexResponse.getEarmarkRefId_40_b_$());
						updateRequest.setPexRefNo_20_b_$(pexResponse.getPexRefNo_20_b_$());
						updateRequest.setSeqNumber_9_f_0("");
						updateRequest.setStatus_12_b_$("001");
						updateRequest.setTranCode_10_b_$("");
						
					
						outLenght = dataBeanUtil.getFullObjectLength(updateRequest);
						headerRequest.setMessageLength_9_f_0(String.valueOf(outLenght-9));
						updateRequest.setRequestHeader(headerRequest);
						outMsgByte = dataBeanUtil.getFieldNamesAndByte(updateRequest, outLenght, "CP037");
						tcpClient = new TCPClientUtil("localhost", rbsPort, 10*1000 , "CP037", 1024 * 8);
						tcpClient.send(outMsgByte);
						tcpResponse = tcpClient.receiveByte();
						tcpClient.closeSocketx();
						
						OCM_UpdatePExStatusResponse updateResponse = new OCM_UpdatePExStatusResponse();
						updateResponse = (OCM_UpdatePExStatusResponse) dataBeanUtil.setFieldNamesAndByte(updateResponse, tcpResponse, "CP037");
						resultRsp = updateResponse.getResponseHeader().getResponsecode_20_b_$();
						
						
						String result2 = "Result Perform ATM Collection :: "+resultRsp;
						model.addObject("rs2", result2);
					}
					else
					{
						
					}
					
					
				}
				else if(null!=pin || null!=amount || null!=ccode || null!=mnumber)
				{
					log.info("----invalid Input-----");
					String error = "Invalid Input Data";
					model.addObject("rsx", error);
				}
		
			}catch( Exception ex)
			{
				ex.printStackTrace();
			}
		
		}
		
		
		return model;
	}
 
}