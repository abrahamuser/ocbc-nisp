package com.silverlake.mleb.pex.module.services;

// Generated May 22, 2013 6:08:42 PM by Hibernate Tools 3.4.0.CR1

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silverlake.hlb.mib.bean.ObAccountBean;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.server.socket.bean.DSPHeader;
import com.silverlake.mleb.pex.server.socket.bean.HostMbaseMsg;
import com.silverlake.mleb.pex.server.socket.bean.HostMbaseViMsg;
import com.silverlake.mleb.pex.server.socket.bean.MBaseMsg;
import com.silverlake.mleb.pex.server.socket.bean.MBaseResponseDA;
import com.silverlake.mleb.pex.server.socket.bean.MBaseResponseEC;
import com.silverlake.mleb.pex.server.socket.bean.RBSRequest;
import com.silverlake.mleb.pex.server.socket.bean.RBSResponse;
import com.silverlake.mleb.pex.server.socket.bean.RBSViRequest;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPDAResponse;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPECResponse;
import com.silverlake.mleb.pex.server.socket.bean.RBS_DSPFBResponse;
import com.silverlake.mleb.pex.util.DataBeanUtil;
import com.silverlake.mleb.pex.util.PropertiesManager;
import com.silverlake.mleb.pex.util.TCPClientUtil;

public class RBSServices
{
	MLEBPExDAO dao;
	private PropertiesManager pmgr = new PropertiesManager();
	private static Logger log = LogManager.getLogger(RBSServices.class);
	public RBSServices(MLEBPExDAO dao)
	{
		
		tranCode_caAddHold = pmgr.getProperty("RBS.TRANX.CA.ADDHOLD");
		tranCode_saAddHold = pmgr.getProperty("RBS.TRANX.SA.ADDHOLD");
		tranCode_caReleaseHold = pmgr.getProperty("RBS.TRANX.CA.RELEASEHOLD");
		tranCode_saReleaseHold = pmgr.getProperty("RBS.TRANX.SA.RELEASEHOLD");
		tranCode_caFTca = pmgr.getProperty("RBS.TRANX.CA.CA.FT");
		tranCode_caFTsa = pmgr.getProperty("RBS.TRANX.CA.SA.FT");
		tranCode_saFTca = pmgr.getProperty("RBS.TRANX.SA.CA.FT");
		tranCode_saFTsa = pmgr.getProperty("RBS.TRANX.SA.SA.FT");
		
		
		tranCode_inquiryTACnumber = pmgr.getProperty("RBS.TRANX.TACNUMBER.INQ");
		tranCode_inquiryAccDetails = pmgr.getProperty("RBS.TRANX.ACCDETAILS.INQ");
		tranCode_inquiryAccDetailsSA = pmgr.getProperty("RBS.TRANX.ACCDETAILS.SA.INQ");
		tranCode_caAddHold_pex = pmgr.getProperty("RBS.TRANX.CA.ADDHOLD.DIRECT");
		tranCode_saAddHold_pex = pmgr.getProperty("RBS.TRANX.SA.ADDHOLD.DIRECT");
		tranCode_caReleaseHold_pex = pmgr.getProperty("RBS.TRANX.CA.RELEASEHOLD.DIRECT");
		tranCode_saReleaseHold_pex = pmgr.getProperty("RBS.TRANX.SA.RELEASEHOLD.DIRECT");
		tranCode_caFTca_pex = pmgr.getProperty("RBS.TRANX.CA.CA.FT.DIRECT");
		tranCode_caFTsa_pex = pmgr.getProperty("RBS.TRANX.CA.SA.FT.DIRECT");
		tranCode_saFTca_pex = pmgr.getProperty("RBS.TRANX.SA.CA.FT.DIRECT");
		tranCode_saFTsa_pex = pmgr.getProperty("RBS.TRANX.SA.SA.FT.DIRECT");
		this.dao =  dao;
	}
	
	
	public static final String SAVING_TYPE = "sav";
	public static final String CURRENT_TYPE = "cur";
	
	public static String tranCode_caAddHold = "9128";
	public static String tranCode_saAddHold = "9129";
	public static String tranCode_caReleaseHold = "9130";
	public static  String tranCode_saReleaseHold = "9131";
	public static  String tranCode_caFTca = "9137";
	public static  String tranCode_caFTsa = "9136";
	public static  String tranCode_saFTca = "9138";
	public static  String tranCode_saFTsa = "9139";
	
	public static  String tranCode_inquiryTACnumber = "9120";
	public static  String tranCode_inquiryAccDetails = "9121";
	public static  String tranCode_inquiryAccDetailsSA = "9121";
	public static  String tranCode_caAddHold_pex = "9132";
	public static  String tranCode_saAddHold_pex = "9133";
	public static  String tranCode_caReleaseHold_pex = "9134";
	public static  String tranCode_saReleaseHold_pex = "9135";
	public static  String tranCode_caFTca_pex = "9123";
	public static  String tranCode_caFTsa_pex = "9122";
	public static  String tranCode_saFTca_pex = "9124";
	public static  String tranCode_saFTsa_pex = "9125";
	
	
	
	private String fullResponseData;
	private byte[] fullResponseByte ;
	
	public int sender_bene_name_length = 40;
	public int remark_length = 40;
	
	
	/*public RBSServices()
	{
		tranCode_caAddHold = pmgr.getProperty("RBS.TRANX.CA.ADDHOLD");
		tranCode_saAddHold = pmgr.getProperty("RBS.TRANX.SA.ADDHOLD");
		tranCode_caReleaseHold = pmgr.getProperty("RBS.TRANX.CA.RELEASEHOLD");
		tranCode_saReleaseHold = pmgr.getProperty("RBS.TRANX.SA.RELEASEHOLD");
		tranCode_caFTca = pmgr.getProperty("RBS.TRANX.CA.CA.FT");
		tranCode_caFTsa = pmgr.getProperty("RBS.TRANX.CA.SA.FT");
		tranCode_saFTca = pmgr.getProperty("RBS.TRANX.SA.CA.FT");
		tranCode_saFTsa = pmgr.getProperty("RBS.TRANX.SA.SA.FT");
		
		
		tranCode_inquiryTACnumber = pmgr.getProperty("RBS.TRANX.TACNUMBER.INQ");
		tranCode_inquiryAccDetails = pmgr.getProperty("RBS.TRANX.ACCDETAILS.INQ");
		tranCode_caAddHold_pex = pmgr.getProperty("RBS.TRANX.CA.ADDHOLD.DIRECT");
		tranCode_saAddHold_pex = pmgr.getProperty("RBS.TRANX.SA.ADDHOLD.DIRECT");
		tranCode_caReleaseHold_pex = pmgr.getProperty("RBS.TRANX.CA.RELEASEHOLD.DIRECT");
		tranCode_saReleaseHold_pex = pmgr.getProperty("RBS.TRANX.SA.RELEASEHOLD.DIRECT");
		tranCode_caFTca_pex = pmgr.getProperty("RBS.TRANX.CA.CA.FT.DIRECT");
		tranCode_caFTsa_pex = pmgr.getProperty("RBS.TRANX.CA.SA.FT.DIRECT");
		tranCode_saFTca_pex = pmgr.getProperty("RBS.TRANX.SA.CA.FT.DIRECT");
		tranCode_saFTsa_pex = pmgr.getProperty("RBS.TRANX.SA.SA.FT.DIRECT");
		
		
	}*/
	
	
	
