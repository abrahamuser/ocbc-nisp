package com.silverlake.mleb.pex.eai;



import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.ibm.jms.JMSBytesMessage;
import com.silverlake.hlb.mib.bean.ObAccountBean;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.mleb.pex.bean.ObAccountInquiryBean;
import com.silverlake.mleb.pex.bean.ObCIFDetailsBean;
import com.silverlake.mleb.pex.bean.ObEAIHeader;
import com.silverlake.mleb.pex.bean.ObEarmarkBean;
import com.silverlake.mleb.pex.entity.PexProcessTranx;
import com.silverlake.mleb.pex.util.PropertiesManager;
import com.silverlake.mleb.pex.util.RequestSendMessageCreator;
import com.silverlake.mleb.pex.util.TCPClientUtil;




public class EAIService 
{

	private static Logger log = LogManager.getLogger(EAIService.class);
	private PropertiesManager pmgr = new PropertiesManager();

	private JmsTemplate requestMessageQueue;
	private JmsTemplate responseMessageQueue;
	String requestQueueName;
	String responseQueueName;
	
	public EAIService(JmsTemplate requestMessageQueue,JmsTemplate responseMessageQueue) throws Exception
	{
		this.requestMessageQueue = requestMessageQueue;
		this.responseMessageQueue = responseMessageQueue;
		requestQueueName = pmgr.getProperty("request.mq").toString();
		responseQueueName = pmgr.getProperty("response.mq").toString();
	
	}
	
	
	public String sendMsg(String msg) throws JMSException
	{
		RequestSendMessageCreator messageCreator = new RequestSendMessageCreator();
		messageCreator.setMessage(msg);
		messageCreator.setDestQueueName(responseQueueName);
		String selector = "";
		if(null!=pmgr.getProperty("bds.ws.mock") && !pmgr.getProperty("bds.ws.mock").equalsIgnoreCase("false"))
		{
			 log.info("");
		}
		else
		{
			requestMessageQueue.send(requestQueueName, messageCreator);
			selector = "JMSCorrelationID='"+messageCreator.getMsgID()+"'";
		}
		
		
		
		return selector;
	}
	
	
	//typeTransaction use for simulate EAI response only
	public byte[] receiveMsg(String JMSCorrelationID, String typeTransaction) throws JMSException 
	{
		byte[] msgByte = null;
		if(null!=pmgr.getProperty("bds.ws.mock") && !pmgr.getProperty("bds.ws.mock").equalsIgnoreCase("false") && null!=typeTransaction)
		{
			
			//simulate the eai response by typeTransaction
			if(typeTransaction.equalsIgnoreCase("eaiInquiry"))
			{
				
				 msgByte = "<Msg><Header><SC_TransType>270124</SC_TransType><SC_ApplName>MOBMY</SC_ApplName><SC_ApplID>MOBMY</SC_ApplID>  <SC_ApplTransID>123</SC_ApplTransID><SC_TransDate>240216</SC_TransDate>  <SC_TransTime>130204</SC_TransTime><SC_TransUserID/><SC_TransUserInfo/><SC_ApplUserID>MOBILITY</SC_ApplUserID><SC_TellerID>MOBI001</SC_TellerID>  <SC_BranchCode>11</SC_BranchCode><SC_CtrlUnit>1</SC_CtrlUnit><SC_OCMUSER/>  <ErrorCode></ErrorCode>  <ErrorMsg>INELIGIBLE ACCOUNT</ErrorMsg>  <ReasonCode>511</ReasonCode>  <ApprovalCode>132211</ApprovalCode><ARespCode>AB</ARespCode><BrokerHost>DEVEAIMBS01</BrokerHost><RequestDateStamp>2016-02-24 13:17:28.380318</RequestDateStamp><ResponseDateStamp>2016-02-24 13:17:29.518686</ResponseDateStamp><ConNum>85512345678</ConNum><ConNum1>85512345678</ConNum1></Header><Body><PEX><Request><JournalSequence>9</JournalSequence><Terminal_ID>sxmobaphr0</Terminal_ID><TrnSrc>MBP</TrnSrc><BankNo>14</BankNo><ReconKey>0000002015121541</ReconKey><CurrCode>MYR</CurrCode><RetRefNo/><CCNo>5888308890027176487</CCNo><FromAccType>D</FromAccType><DebAccNo>13900007571</DebAccNo><DebAccCurr>MYR</DebAccCurr><TranName>FADZLEY TOMEI TOMEI BIMB</TranName><TranAmt>2.30</TranAmt><FeeAmt>0.00</FeeAmt><TRefNo>R-PEx20151215-41</TRefNo><BeneBkRoute>603346</BeneBkRoute><AbvBeneBk>BIMB</AbvBeneBk><BeneAccNo>14041010038545</BeneAccNo><BeneAccType>D</BeneAccType><BeneAccCurr>MYR</BeneAccCurr><RR>Meals</RR><OPD/><SenderName>CIF00000000027176487</SenderName><GSTRate1>6.00</GSTRate1><GSTAmt1>0.00</GSTAmt1><GSTCode1>SR1</GSTCode1><TicketNo>C151215M120850000041</TicketNo><GSTExc>E</GSTExc></Request><Response><ReconKey>0000002015121541</ReconKey></Response></PEX></Body></Msg>".getBytes();
			}
			else if (typeTransaction.equalsIgnoreCase("tranxReleaseEar"))
			{
				 msgByte = "<Msg><Header><SC_TransType>270124</SC_TransType><SC_ApplName>MOBMY</SC_ApplName><SC_ApplID>MOBMY</SC_ApplID>  <SC_ApplTransID>123</SC_ApplTransID><SC_TransDate>240216</SC_TransDate>  <SC_TransTime>130204</SC_TransTime><SC_TransUserID/><SC_TransUserInfo/><SC_ApplUserID>MOBILITY</SC_ApplUserID><SC_TellerID>MOBI001</SC_TellerID>  <SC_BranchCode>11</SC_BranchCode><SC_CtrlUnit>1</SC_CtrlUnit><SC_OCMUSER/>  <ErrorCode></ErrorCode>  <ErrorMsg>INELIGIBLE ACCOUNT</ErrorMsg>  <ReasonCode>511</ReasonCode>  <ApprovalCode>132211</ApprovalCode><ARespCode>AB</ARespCode><BrokerHost>DEVEAIMBS01</BrokerHost><RequestDateStamp>2016-02-24 13:17:28.380318</RequestDateStamp><ResponseDateStamp>2016-02-24 13:17:29.518686</ResponseDateStamp></Header><Body><PEX><Request><JournalSequence>9</JournalSequence><Terminal_ID>sxmobaphr0</Terminal_ID><TrnSrc>MBP</TrnSrc><BankNo>14</BankNo><ReconKey>0000002015121541</ReconKey><CurrCode>MYR</CurrCode><RetRefNo/><CCNo>5888308890027176487</CCNo><FromAccType>D</FromAccType><DebAccNo>13900007571</DebAccNo><DebAccCurr>MYR</DebAccCurr><TranName>FADZLEY TOMEI TOMEI BIMB</TranName><TranAmt>2.30</TranAmt><FeeAmt>0.00</FeeAmt><TRefNo>R-PEx20151215-41</TRefNo><BeneBkRoute>603346</BeneBkRoute><AbvBeneBk>BIMB</AbvBeneBk><BeneAccNo>14041010038545</BeneAccNo><BeneAccType>D</BeneAccType><BeneAccCurr>MYR</BeneAccCurr><RR>Meals</RR><OPD/><SenderName>CIF00000000027176487</SenderName><GSTRate1>6.00</GSTRate1><GSTAmt1>0.00</GSTAmt1><GSTCode1>SR1</GSTCode1><TicketNo>C151215M120850000041</TicketNo><GSTExc>E</GSTExc></Request><Response><ReconKey>0000002015121541</ReconKey></Response></PEX></Body></Msg>".getBytes();
					
			}
			else if (typeTransaction.equalsIgnoreCase("earkTranx"))
			{
				 msgByte = "<Msg><Header><SC_TransType>270124</SC_TransType><SC_ApplName>MOBMY</SC_ApplName><SC_ApplID>MOBMY</SC_ApplID>  <SC_ApplTransID>123</SC_ApplTransID><SC_TransDate>240216</SC_TransDate>  <SC_TransTime>130204</SC_TransTime><SC_TransUserID/><SC_TransUserInfo/><SC_ApplUserID>MOBILITY</SC_ApplUserID><SC_TellerID>MOBI001</SC_TellerID>  <SC_BranchCode>11</SC_BranchCode><SC_CtrlUnit>1</SC_CtrlUnit><SC_OCMUSER/>  <ErrorCode></ErrorCode>  <ErrorMsg>INELIGIBLE ACCOUNT</ErrorMsg>  <ReasonCode>511</ReasonCode>  <ApprovalCode>132211</ApprovalCode><ARespCode>AB</ARespCode><BrokerHost>DEVEAIMBS01</BrokerHost><RequestDateStamp>2016-02-24 13:17:28.380318</RequestDateStamp><ResponseDateStamp>2016-02-24 13:17:29.518686</ResponseDateStamp><SeqNo>3333333</SeqNo></Header><Body><PEX><Request><JournalSequence>9</JournalSequence><Terminal_ID>sxmobaphr0</Terminal_ID><TrnSrc>MBP</TrnSrc><BankNo>14</BankNo><ReconKey>0000002015121541</ReconKey><CurrCode>MYR</CurrCode><RetRefNo/><CCNo>5888308890027176487</CCNo><FromAccType>D</FromAccType><DebAccNo>13900007571</DebAccNo><DebAccCurr>MYR</DebAccCurr><TranName>FADZLEY TOMEI TOMEI BIMB</TranName><TranAmt>2.30</TranAmt><FeeAmt>0.00</FeeAmt><TRefNo>R-PEx20151215-41</TRefNo><BeneBkRoute>603346</BeneBkRoute><AbvBeneBk>BIMB</AbvBeneBk><BeneAccNo>14041010038545</BeneAccNo><BeneAccType>D</BeneAccType><BeneAccCurr>MYR</BeneAccCurr><RR>Meals</RR><OPD/><SenderName>CIF00000000027176487</SenderName><GSTRate1>6.00</GSTRate1><GSTAmt1>0.00</GSTAmt1><GSTCode1>SR1</GSTCode1><TicketNo>C151215M120850000041</TicketNo><GSTExc>E</GSTExc></Request><Response><ReconKey>0000002015121541</ReconKey></Response></PEX></Body></Msg>".getBytes();
					
			}
			else if (typeTransaction.equalsIgnoreCase("tranxService"))
			{
				 msgByte = "<Msg><Header><SC_TransType>270124</SC_TransType><SC_ApplName>MOBMY</SC_ApplName><SC_ApplID>MOBMY</SC_ApplID>  <SC_ApplTransID>123</SC_ApplTransID><SC_TransDate>240216</SC_TransDate>  <SC_TransTime>130204</SC_TransTime><SC_TransUserID/><SC_TransUserInfo/><SC_ApplUserID>MOBILITY</SC_ApplUserID><SC_TellerID>MOBI001</SC_TellerID>  <SC_BranchCode>11</SC_BranchCode><SC_CtrlUnit>1</SC_CtrlUnit><SC_OCMUSER/>  <ErrorCode></ErrorCode>  <ErrorMsg>INELIGIBLE ACCOUNT</ErrorMsg>  <ReasonCode>511</ReasonCode>  <ApprovalCode>132211</ApprovalCode><ARespCode>AB</ARespCode><BrokerHost>DEVEAIMBS01</BrokerHost><RequestDateStamp>2016-02-24 13:17:28.380318</RequestDateStamp><ResponseDateStamp>2016-02-24 13:17:29.518686</ResponseDateStamp></Header><Body><PEX><Request><JournalSequence>9</JournalSequence><Terminal_ID>sxmobaphr0</Terminal_ID><TrnSrc>MBP</TrnSrc><BankNo>14</BankNo><ReconKey>0000002015121541</ReconKey><CurrCode>MYR</CurrCode><RetRefNo/><CCNo>5888308890027176487</CCNo><FromAccType>D</FromAccType><DebAccNo>13900007571</DebAccNo><DebAccCurr>MYR</DebAccCurr><TranName>FADZLEY TOMEI TOMEI BIMB</TranName><TranAmt>2.30</TranAmt><FeeAmt>0.00</FeeAmt><TRefNo>R-PEx20151215-41</TRefNo><BeneBkRoute>603346</BeneBkRoute><AbvBeneBk>BIMB</AbvBeneBk><BeneAccNo>14041010038545</BeneAccNo><BeneAccType>D</BeneAccType><BeneAccCurr>MYR</BeneAccCurr><RR>Meals</RR><OPD/><SenderName>CIF00000000027176487</SenderName><GSTRate1>6.00</GSTRate1><GSTAmt1>0.00</GSTAmt1><GSTCode1>SR1</GSTCode1><TicketNo>C151215M120850000041</TicketNo><GSTExc>E</GSTExc></Request><Response><ReconKey>0000002015121541</ReconKey></Response></PEX></Body></Msg>".getBytes();
					
			}
			else if (typeTransaction.equalsIgnoreCase("eaiAccInquiry"))
			{
				 msgByte = "<Msg><Header><SC_TransType>270124</SC_TransType><SC_ApplName>MOBMY</SC_ApplName><SC_ApplID>MOBMY</SC_ApplID>  <SC_ApplTransID>123</SC_ApplTransID><SC_TransDate>240216</SC_TransDate>  <SC_TransTime>130204</SC_TransTime><SC_TransUserID/><SC_TransUserInfo/><SC_ApplUserID>MOBILITY</SC_ApplUserID><SC_TellerID>MOBI001</SC_TellerID>  <SC_BranchCode>11</SC_BranchCode><SC_CtrlUnit>1</SC_CtrlUnit><SC_OCMUSER/>  <ErrorCode></ErrorCode>  <ErrorMsg>INELIGIBLE ACCOUNT</ErrorMsg>  <ReasonCode>511</ReasonCode>  <ApprovalCode>132211</ApprovalCode><ARespCode>AB</ARespCode><BrokerHost>DEVEAIMBS01</BrokerHost><RequestDateStamp>2016-02-24 13:17:28.380318</RequestDateStamp><ResponseDateStamp>2016-02-24 13:17:29.518686</ResponseDateStamp></Header><Body><PEX><Request><JournalSequence>9</JournalSequence><Terminal_ID>sxmobaphr0</Terminal_ID><TrnSrc>MBP</TrnSrc><BankNo>14</BankNo><ReconKey>0000002015121541</ReconKey><CurrCode>MYR</CurrCode><RetRefNo/><CCNo>5888308890027176487</CCNo><FromAccType>D</FromAccType><DebAccNo>13900007571</DebAccNo><DebAccCurr>MYR</DebAccCurr><TranName>FADZLEY TOMEI TOMEI BIMB</TranName><TranAmt>2.30</TranAmt><FeeAmt>0.00</FeeAmt><TRefNo>R-PEx20151215-41</TRefNo><BeneBkRoute>603346</BeneBkRoute><AbvBeneBk>BIMB</AbvBeneBk><BeneAccNo>14041010038545</BeneAccNo><BeneAccType>D</BeneAccType><BeneAccCurr>MYR</BeneAccCurr><RR>Meals</RR><OPD/><SenderName>CIF00000000027176487</SenderName><GSTRate1>6.00</GSTRate1><GSTAmt1>0.00</GSTAmt1><GSTCode1>SR1</GSTCode1><TicketNo>C151215M120850000041</TicketNo><GSTExc>E</GSTExc></Request><Response><ReconKey>0000002015121541</ReconKey></Response></PEX></Body></Msg>".getBytes();
					
			}
					
		}
		else
		{
			Message msgRs = responseMessageQueue.receiveSelected(responseQueueName, JMSCorrelationID);
			if(null!=msgRs)
			{
				JMSBytesMessage ibmMsg = (JMSBytesMessage) msgRs;
				msgByte = new byte[(int)ibmMsg.getBodyLength()];
				ibmMsg.readBytes(msgByte);
			}
		}
		
		return msgByte;
	}
	
	
	
