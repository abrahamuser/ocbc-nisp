package com.silverlake.mleb.pex.module.rbs.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.silverlake.mleb.pex.server.socket.bean.Error41N42Response;
import com.silverlake.mleb.pex.server.socket.bean.HostMbaseViMsg;
import com.silverlake.mleb.pex.server.socket.bean.MBaseMsg;
import com.silverlake.mleb.pex.server.socket.bean.RBSResponse;
import com.silverlake.mleb.pex.server.socket.bean.RBSViRequest;
import com.silverlake.mleb.pex.server.socket.bean.ReleaseEarMarkSuccessResponse;
import com.silverlake.mleb.pex.util.DataBeanUtil;
import com.silverlake.mleb.pex.util.TCPClientUtil;


public class ReleaseEarmarkServices extends DSPMsgUtil
{
	
	private byte[]  fullSuccessRespData;
	private byte[]  fullErrorRespData;
	
	/**
	 * @param ip - RBS/HOST DSP IPADRESS
	 * @param port - RBS/HOST DSP PORT
	 * @param timeoutInSecond - connection timeout in second
	 */
	public ReleaseEarmarkServices(String ip, int port, int timeoutInSecond)
	{
		ip = ip;
		port = port;
		timeoutInSecond = timeoutInSecond;
	}
	
	
	/**
	 * load RBS ip,port,timeout from properties
	 */
	public ReleaseEarmarkServices()
	{
		setEnv();
	}
	
	
	
	/**
	 * call when performEarmarkAccount response "success"
	 * @return - EarMarkSuccessResponse - refer to RBS GOOD response mapping
	 * @throws Exception
	 */
	public ReleaseEarMarkSuccessResponse getSuccessResponse() throws Exception
	{
		DataBeanUtil dataBeanUtil = new DataBeanUtil();
		ReleaseEarMarkSuccessResponse resp = new ReleaseEarMarkSuccessResponse();
		
		if(null!=fullSuccessRespData)
		{
			resp = (ReleaseEarMarkSuccessResponse) dataBeanUtil.setFieldNamesAndByte(resp, fullSuccessRespData, "CP037");
		}
		else
		{
			resp = null;
		}
		
		
		return  resp;
	}
	
	
	/**
	 * call when performEarmarkAccount response "error"
	 * @return - Error41N42Response - refer to RBS BAD response mapping
	 * @throws Exception
	 */
	public Error41N42Response getErrorResponse() throws Exception
	{
		Error41N42Response resp = new Error41N42Response();
		DataBeanUtil dataBeanUtil = new DataBeanUtil();
		
		if(null!=fullErrorRespData)
		{
			resp = (Error41N42Response) dataBeanUtil.setFieldNamesAndByte(resp, fullErrorRespData, "CP037");
		}
		else
		{
			resp = null;
		}
		
		return  resp;
	}
	
	
	
	