	/*public String earMarkAccount(boolean isPexDirect, ObAccountBean eMarkAcc, String tranxAccType, BigDecimal Amount, String currency, String payeeRef, Date transactionDate, Date expiredDate,String runningNumber) throws IOException 
	{
		TCPClientUtil tcpClient = null;
		try
		{
			String rbsIP = pmgr.getProperty("RBS.IP");
			int rbsPort01 = Integer.parseInt(pmgr.getProperty("RBS.PORT01"));
			//int rbsPort02 = Integer.parseInt(pmgr.getProperty("RBS.PORT02"));
			int[] rbsPort = {rbsPort01};
			
			DataBeanUtil dataBeanUtil = new DataBeanUtil();
			RBS_DSPRequest rbsEarMarkAccMsg = new RBS_DSPRequest();
	
			SimpleDateFormat tranxFormat = new SimpleDateFormat(PExConstant.PEX_RBS_DATE);
			SimpleDateFormat expiredFormat = new SimpleDateFormat(PExConstant.PEX_RBS_EXPDATE);
			
			MBaseMsg mbaseMsg = new MBaseMsg();
			
			
			mbaseMsg.setMessageLength_4_n_0(new String(intTo4ByteArray(1020)));
			
			
			mbaseMsg.setMessageHeader_5_b_$("*MOSA");
			mbaseMsg.setFinalSocketAddr_5_p_0("00000");
			mbaseMsg.setTransactionType_5_b_$("T9999");
			mbaseMsg.setReserved_5_b_$("    Y");
			//need to get from RBS
			
			
			MBaseHD0Msg mbasehd0Msg = new MBaseHD0Msg();
			mbasehd0Msg.setTellerID_10_b_$("0018");
			mbasehd0Msg.setJournalSeq_5_p_0(runningNumber);
			
			MBaseHD1Msg mbasehd1Msg = new MBaseHD1Msg();
			mbasehd1Msg.setCorrection_1_b_N("N");
			
			if(tranxAccType.equalsIgnoreCase(SAVING_TYPE))
			{
				mbasehd1Msg.setTransactionCode_10_b_$(tranCode_saAddHold);
			}
			else if(tranxAccType.equalsIgnoreCase(CURRENT_TYPE))
			{
				mbasehd1Msg.setTransactionCode_10_b_$(tranCode_caAddHold);
			}
		
			
		
			mbasehd1Msg.setTransactionDate_6_b_$(tranxFormat.format(transactionDate));
		
			
			
			
			
			//need to get from RBS
			mbasehd1Msg.setControlUnit_2_b_$("11");
			//need to get from RBS
			mbasehd1Msg.setTellerBranch_5_f_0("1");
			
			mbasehd1Msg.setAmpmMode_1_f_1("1");
		
			
			MBaseEx0Msg mbaseEx0Msg = new MBaseEx0Msg();
			mbaseEx0Msg.setField01_10_p_0(	eMarkAcc.getAccountNumber());
			mbaseEx0Msg.setField02_10_p_0(Amount.toString().replaceAll("\\.", ""));
			mbaseEx0Msg.setField11_10_p_0(expiredFormat.format(expiredDate));
			mbaseEx0Msg.setField20_10_p_0("5");
			
			
			
			MBaseEx1Msg mbaseEx1Msg = new MBaseEx1Msg();
			mbaseEx1Msg.setCurrencyCode_4_b_$(currency);
			mbaseEx1Msg.setPayeeRef_40_b_$(payeeRef);
			
			rbsEarMarkAccMsg.setDspHeader(getDspHeader());
			rbsEarMarkAccMsg.setMbaseMsg(mbaseMsg);
			rbsEarMarkAccMsg.setMbaseHd0Msg(mbasehd0Msg);
			rbsEarMarkAccMsg.setMbaseHd1Msg(mbasehd1Msg);
			rbsEarMarkAccMsg.setMbaseEx0Msg(mbaseEx0Msg);
			rbsEarMarkAccMsg.setMbaseEx1Msg(mbaseEx1Msg);
			
			String fullMsg = dataBeanUtil.getFieldNamesAndValues(rbsEarMarkAccMsg);
	
			String hexa = hex((fullMsg.length()-4)).substring(2);

			log.info("["+fullMsg.length()+"]------["+ ((dataBeanUtil.getFieldNamesAndValues(mbaseMsg).length())-4)+"]");
		
			
			rbsEarMarkAccMsg.getDspHeader().setMessageLength_4_n_0(new String(intTo4ByteArray(fullMsg.length()-4)));
	
			
			
			
			
			
			//fullMsg = dataBeanUtil.getFieldNamesAndValues(rbsEarMarkAccMsg);
			//log.info("----------------------["+rbsEarMarkAccMsg.getDspHeader().getMessageLength_4_n_0()+"]------------------------");
			//log.info(dataBeanUtil.getFieldNamesAndValues(rbsEarMarkAccMsg.getDspHeader()));
			//log.info("----------------------["+ByteBuffer.wrap(rbsEarMarkAccMsg.getMbaseMsg().getMessageLength_9_f_0().getBytes()).getInt()+"]------------------------");
			Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
			log.info(dataBeanUtil.getFieldNamesAndValues(rbsEarMarkAccMsg));
			log.info("--------------------------------------------");
			tcpClient = new TCPClientUtil(rbsIP,rbsPort,120000,"CP037",1024*8);
		
			String hxader = dataBeanUtil.getFieldNamesAndValues(rbsEarMarkAccMsg.getDspHeader());
		
			String msgBdy = dataBeanUtil.getFieldNamesAndValues(rbsEarMarkAccMsg.getMbaseMsg());
	
			String hd0 = dataBeanUtil.getFieldNamesAndValues(rbsEarMarkAccMsg.getMbaseHd0Msg());
			
			String hd1 = dataBeanUtil.getFieldNamesAndValues(rbsEarMarkAccMsg.getMbaseHd1Msg());
			
			String exx1 = dataBeanUtil.getFieldNamesAndValues(rbsEarMarkAccMsg.getMbaseEx0Msg());

			String exx2 = dataBeanUtil.getFieldNamesAndValues(rbsEarMarkAccMsg.getMbaseEx1Msg());
			tcpClient.send(hxader,msgBdy,hd0,hd1,exx1,exx2);
			log.info("-----------------------2----------------------");
			String tcpResponse = tcpClient.receive();
			tcpClient.closeSocketx();
			

			if(tcpResponse.startsWith("TIMEOUT"))
			{
				return null;
			}
			else if(tcpResponse.length()<4 || !dataBeanUtil.checkLengthHexData(tcpResponse.substring(0, 4).getBytes()))
			{
				return "HOST_INVALID_RESPONSE";
			}
			else
			{
				RBS_DSPResponse earMarkResponse = new RBS_DSPResponse();
				earMarkResponse = (RBS_DSPResponse) dataBeanUtil.setFieldNamesAndValues(earMarkResponse, tcpResponse);
				
				
				
				log.info(gsonLog.toJson(earMarkResponse));
				//log.info(ByteBuffer.wrap(earMarkResponse.getMbaseMsg().getMessageLength_4_n_0().getBytes("CP037")).getInt());
				
				log.info("normal convert " + earMarkResponse.getResponseHeader().getRspCOD_2_b_$());
				log.info("convert  307 " +  new String(earMarkResponse.getResponseHeader().getRspCOD_2_b_$().getBytes("CP037")));
				fullResponseData = tcpResponse;
				
				
				return earMarkResponse.getResponseHeader().getRspCOD_2_b_$();
			}
		}catch(Exception e)
		{
			e.printStackTrace(); 
			if(null!=tcpClient)
				tcpClient.closeSocketx();
			return PExConstant.PEX_ERR_RBS_SERVICE_UNAVAILABLE;
		}
		
		
	}*/
	
	
	public String earMarkReverse(boolean isPexDirect,ObAccountBean eMarkAcc, String tranxAccType, BigDecimal amount,BigDecimal totalAmount,BigDecimal chargesAmount, String currency, String payeeRef, Date transactionDate, Date expiredDate,String runningNumber) throws IOException 
	{
		Date rspDate = new Date();
		TCPClientUtil tcpClient = null;
		try
		{
			String rbsIP = pmgr.getProperty("RBS.IP");
			int rbsPort01 = Integer.parseInt(pmgr.getProperty("RBS.PORT01"));
			int rbsPort02 = Integer.parseInt(pmgr.getProperty("RBS.PORT02"));
			int[] rbsPort = {rbsPort01};
			
			DataBeanUtil dataBeanUtil = new DataBeanUtil();
			RBSRequest rbsEarMarkAccMsg = new RBSRequest();
	
			SimpleDateFormat tranxFormat = new SimpleDateFormat(PExConstant.PEX_RBS_DATE);
			SimpleDateFormat expiredFormat = new SimpleDateFormat(PExConstant.PEX_RBS_EXPDATE);
			
			MBaseMsg mbaseMsg = new MBaseMsg();
			
			
			//mbaseMsg.setMessageLength_4_n_0(new String(intTo4ByteArray(1020)));
			mbaseMsg.setMessageLength_4_n_0("1020");
			
			mbaseMsg.setMessageHeader_5_b_$("*MOSA");
			mbaseMsg.setFinalSocketAddr_5_p_0("00000");
			mbaseMsg.setTransactionType_5_b_$("T9999");
			mbaseMsg.setReserved_5_b_$("    Y");
			//need to get from RBS
			
			
			HostMbaseMsg hostMsg = new HostMbaseMsg();
			hostMsg.setTellerID_10_b_$("MOBI001");
			hostMsg.setJournalSeq_5_p_0(runningNumber);
			hostMsg.setCorrection_1_b_N("Y");
			
			if(isPexDirect)
			{
				if(tranxAccType.equalsIgnoreCase(SAVING_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saAddHold_pex);
				}
				else if(tranxAccType.equalsIgnoreCase(CURRENT_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caAddHold_pex);
				}
			}
			else
			{
				if(tranxAccType.equalsIgnoreCase(SAVING_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saAddHold);
				}
				else if(tranxAccType.equalsIgnoreCase(CURRENT_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caAddHold);
				}
			}
		
			
			
			hostMsg.setTransactionDate_6_b_$(tranxFormat.format(transactionDate));

			
			hostMsg.setControlUnit_2_b_$("11");
			hostMsg.setTellerBranch_5_f_0("1");
			hostMsg.setAmpmMode_1_f_1("1");
			hostMsg.setField01_10_p_0(	eMarkAcc.getAccountNumber());
			hostMsg.setField02_10_p_0(amount.toString().replaceAll("\\.", ""));
			hostMsg.setField03_10_p_0(totalAmount.toString().replaceAll("\\.", ""));
			hostMsg.setField04_10_p_0(chargesAmount.toString().replaceAll("\\.", ""));
			hostMsg.setField11_10_p_0(expiredFormat.format(expiredDate));
			hostMsg.setField20_10_p_0("5");
			hostMsg.setCurrencyCode_4_b_$(currency);
			hostMsg.setPayeeRef_40_b_$(payeeRef);
			
			rbsEarMarkAccMsg.setDspHeader(getDspHeader(String.valueOf(rbsPort01)));
			rbsEarMarkAccMsg.setMbaseMsg(mbaseMsg);
			rbsEarMarkAccMsg.setHostMsg(hostMsg);
		
			
	
	
			int fullmsgLen = dataBeanUtil.getFullObjectLength(rbsEarMarkAccMsg);
		    rbsEarMarkAccMsg.getDspHeader().setMessageLength_4_n_0(String.valueOf((fullmsgLen-4)));
			
		    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
		 
			log.info(gsonLog.toJson(rbsEarMarkAccMsg));
			log.info("--------------------------------------------------RBS------------------------------------------------");
		
			tcpClient = new TCPClientUtil(rbsIP,rbsPort,30000,"CP037",1024*8);
			//rbsEarMarkAccMsg
			byte[] dataSend = dataBeanUtil.getFieldNamesAndByte(rbsEarMarkAccMsg, Integer.parseInt(rbsEarMarkAccMsg.getDspHeader().getMessageLength_4_n_0())+4, "CP037");
			
			tcpClient.send(dataSend);
		
			
			byte[] tcpResponse = tcpClient.receiveByte();
			tcpClient.closeSocketx();
			

			if(tcpResponse == null)
			{
				return null;
			}
			else
			{
				RBSResponse earMarkResponse = new RBSResponse();
				earMarkResponse = (RBSResponse) dataBeanUtil.setFieldNamesAndByte(earMarkResponse, tcpResponse, "CP037");
				
				
				log.info("["+new String(tcpResponse,"CP037")+"]");
					
				log.info(gsonLog.toJson(earMarkResponse));
				
				
				fullResponseByte = tcpResponse;
				
				//String transactionCode,String seqNo, String responseCode, String[] successCode,byte[] responseDataByte, Date requestDateTime, Date responseDateTime, String refNo, String cif, String fromAcc, String toAcc
				insertRBSLog(hostMsg.getTransactionCode_10_b_$(),hostMsg.getJournalSeq_5_p_0(),earMarkResponse.getResponseHeader().getRspCOD_2_b_$(),new String[]{"F1"},fullResponseByte,rspDate,new Date(),"","",eMarkAcc.getAccountNumber(),"");
				return earMarkResponse.getResponseHeader().getRspCOD_2_b_$();
			}
		}catch(Exception e)
		{
			log.info("----------------------------------------------------Exception ");
			e.printStackTrace(); 
			if(null!=tcpClient)
				tcpClient.closeSocketx();
			return PExConstant.PEX_ERR_RBS_SERVICE_UNAVAILABLE;
		}
		
		
	}
	
	
	
	
	
	
	public String earMarkPexAccount(boolean isPexDirect,ObAccountBean eMarkAcc, String tranxAccType, BigDecimal amount,BigDecimal totalAmount,BigDecimal chargesAmount, String currency, String payeeRef, Date transactionDate, Date expiredDate,String runningNumber) throws IOException 
	{
		Date rspDate = new Date();
		TCPClientUtil tcpClient = null;
		try
		{
			
			
			Date expiredDateExtend = new Date(expiredDate.getTime()+(1000 * 60 * 60 * 24));
			
			String rbsIP = pmgr.getProperty("RBS.IP");
			int rbsPort01 = Integer.parseInt(pmgr.getProperty("RBS.PORT01"));
			int rbsPort02 = Integer.parseInt(pmgr.getProperty("RBS.PORT02"));
			int[] rbsPort = {rbsPort01};
			
			DataBeanUtil dataBeanUtil = new DataBeanUtil();
			RBSRequest rbsEarMarkAccMsg = new RBSRequest();
	
			SimpleDateFormat tranxFormat = new SimpleDateFormat(PExConstant.PEX_RBS_DATE);
			SimpleDateFormat expiredFormat = new SimpleDateFormat(PExConstant.PEX_RBS_EXPDATE);
			
			MBaseMsg mbaseMsg = new MBaseMsg();
			
			
			//mbaseMsg.setMessageLength_4_n_0(new String(intTo4ByteArray(1020)));
			mbaseMsg.setMessageLength_4_n_0("1020");
			
			mbaseMsg.setMessageHeader_5_b_$("*MOSA");
			mbaseMsg.setFinalSocketAddr_5_p_0("00000");
			mbaseMsg.setTransactionType_5_b_$("T9999");
			mbaseMsg.setReserved_5_b_$("    Y");
			//need to get from RBS
			
			
			HostMbaseMsg hostMsg = new HostMbaseMsg();
			hostMsg.setTellerID_10_b_$("MOBI001");
			hostMsg.setJournalSeq_5_p_0(runningNumber);
			hostMsg.setCorrection_1_b_N("N");
			
			if(isPexDirect)
			{
				if(tranxAccType.equalsIgnoreCase(SAVING_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saAddHold_pex);
				}
				else if(tranxAccType.equalsIgnoreCase(CURRENT_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caAddHold_pex);
				}
			}
			else
			{
				if(tranxAccType.equalsIgnoreCase(SAVING_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saAddHold);
				}
				else if(tranxAccType.equalsIgnoreCase(CURRENT_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caAddHold);
				}
			}
		
			
			
			hostMsg.setTransactionDate_6_b_$(tranxFormat.format(transactionDate));

			
			hostMsg.setControlUnit_2_b_$("11");
			hostMsg.setTellerBranch_5_f_0("1");
			hostMsg.setAmpmMode_1_f_1("1");
			hostMsg.setField01_10_p_0(	eMarkAcc.getAccountNumber());
			hostMsg.setField02_10_p_0(amount.toString().replaceAll("\\.", ""));
			hostMsg.setField03_10_p_0(totalAmount.toString().replaceAll("\\.", ""));
			hostMsg.setField04_10_p_0(chargesAmount.toString().replaceAll("\\.", ""));
			hostMsg.setField11_10_p_0(expiredFormat.format(expiredDateExtend));
			hostMsg.setField20_10_p_0("5");
			hostMsg.setCurrencyCode_4_b_$(currency);
			hostMsg.setPayeeRef_40_b_$(payeeRef);
			
			rbsEarMarkAccMsg.setDspHeader(getDspHeader(String.valueOf(rbsPort01)));
			rbsEarMarkAccMsg.setMbaseMsg(mbaseMsg);
			rbsEarMarkAccMsg.setHostMsg(hostMsg);
		
			
	
	
			int fullmsgLen = dataBeanUtil.getFullObjectLength(rbsEarMarkAccMsg);
		    rbsEarMarkAccMsg.getDspHeader().setMessageLength_4_n_0(String.valueOf((fullmsgLen-4)));
			
		    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
	
			log.info(gsonLog.toJson(rbsEarMarkAccMsg));
			log.info("--------------------------------------------------RBS------------------------------------------------");
		
			tcpClient = new TCPClientUtil(rbsIP,rbsPort,30000,"CP037",1024*8);
			//rbsEarMarkAccMsg
			byte[] dataSend = dataBeanUtil.getFieldNamesAndByte(rbsEarMarkAccMsg, Integer.parseInt(rbsEarMarkAccMsg.getDspHeader().getMessageLength_4_n_0())+4, "CP037");
			
			tcpClient.send(dataSend);
		
			
			byte[] tcpResponse = tcpClient.receiveByte();
			tcpClient.closeSocketx();
			

			if(tcpResponse == null)
			{
				return null;
			}
			else
			{
				RBSResponse earMarkResponse = new RBSResponse();
				earMarkResponse = (RBSResponse) dataBeanUtil.setFieldNamesAndByte(earMarkResponse, tcpResponse, "CP037");
				
				
				//log.info("["+new String(tcpResponse,"CP037")+"]");
					
				log.info(gsonLog.toJson(earMarkResponse));
				
				
				fullResponseByte = tcpResponse;
				//String transactionCode,String seqNo, String responseCode, String[] successCode,byte[] responseDataByte, Date requestDateTime, Date responseDateTime, String refNo, String cif, String fromAcc, String toAcc
				insertRBSLog(hostMsg.getTransactionCode_10_b_$(),hostMsg.getJournalSeq_5_p_0(),earMarkResponse.getResponseHeader().getRspCOD_2_b_$(),new String[]{"F1"},fullResponseByte,rspDate,new Date(),"","",eMarkAcc.getAccountNumber(),"");
				return earMarkResponse.getResponseHeader().getRspCOD_2_b_$();
			}
		}catch(Exception e)
		{
			
			e.printStackTrace(); 
			if(null!=tcpClient)
				tcpClient.closeSocketx();
			
			return PExConstant.PEX_ERR_RBS_SERVICE_UNAVAILABLE;
		}
		
		
	}
	
	
	