	class ResponseMessageCreator implements MessageCreator{

		private TextMessage texMessage;
		private String message;
		
		@Override
		public Message createMessage(Session arg0) throws JMSException {
			// TODO Auto-generated method stub
			setTexMessage(arg0.createTextMessage(message));
			return getTexMessage();
		}
		
		
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public TextMessage getTexMessage() {
			return texMessage;
		}

		public void setTexMessage(TextMessage texMessage) {
			this.texMessage = texMessage;
		}
		
		public String getMsgID() throws JMSException
		{
			return texMessage.getJMSMessageID();
		}
		
	}
	
	 public Document stringToDom(String xmlSource) throws Exception {
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	       // return builder.parse(new InputSource(new StringReader(xmlSource)));
	        return builder.parse(new InputSource(new StringReader(new String(xmlSource.getBytes("UTF-8")))));
	        
	    }
	 
	 
	 //pex release EAI
	 public ObEAIHeader performFundTransfer(String tranxID, String senderAccount,String senderName, BigDecimal transferAmount,String recipientAcc,String recipientName, 
			 	Date transactionDate, String pexDailyRunningNumber,String tranxFromAccType, String tranxToAccType, 
				String tranxFromAccCurrency, String tranxToAccCurrency, String seqNo)
						throws IOException {

			TCPClientUtil tcpClient = null;
			try {
				
				SimpleDateFormat refDateFormat = new SimpleDateFormat("ddMMyy");
				SimpleDateFormat refTimeFormat = new SimpleDateFormat("HHMMss");
				String typeTransaction = "tranxService"; 
				String debAccType;
				String creAccType;
				ObEAIHeader response = null;
				//EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
				String text = new Scanner( getClass().getClassLoader().getResourceAsStream("eai_message/FT.xml") ).useDelimiter("\\A").next();
				//Header
				text = text.replaceAll("%APPTRANSID%", tranxID);
				text = text.replaceAll("%TRANSDATE%", refDateFormat.format(transactionDate));
				text = text.replaceAll("%TRANSTIME%", refTimeFormat.format(transactionDate));
				text = text.replaceAll("%APPUSERID%", "MOBILITY"); //not confirm
				text = text.replaceAll("%TELLERID%", "MOBI001");  //not confirm
				text = text.replaceAll("%BRANCHCODE%", "11"); //not confirmed
				text = text.replaceAll("%CTRLUNIT%", "1"); //not confirm
				text = text.replaceAll("%ECFLAG%", "");
				//Header
				
				//Request
				text = text.replaceAll("%JOURNALSEQUENCE_6%", pexDailyRunningNumber);
				text = text.replaceAll("%ORIJNLSEQ_9%", ""); //original seq number
				text = text.replaceAll("%DEBACC_19%", senderAccount); //debiting accout
				
				
				text = text.replaceAll("%DEBACCTYPE_1%", tranxFromAccType); //debit acc type
				text = text.replaceAll("%AMOUNT_17_2%", transferAmount.toString()); // amount
				text = text.replaceAll("%CREDACC_19%", recipientAcc); //crediting account
				
				
				
				text = text.replaceAll("%CREACCTYPE_1%", tranxToAccType); //credit account type
				text = text.replaceAll("%SEQNO_19%", seqNo); //seq number (take from EAI earmark response)
				text = text.replaceAll("%CREDACCCURR_4%", tranxToAccCurrency); //credit account currency
				text = text.replaceAll("%DEBACCCURR_4%", tranxFromAccCurrency); //debit account currency
				//Request
				
				log.info("EAI REQUEST FUND TRANSFER request\n : "+text);
				String selector = sendMsg(text);
				
				
				byte[] msgByte = receiveMsg(selector, typeTransaction);
				
				if(msgByte!=null) {
					String msg = new String(msgByte);
					log.info("EAI RESPONSE \n : "+msg);
					try{
						Document doc =  stringToDom(msg);
						NodeList rsList = doc.getElementsByTagName("Header");
						Element el_header = (Element) rsList.item(0);
						NodeList rsResponseCodeList = el_header.getElementsByTagName("ResponseCode");
						NodeList errorResponseCodeList = el_header.getElementsByTagName("ErrorCode");
						NodeList errorMsgList = el_header.getElementsByTagName("ErrorMsg");
						NodeList reasonCodeList = el_header.getElementsByTagName("ReasonCode");
						NodeList OCMRefNoList = el_header.getElementsByTagName("OCMRefNo");
						
						String bene_name = "";									
						NodeList beneAccNnameList = doc.getElementsByTagName("BeneName");
						if(beneAccNnameList.getLength() > 0){		
							org.w3c.dom.Element el_responseBeneAccName = (org.w3c.dom.Element) beneAccNnameList.item(0);
							log.info("bene account name :" +el_responseBeneAccName.getTextContent());
							bene_name = el_responseBeneAccName.getTextContent();
						}

						String responseCode = "";
						String errorResponseCode = "";
						String errorMsg="";
						String ocmRefNo = "";
						String reasonCode = "";
						if(rsResponseCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_responseCode=  (org.w3c.dom.Element) rsResponseCodeList.item(0);
							responseCode = el_responseCode.getTextContent();
						}
						if(errorMsgList.getLength()>0)
						{
							org.w3c.dom.Element el_errorMsgCode=  (org.w3c.dom.Element) errorMsgList.item(0);
							errorMsg = el_errorMsgCode.getTextContent();
						}
						if(reasonCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_reasonCode=  (org.w3c.dom.Element) reasonCodeList.item(0);
							reasonCode = el_reasonCode.getTextContent();
						}
						if(errorResponseCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_AResponse=  (org.w3c.dom.Element) errorResponseCodeList.item(0);
							errorResponseCode = el_AResponse.getTextContent();
						}
						
						if(OCMRefNoList.getLength()>0)
						{
							org.w3c.dom.Element el_ocmRefNo=  (org.w3c.dom.Element) OCMRefNoList.item(0);
							ocmRefNo = el_ocmRefNo.getTextContent();
						}
						
						log.info("EAI Error Response Code For FUND TRANSFER: ["+errorResponseCode+"]");
					
						ObEAIHeader eaiResponse = new ObEAIHeader();
						eaiResponse.setResponseCode(responseCode);
						eaiResponse.setErrorResponseCode(errorResponseCode);
						eaiResponse.setErrorMsg(errorMsg);
						eaiResponse.setReasonCode(reasonCode);
						eaiResponse.setOcmRefNo(ocmRefNo);
						eaiResponse.setBeneName(bene_name);
						response = eaiResponse;
						log.info("Response Code :: " + response);
						
						
					
						
					}catch(Exception e){
						log.info("-------------- EAI parsing account name with invalid characters");
						log.error(e);
						return null;
						
					}
					
				}
				
				
				return response;
			} catch (Exception e) {
				e.printStackTrace();
				if (null != tcpClient)
					tcpClient.closeSocketx();
				return null;
			}

	}