	/**
	 * @param trxCode
	 * @param runningNumber
	 * @param eMarkAccNo
	 * @param amount
	 * @param currency
	 * @param payeeRef
	 * @param transactionDate
	 * @param earmarkSeqNumber
	 * @return
	 * @throws IOException
	 */
	public String performReleaseEarmarkAcc(String trxCode,String runningNumber, String eMarkAccNo,BigDecimal amount, String currency, String payeeRef,
			Date transactionDate, String earmarkSeqNumber)
					throws IOException {

		TCPClientUtil tcpClient = null;
		try {
			

			DataBeanUtil dataBeanUtil = new DataBeanUtil();
			RBSViRequest rbsEarMarkAccMsg = new RBSViRequest();

			SimpleDateFormat tranxFormat = new SimpleDateFormat(RBS_DATE);
			SimpleDateFormat expiredFormat = new SimpleDateFormat(RBS_EXPDATE);

			MBaseMsg mbaseMsg = new MBaseMsg();
			mbaseMsg.setMessageLength_4_n_0("1020");
			mbaseMsg.setMessageHeader_5_b_$("*MOSA");
			mbaseMsg.setFinalSocketAddr_5_p_0("00000");
			mbaseMsg.setTransactionType_5_b_$("T9998");
			mbaseMsg.setReserved_5_b_$("    Y");

			HostMbaseViMsg hostMsg = new HostMbaseViMsg();
			hostMsg.setTellerID_10_b_$("MOBI002");
			hostMsg.setJournalSeq_5_p_0(runningNumber);
			hostMsg.setCorrection_1_b_N("N");
			hostMsg.setTransactionCode_10_b_$(trxCode);


			hostMsg.setTransactionDate_6_b_$(tranxFormat.format(transactionDate));
			hostMsg.setControlUnit_2_b_$("A1");
			hostMsg.setTellerBranch_5_f_0("1");
			hostMsg.setAmpmMode_1_f_1("1");
			hostMsg.setField05_10_p_0(earmarkSeqNumber);
			hostMsg.setField01_10_p_0(eMarkAccNo);
			hostMsg.setField20_10_p_0(amount.toString().replaceAll("\\.", ""));
			hostMsg.setCurrencyCode_4_b_$(currency);
			hostMsg.setPayeeRef_40_b_$(payeeRef);

			
			rbsEarMarkAccMsg.setDspHeader(getDspHeader(String.valueOf(port)));
			rbsEarMarkAccMsg.getDspHeader().setSenarioNumber_16_b_$("BBHTLNOMONFNC");
			rbsEarMarkAccMsg.setMbaseMsg(mbaseMsg);
			rbsEarMarkAccMsg.setHostMsg(hostMsg);
			
			int fullmsgLen = dataBeanUtil.getFullObjectLength(rbsEarMarkAccMsg);
			rbsEarMarkAccMsg.getDspHeader().setMessageLength_4_n_0(String.valueOf((fullmsgLen - 4)));
			byte[] dataSend = dataBeanUtil.getFieldNamesAndByte(rbsEarMarkAccMsg,
					Integer.parseInt(rbsEarMarkAccMsg.getDspHeader().getMessageLength_4_n_0()) + 4, "CP037");
			
			byte[] tcpResponse;
			int[] rbsPort = { port };
			tcpClient = new TCPClientUtil(ip, rbsPort, timeoutInSecond*1000 , "CP037", 1024 * 8);
			tcpClient.send(dataSend);
			tcpResponse = tcpClient.receiveByte();
			tcpClient.closeSocketx();
			

			if (tcpResponse == null) {
				return null;
			} else {
				
					RBSResponse earMarkResponse = new RBSResponse();
					earMarkResponse = (RBSResponse) dataBeanUtil.setFieldNamesAndByte(earMarkResponse, tcpResponse,
							"CP037");
					
					if( earMarkResponse.getResponseHeader().getRspCOD_2_b_$().equalsIgnoreCase("F4") || earMarkResponse.getResponseHeader().getRspCOD_2_b_$().equalsIgnoreCase("F1"))
					{
						fullSuccessRespData = tcpResponse;
						return SUCCESS_RESPONSE;
					}
					else
					{
						fullErrorRespData = tcpResponse;
						return ERROR_RESPONSE;
					}

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			if (null != tcpClient)
				tcpClient.closeSocketx();
			return null;
		}

	}
	
	public static void main(String args[]) throws Exception
	{
		ReleaseEarmarkServices emService = new ReleaseEarmarkServices("localhost",54321,30);
		
		String result = emService.performReleaseEarmarkAcc("21128", "123", "123456789012", new BigDecimal(20.00), "VND", "", new Date(), "12345");
		System.out.println(result);
		
		if(result.equalsIgnoreCase("success"))
		{
			ReleaseEarMarkSuccessResponse rsData = emService.getSuccessResponse();
			System.out.println(rsData.getResponseHeader().getRspCOD_2_b_$());
			
		}
		else
		{
			Error41N42Response rsData = emService.getErrorResponse();
			System.out.println(rsData.getResponse41().getRspHeader().getRspCOD_2_b_$());
			System.out.println(rsData.getResponse42().getErrorCode_2_p_0());
		}
	}
	


}