	public String releaseEarmark(boolean isPexDirect,ObAccountBean eMarkAcc,String tranxAccType, BigDecimal Amount, String currency, String payeeRef, Date transactionDate,String runningNumber, int timeout) throws IOException 
	{   Date rspDate = new Date();
		TCPClientUtil tcpClient = null;
		try
		{
			String rbsIP = pmgr.getProperty("RBS.IP");
			int rbsPort01 = Integer.parseInt(pmgr.getProperty("RBS.PORT01"));
			int rbsPort02 = Integer.parseInt(pmgr.getProperty("RBS.PORT02"));
			int[] rbsPort = {rbsPort01};
			
			DataBeanUtil dataBeanUtil = new DataBeanUtil();
			RBSRequest rbsEarMarkAccMsg = new RBSRequest();
	
			SimpleDateFormat tranxFormat = new SimpleDateFormat(PExConstant.PEX_RBS_DATE);
			SimpleDateFormat expiredFormat = new SimpleDateFormat(PExConstant.PEX_RBS_EXPDATE);
			
			MBaseMsg mbaseMsg = new MBaseMsg();
			mbaseMsg.setMessageLength_4_n_0("1020");
			
			mbaseMsg.setMessageHeader_5_b_$("*MOSA");
			mbaseMsg.setFinalSocketAddr_5_p_0("00000");
			mbaseMsg.setTransactionType_5_b_$("T9999");
			mbaseMsg.setReserved_5_b_$("    Y");
		
			
			HostMbaseMsg hostMsg = new HostMbaseMsg();
			hostMsg.setTellerID_10_b_$("MOBI001");
			hostMsg.setJournalSeq_5_p_0(runningNumber);
	
			
			
			hostMsg.setCorrection_1_b_N("N");
			
			
			if(isPexDirect)
			{
				if(tranxAccType.equalsIgnoreCase(SAVING_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saReleaseHold_pex);
				}
				else if(tranxAccType.equalsIgnoreCase(CURRENT_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caReleaseHold_pex);
				}
			
			}
			else
			{
				if(tranxAccType.equalsIgnoreCase(SAVING_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saReleaseHold);
				}
				else if(tranxAccType.equalsIgnoreCase(CURRENT_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caReleaseHold);
				}
			}
			
			hostMsg.setTransactionDate_6_b_$(tranxFormat.format(transactionDate));
			hostMsg.setControlUnit_2_b_$("11");
			hostMsg.setTellerBranch_5_f_0("1");
			hostMsg.setAmpmMode_1_f_1("1");
			
		
			
			
			hostMsg.setField01_10_p_0(	eMarkAcc.getAccountNumber());
			hostMsg.setField02_10_p_0(Amount.toString().replaceAll("\\.", ""));
			hostMsg.setField20_10_p_0("5");
			
			hostMsg.setCurrencyCode_4_b_$(currency);
			hostMsg.setPayeeRef_40_b_$(payeeRef);
			
			rbsEarMarkAccMsg.setDspHeader(getDspHeader(String.valueOf(rbsPort01)));
			rbsEarMarkAccMsg.setMbaseMsg(mbaseMsg);
			rbsEarMarkAccMsg.setHostMsg(hostMsg);
			
			int fullmsgLen = dataBeanUtil.getFullObjectLength(rbsEarMarkAccMsg);
		    rbsEarMarkAccMsg.getDspHeader().setMessageLength_4_n_0(String.valueOf((fullmsgLen-4)));
			
		    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
			 
			log.info(gsonLog.toJson(rbsEarMarkAccMsg));
			log.info("--------------------------------------------------RBS------------------------------------------------");
			tcpClient = new TCPClientUtil(rbsIP,rbsPort,timeout,"Cp1047",1024*8);
			byte[] dataSend = dataBeanUtil.getFieldNamesAndByte(rbsEarMarkAccMsg, Integer.parseInt(rbsEarMarkAccMsg.getDspHeader().getMessageLength_4_n_0())+4, "CP037");
			
			tcpClient.send(dataSend);
			byte[] tcpResponse = tcpClient.receiveByte();
			tcpClient.closeSocketx();
			
			if(tcpResponse == null)
			{
				return null;
			}
			else
			{
				RBSResponse earMarkResponse = new RBSResponse();
				earMarkResponse = (RBSResponse) dataBeanUtil.setFieldNamesAndByte(earMarkResponse, tcpResponse, "CP037");
				
				
				fullResponseByte = tcpResponse;
				
				//String transactionCode,String seqNo, String responseCode, String[] successCode,byte[] responseDataByte, Date requestDateTime, Date responseDateTime, String refNo, String cif, String fromAcc, String toAcc
				insertRBSLog(hostMsg.getTransactionCode_10_b_$(),hostMsg.getJournalSeq_5_p_0(),earMarkResponse.getResponseHeader().getRspCOD_2_b_$(),new String[]{"F1"},fullResponseByte,rspDate,new Date(),"","",eMarkAcc.getAccountNumber(),"");
				
				return earMarkResponse.getResponseHeader().getRspCOD_2_b_$();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			if(null!=tcpClient)
				tcpClient.closeSocketx();
			return PExConstant.PEX_ERR_RBS_SERVICE_UNAVAILABLE;
		}
		
		
	}
	
	
	public String thirdPartyTransfer(boolean isPexDirect,ObAccountBean eMarkAcc, ObAccountBean CollectionAcc, String tranxFromAccType, String tranxToAccType, BigDecimal earmarkAmount,BigDecimal chargeAmount, String currency, String payeeRef, Date transactionData, String runningNumber, String pexRef) throws IOException 
	{ 	Date rspDate = new Date();
		TCPClientUtil tcpClient = null;
		try
		{
			String rbsIP = pmgr.getProperty("RBS.IP");
			int rbsPort01 = Integer.parseInt(pmgr.getProperty("RBS.PORT01"));
			int rbsPort02 = Integer.parseInt(pmgr.getProperty("RBS.PORT02"));
			int[] rbsPort = {rbsPort01};
			
			DataBeanUtil dataBeanUtil = new DataBeanUtil();
			RBSRequest rbsEarMarkAccMsg = new RBSRequest();
			
	
			SimpleDateFormat tranxFormat = new SimpleDateFormat(PExConstant.PEX_RBS_DATE);
			SimpleDateFormat expiredFormat = new SimpleDateFormat(PExConstant.PEX_RBS_EXPDATE);
			
			MBaseMsg mbaseMsg = new MBaseMsg();
			mbaseMsg.setMessageLength_4_n_0("1020");
			
			mbaseMsg.setMessageHeader_5_b_$("*MOSA");
			mbaseMsg.setFinalSocketAddr_5_p_0("00000");
			mbaseMsg.setTransactionType_5_b_$("T9999");
			mbaseMsg.setReserved_5_b_$("    Y");

			
			HostMbaseMsg hostMsg = new HostMbaseMsg();
			hostMsg.setTellerID_10_b_$("MOBI001");
			hostMsg.setJournalSeq_5_p_0(runningNumber);
			hostMsg.setField01_10_p_0(eMarkAcc.getAccountNumber());
			
			
			hostMsg.setCorrection_1_b_N("N");
			
			
			if(isPexDirect)
			{
				if(tranxFromAccType.equalsIgnoreCase(SAVING_TYPE) && tranxToAccType.equalsIgnoreCase(SAVING_TYPE) )
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saFTsa_pex);
				}
				else if(tranxFromAccType.equalsIgnoreCase(SAVING_TYPE) && tranxToAccType.equalsIgnoreCase(CURRENT_TYPE) )
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saFTca_pex);
				}
				else if(tranxFromAccType.equalsIgnoreCase(CURRENT_TYPE) && tranxToAccType.equalsIgnoreCase(SAVING_TYPE) )
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caFTsa_pex);
				}
				else if(tranxFromAccType.equalsIgnoreCase(CURRENT_TYPE) && tranxToAccType.equalsIgnoreCase(CURRENT_TYPE) )
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caFTca_pex);
				}
			}
			else
			{
				if(tranxFromAccType.equalsIgnoreCase(SAVING_TYPE) && tranxToAccType.equalsIgnoreCase(SAVING_TYPE) )
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saFTsa);
				}
				else if(tranxFromAccType.equalsIgnoreCase(SAVING_TYPE) && tranxToAccType.equalsIgnoreCase(CURRENT_TYPE) )
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saFTca);
				}
				else if(tranxFromAccType.equalsIgnoreCase(CURRENT_TYPE) && tranxToAccType.equalsIgnoreCase(SAVING_TYPE) )
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caFTsa);
				}
				else if(tranxFromAccType.equalsIgnoreCase(CURRENT_TYPE) && tranxToAccType.equalsIgnoreCase(CURRENT_TYPE) )
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caFTca);
				}
			}
			
			
			
			hostMsg.setTransactionDate_6_b_$(tranxFormat.format(transactionData));

			hostMsg.setControlUnit_2_b_$("11");
			hostMsg.setTellerBranch_5_f_0("1");
			hostMsg.setAmpmMode_1_f_1("1");
			
		
			
	
			hostMsg.setField01_10_p_0(eMarkAcc.getAccountNumber());
			hostMsg.setField03_10_p_0(earmarkAmount.toString().replaceAll("\\.",""));
			hostMsg.setField04_10_p_0(chargeAmount.toString().replaceAll("\\.",""));
			hostMsg.setField09_10_p_0(CollectionAcc.getAccountNumber());
			hostMsg.setField10_10_p_0("900");
			hostMsg.setField11_10_p_0(expiredFormat.format(transactionData));
			hostMsg.setField20_10_p_0("5");
			hostMsg.setCurrencyCode_4_b_$(currency);
			hostMsg.setPayeeRef_40_b_$(payeeRef);
			//hostMsg.setRefenceNumber_20_b_$(pexRef);
			hostMsg.setThirdPartyNames_40_b_$(pexRef);
			hostMsg.setFiller_1024_b_$(pexRef);
			rbsEarMarkAccMsg.setDspHeader(getDspHeader(String.valueOf(rbsPort01)));
			rbsEarMarkAccMsg.setMbaseMsg(mbaseMsg);
			rbsEarMarkAccMsg.setHostMsg(hostMsg);
			
			int fullmsgLen = dataBeanUtil.getFullObjectLength(rbsEarMarkAccMsg);
		    rbsEarMarkAccMsg.getDspHeader().setMessageLength_4_n_0(String.valueOf((fullmsgLen-4)));
			
			
		    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
			
			log.info(gsonLog.toJson(rbsEarMarkAccMsg));
			log.info("------------THIRD PARTY TRANSFER----------------------");
		    
			tcpClient = new TCPClientUtil(rbsIP,rbsPort,30000,"CP037",1024*8);
			byte[] dataSend = dataBeanUtil.getFieldNamesAndByte(rbsEarMarkAccMsg, Integer.parseInt(rbsEarMarkAccMsg.getDspHeader().getMessageLength_4_n_0())+4, "CP037");
			
			tcpClient.send(dataSend);
			byte[] tcpResponse = tcpClient.receiveByte();
			tcpClient.closeSocketx();
			
			if(tcpResponse == null)
			{
				return null;
			}
			else
			{
				RBSResponse earMarkResponse = new RBSResponse();
				earMarkResponse = (RBSResponse) dataBeanUtil.setFieldNamesAndByte(earMarkResponse, tcpResponse, "CP037");
				
				
				
				log.info("Thirdparty RBS Response");
				log.info(gsonLog.toJson(earMarkResponse));
				
				fullResponseByte = tcpResponse;
				
				//String transactionCode,String seqNo, String responseCode, String[] successCode,byte[] responseDataByte, Date requestDateTime, Date responseDateTime, String refNo, String cif, String fromAcc, String toAcc
				insertRBSLog(hostMsg.getTransactionCode_10_b_$(),hostMsg.getJournalSeq_5_p_0(),earMarkResponse.getResponseHeader().getRspCOD_2_b_$(),new String[]{"F1"},fullResponseByte,rspDate,new Date(),pexRef,"",eMarkAcc.getAccountNumber(),CollectionAcc.getAccountNumber());
				
				return earMarkResponse.getResponseHeader().getRspCOD_2_b_$();
			}
		}catch(Exception e)
		{
			tcpClient.closeSocketx();
			e.printStackTrace();
			return PExConstant.PEX_ERR_RBS_SERVICE_UNAVAILABLE;
		}
		
		
	}
	
	
	
	
	

	public String inquiryACCDetails(String pexAccount,Date transactionData, String runningNumber) throws IOException 
	{	Date rspDate = new Date();
		TCPClientUtil tcpClient = null;
		try
		{
			String rbsIP = pmgr.getProperty("RBS.IP");
			int rbsPort01 = Integer.parseInt(pmgr.getProperty("RBS.PORT01"));
			int rbsPort02 = Integer.parseInt(pmgr.getProperty("RBS.PORT02"));
			int[] rbsPort = {rbsPort01};
			
			DataBeanUtil dataBeanUtil = new DataBeanUtil();
			RBSRequest rbsEarMarkAccMsg = new RBSRequest();
	
			SimpleDateFormat tranxFormat = new SimpleDateFormat(PExConstant.PEX_RBS_DATE);
			SimpleDateFormat expiredFormat = new SimpleDateFormat(PExConstant.PEX_RBS_EXPDATE);
			
			MBaseMsg mbaseMsg = new MBaseMsg();
			mbaseMsg.setMessageLength_4_n_0("1020");
			mbaseMsg.setMessageHeader_5_b_$("*MOSA");
			mbaseMsg.setFinalSocketAddr_5_p_0("00000");
			mbaseMsg.setTransactionType_5_b_$("T9999");
			mbaseMsg.setReserved_5_b_$("    Y");
			
			
			HostMbaseMsg hostMsg = new HostMbaseMsg();
			hostMsg.setTellerID_10_b_$("MOBI001");
			hostMsg.setJournalSeq_5_p_0(runningNumber);
			hostMsg.setCorrection_1_b_N("N");
			hostMsg.setTransactionCode_10_b_$(tranCode_inquiryAccDetails);
			hostMsg.setTransactionDate_6_b_$(tranxFormat.format(transactionData));
			hostMsg.setControlUnit_2_b_$("11");
			hostMsg.setTellerBranch_5_f_0("1");
			hostMsg.setAmpmMode_1_f_1("1");
			hostMsg.setField01_10_p_0(pexAccount);
			hostMsg.setField20_10_p_0("1");
		
			rbsEarMarkAccMsg.setDspHeader(getDspHeader(String.valueOf(rbsPort01)));
			rbsEarMarkAccMsg.setMbaseMsg(mbaseMsg);
			rbsEarMarkAccMsg.setHostMsg(hostMsg);
			
			int fullmsgLen = dataBeanUtil.getFullObjectLength(rbsEarMarkAccMsg);
		    rbsEarMarkAccMsg.getDspHeader().setMessageLength_4_n_0(String.valueOf((fullmsgLen-4)));
			
		    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
			 
			log.info(gsonLog.toJson(rbsEarMarkAccMsg));
			log.info("--------------------------------------------------RBS------------------------------------------------");
			tcpClient = new TCPClientUtil(rbsIP,rbsPort,60000,"CP037",1024*8);
			//rbsEarMarkAccMsg
			byte[] dataSend = dataBeanUtil.getFieldNamesAndByte(rbsEarMarkAccMsg, Integer.parseInt(rbsEarMarkAccMsg.getDspHeader().getMessageLength_4_n_0())+4, "CP037");
			
			tcpClient.send(dataSend);
			byte[] tcpResponse = tcpClient.receiveByte();
			tcpClient.closeSocketx();
			
			if(tcpResponse == null)
			{
				return null;
			}
			else
			{
				RBSResponse earMarkResponse = new RBSResponse();
				earMarkResponse = (RBSResponse) dataBeanUtil.setFieldNamesAndByte(earMarkResponse, tcpResponse, "CP037");
				
				
				//Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
					
				log.info(gsonLog.toJson(earMarkResponse));
				
				
				fullResponseByte = tcpResponse;
				
				//String transactionCode,String seqNo, String responseCode, String[] successCode,byte[] responseDataByte, Date requestDateTime, Date responseDateTime, String refNo, String cif, String fromAcc, String toAcc
				insertRBSLog(hostMsg.getTransactionCode_10_b_$(),hostMsg.getJournalSeq_5_p_0(),earMarkResponse.getResponseHeader().getRspCOD_2_b_$(),new String[]{"DA"},fullResponseByte,rspDate,new Date(),"","",pexAccount,"");
				
				return earMarkResponse.getResponseHeader().getRspCOD_2_b_$();
			}
		}catch(Exception e)
		{
			tcpClient.closeSocketx();
			e.printStackTrace();
			return PExConstant.PEX_ERR_RBS_SERVICE_UNAVAILABLE;
		}
		
		
	}
	
	
	
	
	
	public String inquiryTACNumber(String cifNumber, String currency, Date transactionData, String runningNumber) throws IOException 
	{ 	Date rspDate = new Date();
		TCPClientUtil tcpClient = null;
		try
		{
			String rbsIP = pmgr.getProperty("RBS.IP");
			int rbsPort01 = Integer.parseInt(pmgr.getProperty("RBS.PORT01"));
			int rbsPort02 = Integer.parseInt(pmgr.getProperty("RBS.PORT02"));
			int[] rbsPort = {rbsPort01};
			
			DataBeanUtil dataBeanUtil = new DataBeanUtil();
			RBSRequest rbsEarMarkAccMsg = new RBSRequest();
	
			SimpleDateFormat tranxFormat = new SimpleDateFormat(PExConstant.PEX_RBS_DATE);
			SimpleDateFormat expiredFormat = new SimpleDateFormat(PExConstant.PEX_RBS_EXPDATE);
			
			MBaseMsg mbaseMsg = new MBaseMsg();
			mbaseMsg.setMessageLength_4_n_0("1020");
			mbaseMsg.setMessageHeader_5_b_$("*MOSA");
			mbaseMsg.setFinalSocketAddr_5_p_0("00000");
			mbaseMsg.setTransactionType_5_b_$("T9999");
			mbaseMsg.setReserved_5_b_$("    Y");
			
			
			HostMbaseMsg hostMsg = new HostMbaseMsg();
			hostMsg.setTellerID_10_b_$("MOBI001");
			hostMsg.setJournalSeq_5_p_0(runningNumber);
			hostMsg.setCorrection_1_b_N("N");
			hostMsg.setTransactionCode_10_b_$(tranCode_inquiryTACnumber);
			hostMsg.setTransactionDate_6_b_$(tranxFormat.format(transactionData));
			hostMsg.setControlUnit_2_b_$("11");
			hostMsg.setTellerBranch_5_f_0("1");
			hostMsg.setAmpmMode_1_f_1("1");
			
			

			//define request data
			hostMsg.setField01_10_p_0(	cifNumber);
			hostMsg.setField20_10_p_0("5");
			hostMsg.setCurrencyCode_4_b_$(currency);
			hostMsg.setAdditionFInfoC_3_b_$("IBK");
			
			rbsEarMarkAccMsg.setDspHeader(getDspHeader(String.valueOf(rbsPort01)));
			rbsEarMarkAccMsg.setMbaseMsg(mbaseMsg);
			rbsEarMarkAccMsg.setHostMsg(hostMsg);
			
			int fullmsgLen = dataBeanUtil.getFullObjectLength(rbsEarMarkAccMsg);
		    rbsEarMarkAccMsg.getDspHeader().setMessageLength_4_n_0(String.valueOf((fullmsgLen-4)));
			
		    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
			 
			log.info(gsonLog.toJson(rbsEarMarkAccMsg));
			log.info("--------------------------------------------------RBS------------------------------------------------");
			
			tcpClient = new TCPClientUtil(rbsIP,rbsPort,60000,"CP037",1024*8);
			//rbsEarMarkAccMsg
			byte[] dataSend = dataBeanUtil.getFieldNamesAndByte(rbsEarMarkAccMsg, Integer.parseInt(rbsEarMarkAccMsg.getDspHeader().getMessageLength_4_n_0())+4, "CP037");
			
			tcpClient.send(dataSend);
			byte[] tcpResponse = tcpClient.receiveByte();
			tcpClient.closeSocketx();
			
			if(tcpResponse == null)
			{
				return null;
			}
			else
			{
				log.info("["+tcpResponse+"]");
				RBSResponse earMarkResponse = new RBSResponse();
				earMarkResponse = (RBSResponse) dataBeanUtil.setFieldNamesAndByte(earMarkResponse, tcpResponse, "CP037");
				
				fullResponseByte = tcpResponse;
				
				//String transactionCode,String seqNo, String responseCode, String[] successCode,byte[] responseDataByte, Date requestDateTime, Date responseDateTime, String refNo, String cif, String fromAcc, String toAcc
				insertRBSLog(hostMsg.getTransactionCode_10_b_$(),hostMsg.getJournalSeq_5_p_0(),earMarkResponse.getResponseHeader().getRspCOD_2_b_$(),new String[]{"EC"},fullResponseByte,rspDate,new Date(),"",cifNumber,"","");
				
				return earMarkResponse.getResponseHeader().getRspCOD_2_b_$();
			}
		}catch(Exception e)
		{
			tcpClient.closeSocketx();
			e.printStackTrace();
			return PExConstant.PEX_ERR_RBS_SERVICE_UNAVAILABLE;
		}
		
		
	}
	
	
	public String earMarkPexAccountKH(boolean isPexDirect,ObAccountBean eMarkAcc, String tranxAccType, BigDecimal amount,BigDecimal totalAmount,BigDecimal chargesAmount, String currency, String payeeRef, Date transactionDate, Date expiredDate,String runningNumber) throws IOException 
	{	Date rspDate = new Date();
		TCPClientUtil tcpClient = null;
		try
		{
			Date expiredDateExtend = new Date(expiredDate.getTime()+(1000 * 60 * 60 * 24));
			DataBeanUtil dataBeanUtil = new DataBeanUtil();
			if(null!=pmgr.getProperty("fuzion.mock")  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false")   ){
				RBS_DSPFBResponse inResponse = new RBS_DSPFBResponse();
				/*inResponse.setSequence_5_p_0(new MBaseResponseDA());
				inResponse.getResponseDA().setAccountName_40_b_$("Renzo");*/
				fullResponseByte = dataBeanUtil.getFieldNamesAndByte(inResponse, 1024*8, "CP037");
				return "FB"; 
			}
			
			String rbsIP = pmgr.getProperty("RBS.IP");
			int rbsPort01 = Integer.parseInt(pmgr.getProperty("RBS.PORT01"));
			int rbsPort02 = Integer.parseInt(pmgr.getProperty("RBS.PORT02"));
			int[] rbsPort = {rbsPort01};
			
			
			RBSViRequest rbsEarMarkAccMsg = new RBSViRequest();
	
			SimpleDateFormat tranxFormat = new SimpleDateFormat(PExConstant.PEX_RBS_DATE);
			SimpleDateFormat expiredFormat = new SimpleDateFormat(PExConstant.PEX_RBS_EXPDATE);
			
			MBaseMsg mbaseMsg = new MBaseMsg();
			
			
			//mbaseMsg.setMessageLength_4_n_0(new String(intTo4ByteArray(1020)));
			mbaseMsg.setMessageLength_4_n_0("1020");
			
			mbaseMsg.setMessageHeader_5_b_$("*MOSA");
			mbaseMsg.setFinalSocketAddr_5_p_0("00000");
			mbaseMsg.setTransactionType_5_b_$("T9998");
			mbaseMsg.setReserved_5_b_$("    Y");
			//need to get from RBS
			
			
			HostMbaseViMsg hostMsg = new HostMbaseViMsg();
			hostMsg.setTellerID_10_b_$("MOBI001");
			hostMsg.setJournalSeq_5_p_0(runningNumber);
			hostMsg.setCorrection_1_b_N("N");
			hostMsg.setAdditionFInfoC_3_b_$("MBI");
			if(isPexDirect)
			{
				if(tranxAccType.equalsIgnoreCase(SAVING_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saAddHold_pex);
				}
				else if(tranxAccType.equalsIgnoreCase(CURRENT_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caAddHold_pex);
				}
			}
			else
			{
				if(tranxAccType.equalsIgnoreCase(SAVING_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saAddHold);
				}
				else if(tranxAccType.equalsIgnoreCase(CURRENT_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caAddHold);
				}
			}
		
			
			
			hostMsg.setTransactionDate_6_b_$(tranxFormat.format(transactionDate));

			
			hostMsg.setControlUnit_2_b_$("A1");
			hostMsg.setTellerBranch_5_f_0("1");
			hostMsg.setAmpmMode_1_f_1("1");
			hostMsg.setField01_10_p_0(	eMarkAcc.getAccountNumber());
			hostMsg.setField20_10_p_0(totalAmount.toString().replaceAll("\\.", ""));
			//hostMsg.setField21_10_p_0(totalAmount.toString().replaceAll("\\.", ""));
			//hostMsg.setField22_10_p_0(chargesAmount.toString().replaceAll("\\.", ""));
			hostMsg.setField11_10_p_0(expiredFormat.format(expiredDateExtend));
			//hostMsg.setField20_10_p_0("5");
			hostMsg.setCurrencyCode_4_b_$(currency);
			hostMsg.setPayeeRef_40_b_$(payeeRef);
			
			rbsEarMarkAccMsg.setDspHeader(getDspHeader(String.valueOf(rbsPort01)));
			rbsEarMarkAccMsg.getDspHeader().setSenarioNumber_16_b_$("BBHTLNOMONFNC");
			rbsEarMarkAccMsg.setMbaseMsg(mbaseMsg);
			rbsEarMarkAccMsg.setHostMsg(hostMsg);
		
			
	
	
			int fullmsgLen = dataBeanUtil.getFullObjectLength(rbsEarMarkAccMsg);
		    rbsEarMarkAccMsg.getDspHeader().setMessageLength_4_n_0(String.valueOf((fullmsgLen-4)));
			
		    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
		 
			log.info(gsonLog.toJson(rbsEarMarkAccMsg));
			log.info("--------------------------------------------------RBS------------------------------------------------");
		
			tcpClient = new TCPClientUtil(rbsIP,rbsPort,30000,"CP037",1024*8);
			//rbsEarMarkAccMsg
			byte[] dataSend = dataBeanUtil.getFieldNamesAndByte(rbsEarMarkAccMsg, Integer.parseInt(rbsEarMarkAccMsg.getDspHeader().getMessageLength_4_n_0())+4, "CP037");
			
			tcpClient.send(dataSend);
			System.out.println(new String(dataSend,"CP037"));
			
			byte[] tcpResponse = tcpClient.receiveByte();
			tcpClient.closeSocketx();
			

			if(tcpResponse == null)
			{
				return null;
			}
			else
			{
				RBSResponse earMarkResponse = new RBSResponse();
				earMarkResponse = (RBSResponse) dataBeanUtil.setFieldNamesAndByte(earMarkResponse, tcpResponse, "CP037");
				
				
				log.info("["+new String(tcpResponse,"CP037")+"]");
					
				log.info(gsonLog.toJson(earMarkResponse));
				
				
				fullResponseByte = tcpResponse;
				
				//String transactionCode,String seqNo, String responseCode, String[] successCode,byte[] responseDataByte, Date requestDateTime, Date responseDateTime, String refNo, String cif, String fromAcc, String toAcc
				insertRBSLog(hostMsg.getTransactionCode_10_b_$(),hostMsg.getJournalSeq_5_p_0(),earMarkResponse.getResponseHeader().getRspCOD_2_b_$(),new String[]{"FB"},fullResponseByte,rspDate,new Date(),"","",eMarkAcc.getAccountNumber(),"");
				
				return earMarkResponse.getResponseHeader().getRspCOD_2_b_$();
			}
		}catch(Exception e)
		{
			log.info("----------------------------------------------------Exception ");
			e.printStackTrace(); 
			if(null!=tcpClient)
				tcpClient.closeSocketx();
			return PExConstant.PEX_ERR_RBS_SERVICE_UNAVAILABLE;
		}
		
		
	}
	
	
	
	public String releaseEarmarkKH(boolean isPexDirect,ObAccountBean eMarkAcc,String tranxAccType, BigDecimal Amount, String currency, String payeeRef, Date transactionDate,String runningNumber, int timeout) throws IOException 
	{	Date rspDate = new Date();
		TCPClientUtil tcpClient = null;
		try
		{
            DataBeanUtil dataBeanUtil = new DataBeanUtil();
            
			if(null!=pmgr.getProperty("fuzion.mock")  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false")   ){
				
				//return "F1"; //for pex creation status code 
                  return "F9"; //for cancel pex status code
			
            }			
			
			String rbsIP = pmgr.getProperty("RBS.IP");
			int rbsPort01 = Integer.parseInt(pmgr.getProperty("RBS.PORT01"));
			int rbsPort02 = Integer.parseInt(pmgr.getProperty("RBS.PORT02"));
			int[] rbsPort = {rbsPort01};
			
			
			RBSViRequest rbsEarMarkAccMsg = new RBSViRequest();
	
			SimpleDateFormat tranxFormat = new SimpleDateFormat(PExConstant.PEX_RBS_DATE);
			SimpleDateFormat expiredFormat = new SimpleDateFormat(PExConstant.PEX_RBS_EXPDATE);
			
			MBaseMsg mbaseMsg = new MBaseMsg();
			mbaseMsg.setMessageLength_4_n_0("1020");
			
			mbaseMsg.setMessageHeader_5_b_$("*MOSA");
			mbaseMsg.setFinalSocketAddr_5_p_0("00000");
			mbaseMsg.setTransactionType_5_b_$("T9998");
			mbaseMsg.setReserved_5_b_$("    Y");
		
			
			HostMbaseViMsg hostMsg = new HostMbaseViMsg();
			hostMsg.setTellerID_10_b_$("MOBI001");
			hostMsg.setJournalSeq_5_p_0(runningNumber);
	
			
			
			hostMsg.setCorrection_1_b_N("N");
			
			
			if(isPexDirect)
			{
				if(tranxAccType.equalsIgnoreCase(SAVING_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saReleaseHold_pex);
				}
				else if(tranxAccType.equalsIgnoreCase(CURRENT_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caReleaseHold_pex);
				}
			
			}
			else
			{
				if(tranxAccType.equalsIgnoreCase(SAVING_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saReleaseHold);
				}
				else if(tranxAccType.equalsIgnoreCase(CURRENT_TYPE))
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caReleaseHold);
				}
			}
			
			hostMsg.setTransactionDate_6_b_$(tranxFormat.format(transactionDate));
			hostMsg.setControlUnit_2_b_$("A1");
			hostMsg.setTellerBranch_5_f_0("1");
			hostMsg.setAmpmMode_1_f_1("1");
			hostMsg.setField05_10_p_0(payeeRef);
		
			
			
			hostMsg.setField01_10_p_0(	eMarkAcc.getAccountNumber());
			//hostMsg.setField02_10_p_0(Amount.toString().replaceAll(".", ""));
			hostMsg.setField20_10_p_0(Amount.toString().replaceAll("\\.", ""));
			
			hostMsg.setCurrencyCode_4_b_$(currency);
			hostMsg.setPayeeRef_40_b_$(payeeRef);
			
			rbsEarMarkAccMsg.setDspHeader(getDspHeader(String.valueOf(rbsPort01)));
			rbsEarMarkAccMsg.getDspHeader().setSenarioNumber_16_b_$("BBHTLNOMONFNC");
			rbsEarMarkAccMsg.setMbaseMsg(mbaseMsg);
			rbsEarMarkAccMsg.setHostMsg(hostMsg);
			
			int fullmsgLen = dataBeanUtil.getFullObjectLength(rbsEarMarkAccMsg);
		    rbsEarMarkAccMsg.getDspHeader().setMessageLength_4_n_0(String.valueOf((fullmsgLen-4)));
			
		    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
			 
			log.info(gsonLog.toJson(rbsEarMarkAccMsg));
			log.info("--------------------------------------------------RBS------------------------------------------------");
			
			
			tcpClient = new TCPClientUtil(rbsIP,rbsPort,timeout,"Cp1047",1024*8);
			byte[] dataSend = dataBeanUtil.getFieldNamesAndByte(rbsEarMarkAccMsg, Integer.parseInt(rbsEarMarkAccMsg.getDspHeader().getMessageLength_4_n_0())+4, "CP037");
			
			tcpClient.send(dataSend);
			byte[] tcpResponse = tcpClient.receiveByte();
			tcpClient.closeSocketx();
			
			if(tcpResponse == null)
			{
				return null;
			}
			else
			{
				RBSResponse earMarkResponse = new RBSResponse();
				earMarkResponse = (RBSResponse) dataBeanUtil.setFieldNamesAndByte(earMarkResponse, tcpResponse, "CP037");
				
				log.info(gsonLog.toJson(earMarkResponse));
				fullResponseByte = tcpResponse;
				
				//String transactionCode,String seqNo, String responseCode, String[] successCode,byte[] responseDataByte, Date requestDateTime, Date responseDateTime, String refNo, String cif, String fromAcc, String toAcc
				insertRBSLog(hostMsg.getTransactionCode_10_b_$(),hostMsg.getJournalSeq_5_p_0(),earMarkResponse.getResponseHeader().getRspCOD_2_b_$(),new String[]{"F9"},fullResponseByte,rspDate,new Date(),"","",eMarkAcc.getAccountNumber(),"");
				
				return earMarkResponse.getResponseHeader().getRspCOD_2_b_$();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			if(null!=tcpClient)
				tcpClient.closeSocketx();
			return PExConstant.PEX_ERR_RBS_SERVICE_UNAVAILABLE;
		}
		
		
	}
	
	
	public String thirdPartyTransferKH(boolean isPexDirect,ObAccountBean eMarkAcc, ObAccountBean CollectionAcc, String tranxFromAccType, String tranxToAccType,
			BigDecimal pexAmount ,BigDecimal earmarkAmount,BigDecimal chargeAmount, String currency, String payeeRef, Date transactionData, String runningNumber,
			String runningNumberReleaseHold, String pexRef, String payerName, String remark) throws IOException 
	{	
		
		
		
		Date rspDate = new Date();
		TCPClientUtil tcpClient = null;
		try
		{
			
			if(null!=pmgr.getProperty("fuzion.mock")  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false")   ){
				/*RBS_DSPFBResponse inResponse = new RBS_DSPFBResponse();
				inResponse.setSequence_5_p_0(new MBaseResponseDA());
				inResponse.getResponseDA().setAccountName_40_b_$("Renzo");
				fullResponseByte = dataBeanUtil.getFieldNamesAndByte(inResponse, 1024*8, "CP037");*/
				return "FA"; 
			}
			
			String rsReleaseHold = releaseEarmarkKH(isPexDirect,eMarkAcc,tranxFromAccType,earmarkAmount,currency,payeeRef,transactionData,runningNumberReleaseHold,30000);
			if(!"F9".equalsIgnoreCase(rsReleaseHold))
			{
				
				return "rbs.release."+rsReleaseHold;
			}
			
			String rbsIP = pmgr.getProperty("RBS.IP");
			int rbsPort01 = Integer.parseInt(pmgr.getProperty("RBS.PORT01"));
			int rbsPort02 = Integer.parseInt(pmgr.getProperty("RBS.PORT02"));
			int[] rbsPort = {rbsPort01};
			
			DataBeanUtil dataBeanUtil = new DataBeanUtil();
			RBSViRequest rbsEarMarkAccMsg = new RBSViRequest();
			
	
			SimpleDateFormat tranxFormat = new SimpleDateFormat(PExConstant.PEX_RBS_DATE);
			SimpleDateFormat expiredFormat = new SimpleDateFormat(PExConstant.PEX_RBS_EXPDATE);
			
			MBaseMsg mbaseMsg = new MBaseMsg();
			mbaseMsg.setMessageLength_4_n_0("1020");
			
			mbaseMsg.setMessageHeader_5_b_$("*MOSA");
			mbaseMsg.setFinalSocketAddr_5_p_0("00000");
			mbaseMsg.setTransactionType_5_b_$("T9998");
			mbaseMsg.setReserved_5_b_$("    Y");

			
			HostMbaseViMsg hostMsg = new HostMbaseViMsg();
			hostMsg.setTellerID_10_b_$("MOBI001");
			hostMsg.setJournalSeq_5_p_0(runningNumber);
			hostMsg.setField01_10_p_0(eMarkAcc.getAccountNumber());
			
			
			hostMsg.setCorrection_1_b_N("N");
			
			
			if(isPexDirect)
			{
				if(tranxFromAccType.equalsIgnoreCase(SAVING_TYPE) && tranxToAccType.equalsIgnoreCase(SAVING_TYPE) )
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saFTsa_pex);
				}
				else if(tranxFromAccType.equalsIgnoreCase(SAVING_TYPE) && tranxToAccType.equalsIgnoreCase(CURRENT_TYPE) )
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saFTca_pex);
				}
				else if(tranxFromAccType.equalsIgnoreCase(CURRENT_TYPE) && tranxToAccType.equalsIgnoreCase(SAVING_TYPE) )
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caFTsa_pex);
				}
				else if(tranxFromAccType.equalsIgnoreCase(CURRENT_TYPE) && tranxToAccType.equalsIgnoreCase(CURRENT_TYPE) )
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caFTca_pex);
				}
			}
			else
			{
				if(tranxFromAccType.equalsIgnoreCase(SAVING_TYPE) && tranxToAccType.equalsIgnoreCase(SAVING_TYPE) )
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saFTsa);
				}
				else if(tranxFromAccType.equalsIgnoreCase(SAVING_TYPE) && tranxToAccType.equalsIgnoreCase(CURRENT_TYPE) )
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_saFTca);
				}
				else if(tranxFromAccType.equalsIgnoreCase(CURRENT_TYPE) && tranxToAccType.equalsIgnoreCase(SAVING_TYPE) )
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caFTsa);
				}
				else if(tranxFromAccType.equalsIgnoreCase(CURRENT_TYPE) && tranxToAccType.equalsIgnoreCase(CURRENT_TYPE) )
				{
					hostMsg.setTransactionCode_10_b_$(tranCode_caFTca);
				}
			}
			
			
			
			hostMsg.setTransactionDate_6_b_$(tranxFormat.format(transactionData));

			hostMsg.setControlUnit_2_b_$("A1");
			hostMsg.setTellerBranch_5_f_0("1");
			hostMsg.setAmpmMode_1_f_1("1");
			
		
			
	
			hostMsg.setField01_10_p_0(eMarkAcc.getAccountNumber());
			hostMsg.setField04_10_p_0(pexAmount.toString().replaceAll("\\.",""));
			//hostMsg.setField05_10_p_0(chargeAmount.toString().replaceAll("\\.",""));
			hostMsg.setField09_10_p_0(CollectionAcc.getAccountNumber());
			hostMsg.setField10_10_p_0(eMarkAcc.getBankCode());
			//hostMsg.setField11_10_p_0(expiredFormat.format(transactionData));
			hostMsg.setField20_10_p_0(earmarkAmount.toString().replaceAll("\\.",""));
			hostMsg.setAmtFieldUsage04_1_b_$("3");
			hostMsg.setAmtFieldUsage20_1_b_$("2");
			hostMsg.setGlLineUsage02_1_b_$("1");
			hostMsg.setGlLineUsage03_1_b_$("1");
			hostMsg.setAmtFieldUsage33_1_b_$("2");
			hostMsg.setAmtFieldUsage36_1_b_$("1");
			hostMsg.setAmtFieldUsage37_1_b_$("1");
			//hostMsg.setSingleFields33_10_p_0(earmarkAmount.toString().replaceAll("\\.",""));
			hostMsg.setSingleFields36_10_p_0(chargeAmount.toString().replaceAll("\\.",""));
			
			hostMsg.setCurrencyCode_4_b_$(currency);
			hostMsg.setAlternateCCY01_4_b_$(currency);
			hostMsg.setAlternateCCY02_4_b_$(currency);
			hostMsg.setAlternateCCY03_4_b_$(currency);
			hostMsg.setAlternateCCY04_4_b_$(currency);
			
			hostMsg.setPayeeRef_40_b_$(payeeRef);
			hostMsg.setThirdPartyNames_40_b_$(payerName);//changed to send Payer Name after Payment Reference Implementation
			hostMsg.setRemark_256_b_$(remark);//Sending in (RefNo + Remark 1 (40)) + Sender Name (40) after Payment Reference Implementation
			hostMsg.setRefenceNumber_20_b_$(pexRef);
			rbsEarMarkAccMsg.setDspHeader(getDspHeader(String.valueOf(rbsPort01)));
			rbsEarMarkAccMsg.setMbaseMsg(mbaseMsg);
			rbsEarMarkAccMsg.setHostMsg(hostMsg);
			//hostMsg.setThirdPartyNames_40_b_$(pexRef); Commented out by Nag
			int fullmsgLen = dataBeanUtil.getFullObjectLength(rbsEarMarkAccMsg);
		    rbsEarMarkAccMsg.getDspHeader().setMessageLength_4_n_0(String.valueOf((fullmsgLen-4)));
			
			
		    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
			
			log.info(gsonLog.toJson(rbsEarMarkAccMsg));
			log.info("------------THIRD PARTY TRANSFER----------------------");
		    
			tcpClient = new TCPClientUtil(rbsIP,rbsPort,30000,"CP037",1024*8);
			byte[] dataSend = dataBeanUtil.getFieldNamesAndByte(rbsEarMarkAccMsg, Integer.parseInt(rbsEarMarkAccMsg.getDspHeader().getMessageLength_4_n_0())+4, "CP037");
			
			tcpClient.send(dataSend);
			byte[] tcpResponse = tcpClient.receiveByte();
			tcpClient.closeSocketx();
			
			if(tcpResponse == null)
			{
				return null;
			}
			else
			{
				RBSResponse earMarkResponse = new RBSResponse();
				earMarkResponse = (RBSResponse) dataBeanUtil.setFieldNamesAndByte(earMarkResponse, tcpResponse, "CP037");
				
				
				
				log.info("Thirdparty RBS Response");
				log.info(gsonLog.toJson(earMarkResponse));
				
				fullResponseByte = tcpResponse;
				

				//String transactionCode,String seqNo, String responseCode, String[] successCode,byte[] responseDataByte, Date requestDateTime, Date responseDateTime, String refNo, String cif, String fromAcc, String toAcc
				insertRBSLog(hostMsg.getTransactionCode_10_b_$(),hostMsg.getJournalSeq_5_p_0(),earMarkResponse.getResponseHeader().getRspCOD_2_b_$(),new String[]{"F1","F4"},fullResponseByte,rspDate,new Date(),pexRef,"",eMarkAcc.getAccountNumber(),CollectionAcc.getAccountNumber());
				return earMarkResponse.getResponseHeader().getRspCOD_2_b_$();
			}
		}catch(Exception e)
		{
			tcpClient.closeSocketx();
			e.printStackTrace();
			return PExConstant.PEX_ERR_RBS_SERVICE_UNAVAILABLE;
		}
		
		
	}
	
	public String inquiryACCDetailsKH(String pexAccount,String accType, Date transactionData, String runningNumber) throws IOException 
	{	Date rspDate = new Date();
		TCPClientUtil tcpClient = null;
		try
		{
			DataBeanUtil dataBeanUtil = new DataBeanUtil();
			if(null!=pmgr.getProperty("fuzion.mock")  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false")   ){
				RBS_DSPDAResponse inResponse = new RBS_DSPDAResponse();
				inResponse.setResponseDA(new MBaseResponseDA());
				inResponse.getResponseDA().setAccountName_40_b_$("Renzo");
				fullResponseByte = dataBeanUtil.getFieldNamesAndByte(inResponse, 1024*8, "CP037");
				return "I1"; 
			}
			String rbsIP = pmgr.getProperty("RBS.IP");
			int rbsPort01 = Integer.parseInt(pmgr.getProperty("RBS.PORT01"));
			int rbsPort02 = Integer.parseInt(pmgr.getProperty("RBS.PORT02"));
			int[] rbsPort = {rbsPort01};
			
		
			RBSViRequest rbsEarMarkAccMsg = new RBSViRequest();
	
			SimpleDateFormat tranxFormat = new SimpleDateFormat(PExConstant.PEX_RBS_DATE);
			SimpleDateFormat expiredFormat = new SimpleDateFormat(PExConstant.PEX_RBS_EXPDATE);
			
			MBaseMsg mbaseMsg = new MBaseMsg();
			mbaseMsg.setMessageLength_4_n_0("1020");
			mbaseMsg.setMessageHeader_5_b_$("*MOSA");
			mbaseMsg.setFinalSocketAddr_5_p_0("00000");
			mbaseMsg.setTransactionType_5_b_$("T9998");
			mbaseMsg.setReserved_5_b_$("    Y");
			
			
			HostMbaseViMsg hostMsg = new HostMbaseViMsg();
			hostMsg.setTellerID_10_b_$("MOBI001");
			hostMsg.setJournalSeq_5_p_0(runningNumber);
			hostMsg.setCorrection_1_b_N("N");
			if(accType.equalsIgnoreCase("D"))
			{
			
				hostMsg.setTransactionCode_10_b_$(tranCode_inquiryAccDetails);
			}
			else
			{
				hostMsg.setTransactionCode_10_b_$(tranCode_inquiryAccDetailsSA);
			}
			hostMsg.setTransactionDate_6_b_$(tranxFormat.format(transactionData));
			hostMsg.setControlUnit_2_b_$("A1");
			hostMsg.setTellerBranch_5_f_0("1");
			hostMsg.setAmpmMode_1_f_1("1");
			hostMsg.setField01_10_p_0(pexAccount);
			hostMsg.setField20_10_p_0("1");
		
			rbsEarMarkAccMsg.setDspHeader(getDspHeader(String.valueOf(rbsPort01)));
			rbsEarMarkAccMsg.setMbaseMsg(mbaseMsg);
			rbsEarMarkAccMsg.setHostMsg(hostMsg);
			
			int fullmsgLen = dataBeanUtil.getFullObjectLength(rbsEarMarkAccMsg);
		    rbsEarMarkAccMsg.getDspHeader().setMessageLength_4_n_0(String.valueOf((fullmsgLen-4)));
			
		    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
			 
			log.info(gsonLog.toJson(rbsEarMarkAccMsg));
			log.info("--------------------------------------------------RBS------------------------------------------------");
			
			tcpClient = new TCPClientUtil(rbsIP,rbsPort,60000,"CP037",1024*8);
			//rbsEarMarkAccMsg
			byte[] dataSend = dataBeanUtil.getFieldNamesAndByte(rbsEarMarkAccMsg, Integer.parseInt(rbsEarMarkAccMsg.getDspHeader().getMessageLength_4_n_0())+4, "CP037");
			
			tcpClient.send(dataSend);
			byte[] tcpResponse = tcpClient.receiveByte();
			tcpClient.closeSocketx();
			
			if(tcpResponse == null)
			{
				return null;
			}
			else
			{
				RBSResponse earMarkResponse = new RBSResponse();
				earMarkResponse = (RBSResponse) dataBeanUtil.setFieldNamesAndByte(earMarkResponse, tcpResponse, "CP037");
				
				
				//Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
					
				log.info(gsonLog.toJson(earMarkResponse));
				
				
				fullResponseByte = tcpResponse;
				
				//String transactionCode,String seqNo, String responseCode, String[] successCode,byte[] responseDataByte, Date requestDateTime, Date responseDateTime, String refNo, String cif, String fromAcc, String toAcc
				insertRBSLog(hostMsg.getTransactionCode_10_b_$(),hostMsg.getJournalSeq_5_p_0(),earMarkResponse.getResponseHeader().getRspCOD_2_b_$(),new String[]{"I1"},fullResponseByte,rspDate,new Date(),"","",pexAccount,"");
				
				return earMarkResponse.getResponseHeader().getRspCOD_2_b_$();
			}
		}catch(Exception e)
		{
			tcpClient.closeSocketx();
			e.printStackTrace();
			return PExConstant.PEX_ERR_RBS_SERVICE_UNAVAILABLE;
		}
		
		
	}
	
	
	
	
	
	public String inquiryTACNumberKH(String cifNumber, String currency, Date transactionData, String runningNumber) throws IOException 
	{	Date rspDate = new Date();
		TCPClientUtil tcpClient = null;
		try
		{
			DataBeanUtil dataBeanUtil = new DataBeanUtil();
			if(null!=pmgr.getProperty("fuzion.mock")  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false")   ){
				RBS_DSPECResponse inResponse = new RBS_DSPECResponse();
				inResponse.setResponseEC(new MBaseResponseEC());
				inResponse.getResponseEC().setCustContactNumTAC_30_b_$("0166391973");
				inResponse.getResponseEC().setCustContactNumTAC1_30_b_$("");
				fullResponseByte = dataBeanUtil.getFieldNamesAndByte(inResponse, 1024*8, "CP037");
				return "CG"; 
			}
		
			String rbsIP = pmgr.getProperty("RBS.IP");
			int rbsPort01 = Integer.parseInt(pmgr.getProperty("RBS.PORT01"));
			int rbsPort02 = Integer.parseInt(pmgr.getProperty("RBS.PORT02"));
			int[] rbsPort = {rbsPort01};
			
			
			RBSViRequest rbsEarMarkAccMsg = new RBSViRequest();
	
			SimpleDateFormat tranxFormat = new SimpleDateFormat(PExConstant.PEX_RBS_DATE);
			SimpleDateFormat expiredFormat = new SimpleDateFormat(PExConstant.PEX_RBS_EXPDATE);
			
			MBaseMsg mbaseMsg = new MBaseMsg();
			mbaseMsg.setMessageLength_4_n_0("1020");
			mbaseMsg.setMessageHeader_5_b_$("*MOSA");
			mbaseMsg.setFinalSocketAddr_5_p_0("00000");
			mbaseMsg.setTransactionType_5_b_$("T9998");
			mbaseMsg.setReserved_5_b_$("    Y");
			
			
			HostMbaseViMsg hostMsg = new HostMbaseViMsg();
			hostMsg.setTellerID_10_b_$("MOBI001");
			hostMsg.setJournalSeq_5_p_0(runningNumber);
			hostMsg.setCorrection_1_b_N("N");
			hostMsg.setTransactionCode_10_b_$(tranCode_inquiryTACnumber);
			hostMsg.setTransactionDate_6_b_$(tranxFormat.format(transactionData));
			hostMsg.setControlUnit_2_b_$("A1");
			hostMsg.setTellerBranch_5_f_0("1");
			hostMsg.setAmpmMode_1_f_1("1");
			
			

			//define request data
			hostMsg.setField01_10_p_0(	cifNumber);
			hostMsg.setField20_10_p_0("1");
			hostMsg.setCurrencyCode_4_b_$(currency);
			hostMsg.setAdditionFInfoC_3_b_$("IBK");
			
			rbsEarMarkAccMsg.setDspHeader(getDspHeader(String.valueOf(rbsPort01)));
			rbsEarMarkAccMsg.setMbaseMsg(mbaseMsg);
			rbsEarMarkAccMsg.setHostMsg(hostMsg);
			
			int fullmsgLen = dataBeanUtil.getFullObjectLength(rbsEarMarkAccMsg);
		    rbsEarMarkAccMsg.getDspHeader().setMessageLength_4_n_0(String.valueOf((fullmsgLen-4)));
			
		    Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
			 
			log.info(gsonLog.toJson(rbsEarMarkAccMsg));
			log.info("--------------------------------------------------RBS------------------------------------------------");
			
			tcpClient = new TCPClientUtil(rbsIP,rbsPort,60000,"CP037",1024*8);
			//rbsEarMarkAccMsg
			byte[] dataSend = dataBeanUtil.getFieldNamesAndByte(rbsEarMarkAccMsg, Integer.parseInt(rbsEarMarkAccMsg.getDspHeader().getMessageLength_4_n_0())+4, "CP037");
			
			tcpClient.send(dataSend);
			byte[] tcpResponse = tcpClient.receiveByte();
			tcpClient.closeSocketx();
			
			if(tcpResponse == null)
			{
				return null;
			}
			else
			{
				log.info("["+tcpResponse+"]");
				RBSResponse earMarkResponse = new RBSResponse();
				earMarkResponse = (RBSResponse) dataBeanUtil.setFieldNamesAndByte(earMarkResponse, tcpResponse, "CP037");
				log.info(gsonLog.toJson(rbsEarMarkAccMsg));
				fullResponseByte = tcpResponse;
				
				//String transactionCode,String seqNo, String responseCode, String[] successCode,byte[] responseDataByte, Date requestDateTime, Date responseDateTime, String refNo, String cif, String fromAcc, String toAcc
				insertRBSLog(hostMsg.getTransactionCode_10_b_$(),hostMsg.getJournalSeq_5_p_0(),earMarkResponse.getResponseHeader().getRspCOD_2_b_$(),new String[]{"CG"},fullResponseByte,rspDate,new Date(),"",cifNumber,"","");
				
				return earMarkResponse.getResponseHeader().getRspCOD_2_b_$();
			}
		}catch(Exception e)
		{
			tcpClient.closeSocketx();
			e.printStackTrace();
			return PExConstant.PEX_ERR_RBS_SERVICE_UNAVAILABLE;
		}
		
		
	}
	
	
	
	
	public DSPHeader getDspHeader(String portNumber) throws Exception
	{
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
	


	
	public  static byte[] intTo4ByteArray(int value) {
	    return new byte[] {
	            (byte)(value >>> 24),
	            (byte)(value >>> 16),
	            (byte)(value >>> 8),
	            (byte)value};
	}
	
	

	public static final byte[] intTo5ByteArray(int value) {
	    return new byte[] {
	    		(byte)(value >>> 32),
	            (byte)(value >>> 24),
	            (byte)(value >>> 16),
	            (byte)(value >>> 8),
	            (byte)value};
	}
	
	
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	public static void main(String args[]) throws Exception
	{
		
	/*	String hexa = hex(2605).substring(2);
		log.info("x"+new String(hexStringToByteArray(hexa))+"x");
		
		log.info(toHexString(new String(hexStringToByteArray(hexa.substring(2))).getBytes()));
		
		
		
		byte[] byt = intTo4ByteArray(2605);
		String datax = new String(byt);
		log.info(""+datax);
		int result = ByteBuffer.wrap(datax.getBytes()).getInt();
		int resultx =  ByteBuffer.wrap(byt).getInt();
		log.info(result+" - "+resultx);*/
		
		//int a = 2618;
		
		log.info((hex(1020)));
		
		byte[] byt = hexStringToByteArray(hex(1020));
		
		String xxx = new String(byt,"CP037");
		
		log.info(toxHex(xxx.getBytes()));
		
		
	}
	
	public static String toxHex(byte[] bytes) {
	    BigInteger bi = new BigInteger(1, bytes);
	    return String.format("%0" + (bytes.length << 1) + "X", bi);
	}


	public String getFullResponseData() {
		return fullResponseData;
	}


	public void setFullResponseData(String fullResponseData) {
		this.fullResponseData = fullResponseData;
	}
	
	
	public static String hex(int n) {
	    // call toUpperCase() if that's required
	    return String.format("0x%8s", Integer.toHexString(n)).replace(' ', '0');
	}
	
	public static int convert(int n) {
		  return Integer.valueOf(String.valueOf(n), 16);
		}

	
	
	public static byte[] toBytes(int... ints) { // helper function
	    byte[] result = new byte[ints.length];
	    for (int i = 0; i < ints.length; i++) {
	        result[i] = (byte) ints[i];
	    }
	    return result;
	}
	
/*		public static void main(String[] args) {
			  log.info(toInt(toBytes(2065)));
			  
		  log.info();  // 32
		}*/
		public static String toHex(byte[] bytes) {
		    BigInteger bi = new BigInteger(1, bytes);
		    return String.format("%0" + (bytes.length << 1) + "X", bi);
		}
		
		public static String toHexString(byte[] bytes) {
		    char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		    char[] hexChars = new char[bytes.length * 2];
		    int v;
		    for ( int j = 0; j < bytes.length; j++ ) {
		        v = bytes[j] & 0xFF;
		        hexChars[j*2] = hexArray[v/16];
		        hexChars[j*2 + 1] = hexArray[v%16];
		    }
		    return new String(hexChars);
		}
		
	
		 
		 
		 
		 public static int toInt( byte[] bArray ) {
		        long longInt;

		        if ( bArray.length > 3 ) {
		            longInt = ( bArray[0] << 24 ) & 0xFF000000L;
		            longInt = longInt | ( ( bArray[1] << 16 ) & 0xFF0000L );
		            longInt = longInt | ( ( bArray[2] << 8 ) & 0xFF00L );
		            longInt = longInt | ( bArray[3] & 0xFFL );
		        } else if ( bArray.length > 2 ) {
		            longInt = ( ( bArray[0] << 16 ) & 0xFF0000L );
		            longInt = longInt | ( ( bArray[1] << 8 ) & 0xFF00L );
		            longInt = longInt | ( bArray[2] & 0xFFL );
		        } else if ( bArray.length > 1 ) {
		            longInt = ( ( bArray[0] << 8 ) & 0xFF00L );
		            longInt = longInt | ( bArray[1] & 0xFFL );
		        } else {
		            longInt = ( bArray[0] & 0xFFL );
		        }
		        return (int) longInt;
		    }

		    public static byte[] toBytes(String hexString)
		    {
		        final int byteSize = 2;
		        
		        if(hexString.length() % byteSize != 0) {
		            hexString = "0" + hexString;
		        }

		        byte[] bytes = new byte[hexString.length()/byteSize];

		        for(int i = 0;i < bytes.length;i++) {
		            bytes[i] = (byte) Integer.parseInt( hexString.substring( i * byteSize, i * byteSize + byteSize ), 16);
		        }
		        
		        return bytes;
		    }


		    public static byte[] toBytes(int integer) {
		        String hexStr = Integer.toHexString( integer );
		        return toBytes( hexStr );
		    }
		    
		    
		    public static byte[] fromHexString(final String encoded) {
		        if ((encoded.length() % 2) != 0)
		            throw new IllegalArgumentException("Input string must contain an even number of characters");

		        final byte result[] = new byte[encoded.length()/2];
		        final char enc[] = encoded.toCharArray();
		        for (int i = 0; i < enc.length; i += 2) {
		            StringBuilder curr = new StringBuilder(2);
		            curr.append(enc).append(enc[i + 1]);

		    result[i/2] = (byte) Integer.parseInt(curr.toString(), 16);

		    }

		    return result;

		    }
		    
		    
		    public byte[] packx(long value, int length) {
		        if (length < 1 || length > 16)
		            throw new IllegalArgumentException("invalid length");
		        BigInteger max = BigInteger.TEN.pow(length * 2 - 1);
		        BigInteger abs = BigInteger.valueOf(value).abs();
		        if (max.compareTo(abs) < 0) {
		            throw new IllegalArgumentException("decimal overflow");
		        }
		        BigInteger workValue = abs.mod(max);
		        byte[] bytes = new BigInteger(workValue.toString()
		             + (value < 0 ? "D" : "F"), 16).toByteArray();
		        byte[] rv = new byte[length];
		        System.arraycopy(bytes, 0, rv, rv.length - bytes.length, bytes.length);
		        return rv;
		       }




			public byte[] getFullResponseByte() {
				return fullResponseByte;
			}




			public void setFullResponseByte(byte[] fullResponseByte) {
				this.fullResponseByte = fullResponseByte;
			}
	
			
			
			public int insertRBSLog(String transactionCode,String seqNo, String responseCode, String[] successCode,byte[] responseDataByte, Date requestDateTime, Date responseDateTime, String refNo, String cif, String fromAcc, String toAcc)
			{
				
				log.info("PEx RBS Log : [refNo:"+refNo+"][tranCode:"+transactionCode+"][seqNo:"+seqNo+"]");
				return 1;
				
				/*
				HlbRBSPExLog rbsPexLog = new HlbRBSPExLog();
				rbsPexLog.setTransactionCode(transactionCode);
				rbsPexLog.setResponseCode(responseCode);
				rbsPexLog.setRequestDateTime(requestDateTime);
				rbsPexLog.setResponseDateTime(responseDateTime);
				rbsPexLog.setRefNo(refNo);
				rbsPexLog.setCif(cif);
				rbsPexLog.setFromAccNo(fromAcc);
				rbsPexLog.setToAccNo(toAcc);
				
				
				for(String sc:successCode)
				{
					if(sc.equalsIgnoreCase(responseCode))
					{
						dao.insertEntity(rbsPexLog);
						return 1;
					}
				}
				
				DataBeanUtil dataBeanUtil = new DataBeanUtil();
				RBS_DSPERRResponse earMarkERRResponse = new RBS_DSPERRResponse();
				
				if(!responseCode.equalsIgnoreCase(PExConstant.PEX_ERR_RBS_SERVICE_UNAVAILABLE))
				{
					try {
						earMarkERRResponse = (RBS_DSPERRResponse) dataBeanUtil.setFieldNamesAndByte(earMarkERRResponse, responseDataByte, "CP037");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String errorCode = earMarkERRResponse.getResponse42().getErrorCode_2_p_0();
					
					rbsPexLog.setErrorCode(errorCode);
				}
				else
				{
					dao.insertEntity(rbsPexLog);
				}
				
				
				
				
				return 1;*/
				
			}
			
}