	 public ObEAIHeader performReleaseEarmarkAcc(String tranxID, ObAccountBean eMarkAccNo,BigDecimal amount, String currencyCode, String accType,
				Date transactionDate, String earmarkSeqNumber, String pexDailyRunningNumber)
						throws IOException {

			TCPClientUtil tcpClient = null;
			try {
				
				SimpleDateFormat refDateFormat = new SimpleDateFormat("ddMMyy");
				SimpleDateFormat refTimeFormat = new SimpleDateFormat("HHMMss");
				//EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
				String typeTransaction = "tranxReleaseEar"; 
				//String typeTransaction = "eaiInquiry"; 
				ObEAIHeader response = null;
				String text = new Scanner( getClass().getClassLoader().getResourceAsStream("eai_message/REL_EARMARK.xml") ).useDelimiter("\\A").next();

				//Header
				text = text.replaceAll("%APPTRANSID%", tranxID);
				text = text.replaceAll("%TRANSDATE%", refDateFormat.format(transactionDate));
				text = text.replaceAll("%TRANSTIME%", refTimeFormat.format(transactionDate));
				text = text.replaceAll("%APPUSERID%", "MOBILITY"); //not confirm
				text = text.replaceAll("%TELLERID%", "MOBI001");  //not confirm
				text = text.replaceAll("%BRANCHCODE%", "11"); //not confirmed
				text = text.replaceAll("%CTRLUNIT%", "1"); //not confirm
				//Header
				//Request
				text = text.replaceAll("%JOURNALSEQUENCE_6%", pexDailyRunningNumber);
				text = text.replaceAll("%ACCNO_19%", eMarkAccNo.getAccountNumber().toString());
				text = text.replaceAll("%ACCTYPE_1%", accType);
				text = text.replaceAll("%TRANSAMT_17_2%", amount.toString());
				text = text.replaceAll("%SEQNO_19%", earmarkSeqNumber);
				text = text.replaceAll("%CURRCODE_4%", currencyCode);
				text = text.replaceAll("%IBIND_3%", "IBK");
				//Request
				
				
				
				log.info("EAI REQUEST EARMARK RELEASE request\n : "+text);
				String selector = sendMsg(text);
				
				
				byte[] msgByte = receiveMsg(selector, typeTransaction);
				
				
				if(msgByte!=null) {
					
					String msg = new String(msgByte);
					log.info("EAI RESPONSE \n : "+msg);
					try{
						Document doc =  stringToDom(msg);
						NodeList rsList = doc.getElementsByTagName("Header");
						Element el_header = (Element) rsList.item(0);
						NodeList rsResponseCodeList = el_header.getElementsByTagName("ResponseCode");
						NodeList errorResponseCodeList = el_header.getElementsByTagName("ErrorCode");
						NodeList errorMsgList = el_header.getElementsByTagName("ErrorMsg");
						NodeList reasonCodeList = el_header.getElementsByTagName("ReasonCode");
						NodeList OCMRefNoList = el_header.getElementsByTagName("OCMRefNo");
						
						String bene_name = "";									
						NodeList beneAccNnameList = doc.getElementsByTagName("BeneName");
						if(beneAccNnameList.getLength() > 0){		
							org.w3c.dom.Element el_responseBeneAccName = (org.w3c.dom.Element) beneAccNnameList.item(0);
							log.info("bene account name :" +el_responseBeneAccName.getTextContent());
							bene_name = el_responseBeneAccName.getTextContent();
						}

						String responseCode = "";
						String errorResponseCode = "";
						String errorMsg="";
						String ocmRefNo = "";
						String reasonCode = "";
						if(rsResponseCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_responseCode=  (org.w3c.dom.Element) rsResponseCodeList.item(0);
							responseCode = el_responseCode.getTextContent();
						}
						if(errorMsgList.getLength()>0)
						{
							org.w3c.dom.Element el_errorMsgCode=  (org.w3c.dom.Element) errorMsgList.item(0);
							errorMsg = el_errorMsgCode.getTextContent();
						}
						if(reasonCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_reasonCode=  (org.w3c.dom.Element) reasonCodeList.item(0);
							reasonCode = el_reasonCode.getTextContent();
						}
						if(errorResponseCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_AResponse=  (org.w3c.dom.Element) errorResponseCodeList.item(0);
							errorResponseCode = el_AResponse.getTextContent();
						}
						
						if(OCMRefNoList.getLength()>0)
						{
							org.w3c.dom.Element el_ocmRefNo=  (org.w3c.dom.Element) OCMRefNoList.item(0);
							ocmRefNo = el_ocmRefNo.getTextContent();
						}
						
						log.info("EAI Error Response Code For EARMARK RELEASE ACC: ["+errorResponseCode+"]");
						ObEAIHeader release = new ObEAIHeader();
							release.setResponseCode(responseCode);
							release.setErrorResponseCode(errorResponseCode);
							release.setErrorMsg(errorMsg);
							release.setReasonCode(reasonCode);
							release.setOcmRefNo(ocmRefNo);
							release.setBeneName(bene_name);
							response = release;
							log.info("Response Code :: " + response);
						
						
						
					}catch(Exception e){
						log.info("-------------- EAI parsing account name with invalid characters");
						log.error(e);
						return null;
						
					}
					
				}
				
				return response;
			} catch (Exception e) {
				e.printStackTrace();
				if (null != tcpClient)
					tcpClient.closeSocketx();
				return null;
			}

	}
	 
