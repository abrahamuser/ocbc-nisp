package com.silverlake.mleb.pex.module.rbs.services;

// Generated May 22, 2013 6:08:42 PM by Hibernate Tools 3.4.0.CR1

import java.net.InetAddress;

import com.silverlake.mleb.pex.server.socket.bean.DSPHeader;
import com.silverlake.mleb.pex.util.PropertiesManager;

public abstract class DSPMsgUtil {
	
	private PropertiesManager pmgr = new PropertiesManager();
	
	public DSPMsgUtil()
	{
		
	}
	protected String ip;
	protected int port;
	protected int timeoutInSecond;
	
	public final static String RBS_DATE = "ddMMyy";
	public final static String RBS_EXPDATE = "ddMMyy";
	public final static String REF_DATE_FORMAT = "yyyyMMdd";
	public final static String SUCCESS_RESPONSE = "success";
	public final static String ERROR_RESPONSE = "error";
	
	public DSPHeader getDspHeader(String portNumber) throws Exception {
		DSPHeader dspHeader = new DSPHeader();
		dspHeader.setVersionNumber_4_f_0("0005");
		dspHeader.setHeaderFormat_4_b_$("*DSP");
		dspHeader.setHeaderType_5_b_$("*MOBI");
		dspHeader.setDeviceName_15_b_$(InetAddress.getLocalHost().toString());
		dspHeader.setSocketNumber_5_f_0("00002");
		dspHeader.setPortNumber_6_f_0(portNumber);
		dspHeader.setHeaderLength_4_f_0("213");
		dspHeader.setMessageLength_6_f_0("1237");
		dspHeader.setDataFormat_10_b_$("ABCS");
		dspHeader.setSourceID_10_b_$("*MOBI");
		dspHeader.setSenarioNumber_16_b_$("BBHTLMONEYFNC");

		return dspHeader;
	}
	
	
	public void setEnv()
	{
		ip = pmgr.getProperty("RBS.IP");
		port = Integer.parseInt(pmgr.getProperty("RBS.PORT01"));
		timeoutInSecond = 30;
	}
	
	
	
}