	 public ObEarmarkBean performEarmarkAccount(String tranxID, ObAccountBean eMarkAccNo,BigDecimal eMarkAmount, String accType,
				Date transactionDate, String pexDailyRunningNumber, String currencyCode)
					{

			TCPClientUtil tcpClient = null;
			try {
				SimpleDateFormat refDateFormat = new SimpleDateFormat("ddMMyy");
				SimpleDateFormat refTimeFormat = new SimpleDateFormat("HHMMss");
				String typeTransaction = "earkTranx"; 
				//String typeTransaction = "eaiInquiry"; 
				//EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
				String text = new Scanner( getClass().getClassLoader().getResourceAsStream("eai_message/EARMARK.xml") ).useDelimiter("\\A").next();
				ObEarmarkBean response = null;
				//Header
				text = text.replaceAll("%APPTRANSID%", tranxID);
				text = text.replaceAll("%TRANSDATE%", refDateFormat.format(transactionDate));
				text = text.replaceAll("%TRANSTIME%", refTimeFormat.format(transactionDate));
				text = text.replaceAll("%APPUSERID%", "MOBILITY"); //not confirm
				text = text.replaceAll("%TELLERID%", "MOBI001");  //not confirm
				text = text.replaceAll("%BRANCHCODE%", "11"); //not confirmed
				text = text.replaceAll("%CTRLUNIT%", "1"); //not confirm
				//Header
				
				//Request
				text = text.replaceAll("%JOURNALSEQUENCE_6%", pexDailyRunningNumber);
				text = text.replaceAll("%ACCNO_19%", eMarkAccNo.getAccountNumber().toString());
				
				text = text.replaceAll("%ACCTYPE_1%", accType);
				text = text.replaceAll("%HOLDAMT_17_2%", eMarkAmount.toString());
				text = text.replaceAll("%CURRCODE_4%", currencyCode);
				
				log.info("EAI REQUEST EARMARK ACC request\n : "+text);
				String selector = sendMsg(text);
				
				
				byte[] msgByte = receiveMsg(selector, typeTransaction);
				
				if(msgByte!=null) {
					String msg = new String(msgByte);
					log.info("EAI RESPONSE \n : "+msg);
					try{
						Document doc =  stringToDom(msg);
						NodeList rsList = doc.getElementsByTagName("Header");
						Element el_header = (Element) rsList.item(0);
						NodeList rsResponseCodeList = el_header.getElementsByTagName("ResponseCode");
						NodeList errorResponseCodeList = el_header.getElementsByTagName("ErrorCode");
						NodeList errorMsgList = el_header.getElementsByTagName("ErrorMsg");
						NodeList reasonCodeList = el_header.getElementsByTagName("ReasonCode");
						NodeList OCMRefNoList = el_header.getElementsByTagName("OCMRefNo");
						NodeList seqNoList = el_header.getElementsByTagName("SeqNo");
						
						String bene_name = "";									
						NodeList beneAccNnameList = doc.getElementsByTagName("BeneName");
						if(beneAccNnameList.getLength() > 0){		
							org.w3c.dom.Element el_responseBeneAccName = (org.w3c.dom.Element) beneAccNnameList.item(0);
							log.info("bene account name :" +el_responseBeneAccName.getTextContent());
							bene_name = el_responseBeneAccName.getTextContent();
						}

						String responseCode = "";
						String errorResponseCode = "";
						String errorMsg="";
						String ocmRefNo = "";
						String reasonCode = "";
						String seqNo="";
						
						if(rsResponseCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_responseCode=  (org.w3c.dom.Element) rsResponseCodeList.item(0);
							responseCode = el_responseCode.getTextContent();
						}
						if(errorMsgList.getLength()>0)
						{
							org.w3c.dom.Element el_errorMsgCode=  (org.w3c.dom.Element) errorMsgList.item(0);
							errorMsg = el_errorMsgCode.getTextContent();
						}
						if(reasonCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_reasonCode=  (org.w3c.dom.Element) reasonCodeList.item(0);
							reasonCode = el_reasonCode.getTextContent();
						}
						if(errorResponseCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_AResponse=  (org.w3c.dom.Element) errorResponseCodeList.item(0);
							errorResponseCode = el_AResponse.getTextContent();
						}
						
						if(OCMRefNoList.getLength()>0)
						{
							org.w3c.dom.Element el_ocmRefNo=  (org.w3c.dom.Element) OCMRefNoList.item(0);
							ocmRefNo = el_ocmRefNo.getTextContent();
						}
						
						if(seqNoList.getLength()>0)
						{
							org.w3c.dom.Element el_seqNo=  (org.w3c.dom.Element) seqNoList.item(0);
							seqNo = el_seqNo.getTextContent();
						}
						
						log.info("EAI Error Response Code For EARMARK ACC: ["+errorResponseCode+"]");
						
							ObEarmarkBean earmark = new ObEarmarkBean();
							
							earmark.setResponseCode(responseCode);
							earmark.setErrorResponseCode(errorResponseCode);
							earmark.setErrorMsg(errorMsg);
							earmark.setReasonCode(reasonCode);
							earmark.setOcmRefNo(ocmRefNo);
							earmark.setSequenceNo(seqNo);
							response = earmark;
							log.info("Response Code :: " + response);
						
						
						
						
					}catch(Exception e){
						log.info("-------------- EAI parsing account name with invalid characters");
						log.error(e);
						return null;
						
					}
					
				}
				
				return response;
	 
			} catch (Exception e) {
				e.printStackTrace();
				if (null != tcpClient)
					tcpClient.closeSocketx();
				return null;
			}

	}
	 
	 
	
	 
	 public ObAccountInquiryBean performAccountInquiry(String tranxID,String runningNumber, String accountNo,
				Date transactionDate, String accountType, String currCode)
						throws IOException {

			TCPClientUtil tcpClient = null;
			try {
				SimpleDateFormat refDateFormat = new SimpleDateFormat("ddMMyy");
				SimpleDateFormat refTimeFormat = new SimpleDateFormat("HHMMss");
				String typeTransaction = "eaiAccInquiry"; 
			
				ObAccountInquiryBean response = null;
				EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
				String text = new Scanner( getClass().getClassLoader().getResourceAsStream("eai_message/ACC_INQUIRY.xml") ).useDelimiter("\\A").next();
				
				//Header -start
				text = text.replaceAll("%APPTRANSID%", tranxID);
				text = text.replaceAll("%TRANSDATE%", refDateFormat.format(transactionDate));
				text = text.replaceAll("%TRANSTIME%", refTimeFormat.format(transactionDate));
				text = text.replaceAll("%APPUSERID%", "MOBILITY"); //not confirm
				text = text.replaceAll("%TELLERID%", "MOBI001");  //not confirm
				text = text.replaceAll("%BRANCHCODE%", "11"); //not confirmed
				text = text.replaceAll("%CTRLUNIT%", "1"); //not confirm
				//Header - end
				
				//Request - start
				text = text.replaceAll("%JOURNALSEQUENCE_6%", runningNumber);
				text = text.replaceAll("%CURR_CODE_4%", currCode);
				text = text.replaceAll("%ACCNO_19%", accountNo);
				text = text.replaceAll("%ACCTYPE_1%", accountType);
				text = text.replaceAll("%IBIND_10%", "IBK");
				text = text.replaceAll("%STAFFCONT_1%", "");
				//Request - end
				
				
				log.info("EAI REQUEST ACC INQUIRY DETIALS request\n : "+text);
				String selector = service.sendMsg(text);
				
				
				byte[] msgByte = service.receiveMsg(selector, typeTransaction);
				
				if(msgByte!=null) {
					String msg = new String(msgByte);
					log.info("EAI RESPONSE \n : "+msg);
					try{
						Document doc =  service.stringToDom(msg);
						NodeList rsList = doc.getElementsByTagName("Header");
						Element el_header = (Element) rsList.item(0);
						NodeList rsResponseCodeList = el_header.getElementsByTagName("ResponseCode");
						NodeList errorResponseCodeList = el_header.getElementsByTagName("ErrorCode");
						NodeList errorMsgList = el_header.getElementsByTagName("ErrorMsg");
						NodeList reasonCodeList = el_header.getElementsByTagName("ReasonCode");
						NodeList OCMRefNoList = el_header.getElementsByTagName("OCMRefNo");
						NodeList accNoList = el_header.getElementsByTagName("AccNum");
						NodeList accTypeList = el_header.getElementsByTagName("AccType");
						NodeList accNameList = el_header.getElementsByTagName("AccountName");
						NodeList currencyTypeList = el_header.getElementsByTagName("CurrType");
						
						String bene_name = "";									
						NodeList beneAccNnameList = doc.getElementsByTagName("BeneName");
						if(beneAccNnameList.getLength() > 0){		
							org.w3c.dom.Element el_responseBeneAccName = (org.w3c.dom.Element) beneAccNnameList.item(0);
							log.info("bene account name :" +el_responseBeneAccName.getTextContent());
							bene_name = el_responseBeneAccName.getTextContent();
						}

						String responseCode = "";
						String errorResponseCode = "";
						String errorMsg="";
						String ocmRefNo = "";
						String reasonCode = "";
						String accNo = "";
						String accType = "";
						String accName = "";
						String currencyType = "";
						
						if(rsResponseCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_responseCode=  (org.w3c.dom.Element) rsResponseCodeList.item(0);
							responseCode = el_responseCode.getTextContent();
						}
						if(errorMsgList.getLength()>0)
						{
							org.w3c.dom.Element el_errorMsgCode=  (org.w3c.dom.Element) errorMsgList.item(0);
							errorMsg = el_errorMsgCode.getTextContent();
						}
						if(reasonCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_reasonCode=  (org.w3c.dom.Element) reasonCodeList.item(0);
							reasonCode = el_reasonCode.getTextContent();
						}
						if(errorResponseCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_AResponse=  (org.w3c.dom.Element) errorResponseCodeList.item(0);
							errorResponseCode = el_AResponse.getTextContent();
						}
						
						if(OCMRefNoList.getLength()>0)
						{
							org.w3c.dom.Element el_ocmRefNo=  (org.w3c.dom.Element) OCMRefNoList.item(0);
							ocmRefNo = el_ocmRefNo.getTextContent();
						}
						
						if(accNoList.getLength()>0)
						{
							org.w3c.dom.Element el_accNo=  (org.w3c.dom.Element) accNoList.item(0);
							accNo = el_accNo.getTextContent();
						}
						
						if(accTypeList.getLength()>0)
						{
							org.w3c.dom.Element el_accType=  (org.w3c.dom.Element) accTypeList.item(0);
							accType = el_accType.getTextContent();
						}
						
						if(accNameList.getLength()>0)
						{
							org.w3c.dom.Element el_accName=  (org.w3c.dom.Element) accNameList.item(0);
							accName = el_accName.getTextContent();
						}
						
						if(currencyTypeList.getLength()>0)
						{
							org.w3c.dom.Element el_currencyType=  (org.w3c.dom.Element) currencyTypeList.item(0);
							currencyType = el_currencyType.getTextContent();
						}
						
						log.info("EAI Error Response Code For ACC INQUIRY DETAILS: ["+errorResponseCode+"]");
						
							
						ObAccountInquiryBean inquiry = new ObAccountInquiryBean();
							inquiry.setResponseCode(responseCode);
							inquiry.setErrorResponseCode(errorResponseCode);
							inquiry.setErrorMsg(errorMsg);
							inquiry.setReasonCode(reasonCode);
							inquiry.setOcmRefNo(ocmRefNo);
						    inquiry.setAccNumber(accNo);
							inquiry.setAccName(accName);
							inquiry.setAccType(accType);
							inquiry.setCurrencyType(currencyType);
							
							response = inquiry;
						
							log.info("Response Code :: " + response);
							
						
						
					}catch(Exception e){
						log.info("-------------- EAI parsing account name with invalid characters");
						log.error(e);
						return null;
					}
					
				}
				
				
				return response;
				
			} catch (Exception e) {
				e.printStackTrace();
				if (null != tcpClient)
					tcpClient.closeSocketx();
				return null;
			}
	 
	 }
	 
	 //aka TAC Number Inquiry
	 public ObCIFDetailsBean performCIFInquiry(String tranxID,String runningNumber, String cifNumber, String currencyCode, String idNo, String idType, String eAddCode,
				Date transactionDate)
						throws IOException {

			TCPClientUtil tcpClient = null;
			try {
				SimpleDateFormat refDateFormat = new SimpleDateFormat("ddMMyy");
				SimpleDateFormat refTimeFormat = new SimpleDateFormat("HHMMss");
				String typeTransaction = "eaiInquiry"; 
				//String typeTransaction = "eaiInquiry"; 
				ObCIFDetailsBean response = null;
				//EAIService service = new EAIService(requestMessageQueue, responseMessageQueue);
				String text = new Scanner( getClass().getClassLoader().getResourceAsStream("eai_message/CIF_INQUIRY.xml") ).useDelimiter("\\A").next();
				
				//Header -start
				text = text.replaceAll("%APPTRANSID%", tranxID);
				text = text.replaceAll("%TRANSDATE%", refDateFormat.format(transactionDate));
				text = text.replaceAll("%TRANSTIME%", refTimeFormat.format(transactionDate));
				text = text.replaceAll("%APPUSERID%", "MOBILITY"); //not confirm
				text = text.replaceAll("%TELLERID%", "MOBI001");  //not confirm
				text = text.replaceAll("%BRANCHCODE%", "11"); //not confirmed
				text = text.replaceAll("%CTRLUNIT%", "1"); //not confirm
				//Header - end
				
				//Request - start
				text = text.replaceAll("%JOURNALSEQUENCE_6%", runningNumber);
				text = text.replaceAll("%CURR_CODE_4%", currencyCode);
				text = text.replaceAll("%CIFNUM_19%", cifNumber);
				text = text.replaceAll("%IDNO_37%", idNo);
				text = text.replaceAll("%IDTYPE_3%", idType);
				text = text.replaceAll("%EADDCODE_10%", eAddCode);
				text = text.replaceAll("%IBIND_10%", "IBK");
				
				
				//Request - end
				
				log.info("EAI REQUEST CIF ACC INQUIRY DETIALS request\n : "+text);
				String selector = sendMsg(text);
				
				
				byte[] msgByte = receiveMsg(selector, typeTransaction);
				
				if(msgByte!=null) {
					String msg = new String(msgByte);
					log.info("EAI RESPONSE \n : "+msg);
					try{
						Document doc =  stringToDom(msg);
						NodeList rsList = doc.getElementsByTagName("Header");
						Element el_header = (Element) rsList.item(0);
						NodeList rsResponseCodeList = el_header.getElementsByTagName("ResponseCode");
						NodeList errorResponseCodeList = el_header.getElementsByTagName("ErrorCode");
						NodeList errorMsgList = el_header.getElementsByTagName("ErrorMsg");
						NodeList reasonCodeList = el_header.getElementsByTagName("ReasonCode");
						NodeList OCMRefNoList = el_header.getElementsByTagName("OCMRefNo");
						NodeList contactNumList = el_header.getElementsByTagName("ConNum");
						NodeList addressSeq1List = el_header.getElementsByTagName("AddSeq1");
						NodeList contactNum1List = el_header.getElementsByTagName("ConNum1");
						NodeList addressSeq3List = el_header.getElementsByTagName("AddSeq3");
						
						String bene_name = "";									
						NodeList beneAccNnameList = doc.getElementsByTagName("BeneName");
						if(beneAccNnameList.getLength() > 0){		
							org.w3c.dom.Element el_responseBeneAccName = (org.w3c.dom.Element) beneAccNnameList.item(0);
							log.info("bene account name :" +el_responseBeneAccName.getTextContent());
							bene_name = el_responseBeneAccName.getTextContent();
						}

						String responseCode = "";
						String errorResponseCode = "";
						String errorMsg="";
						String ocmRefNo = "";
						String reasonCode = "";
						String contactNo = "";
						String addressSeq1 = "";
						String contactNo1 = "";
						String addressSeq3 = "";
						
						if(rsResponseCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_responseCode=  (org.w3c.dom.Element) rsResponseCodeList.item(0);
							responseCode = el_responseCode.getTextContent();
						}
						if(errorMsgList.getLength()>0)
						{
							org.w3c.dom.Element el_errorMsgCode=  (org.w3c.dom.Element) errorMsgList.item(0);
							errorMsg = el_errorMsgCode.getTextContent();
						}
						if(reasonCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_reasonCode=  (org.w3c.dom.Element) reasonCodeList.item(0);
							reasonCode = el_reasonCode.getTextContent();
						}
						if(errorResponseCodeList.getLength()>0)
						{
							org.w3c.dom.Element el_AResponse=  (org.w3c.dom.Element) errorResponseCodeList.item(0);
							errorResponseCode = el_AResponse.getTextContent();
						}
						
						if(OCMRefNoList.getLength()>0)
						{
							org.w3c.dom.Element el_ocmRefNo=  (org.w3c.dom.Element) OCMRefNoList.item(0);
							ocmRefNo = el_ocmRefNo.getTextContent();
						}
						
						if(contactNumList.getLength()>0)
						{
							org.w3c.dom.Element el_contactNo=  (org.w3c.dom.Element) contactNumList.item(0);
							contactNo = el_contactNo.getTextContent();
						}
						
						if(addressSeq1List.getLength()>0)
						{
							org.w3c.dom.Element el_addressSeq1=  (org.w3c.dom.Element) addressSeq1List.item(0);
							addressSeq1 = el_addressSeq1.getTextContent();
						}
						
						if(contactNum1List.getLength()>0)
						{
							org.w3c.dom.Element el_contactNum1=  (org.w3c.dom.Element) contactNum1List.item(0);
							contactNo1 = el_contactNum1.getTextContent();
						}
						
						if(addressSeq3List.getLength()>0)
						{
							org.w3c.dom.Element el_addressSeq3=  (org.w3c.dom.Element) addressSeq3List.item(0);
							addressSeq3 = el_addressSeq3.getTextContent();
						}
						log.info("EAI Error Response Code For CIF ACC INQUIRY DETAILS: ["+errorResponseCode+"]");

						ObCIFDetailsBean cifDetails = new ObCIFDetailsBean();
						
						cifDetails.setResponseCode(responseCode);
						cifDetails.setErrorResponseCode(errorResponseCode);
						cifDetails.setErrorMsg(errorMsg);
						cifDetails.setReasonCode(reasonCode);
						cifDetails.setOcmRefNo(ocmRefNo);
						cifDetails.setContactNo(contactNo);
						cifDetails.setAddressSeq1(addressSeq1);
						cifDetails.setContactNo1(contactNo1);
						cifDetails.setAddressSeq3(addressSeq3);
						
						response = cifDetails;
						log.info("Response Code :: " + response);
						
						
						
					}catch(Exception e){
						log.info("-------------- EAI parsing account name with invalid characters");
						log.error(e);
						return null;
					}
					
				}
				
				return response;
				
				
			} catch (Exception e) {
				e.printStackTrace();
				if (null != tcpClient)
					tcpClient.closeSocketx();
				return null;
			}

	}
	 
	 
	 
	 
	
}